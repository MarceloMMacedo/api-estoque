package br.com.apiestoque.domain.financeiro.contrato;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.domain.financeiro.movimento.Fatura;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.enumerador.StatusActiv;
import lombok.Data;
@Entity
@Data
public class FichaLeitura implements BaseEntity, Serializable {

	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
 
	private String descricao;

	 
	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Contrato contrato;
	
	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Fatura fatura;

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EquipamentosContrato equipamentoContrato;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataleitura;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataprogramadaleitura;

	@Convert(converter = StatusConverter.class)
	private String status;

	private int medidorAnteriorLeitura;

	private int medidorAtual;
	
	private int creditos;

	@Transient
	private int medidorAnterior;

	@Transient
	private int medidorFaturamento;

	@Transient
	private int faturamentoEexcedente;

	@Transient
	private double valorExcedente;
	
	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	public FichaLeitura() {
		// TODO Auto-generated constructor stub
	}

	public FichaLeitura(Contrato contrato, EquipamentosContrato equipamentoContrato, Date dataprogramadaleitura,
			String status,Fatura fatura) {
		super();
		this.contrato = contrato;
		this.equipamentoContrato = equipamentoContrato;
		this.dataprogramadaleitura = dataprogramadaleitura;
		this.status = status;
		this.fatura=fatura;
	}
 
	public int getMedidorAnterior() {
		medidorAnterior = medidorAnteriorLeitura;
		if (status.equals(StatusActiv.ATIVO.getDescricao())) {
			medidorAnterior = equipamentoContrato.getEquipamento().getMedidorContrato() ;
		}
		return medidorAnterior;
	}

	 

	public int getMedidorFaturamento() {
		medidorFaturamento = 0;
		medidorFaturamento = getMedidorAtual() - getMedidorAnterior();
		return medidorFaturamento;
	}

	 
	public double getValorExcedente() {
		valorExcedente = 0;
		try {
			valorExcedente += equipamentoContrato.getValorfranquia() * getFaturamentoEexcedente();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return valorExcedente;
	}

 
	public int getFaturamentoEexcedente() {
		faturamentoEexcedente = 0;
		if (getMedidorFaturamento() > equipamentoContrato.getFranquia()) {
			faturamentoEexcedente = getMedidorFaturamento();
		}
		return faturamentoEexcedente;
	}

	 

}
