package br.com.apiestoque.dto;

import java.io.Serializable;

import br.com.apiestoque.domain.pessoas.Fornecedores;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseDto implements     Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String cpfOnCnpj;

	private String avatar;

	private String avatarView;

	private String email;

	private double valor;
	private  double saldo;
	private String status;
 
	public BaseDto(Fornecedores f) {
		super();
		this.id = f.getId();
		this.name = f.getName();
		this.cpfOnCnpj = f.getCpfOnCnpj();
		this.avatar = f.getAvatar();
		this.avatarView = f.getAvatarView();

		this.email = f.getEmail();
	}
	public BaseDto(Integer id, String name, String cpfOnCnpj, String avatar, String avatarView, String email) {
		super();
		this.id = id;
		this.name = name;
		this.cpfOnCnpj = cpfOnCnpj;
		this.avatar = avatar;
		this.avatarView = avatarView;

		this.email = email;
	}

	public BaseDto(Integer id, String name, double saldo, String avatarView, String status, double valorinterno) {
		this.id = id;
		this.name = name;
		this.saldo=saldo;
		valor=valorinterno; 
		this.avatarView = avatarView; 
		this.status=status;
		
	}


}