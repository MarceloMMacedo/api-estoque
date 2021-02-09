package br.com.apiestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.domain.estoque.CategoriaProduto;
import br.com.apiestoque.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService  extends ServiceImpl<CategoriaProduto>  {

	private static final long serialVersionUID = 1L;

	@Autowired
	CategoriaProdutoRepository repo;

 @Override
public JpaRepository<CategoriaProduto, Integer> repo() { 
	return repo;
}
}
