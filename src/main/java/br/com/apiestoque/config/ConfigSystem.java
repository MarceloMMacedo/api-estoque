package br.com.apiestoque.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import br.com.apiestoque.util.services.FilesService;
import br.com.apiestoque.util.services.UtilFile;
@Configuration
public class ConfigSystem {

	@Value("${spring.cloud.gcp.credentials.location}")
	private String credencial;
	
	@Value("${spring.cloud.gcp.config_projectId}")
	private String projectId;
	
//	 @Autowired(required=true)
//		private GenTable genegationTable;
//	 
	@Bean
	public boolean instantiateDatabase()  {
//		GenTable genegationTable=new GenTable();
//	  genegationTable.generationTable(); 
	// genPatrimonio.gen();
		return true;
	}
	@Bean
	public Storage storage() {

		try {
			File ins = ResourceUtils.getFile(credencial);			
			
			InputStream in = new FileInputStream(ins);
			
			Credentials credential = GoogleCredentials.fromStream(in);
			
			Storage storage =   StorageOptions.newBuilder().setCredentials(credential).setProjectId(projectId)
					.build().getService();

			 return storage;
			 
		} catch (IllegalStateException | IOException e) {
		}
		return  StorageOptions.getDefaultInstance().getService();

	}

	 
 	@Bean
	public FilesService filesService() {
		 return new UtilFile();
	} 
	
	/*@Bean
	@Primary
	ObjectMapper objectMapper() {
	  Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	  AnnotationIntrospector annotationIntrospector = new JacksonAnnotationIntrospector() {	  
		private static final long serialVersionUID = 1L;

		@Override
	    protected boolean _isIgnorable(Annotated a) {
	      boolean ignorable = super._isIgnorable(a);
	      if (ignorable) {
	        Transient aTransient = a.getAnnotation(Transient.class);
	        JsonIgnore jsonIgnore = a.getAnnotation(JsonIgnore.class);

	        return aTransient == null || jsonIgnore != null && jsonIgnore.value();
	      }
	      return false;
	    }
	  };
	  builder.annotationIntrospector(annotationIntrospector);
	  return builder.build();
	}*/
}
