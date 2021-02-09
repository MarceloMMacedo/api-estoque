package br.com.apiestoque.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apiestoque.config.services.UserService;
import br.com.apiestoque.domain.pessoas.ContacFuncionarios;
import br.com.apiestoque.domain.pessoas.Funcionarios;
import br.com.apiestoque.dto.BaseDto;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.repository.ContactFuncionariosRepository;
import br.com.apiestoque.repository.FuncionariosRepository;
import br.com.apiestoque.repository.delete.EnderecoFuncRepository;
import br.com.apiestoque.security.UserSS;
import br.com.apiestoque.service.security.exceptions.AuthorizationException;

@Service
public class FuncionariosService
		extends ServiceImpl<Funcionarios> {

	private static final long serialVersionUID = 1L;

	@Autowired
	FuncionariosRepository repo;

	@Autowired
	EnderecoFuncRepository enderecoEmpreRepository;

	@Autowired
	ContactFuncionariosRepository contactFuncionariosRepository;
	  
	@Override
	public Funcionarios findByEmail(String email) {
		Funcionarios pessoa =repo.findByEmail(email);
		return pessoa;
	}
	public BaseDto findEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		 BaseDto baseDto= new BaseDto( );
		 Funcionarios f= repo.findByEmailAndStatus(user.getEmail(),  StatusActiv.ATIVO.getDescricao() );
		 f=SetImgSingle("avatar", "avatarView", f);
		 baseDto=new BaseDto(f.getId(), f.getName(), f.getCpfOnCnpj(),f.getAvatar(), f.getAvatarView(), 
				 f.getEmail() ); 
		return  baseDto;
	}
	/*contact*/
	@SuppressWarnings("unchecked")
	public List<ContacFuncionarios> getListContact( int id) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		List<ContacFuncionarios> contacreturn = new ArrayList<>();
	Funcionarios	pessoa = repo.findById(id).get();

		for (ContacFuncionarios contacFuncionarios2 : (List<ContacFuncionarios>) pessoa.getContatos()) {
			contacreturn.add(contacFuncionarios2);
		}
		return contacreturn;

	}

	public ContacFuncionarios saveContact(int id, ContacFuncionarios contact) {
		UserSS user = UserService.authenticated();
		Funcionarios	pessoa = repo.findById(id).get();
 
		try {
			contact.setPessoas(getClasse().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contact.getPessoas().setId(id);
		contact.setEmpresa(user.getEmpresa());
		contact = contactFuncionariosRepository.save(contact);
		return contact;
	}
	/*the end contact*/
}
