package br.com.apiestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.repository.EmpresasRepository;

@Service
public class EmpresaService  extends ServiceImpl<Empresas>  {

	private static final long serialVersionUID = 1L;

	@Autowired
	EmpresasRepository repo;

 @Override
public JpaRepository<Empresas, Integer> repo() { 
	return repo;
}
 @Override 
public Empresas find(Integer id) {
	// TODO Auto-generated method stub
	return SetImgSingle("avatar", "avatarView", super.find(id));
}
}
