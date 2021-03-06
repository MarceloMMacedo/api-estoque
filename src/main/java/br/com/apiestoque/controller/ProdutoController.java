package br.com.apiestoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiestoque.domain.estoque.CategoriaProduto;
import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.domain.estoque.Produto;
import br.com.apiestoque.domain.pessoas.Fornecedores;
import br.com.apiestoque.dto.BaseDto;
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

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Produto> update(@RequestBody Produto objDto) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.update(objDto));
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

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/allmodelos", method = RequestMethod.GET)
	public ResponseEntity<List<Modelo>> allmodelos() {
		return ResponseEntity.ok(service.modelos());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/ismodelo", method = RequestMethod.GET)
	public ResponseEntity<Boolean> ismodelo(@RequestParam(name = "value") String name) {
		return ResponseEntity.ok(service.ismodelo(name));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/insermodelo", method = RequestMethod.POST)
	public ResponseEntity<Integer> insermodelo(@RequestBody Modelo modelo) {
		modelo = service.insertmodelo(modelo);
		return ResponseEntity.ok().body(modelo.getId());
	}

	// categoriPrduto
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/allcategoriaprodutos", method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaProduto>> allcategoriaprodutos() {
		return ResponseEntity.ok(service.allcategoriaprodutos());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/iscategoriaproduto", method = RequestMethod.GET)
	public ResponseEntity<Boolean> iscategoriaproduto(@RequestParam(name = "value") String name) {
		return ResponseEntity.ok(service.ismodelo(name));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/insertcategoriaproduto", method = RequestMethod.POST)
	public ResponseEntity<Integer> insertcategoriaproduto(@RequestBody CategoriaProduto categoria) {
		categoria = service.insertcategoriaproduto(categoria);
		return ResponseEntity.ok().body(categoria.getId());
	}

//fornecedores
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/fornecedores", method = RequestMethod.GET)
	public ResponseEntity<List<BaseDto>> fornecedores() {
		return ResponseEntity.ok(service.fornecedores());
	}
	
	
}
