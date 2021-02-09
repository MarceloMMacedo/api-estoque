package br.com.apiestoque.enumerador;

public enum TipoPatrimonioEnum {
	


	Maquinas(0,"Maquina"),
	Ferramenta(01,"Ferramenta"),
	Moveis(2,"Móvel"),
	Transformador(3,"Transformador"),
	Eletronicos(4,"Eletronico"),
	Veiculos(5,"Veiculo");
	
	private String descricao;
	private int id;
	
	private TipoPatrimonioEnum( int id,  String descricao) {
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
		for (TipoPatrimonioEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static Integer findById(String s) {
		for (TipoPatrimonioEnum e : values()) {
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
