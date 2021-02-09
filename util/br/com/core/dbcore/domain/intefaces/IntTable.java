package br.com.core.dbcore.domain.intefaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.core.dbcore.EnderecoRepository;
import br.com.core.dbcore.FuncionariosRepository;
import br.com.core.dbcore.StatusActiv;
import br.com.core.dbcore.TipoPessoaEnum;
import br.com.core.dbcore.domain.pessoas.EnderecoFuncionarios;
import br.com.core.dbcore.domain.pessoas.Funcionarios;

@Service
public class IntTable {
 

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	FuncionariosRepository clientesRepository;
	@Autowired
	public BCryptPasswordEncoder encoder;

	public void intTable() {

		EnderecoFuncionarios endereco = new EnderecoFuncionarios();
		Funcionarios f = new Funcionarios();
		f.setTipo(TipoPessoaEnum.Funcionario.getDescricao());
		f.setName("M");
		f.setEmail("marcelo_macedo01@hotmail.com");
		f.setStatus(StatusActiv.ATIVO.getDescricao());
		f.setSenha(encoder.encode("123456"));
		f = clientesRepository.save(f);
		endereco.setPessoas(f);
		endereco = enderecoRepository.save(endereco);
		f.getEnderecos().add(endereco);
		clientesRepository.save(f);
		System.out.println(clientesRepository.findAll());
	}
}
