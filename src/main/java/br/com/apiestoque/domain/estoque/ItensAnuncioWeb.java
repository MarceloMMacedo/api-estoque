package br.com.apiestoque.domain.estoque;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ItensAnuncioWeb implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	private int quantidade;

	@Transient
	private double valorProduto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private ItensEstoqueWeb produto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private AnuncioProdutoWeb anuncio;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoMinimo;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoReserva;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoMaximo;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoDisponivel;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoReposicao;

	@Transient
	private double saldo;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorfornecedor;

	// constructor
	public ItensAnuncioWeb(ItensEstoqueWeb produto, int quantidade) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
	}

	// geter
	public double getSaldo() {

		saldo = produto.getSaldo();
		if (saldo < quantidade)
			saldo = 0;

		return saldo;
	}

	public double getSaldoDisponivel() {
		saldoDisponivel = getSaldo() / quantidade;
		return (int) saldoDisponivel;
	}

	public double getSaldoReposicao() {

		double val = getSaldoDisponivel() - saldoReserva;
		if (getSaldoDisponivel() < saldoReserva)
			saldoReposicao = saldoMaximo - getSaldoDisponivel();
		else
			saldoReposicao = 0;
		return saldoReposicao;
	}

	public double getValorfornecedor() {
		valorfornecedor = 0.0;
		try {
			valorfornecedor = produto.getProduto().getValorinterno() * quantidade;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorfornecedor;
	}

}
