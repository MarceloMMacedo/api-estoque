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
import br.com.apiestoque.converters.TipoFornecedorConverter;
import br.com.apiestoque.converters.TipoPessoaConverter;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.intefaces.PessoaBaseInterface;
import lombok.Data;

@Entity
@Data
public class Fornecedores implements BaseEntity, Serializable , PessoaBaseInterface<ContacFornecedores, EnderecoFornecedores> {

	 
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

	@Convert(converter = TipoFornecedorConverter.class)
	private String tipofornecedor; 
	
	@Convert(converter = TipoPessoaConverter.class)
	private String tipo;

	private String porte;

	private String natureza_juridica;

	private String capital_social;

	private String atividade_principal;

	private String nameFantasia;

	private String tipoSanguineo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<EnderecoFornecedores> enderecos = new ArrayList<EnderecoFornecedores>();

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EnderecoFornecedores enderecoPrincipal;
	
 


   /* @JoinColumn(insertable = true)
	private Endereco enderecoPrincipal=new Endereco();*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<ContasBancFornecedores> contasBancarias = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private List<ContacFornecedores> contatos = new ArrayList<>();
 
	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empresas empresa;
 

	@ManyToOne
	@JoinColumn(columnDefinition = " int ")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private ContacFornecedores contatoPrincipal;
	
	 

	public Fornecedores() {

	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getCpfOnCnpj() {
		return cpfOnCnpj;
	}

	public void setCpfOnCnpj(String cpfOnCnpj) {
		this.cpfOnCnpj = cpfOnCnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getPeriodoFerias() {
		return periodoFerias;
	}

	public void setPeriodoFerias(String periodoFerias) {
		this.periodoFerias = periodoFerias;
	}

	public String getCarteiraprofissional() {
		return carteiraprofissional;
	}

	public void setCarteiraprofissional(String carteiraprofissional) {
		this.carteiraprofissional = carteiraprofissional;
	}

	@Override
	public String getNamewar() {
		return namewar;
	}

	public void setNamewar(String namewar) {
		this.namewar = namewar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	 
	 

	public Date getInstantCreation() {
		return instantCreation;
	}

	public void setInstantCreation(Date instantCreation) {
		this.instantCreation = instantCreation;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData_situacao() {
		return data_situacao;
	}

	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getNatureza_juridica() {
		return natureza_juridica;
	}

	public void setNatureza_juridica(String natureza_juridica) {
		this.natureza_juridica = natureza_juridica;
	}

	public String getCapital_social() {
		return capital_social;
	}

	public void setCapital_social(String capital_social) {
		this.capital_social = capital_social;
	}

	public String getAtividade_principal() {
		return atividade_principal;
	}

	public void setAtividade_principal(String atividade_principal) {
		this.atividade_principal = atividade_principal;
	}

	public String getNameFantasia() {
		return nameFantasia;
	}

	public void setNameFantasia(String nameFantasia) {
		this.nameFantasia = nameFantasia;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	 

 
	public List<ContasBancFornecedores> getContasBancarias() {
		return contasBancarias;
	}

	public void setContasBancarias(List<ContasBancFornecedores> contasBancarias) {
		this.contasBancarias = contasBancarias;
	}

	public List<ContacFornecedores> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContacFornecedores> contatos) {
		this.contatos = contatos;
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
		Fornecedores other = (Fornecedores) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoas [name=" + name + "]";
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

  
	public void setEnderecoPrincipal(EnderecoFornecedores enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setAvatarView(String avatarView) {
		this.avatarView = avatarView;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getRolers() {
		return rolers;
	}

	public void setRolers(String rolers) {
		this.rolers = rolers;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<EnderecoFornecedores> getEnderecos() {
		return enderecos;
	}

	@Override
	public void setEnderecos(List<EnderecoFornecedores> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public ContacFornecedores getContatoPrincipal() {
		return contatoPrincipal;
	}

	public void setContatoPrincipal(ContacFornecedores contatoPrincipal) {
		this.contatoPrincipal = contatoPrincipal;
	}

	@Override
	public String getAvatar() {
		return avatar;
	}

	@Override
	public String getAvatarView() {
		return avatarView;
	}

	@Override
	public EnderecoFornecedores getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	@Override
	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

	 

 

	 

}
