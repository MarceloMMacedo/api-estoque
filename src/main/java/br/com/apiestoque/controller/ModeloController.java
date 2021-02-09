package br.com.apiestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.services.ModeloService;
import br.com.apiestoque.services.ServiceImpl;

@RestController
@RequestMapping(value = "/modelos")
public class ModeloController extends ControllerImp<Modelo> {

	private static final long serialVersionUID = 1L;
	@Autowired
	ModeloService service;

	@Override
	public ServiceImpl<Modelo> service() {
		return service;
	}
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Modelo>> findAll() {
		 
		return  ResponseEntity.ok( service.findAll());
	}
	
}
