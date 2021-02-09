package br.com.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apiestoque.domain.estoque.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Integer> {

}
