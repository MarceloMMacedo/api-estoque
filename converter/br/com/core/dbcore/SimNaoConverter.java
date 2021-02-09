package br.com.core.dbcore;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SimNaoConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return SimNaoEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  SimNaoEnum.getById(dbData);
	}

	 
}
