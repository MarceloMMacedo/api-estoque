package br.com.apiestoque.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String tipoproduto;
	private String avatarView;
	private String modelo;
	private String categoria;
	private double saldo;
	private double valor;
	
	public ProdutoDto(Integer id, String name, String tipoproduto, String avatarView, String modelo, String categoria,
			double saldo, double valor) {
		super();
		this.id = id;
		this.name = name;
		this.tipoproduto = tipoproduto;
		this.avatarView = avatarView;
		this.modelo = modelo;
		this.categoria = categoria;
		this.saldo = saldo;
		this.valor = valor;
	}

	public ProdutoDto(Integer id, String name, double saldo, String avatarView, String status, String modelo,
			String categoria,String tipo,
			double valor) {
		this.id = id;
		this.name = name;
		this.tipoproduto = tipo;
		this.avatarView = avatarView;
		this.modelo = modelo;
		this.categoria = categoria;
		this.saldo = saldo;
		this.valor = valor;
	}
	
	

}
