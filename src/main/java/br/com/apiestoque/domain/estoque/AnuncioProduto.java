package br.com.apiestoque.domain.estoque;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.SimNaoConverter;
import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoAnuncioConverter;
import br.com.apiestoque.domain.financeiro.GrupoFinanceiroAnuncio;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.enumerador.SimNaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnuncioProduto extends Produto implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;

	private String anuncio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Produto produto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
	private List<DescricaoAnuncio> descricaoAnuncios;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
	private List<ItensAnuncio> itensanuncio;

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

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dateExpiracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private GrupoFinanceiroAnuncio financeiroAnuncio;

	@Convert(converter = SimNaoConverter.class)
	private String isValorPropos = SimNaoEnum.Nao.getDescricao();

	private double desconto;

}
