package br.com.apiestoque.repository.delete;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apiestoque.domain.pessoas.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
 
	@Transactional(readOnly=true)
	Clientes findByEmail(String email);
 
	
	@Transactional(readOnly=true)
	Clientes findByEmailAndStatus(String email, String status);
	
	@Transactional(readOnly=true)
	List<Clientes> findAllByEmpresaId(Integer id);
}
