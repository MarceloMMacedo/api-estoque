package br.com.apiestoque.repository.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.financeiro.GrupoFinanceiroContrato;

@Repository
public interface GrupoFinanceiroContratoRepository extends JpaRepository<GrupoFinanceiroContrato, Integer> {

}
