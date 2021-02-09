package br.com.apiestoque.enumerador;

public enum UnidadeProdutoEnum {

	Refil(0, "Refil"), 
	Unidade(1, "Unidade"), 
	Litro(2, "Litro"),  
	Kilo(3, "Kilo") ,  
	Gramas(3, "Gramas") ;
	 

	private Integer id;
	private String descricao;

	private UnidadeProdutoEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static String getById(Integer id) {
		for (UnidadeProdutoEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static Integer findById(String s) {
		for (UnidadeProdutoEnum e : values()) {
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
