package br.com.apiestoque.domain.estoque;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.SimNaoConverter;
import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoAnuncioConverter;
import br.com.apiestoque.domain.financeiro.GrupoFinanceiroAnuncio;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.enumerador.SimNaoEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class AnuncioProdutoWeb implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String anuncio;

	private double saldo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
	private List<DescricaoAnuncio> descricaoAnuncios;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
	private List<ItensAnuncioWeb> itensanuncio;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double quantidade;

	private String avatar1;

	private String avatar2;

	private String avatar3;

	@Convert(converter = StatusConverter.class)
	private String status;

	@Convert(converter = TipoAnuncioConverter.class)
	private String tipoanuncio;

	@Transient
	private String avatar1View;

	@Transient
	private String avatar2View;

	@Transient
	private String avatar3View;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoDisponivel;

	/**
	 * soma de cada item do anuncio deduzido do descont, se desconto for maior
	 * grupofinanceiroanucio.centrocosto valor desconto sera
	 * grupofinanceiroanucio.centrocosto
	 */
	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorfornecedor;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double maxdescontox;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dateExpiracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private GrupoFinanceiroAnuncio financeiroAnuncio;

	@Convert(converter = SimNaoConverter.class)
	private String isValorPropos = SimNaoEnum.Nao.getDescricao();

	private double desconto;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	// geter
	public double getSaldo() {
		double ref = 0;
		saldo = 0.0;
		boolean flag = false;
		for (ItensAnuncioWeb anuncioWeb : getItensanuncio()) {
			if (anuncioWeb.getSaldo() > ref && anuncioWeb.getSaldo() > 0.0 && flag == false) {
				saldo = anuncioWeb.getSaldo();
				ref = getSaldo();
			}
			if (anuncioWeb.getSaldo() <= 0.0) {
				saldo = anuncioWeb.getSaldo();
				ref = getSaldo();
				flag = true;
			}
		}

		return saldo;
	}

	public double getValorfornecedor() {
		valorfornecedor = 0.0;
		try {
			for (ItensAnuncioWeb anuncioWeb : getItensanuncio()) {
				if (anuncioWeb.getValorfornecedor() > valorfornecedor) {
					valorfornecedor = anuncioWeb.getValorfornecedor() * quantidade;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorfornecedor;
	}

	public double getMaxdescontox() {
		maxdescontox = 0.0;

		double val = getValorfornecedor() * (financeiroAnuncio.getPercentualTotal() / 100) + getValorfornecedor();
		double val1 = val - (val * desconto / 100);
		try {
			if (val1 < getValorfornecedor()) {
				val1 = getValorfornecedor();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		maxdescontox = val1;
		return maxdescontox;
	}

	public double getValor() {
		
		return getMaxdescontox();
	}

}
