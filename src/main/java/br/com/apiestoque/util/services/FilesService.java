package br.com.apiestoque.util.services;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;

public interface FilesService {

	public byte[] ViewPdf(Map<String, Object> parameters, List<?> source, String templates)
			throws JRException, IOException;

	public byte[] viewListPatrimonio() throws JRException, IOException;

	public String downloadFile(String fileName) throws IOException;

	public URI uploadProfilePicture(MultipartFile multipartFile);

	public void deleteimage(String filename);

	public String _uploadProfilePicture(MultipartFile multipartFile, String filename);

	public String _uploadProfilePicturefile(String fileName, String filename);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jrxmlFileExists(java.lang.
	 * String)
	 */

	public boolean jrxmlFileExists(String file);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.juliuskrah.jasper.storage.StorageService#jasperFileExists(java.lang.
	 * String)
	 */

	public boolean jasperFileExists(String file);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.juliuskrah.jasper.storage.StorageService#loadJrxmlFile(java.lang.String)
	 */

	public String loadJrxmlFile(String file);

	public java.io.File loadJasperFile(String file);

	public String loadPathJasperFile(String file);
}
