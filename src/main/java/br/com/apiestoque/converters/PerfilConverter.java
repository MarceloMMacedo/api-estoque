package br.com.apiestoque.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.apiestoque.enumerador.Perfil;

@Converter
public class PerfilConverter implements
AttributeConverter<String, String>{

	@Override
	public String convertToDatabaseColumn(String attribute) {
		 
		return Perfil.getIdEnum(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) { 
		return Perfil.getDescricaoEnum(dbData);
	}

	 

	 
}
