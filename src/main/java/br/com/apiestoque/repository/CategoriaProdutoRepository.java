package br.com.apiestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.estoque.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Integer> {
List<CategoriaProduto> findAllByNameContainingIgnoreCase(String name);
}
