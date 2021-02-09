package br.com.apiestoque.domain.financeiro.movimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Convert;
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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoMovimentoConverter;
import br.com.apiestoque.domain.financeiro.GrupoFinanceiro;
import br.com.apiestoque.domain.financeiro.contrato.Contrato;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.ordemservico.OrdemServico;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.enumerador.TipoMovimentoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public class MovimentoFinanceiro implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String name;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movimentoFinanceiro")
	private List<Fatura> faturas = new ArrayList<Fatura>();

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataMovimento;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataQuitacao;

	@NumberFormat(style = Style.DEFAULT, pattern = "0")
	private int parcela;

	@Convert(converter = StatusConverter.class)
	private String status;

	@Convert(converter = TipoMovimentoConverter.class)
	private String tipomovimento;

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empresas empresa;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private GrupoFinanceiro historico;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	private OrdemServico ordemservico;

	@Transient
	private List<Fatura> faturasAberto = new ArrayList<Fatura>();

	@Transient
	private List<Fatura> faturasQuit = new ArrayList<Fatura>();

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorAberto;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorQuit;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorFaturas;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Contrato contrato;

	public MovimentoFinanceiro(String tipomovimento) {
		super();
		this.tipomovimento = tipomovimento;
	}

	public MovimentoFinanceiro(int id, int parcelas) {
		super();
		this.contrato = new Contrato();
		parcela = parcelas;
		tipomovimento = TipoMovimentoEnum.entradaContrato.getDescricao();
		status = StatusActiv.ABERTO.name();
		this.contrato.setId(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentoFinanceiro other = (MovimentoFinanceiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public List<Fatura> getFaturasAberto() {
		faturasAberto = new ArrayList<Fatura>();
		try {
			for (Fatura fatura : getFaturas()) {
				if (fatura.getStatus().equals(StatusActiv.ABERTO.getDescricao())) {
					faturasAberto.add(fatura);
				}
			}
		} catch (Exception e) {
		}
		return faturasAberto;
	}

	public List<Fatura> getFaturasQuit() {
		faturasQuit = new ArrayList<Fatura>();
		try {
			for (Fatura fatura : getFaturas()) {
				if (fatura.getStatus().equals(StatusActiv.QUIT.getDescricao())) {
					faturasQuit.add(fatura);
				}
			}
		} catch (Exception e) {
		}
		return faturasQuit;
	}

	public double getValorAberto() {
		valorAberto = 0;
		try {
			for (Fatura fatura : getFaturasAberto()) {
				valorAberto += fatura.getTotal();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorAberto;
	}

	public double getValorQuit() {
		valorQuit = 0;
		try {
			for (Fatura fatura : getFaturasQuit()) {
				valorQuit += fatura.getTotal();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorQuit;
	}

	public double getValorFaturas() {
		valorFaturas = 0;
		try {
			for (Fatura fatura : getFaturas()) {
				valorFaturas += fatura.getTotal();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorFaturas;
	}

}
