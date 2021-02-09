package br.com.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.pessoas.EnderecoFornecedores;

@Repository
public interface EnderecoFornecedoresRepository extends JpaRepository<EnderecoFornecedores, Integer> {

 
}
