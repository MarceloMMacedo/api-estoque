package br.com.apiestoque.domain.financeiro.contrato;

import java.io.Serializable;

import lombok.Data;

@Data
public class AgregadoFinanceiroDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private double percentual;
	private double valor;
	public AgregadoFinanceiroDto(int id, String name, double percentual, double valor) {
		super();
		this.id = id;
		this.name = name;
		this.percentual = percentual;
		this.valor = valor*percentual/100;
	}
	
	
}
