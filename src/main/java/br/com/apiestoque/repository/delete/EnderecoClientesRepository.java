package br.com.apiestoque.repository.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.pessoas.EnderecoClientes;

@Repository
public interface EnderecoClientesRepository extends JpaRepository<EnderecoClientes, Integer> {

 
}
