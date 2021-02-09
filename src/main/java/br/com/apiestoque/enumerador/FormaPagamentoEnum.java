package br.com.apiestoque.enumerador;

public enum FormaPagamentoEnum { 
	Dinheiro(1,"Dinheiro"),  
	Transferencia(2,"TransferÊncia Bancária" ),
	Debito(3,"Débito em Conta")  ;

	private int id;
	private String descricao;

	 

	private FormaPagamentoEnum(int valor, String descricao) {
		this.id = valor;
		this.descricao = descricao;
	}

	public static String getById(int id) {
		for (FormaPagamentoEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static int findById(String s) {
		for (FormaPagamentoEnum e : values()) {
			if (e.getDescricao().equals(s) )
				return e.getId();
		}
		return 0;
	}

	 

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

}
