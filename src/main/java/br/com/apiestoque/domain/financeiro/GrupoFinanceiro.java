package br.com.apiestoque.domain.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class GrupoFinanceiro implements Serializable, BaseEntity {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String name;

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnore
	private Empresas empresa;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CentroCusto centrocusto;
 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupoFinanceiro")
	private List<AgregadoFinanceiro> agregadofinanceiros = new ArrayList<AgregadoFinanceiro>();

	@NumberFormat(style = Style.NUMBER, pattern = "##0.00")
	private double percentual;

	@NumberFormat(style = Style.NUMBER, pattern = "##0.00")
	@Transient
	private double percentualAgregados;

	@NumberFormat(style = Style.NUMBER, pattern = "##0.00")
	@Transient
	private double percentualTotal;
	
	@NumberFormat(style = Style.NUMBER, pattern = "##0.00")
	@Transient
	private double percentualComplamento;

	public GrupoFinanceiro() {
	}

	public double getPercentualAgregados() {
		percentualAgregados = 0;
		try {
			for (AgregadoFinanceiro agregadoFinanceiro : getAgregadofinanceiros()) {
				percentualAgregados += agregadoFinanceiro.getPercentual();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return percentualAgregados;
	}

	public double getPercentualTotal() {
		percentualTotal = 0;
		try {
			percentualTotal += getPercentual() + getPercentualAgregados();
			if (percentualTotal > 100)
				setPercentual(100 - getPercentualAgregados());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return percentualTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoFinanceiro other = (GrupoFinanceiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public double getPercentualComplamento() {
		percentualComplamento=100;
		try {
			percentualComplamento-=getPercentualAgregados();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return percentualComplamento;
	}

}
