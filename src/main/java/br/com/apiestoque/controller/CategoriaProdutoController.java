package br.com.apiestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiestoque.domain.estoque.CategoriaProduto;
import br.com.apiestoque.services.CategoriaProdutoService;
import br.com.apiestoque.services.ServiceImpl;

@RestController
@RequestMapping(value = "/categoriaproduto")
public class  CategoriaProdutoController extends ControllerImp<CategoriaProduto> {

	private static final long serialVersionUID = 1L;
	@Autowired
	CategoriaProdutoService service;

	@Override
	public ServiceImpl<CategoriaProduto> service() {
		return service;
	}
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaProduto>> findAll() {
		 
		return  ResponseEntity.ok( service.findAll());
	}
	
}
