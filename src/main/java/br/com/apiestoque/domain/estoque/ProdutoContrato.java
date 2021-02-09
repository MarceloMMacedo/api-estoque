package br.com.apiestoque.domain.estoque;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.financeiro.contrato.Contrato;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProdutoContrato extends Produto implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Contrato contrato;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoMinimo;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoReserva;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoMaximo; 

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double quantidade;
	
	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoDisponivel;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoContrato;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoReposicao;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;

	public double getValor() {
		valor = 0;
		try {
			for (FornecedorProduto fornecedorProduto : getFornecedores()) {
				if (fornecedorProduto.getValor() > valor)
					valor = fornecedorProduto.getValor();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valor;
	}

}
