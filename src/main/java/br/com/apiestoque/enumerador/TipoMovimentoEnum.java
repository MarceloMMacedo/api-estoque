package br.com.apiestoque.enumerador;

public enum TipoMovimentoEnum {

	EntradaOrdemServico(0, "Empresa"), 
	entradaContrato(1, "Contrato"),
	Saida(2, "Parceiro");
	 

	private Integer id;
	private String descricao;

	private TipoMovimentoEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static String getById(Integer id) {
		for (TipoMovimentoEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static Integer findById(String s) {
		for (TipoMovimentoEnum e : values()) {
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
