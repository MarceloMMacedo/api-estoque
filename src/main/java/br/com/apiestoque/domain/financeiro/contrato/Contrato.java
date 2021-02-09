package br.com.apiestoque.domain.financeiro.contrato;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.SimNaoConverter;
import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.domain.financeiro.AgregadoFinanceiro;
import br.com.apiestoque.domain.financeiro.GrupoFinanceiroContrato;
import br.com.apiestoque.domain.financeiro.movimento.MovimentoContrato;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Clientes;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.enumerador.SimNaoEnum;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.enumerador.TipoMovimentoEnum;
import br.com.apiestoque.util.Extenso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Contrato implements BaseEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private GrupoFinanceiroContrato financeiroContrato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Clientes cliente=new Clientes();

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date datainicio = new Date();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataInstalacao = new Date();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date dataPrimeiroVencimento = new Date();

	private int periodo = 12;

	private int diaLeitura = 8;

	private int diaVencimento = 10;

	private String imageContrato;

	@Convert(converter = SimNaoConverter.class)
	private String isFranquia = SimNaoEnum.Sim.getDescricao();

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;

	@Convert(converter = StatusConverter.class)
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato") 
	private List<MovimentoContrato> movimentoFinanceiros = new ArrayList<MovimentoContrato>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<EquipamentosContrato> equipamentosContratos = new ArrayList<EquipamentosContrato>();

	@Transient
	private MovimentoContrato movimentoFinanceirosAberto = new MovimentoContrato(
			TipoMovimentoEnum.entradaContrato.getDescricao());

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double total;

	@Transient
	private String imageContratoView;

	@Transient
	private List<AgregadoFinanceiroDto> valoresContrato = new ArrayList<>();

	@Transient
	private String valorPorExtenso;

	@Transient
	private String clientename;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
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

	public MovimentoContrato getMovimentoContratosAberto() {
		try {
			for (MovimentoContrato movimentoFinanceiro : movimentoFinanceiros) {
				if (movimentoFinanceiro.getStatus().equals(StatusActiv.ABERTO.getDescricao())) {
					movimentoFinanceirosAberto = new MovimentoContrato();
					movimentoFinanceirosAberto = movimentoFinanceiro;
				}
			}
		} catch (Exception e) {
		}
		return movimentoFinanceirosAberto;
	}

	public double getTotal() {
		total = 0;
		if (isFranquia.equals(SimNaoEnum.Sim.getDescricao())) {

			try {
				for (EquipamentosContrato equipamentosContrato : equipamentosContratos) {
					total += equipamentosContrato.getValortotal();
				}
			} catch (Exception e) {
			}
		} else {
			total = getValor();
		}
		return total;
	}

	public List<AgregadoFinanceiroDto> getValoresContrato() {
		AgregadoFinanceiroDto agregadoFinanceiro = new AgregadoFinanceiroDto(
				financeiroContrato.getCentrocusto().getId(), financeiroContrato.getCentrocusto().getName(),
				financeiroContrato.getPercentualComplamento(), getTotal());
		valoresContrato.add(agregadoFinanceiro);

		for (AgregadoFinanceiro financeiro : getFinanceiroContrato().getAgregadofinanceiros()) {
			agregadoFinanceiro = new AgregadoFinanceiroDto(financeiro.getCentrocusto().getId(),
					financeiro.getCentrocusto().getName(), financeiro.getPercentual(), getTotal());
			valoresContrato.add(agregadoFinanceiro);
		}

		return valoresContrato;
	}

	public String getValorPorExtenso() {
		try {
			Extenso extenso = new Extenso(getTotal());
			valorPorExtenso = extenso.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorPorExtenso;
	}

	public String getClientename() {
		try{if (cliente != null)
			clientename = cliente.getName();}catch (Exception e) {
				// TODO: handle exception
			}
		return clientename;
	}

}
