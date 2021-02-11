package br.com.apiestoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.apiestoque.domain.estoque.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Integer> {

	 
	List<Modelo> findAllByNameContainingIgnoreCase(String name);
	List<Modelo>  findAllByName( String name);
}
