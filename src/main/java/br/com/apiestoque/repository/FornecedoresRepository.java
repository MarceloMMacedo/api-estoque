package br.com.apiestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apiestoque.domain.pessoas.Fornecedores;

@Repository
public interface FornecedoresRepository extends JpaRepository<Fornecedores, Integer> {
 
	@Transactional(readOnly=true)
	Fornecedores findByEmail(String email);

	@Transactional(readOnly=true)
	Fornecedores findByEmailAndStatus(String email, String status);
	
	@Transactional(readOnly=true)
	List<Fornecedores> findAllByEmpresaId(Integer id);
}
