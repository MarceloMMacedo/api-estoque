package br.com.apiestoque.repository.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.financeiro.ContasBanco;

@Repository
public interface ContasBancoRepository extends JpaRepository<ContasBanco, Integer> {

}
