package br.com.apiestoque.controller;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.dto.BaseDto;

public class PessoaBaseController<T extends BaseEntity> extends ControllerImp<T> {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	@Override
	public Class<T> getClasse() {
		Class<T> classe = null;
		try {
			Class<T> class1 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			classe = class1;
		} catch (Exception e) {
		}
		return classe;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/{id}/picture", method = RequestMethod.POST)
	public ResponseEntity<String> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file,
			@PathVariable Integer id) throws IOException {
		String s = service().uploadProfilePicture(file, id, "avatar");
		return ResponseEntity.ok().body(s);

	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Validated @RequestBody T objDto, @PathVariable Integer id) {
		T obj = service().update(objDto); // obj.setId(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST'  )")
	@RequestMapping(value = "/baseall", method = RequestMethod.GET)
	public ResponseEntity<List<BaseDto>> findBaseAll() {
		 return ResponseEntity.ok(   service().findBaseAll());
	}
	@Override
	public ResponseEntity<T> findemail(String email) {
		// TODO Auto-generated method stub
		return super.findemail(email);
	} 
}
