package br.com.apiestoque.domain.financeiro;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class HistoricoPadraoSaida extends GrupoFinanceiro implements Serializable {

	private static final long serialVersionUID = 1L;

	public HistoricoPadraoSaida() { 
	}
}
