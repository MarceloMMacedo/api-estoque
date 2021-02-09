package br.com.core.dbcore;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
