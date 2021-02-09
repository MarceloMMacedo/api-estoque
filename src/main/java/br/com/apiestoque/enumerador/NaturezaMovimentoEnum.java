package br.com.apiestoque.enumerador;

public enum NaturezaMovimentoEnum {
	


	Entrada(0,"Entrada"),
	Saida(01,"Saída");
	
	private String descricao;
	private int id;
	
	private NaturezaMovimentoEnum( int id,  String descricao) {
		this.id=id;
		this.descricao = descricao;
	}
	
	 
	
	public String getDescricao () {
		return descricao;
	}
 

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static String getById(Integer id) {
		for (NaturezaMovimentoEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static Integer findById(String s) {
		for (NaturezaMovimentoEnum e : values()) {
			if (e.getDescricao().equals(s))
				return e.getId();
		}
		return 0;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	/*public static Perfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}*/

}
