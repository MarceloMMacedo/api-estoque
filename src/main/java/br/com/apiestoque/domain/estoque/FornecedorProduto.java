package br.com.apiestoque.domain.estoque;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.domain.pessoas.Fornecedores;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class FornecedorProduto  implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String name;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnore
	private Empresas empresa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Fornecedores  fornecedor;
	

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;

}
