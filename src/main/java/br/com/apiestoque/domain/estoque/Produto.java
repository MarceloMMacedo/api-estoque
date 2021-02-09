package br.com.apiestoque.domain.estoque;

import java.io.Serializable;
import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoProdutoConverter;
import br.com.apiestoque.converters.UnidadeProdutoConverter;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@Transient
	private String name;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	private String especificação;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataEntrada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CategoriaProduto categoriaproduto;// = new CategoriaProduto();

	@Convert(converter = TipoProdutoConverter.class)
	private String tipoproduto;// servico / produto

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Modelo modelo;

	private String nameComplemento;

	private String localizacao;

	private double altura;

	private double lagura;

	private double comprimento;

	private double peso;

	private String avatar;

	@Transient
	private String avatarView;

	@Convert(converter = UnidadeProdutoConverter.class)
	private String unidadeproduto;

	private String CodBarra;

	@Convert(converter = StatusConverter.class)
	private String status;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldo;// saldo total de itens em estoque;

	@Transient
	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldoweb;// saldo total de itens em estoque;

	@NumberFormat(style = Style.NUMBER, pattern = "#0")
	private double saldocontrato;// saldo total de itens em estoque;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
	private List<FornecedorProduto> fornecedores;// = new ArrayList<FornecedorProduto>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
	private List<ProdutoContrato> produtocontrato;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
	private List<ItensEstoqueWeb> estoqueWebs;

	@Transient
	private double valorinterno;

	public String getName() {
		String modelo = "";
		try {

			try {
				modelo = getModelo().getName();
				if (getModelo().getName() == null)
					modelo = "";
			} catch (Exception e) {
				// TODO: handle exception
			}
			String gupoproduto = "";
			try {
				gupoproduto = getCategoriaproduto().getName();
				if (getCategoriaproduto().getName() == null)
					gupoproduto = "";
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (nameComplemento == null)
				nameComplemento = "";
			// if (tipo.equals(TipoProduto.Produto.getId()))
			{
				name = gupoproduto + " " + nameComplemento + " " + modelo;
			} /*
				 * else { name = nameComplemento; } } else name = nameComplemento;
				 */
		} catch (Exception e) {
			name = nameComplemento;
		}
		if (name == null)
			name = "";

		return name;
	}

	public double getSaldo() {
		
		saldo = 0;
		for (ItensEstoqueWeb itensEstoqueWeb : getEstoqueWebs()) {
			saldo+=itensEstoqueWeb.getSaldo();
		}

		return saldo;
	}

	public double getValorinterno() {
		valorinterno = 0;
		try {
			for (FornecedorProduto fornecedorProduto : getFornecedores()) {
				if (fornecedorProduto.getValor() > valorinterno)
					valorinterno = fornecedorProduto.getValor();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorinterno;
	}

	 
}
