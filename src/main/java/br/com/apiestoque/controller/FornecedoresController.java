package br.com.apiestoque.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apiestoque.domain.pessoas.Fornecedores;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.services.FornecedoresService;
import br.com.apiestoque.services.ServiceImpl;
 

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedoresController extends PessoaBaseController<Fornecedores> {

	private static final long serialVersionUID = 1L;

	@Autowired
	FornecedoresService FornecedoresService;

	 @Override
	public ServiceImpl<Fornecedores> service() {
		// TODO Auto-generated method stub
		return FornecedoresService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	public ResponseEntity<Integer> insert(Fornecedores objDto) {
		objDto = FornecedoresService.insert(objDto);
		objDto.setStatus(StatusActiv.ATIVO.getDescricao());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDto.getId()).toUri();
		return ResponseEntity.created(uri).body(objDto.getId());
	}

	/*@Override
	@RequestMapping(value = "/baseall", method = RequestMethod.GET)
	public List<BaseDto<Empresas>> findBaseAll() {
		// TODO Auto-generated method stub
		return service().findBaseAll();
	}*/
}
