package br.com.apiestoque.repository.delete;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.financeiro.contrato.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
	List<Contrato> findByStatus(String status);
}
