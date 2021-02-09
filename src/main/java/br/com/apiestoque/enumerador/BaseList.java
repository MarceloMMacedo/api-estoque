package br.com.apiestoque.enumerador;

public class BaseList {
	private String id;
	private String descricao;

	public BaseList(String id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
