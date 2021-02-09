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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apiestoque.config.services.UserService;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.mail.storage.FileSystemStorageService;
import br.com.apiestoque.security.UserSS;
import br.com.apiestoque.services.EmpresaService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.MapHtmlResourceHandler;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

/**
 * @author Julius Krah
 *
 */
@Slf4j
@Component
@NoArgsConstructor
public class JasperReportsService implements ReportService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	FileSystemStorageService storageService;

	@Autowired
	EmpresaService empresaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public String generateHtmlReport(String inputFileName, Map<String, Object> params) {
		return generateHtmlReport(inputFileName, params, new ArrayList<>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.mail.ReportService#generateHtmlReport(java.lang.String,
	 * java.util.Map, net.sf.jasperreports.engine.JRDataSource)
	 */
	@Override
	public String generateHtmlReport(String inputFileName, Map<String, Object> params, List<?> dataSource) {
		UserSS user = UserService.authenticated();

		Empresas company = empresaService.find(user.getEmpresa().getId());
		List<Empresas> listCompany = new LinkedList<>();
		listCompany.add(company);
		params.put("Empresa", company);
		params.put("company", listCompany);

		params.put("heard", storageService.loadPathJasperFile("Cabecalho"));
		byte[] bytes = null;
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
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					new JRBeanCollectionDataSource(dataSource));
			Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> exporter;
			exporter = new HtmlExporter();

			exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArray));
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.exportReport();
			bytes = byteArray.toByteArray();
		} catch (JRException | IOException e) {
			e.printStackTrace();
			log.error("Encountered error when loading jasper file", e);
		}

		return new String(bytes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.mail.ReportService#generateInlineHtmlReport(java.lang.
	 * String, java.util.Map)
	 */
	@Override
	public List<Object> generateInlineHtmlReport(String inputFileName, Map<String, Object> params) {
		return generateInlineHtmlReport(inputFileName, params, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.mail.ReportService#generateInlineHtmlReport(java.lang.
	 * String, java.util.Map, net.sf.jasperreports.engine.JRDataSource)
	 */
	@Override
	public List<Object> generateInlineHtmlReport(String inputFileName, Map<String, Object> params,
			List<?> jRDataSource) {
		JasperReport jasperReport = null;
		List<Object> result = new ArrayList<>();

		// Map<byte[], Map<String, byte[]>> result = new HashMap<>();
		try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream()) {
			if (storageService.jasperFileExists(inputFileName)) {
				jasperReport = (JasperReport) JRLoader.loadObject(storageService.loadJasperFile(inputFileName));
			} else {
				String jrxml = storageService.loadJrxmlFile(inputFileName);
				log.debug("{} loaded. Compiling report", jrxml);
				jasperReport = JasperCompileManager.compileReport(jrxml);
				JRSaver.saveObject(jasperReport, storageService.loadJasperFile(inputFileName));
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					new JRBeanCollectionDataSource(jRDataSource));
			Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> exporter;
			exporter = new HtmlExporter();
			Map<String, byte[]> resourcesMap = new HashMap<>();
			SimpleHtmlExporterOutput htmlExporterOutput = new SimpleHtmlExporterOutput(byteArray);
			htmlExporterOutput.setImageHandler(new MapHtmlResourceHandler((resourcesMap)) {
				@Override
				public String getResourcePath(String id) {
					return "cid:" + id;
				}

			});

			exporter.setExporterOutput(htmlExporterOutput);
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.exportReport();
			String html = new String(byteArray.toByteArray());
			result.add(html);
			result.add(resourcesMap);
		} catch (JRException | IOException e) {
			log.error("Encountered error when loading jasper file", e);
		}

		return result;
	}

}
