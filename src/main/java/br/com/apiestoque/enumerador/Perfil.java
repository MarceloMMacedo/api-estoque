package br.com.apiestoque.enumerador;

public enum Perfil {

	ADMIN("ROLE_ADMG","Administrador Geral"), 
	CLIENTE("ROLE_CLI","Cliente"),  
	FINANCEIRO("ROLE_OPF","Operador  Financeiro" ),
	ADMINFINANCEIRO("ROLE_ADMF","Adminstarador Financeiro"), 
	PROVIDER("ROLE_FORN","Fornecedor"),
	PARCEIRO("ROLE_ADMF","Parceiro"),
	ESTOQUE ("ROLE_EST","Estoquista"),
	ADMINESTOQUE ("ROLE_ADMEST","Estoquista") ;

	private String id;
	private String descricao;

	 

	private Perfil(String valor, String descricao) {
		this.id = valor;
		this.descricao = descricao;
	}

	public static String getIdEnum(String descricao) {

		for (Perfil e : values()) {
			if (e.getDescricao().equals(descricao) )
				return e.getId() ;
		}
		return null;
	}
	
	public static String getDescricaoEnum(String id) {

		for (Perfil e : values()) {
			if (e.getId().equals(id) )
				return e.getDescricao() ;
		}
		return null;
	}
	
	
	
	public static Perfil findById(String s) {

		for (Perfil e : values()) {
			if (e.getDescricao().equals(s) )
				return e ;
		}
		return null;
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
