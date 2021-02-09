package br.com.apiestoque.enumerador;

public enum NaturezaPessoaEnum {

	PESSOAFISICA(0, "Pessoa Fisíca"), 
	PESSOAJURIDICA(1, "Pessoa Jurídica");

	private Integer id;
	private String descricao;

	private NaturezaPessoaEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static String getById(Integer id) {
		for (NaturezaPessoaEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static Integer findById(String s) {
		for (NaturezaPessoaEnum e : values()) {
			if (e.getDescricao().equals(s))
				return e.getId();
		}
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
