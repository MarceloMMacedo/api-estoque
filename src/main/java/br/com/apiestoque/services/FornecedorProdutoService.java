package br.com.apiestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.domain.estoque.FornecedorProduto;
import br.com.apiestoque.repository.FornecedorProdutoRepository;

@Service
public class FornecedorProdutoService  extends ServiceImpl<FornecedorProduto>  {

	private static final long serialVersionUID = 1L;

	@Autowired
	FornecedorProdutoRepository repo;

 @Override
public JpaRepository<FornecedorProduto, Integer> repo() { 
	return repo;
}
}
