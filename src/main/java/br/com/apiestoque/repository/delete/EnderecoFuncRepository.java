package br.com.apiestoque.repository.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.pessoas.EnderecoFuncionarios;

@Repository
public interface EnderecoFuncRepository extends JpaRepository<EnderecoFuncionarios, Integer> {

 
}
