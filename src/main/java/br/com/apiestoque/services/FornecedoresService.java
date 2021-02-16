package br.com.apiestoque.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.config.services.UserService;
import br.com.apiestoque.domain.pessoas.Fornecedores;
import br.com.apiestoque.dto.BaseDto;
import br.com.apiestoque.enumerador.TipoFornecedorEnum;
import br.com.apiestoque.repository.ContactFornecedoresRepository;
import br.com.apiestoque.repository.EnderecoFornecedoresRepository;
import br.com.apiestoque.repository.FornecedoresRepository;
import br.com.apiestoque.security.UserSS;
import br.com.apiestoque.service.security.exceptions.AuthorizationException;

@Service
public class FornecedoresService extends ServiceImpl<Fornecedores> {

	private static final long serialVersionUID = 1L;

	@Autowired
	FornecedoresRepository repo;

	@Autowired
	EnderecoFornecedoresRepository enderecoEmpreRepository;

	@Autowired
	ContactFornecedoresRepository contactEmpreRepository;

	@Override
	public JpaRepository<Fornecedores, Integer> repo() {
		return repo;
	}

	@Override
	public List<Fornecedores> findAll() {
		// TODO Auto-generated method stub
		return SetImg("avatar", "avatarView", repo.findAllByTipofornecedor(TipoFornecedorEnum.Produtos.getDescricao()));
	}

	@Override
	public List<BaseDto> findBaseAll() {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		List<BaseDto> baseDto = new ArrayList<>();
		for (Fornecedores t : findAll()) {
			baseDto.add(new BaseDto(t));
		}
		return baseDto;
	}

	@Override
	public Fornecedores insert(Fornecedores obj) {
		obj.setTipofornecedor(TipoFornecedorEnum.Produtos.getDescricao());
		return super.insert(obj);
	}
}
