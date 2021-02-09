package br.com.apiestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apiestoque.domain.estoque.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {
	
	@Transactional(readOnly = true)
	List<Patrimonio> findAllByStatus(String status);
	
	@Transactional(readOnly = true)
	List<Patrimonio> findAllByStatusAndContratoIsNull(String status);
	
	@Transactional(readOnly = true)
	List<Patrimonio> findByStatuslocacao(String status);
}
