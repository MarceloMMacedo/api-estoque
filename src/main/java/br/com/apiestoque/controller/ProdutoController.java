package br.com.apiestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiestoque.domain.estoque.Produto;
import br.com.apiestoque.dto.ProdutoDto;
import br.com.apiestoque.mail.storage.EmailProperties;
import br.com.apiestoque.services.ProdutoService;
import br.com.apiestoque.services.ServiceImpl;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController extends ControllerImp<Produto> {

	private static final long serialVersionUID = 1L;
	@Autowired
	ProdutoService service;

	@Override
	public ServiceImpl<Produto> service() {
		return service;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {

		return ResponseEntity.ok(service.findAll());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/getallprodutos", method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDto>> getAllprodutos() {
		return ResponseEntity.ok(service.getAllprodutos());
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/sendmail", method = RequestMethod.PUT)
	public ResponseEntity<Void> sendmail(@RequestBody EmailProperties properties) {
		service().sendemailreport(properties);
		return ResponseEntity.noContent().build();
	}
}
