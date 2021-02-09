package br.com.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.estoque.FornecedorProduto;

@Repository
public interface FornecedorProdutoRepository  extends JpaRepository<FornecedorProduto, Integer> {

}
