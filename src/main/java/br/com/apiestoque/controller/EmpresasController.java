package br.com.apiestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.services.EmpresaService;
import br.com.apiestoque.services.ServiceImpl;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresasController extends PessoaBaseController<Empresas> {

	private static final long serialVersionUID = 1L;

	@Autowired
	EmpresaService funcionariosService;

	@Override
	public ServiceImpl<Empresas> service() {
		// TODO Auto-generated method stub
		return funcionariosService;
	}

	/*
	 * @Override
	 * 
	 * @RequestMapping(value = "/baseall", method = RequestMethod.GET) public
	 * List<BaseDto<Empresas>> findBaseAll() { // TODO Auto-generated method stub
	 * return service().findBaseAll(); }
	 */
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Empresas> findemail(@RequestParam(name = "value") String email) {

		return ResponseEntity.ok(funcionariosService.findByEmail(email));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/findname", method = RequestMethod.GET)
	public ResponseEntity<Empresas> findname(@RequestParam(name = "value") String name) {

		return ResponseEntity.ok(funcionariosService.findByEmail(name));
	}
}
