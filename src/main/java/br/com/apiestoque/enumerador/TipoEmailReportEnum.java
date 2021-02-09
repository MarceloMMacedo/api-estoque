package br.com.apiestoque.enumerador;

public enum TipoEmailReportEnum { 
	InicioMesFinanceiro(1,"InicioMesFinanceiro"),  
	MetadeMesFinanceiro(3,"MetadeMesFinanceiro" ),
	ResumoFinanceiro(4,"ResumoFinanceiro"),  
	tecnico(6,"Técnico"),
	ESTOQUE (7,"Estoquista") ;

	private int id;
	private String descricao;

	 

	private TipoEmailReportEnum(int valor, String descricao) {
		this.id = valor;
		this.descricao = descricao;
	}

	public static String getById(int id) {
		for (TipoEmailReportEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static int findById(String s) {
		for (TipoEmailReportEnum e : values()) {
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
