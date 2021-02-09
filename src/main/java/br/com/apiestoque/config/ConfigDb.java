package br.com.apiestoque.config;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.apiestoque.IntTable;

@Configuration
@Profile(value="dev")
public class ConfigDb {
	
	@Autowired
	IntTable intTable ;
	
	
	@Bean
	public boolean initTabge() {
		intTable.intTable();
		return true;
	} 
}
