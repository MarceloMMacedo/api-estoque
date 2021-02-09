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
package br.com.apiestoque.mail.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import br.com.apiestoque.service.security.exceptions.AuthorizationException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Julius Krah
 *
 */
@Slf4j
@Component
public class FileSystemStorageService implements StorageService {
	
	
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${default.report}")
	private String template;
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jrxmlFileExists(java.lang.
	 * String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jrxmlFileExists(java.lang.
	 * String)
	 */
	@Override
	public boolean jrxmlFileExists(String file) {
		// @formatter:off
		Resource res = resourceLoader.getResource(template);
		// this.rootLocation = Paths.get(res.getURL().getPath());
		try {
			Path reportFile = Paths.get(res.getURI().getPath());
			reportFile = reportFile.resolve(file + ".jrxml");
			if (Files.exists(reportFile))
				return true;
		} catch (IOException e) {
	//		log.error("Error while trying to get file URI", e);
			return false;
		}
		// @formatter:on
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jasperFileExists(java.lang.
	 * String)
	 */
	@Override
	public boolean jasperFileExists(String file) {
		Resource res = resourceLoader.getResource(template);
		try {
			Path reportFile  = new File(res.getURL().getPath()).toPath();// Paths.get(res.getURL() );
		 
			reportFile = reportFile.resolve(file + ".jasper");
			if (Files.exists(reportFile))
				return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.storage.StorageService#loadJrxmlFile(java.lang.String)
	 */
	@Override
	public String loadJrxmlFile(String file) {
		// @formatter:off
		Resource res = resourceLoader.getResource(template);

		try {
			Path reportFile= new File(res.getURL().getPath()).toPath();// Paths.get(res.getURL() );
			 
			reportFile = reportFile.resolve(file + ".jrxml");
			return reportFile.toString();
		} catch (IOException e) {
			//log.error("Error while trying to get file prefix", e);
			throw new AuthorizationException("Could not load file", e);
		}
		// @formatter:on
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.storage.StorageService#loadJasperFile(java.lang.String)
	 */
	@Override
	public File loadJasperFile(String file) {
		Resource res = resourceLoader.getResource(template);

		try {
			Path reportFile = Paths.get(res.getURI());
			reportFile = reportFile.resolve(file + ".jasper");
			return reportFile.toFile();
		} catch (Exception e) {
			//log.error("Error while trying to get file prefix", e);
			throw new AuthorizationException("Could not load file", e);
		}
	}	
	@Override
	public String loadPathJasperFile(String file) {
		Resource res = resourceLoader.getResource(template);

		try {
			Path reportFile= new File(res.getURL().getPath()).toPath();// Paths.get(res.getURL() );
			 
			reportFile = reportFile.resolve(file + ".jasper");
			return reportFile.toString();
		} catch (IOException e) {
			//log.error("Error while trying to get file prefix", e);
			throw new AuthorizationException("Could not load file", e);
		}
		// @formatter:on
	}
}