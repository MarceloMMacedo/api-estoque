package br.com.apiestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.apiestoque.domain.pessoas.ContacFornecedores;
import br.com.apiestoque.domain.pessoas.EnderecoFornecedores;
import br.com.apiestoque.domain.pessoas.Fornecedores;
import br.com.apiestoque.repository.ContactFornecedoresRepository;
import br.com.apiestoque.repository.EnderecoFornecedoresRepository;
import br.com.apiestoque.repository.FornecedoresRepository;

@Service
public class FornecedoresService
		extends ServiceImpl< Fornecedores> {

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

  

}
