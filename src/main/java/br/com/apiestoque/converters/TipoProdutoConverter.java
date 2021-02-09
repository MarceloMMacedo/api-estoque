package br.com.apiestoque.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.apiestoque.enumerador.TipoProdutoEnum;

@Converter
public class TipoProdutoConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return TipoProdutoEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  TipoProdutoEnum.getById(dbData);
	}

	 
}
