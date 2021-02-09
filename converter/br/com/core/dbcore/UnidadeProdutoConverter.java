package br.com.core.dbcore;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UnidadeProdutoConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return UnidadeProdutoEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  UnidadeProdutoEnum.getById(dbData);
	}

	 
}
