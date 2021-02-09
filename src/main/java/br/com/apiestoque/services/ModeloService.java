package br.com.apiestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.repository.ModeloRepository;

@Service
public class ModeloService  extends ServiceImpl<Modelo>  {

	private static final long serialVersionUID = 1L;

	@Autowired
	ModeloRepository repo;

 @Override
public JpaRepository<Modelo, Integer> repo() { 
	return repo;
}
}
