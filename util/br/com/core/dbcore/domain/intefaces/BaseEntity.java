package br.com.core.dbcore.domain.intefaces;

import br.com.core.dbcore.domain.pessoas.Empresas;

public interface BaseEntity {

	Integer getId();

	/*
	 * String getName();
	 * 
	 * String getAvatar();
	 * 
	 * String getAvatarView();
	 * 
	 * String getEmail();
	 */
	void setId(Integer id);

	 void setEmpresa(Empresas empresa);

}
