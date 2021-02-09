package br.com.apiestoque.domain.financeiro.contrato;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.estoque.Patrimonio;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class EquipamentosContrato implements BaseEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;

	private String setor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Patrimonio equipamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Contrato contrato;

	private int franquia;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorfranquia;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valortotal;

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	public double getValortotal() {
		valortotal = 0;
		try {
			valortotal = valorfranquia * franquia;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valortotal;
	}

}
