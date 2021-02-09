package br.com.apiestoque.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apiestoque.domain.pessoas.ContacFuncionarios;
import br.com.apiestoque.domain.pessoas.Funcionarios;
import br.com.apiestoque.dto.BaseDto;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.services.FuncionariosService;
import br.com.apiestoque.services.ServiceImpl; 

 
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionariosController extends ControllerImp<Funcionarios>{

	private static final long serialVersionUID = 1L;

	@Autowired
	FuncionariosService funcionariosService;

	@Override
	public ServiceImpl<Funcionarios> service() {
		// TODO Auto-generated method stub
		return funcionariosService;
	}

	@RequestMapping(method = org.springframework.web.bind.annotation.RequestMethod.POST)
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	public ResponseEntity<Integer> insert(Funcionarios objDto) {
		objDto = funcionariosService.insert(objDto);
		objDto.setStatus(StatusActiv.ATIVO.getDescricao());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDto.getId()).toUri();
		return ResponseEntity.created(uri).body(objDto.getId());
	}

	/*
	 * @Override
	 * 
	 * @RequestMapping(value = "/baseall", method = RequestMethod.GET) public
	 * List<BaseDto<Empresas>> findBaseAll() { // TODO Auto-generated method stub
	 * return service().findBaseAll(); }
	 */
 
	@RequestMapping(value ="/findemail",method = RequestMethod.GET) 
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )") 
	public ResponseEntity<BaseDto> _findemail(@RequestParam(name = "email") String email) {
		 
		return ResponseEntity.ok(funcionariosService.findEmail(email)) ;
	}
	
//contacets
	@RequestMapping(value ="/{id}/contact",method = RequestMethod.POST) 
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	public ResponseEntity<Void> contact(@PathVariable Integer id,@RequestBody ContacFuncionarios objDto) {
		 funcionariosService.saveContact(id, objDto);
		return ResponseEntity.ok().build();
	}
	@RequestMapping(value ="/{id}/contact",method = RequestMethod.GET) 
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	public ResponseEntity<List<ContacFuncionarios>> contact(@PathVariable Integer id) {
		 
		return ResponseEntity.ok(funcionariosService.getListContact(id)) ;
	}
	
	/*@Override
	@RequestMapping(value = "/baseall", method = RequestMethod.GET)
	public List<BaseDto<Empresas>> findBaseAll() {
		// TODO Auto-generated method stub
		return service().findBaseAll();
	}*/
	 
}
