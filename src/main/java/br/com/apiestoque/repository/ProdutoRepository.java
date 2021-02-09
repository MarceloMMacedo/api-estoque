package br.com.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.domain.estoque.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
