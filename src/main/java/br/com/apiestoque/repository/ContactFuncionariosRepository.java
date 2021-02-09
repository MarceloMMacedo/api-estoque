package br.com.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.pessoas.ContacFuncionarios;

@Repository
public interface ContactFuncionariosRepository extends JpaRepository<ContacFuncionarios, Integer> {

}
