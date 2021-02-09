/*******************************************************************************
 * Copyright 2018, Julius Krah
 * by the @authors tag. See the LICENCE in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package br.com.apiestoque.mail.mail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sun.istack.ByteArrayDataSource;

import br.com.apiestoque.config.services.UserService;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.mail.storage.EmailProperties;
import br.com.apiestoque.mail.storage.FileSystemStorageService;
import br.com.apiestoque.security.UserSS;
import br.com.apiestoque.services.EmpresaService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 * @author Julius Krah
 *
 */
@Async
@Slf4j
@Component
@Data
@RequiredArgsConstructor
public class HtmlEmailService implements EmailService {
 

	private EmailProperties properties;

	@Autowired
	private JavaMailSender javaMail;

	@Autowired
	FileSystemStorageService storageService;

	@Autowired
	EmpresaService empresaService;

	@Value("${default.sender}")
	private String sender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.EmailService#sendHtmlEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void sendHtmlEmail(EmailProperties properties) {
		  MimeMessage message = javaMail.createMimeMessage();
		try {
			final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setFrom(properties.getFrom(), properties.getPersonal());
			helper.setTo(properties.getTo());
			helper.setSubject(properties.getMessageSubject());
			log.info("Sending HTML email to {}", properties.getPersonal());
			// Set to true for HTML
			helper.setText(properties.getHtml(), true);
			javaMail.send(message);
		} catch (MessagingException | UnsupportedEncodingException e) {
			log.error("Error encountered preparing MimeMessage", e);
		}

		log.debug("Sending html email completed");
	}

	public	void sendemailreport(EmailProperties properties, String inputFileName, Map<String, Object> params,
			List<?> dataSource) {
		UserSS user = UserService.authenticated();

		Empresas company = empresaService.find(user.getEmpresa().getId());
		List<Empresas> listCompany = new LinkedList<>();
		listCompany.add(company);
		params.put("Empresa", company);
		params.put("company", listCompany);

		params.put("heard", storageService.loadPathJasperFile("Cabecalho"));

		JasperReport jasperReport = null;
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			// Check if a compiled report exists
			if (storageService.jasperFileExists(inputFileName)) {
				jasperReport = (JasperReport) JRLoader.loadObject(storageService.loadJasperFile(inputFileName));
			}
			// Compile report from source and save
			else {
				String jrxml = storageService.loadJrxmlFile(inputFileName);
				log.info("{} loaded. Compiling report", jrxml);
				jasperReport = JasperCompileManager.compileReport(jrxml);
				// Save compiled report. Compiled report is loaded next time
				JRSaver.saveObject(jasperReport, storageService.loadJasperFile(inputFileName));
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					new JRBeanCollectionDataSource(dataSource));

			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
			DataSource aAttachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

			  MimeMessage message = javaMail.createMimeMessage(); 
			try {
				final MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom(properties.getFrom(), properties.getPersonal());
				helper.setTo(properties.getTo());
				helper.setSubject(properties.getMessageSubject());
				log.info("Sending HTML email to {}", properties.getTo());
				// Set to true for HTML
				helper.setText(properties.getHtml(), true);
				helper.addAttachment("report.pdf", aAttachment);
				javaMail.send(message);

				log.debug("Sending html email completed");

			} catch (MessagingException | UnsupportedEncodingException e) {
				log.error("Error encountered preparing MimeMessage", e);
			}
		} catch (JRException | IOException e) {
			e.printStackTrace();
			log.error("Encountered error when loading jasper file", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.mail.EmailService#sendHtmlEmail(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void sendHtmlEmail(String recipient, String html, Map<String, byte[]> imageSource) {
		  MimeMessage message = javaMail.createMimeMessage();
		try {
			final MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom( properties.getFrom());
			helper.setTo(properties.getTo());
			helper.setSubject(properties.getMessageSubject());
			log.info("Sending HTML email to {}", recipient);
			// Set to true for HTML
			helper.setText(html, true);
			for (Entry<String, byte[]> val : imageSource.entrySet()) {
				helper.addInline(val.getKey(), new ByteArrayResource(val.getValue()), "image/png");
			}
			javaMail.send(message);
		} catch (MessagingException e) {
			log.error("Error encountered preparing MimeMessage", e);
		}

		log.debug("Sending html email completed");
	}

}
