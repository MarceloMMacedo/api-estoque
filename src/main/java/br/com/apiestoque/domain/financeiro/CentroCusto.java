package br.com.apiestoque.domain.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import lombok.Data;
@Data
@Entity
public class CentroCusto implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Empresas empresa;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00") 
	private double saldo;
	 
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	 
	private double saldoPagar;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	 
	private double saldoReceber;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Transient
	private double saldoDisponivel;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "centrocusto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<AgregadoFinanceiro> agregadoFinanceiros = new ArrayList<AgregadoFinanceiro>();


	public CentroCusto() {
		// TODO Auto-generated constructor stub
	}

 

	public double getSaldoDisponivel() {
		saldoDisponivel=0;
		try {
			saldoDisponivel=getSaldo() - getSaldoPagar();
		} catch (Exception e) {
		}
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(double saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

 
	 

}
