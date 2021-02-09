package br.com.apiestoque.utils;

public class DataClintecnpj {
	private String natureza;
	private String code;
	private String data_situacao;
	private String complemento;
	private String tipo;
	private String nome;
	private String telefone;
	private String situacao;
	private String bairro;
	private String logradouro;
	private String numero;
	private String cep;
	private String municipio;
	private String porte;
	private String natureza_juridica;
	private String cnpj;
	private String uf;
	private String capital_social;

	public DataClintecnpj() {
		// TODO Auto-generated constructor stub
	}

	public DataClintecnpj(String natureza, String code, String data_situacao, String complemento, String tipo,
			String nome, String telefone, String situacao, String bairro, String logradouro, String numero, String cep,
			String municipio, String porte, String natureza_juridica, String cnpj, String uf, String capital_social) {
		super();
		this.natureza = natureza;
		this.code = code;
		this.data_situacao = data_situacao;
		this.complemento = complemento;
		this.tipo = tipo;
		this.nome = nome;
		this.telefone = telefone;
		this.situacao = situacao;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.municipio = municipio;
		this.porte = porte;
		this.natureza_juridica = natureza_juridica;
		this.cnpj = cnpj;
		this.uf = uf;
		this.capital_social = capital_social;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCapital_social() {
		return capital_social;
	}

	public void setCapital_social(String capital_social) {
		this.capital_social = capital_social;
	}
}
