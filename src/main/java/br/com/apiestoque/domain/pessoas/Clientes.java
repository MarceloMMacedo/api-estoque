package br.com.apiestoque.domain.pessoas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.FuncaoConverter;
import br.com.apiestoque.converters.NatureaPessoaConverter;
import br.com.apiestoque.converters.PerfilConverter;
import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoPessoaConverter;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.intefaces.PessoaBaseInterface;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Clientes implements BaseEntity, Serializable, PessoaBaseInterface<ContacClientes, EnderecoClientes> {
 
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String descricao;
	private String name;
	private String cpfOnCnpj;
	private String rg;
	private double salario;
	private String periodoFerias;
	private String carteiraprofissional;
	private String namewar;

	private String avatar;

	@Convert(converter = FuncaoConverter.class)
	private String funcao;

	@Transient
	private String avatarView;

	@Convert(converter = StatusConverter.class)
	private String status;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String senha;

	private String telefone;

	//@ElementCollection(fetch=FetchType.EAGER)
	//@CollectionTable()
 	@Convert(converter = PerfilConverter.class)
	private String rolers ;
 	
	@ElementCollection 
	@CollectionTable()
	private Set<String> telefones=new    HashSet<>();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private Date instantCreation;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataAdmissao;

	@Convert(converter = NatureaPessoaConverter.class)
	private String natureza;

	private String code;

	private String data_situacao;

	@Convert(converter = TipoPessoaConverter.class)
	private String tipo;

	private String porte;

	private String natureza_juridica;

	private String capital_social;

	private String atividade_principal;

	private String nameFantasia;

	private String tipoSanguineo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<EnderecoClientes> enderecos = new ArrayList<EnderecoClientes>();

	@ManyToOne 
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EnderecoClientes enderecoPrincipal;

	/*
	 * @JoinColumn(insertable = true) private Endereco enderecoPrincipal=new
	 * Endereco();
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<ContacClientes> contasBancarias = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<ContacClientes> contatos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresas empresa;

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private ContacClientes contatoPrincipal;
 
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Contrato> contratos = new ArrayList<>();
*/
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
		Clientes other = (Clientes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	 
 
	 
}
