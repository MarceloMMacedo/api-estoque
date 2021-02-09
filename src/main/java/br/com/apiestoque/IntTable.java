package br.com.apiestoque;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.apiestoque.config.services.UserDetailsServiceImpl;
import br.com.apiestoque.domain.estoque.CategoriaProduto;
import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.domain.estoque.Patrimonio;
import br.com.apiestoque.domain.estoque.Produto;
import br.com.apiestoque.domain.financeiro.CentroCusto;
import br.com.apiestoque.domain.financeiro.ContasBanco;
import br.com.apiestoque.domain.financeiro.GrupoFinanceiroContrato;
import br.com.apiestoque.domain.financeiro.HistoricoPadraoSaida;
import br.com.apiestoque.domain.financeiro.contrato.Contrato;
import br.com.apiestoque.domain.pessoas.Clientes;
import br.com.apiestoque.domain.pessoas.ContacFuncionarios;
import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.domain.pessoas.EnderecoClientes;
import br.com.apiestoque.domain.pessoas.EnderecoEmpresas;
import br.com.apiestoque.domain.pessoas.EnderecoFuncionarios;
import br.com.apiestoque.domain.pessoas.Funcionarios;
import br.com.apiestoque.enumerador.SimNaoEnum;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.enumerador.TipoPessoaEnum;
import br.com.apiestoque.repository.CategoriaProdutoRepository;
import br.com.apiestoque.repository.ContactFuncionariosRepository;
import br.com.apiestoque.repository.EmpresasRepository;
import br.com.apiestoque.repository.FuncionariosRepository;
import br.com.apiestoque.repository.ModeloRepository;
import br.com.apiestoque.repository.PatrimonioRepository;
import br.com.apiestoque.repository.ProdutoRepository;
import br.com.apiestoque.repository.delete.CentroCustoRepository;
import br.com.apiestoque.repository.delete.ClientesRepository;
import br.com.apiestoque.repository.delete.ContasBancoRepository;
import br.com.apiestoque.repository.delete.ContratoRepository;
import br.com.apiestoque.repository.delete.EnderecoClientesRepository;
import br.com.apiestoque.repository.delete.EnderecoEmpreRepository;
import br.com.apiestoque.repository.delete.EnderecoFuncRepository;
import br.com.apiestoque.repository.delete.GrupoFinanceiroContratoRepository;
import br.com.apiestoque.repository.delete.HistoricoPadraoSaidaRepository;
import br.com.apiestoque.services.CategoriaProdutoService;
import br.com.apiestoque.services.ModeloService;
import br.com.apiestoque.services.ProdutoService;

@Service
public class IntTable {

	@Autowired
	ContasBancoRepository bancoRepository;

	@Autowired
	EnderecoFuncRepository enderecoRepository;

	@Autowired
	FuncionariosRepository funcionariosRepository;

	@Autowired
	EnderecoEmpreRepository enderecoEmpreRepository;

	@Autowired
	EmpresasRepository empresasRepository;

	@Autowired
	PatrimonioRepository patrimonioRepository;

	@Autowired
	ModeloRepository modeloRepository;

	@Autowired
	ClientesRepository clientesRepository;

	@Autowired
	EnderecoClientesRepository enderecoClientesRepository;

	@Autowired
	CentroCustoRepository centroCustoRepository;

	@Autowired
	HistoricoPadraoSaidaRepository historicoPadraoSaidaRepository;

	@Autowired
	GrupoFinanceiroContratoRepository grupoFinanceiroContratoRepository;

	@Autowired
	ContratoRepository contratoService;

	@Autowired
	UserDetailsServiceImpl impl;

	@Autowired
	ProdutoRepository produtoservice;

	@Autowired
	CategoriaProdutoRepository categoriaProdutoService;
	
	@Autowired
	ContactFuncionariosRepository contactFuncionariosRepository;
 

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void intTable() {

		Empresas e = new Empresas();
		e.setName("PAULA ANDREA GOMES MACEDO");
		e.setCpfOnCnpj("10380969000199");
		e.setAvatar("HEx1DWBKyf.png");
		e.setEmail("marcelo_macedo01@hotmail.com");
		e.setStatus(StatusActiv.ATIVO.getDescricao());
		e = empresasRepository.save(e);

		EnderecoEmpresas ed = new EnderecoEmpresas("R DELMIRO GOUVEIA", "63.050-216", "SALESIANOS", "JUAZEIRO DO NORTE",
				"1074", "", "CE", "ENDERECO PRINCIPAL", e);
		ed.setZipCode("63046460");
		ed.setPessoas(e);
		enderecoEmpreRepository.save(ed);
		e.setEnderecoPrincipal(ed);

		e = empresasRepository.save(e);

		ContasBanco banco = new ContasBanco();
		banco.setSaldo(15000);
		bancoRepository.save(banco);

		EnderecoFuncionarios endereco = new EnderecoFuncionarios();
		Funcionarios f = new Funcionarios();
		f.setTipo(TipoPessoaEnum.Funcionario.getDescricao());
		f.setName("M");
		f.setAvatar("HEx1DWBKyf.png");
		f.setEmpresa(e);
		java.util.List<String> list = new LinkedList<String>();
		list.add("Administrador Geral");
		f.setRolers("Administrador Geral");
		f.setEmail("marcelo_macedo01@hotmail.com");
		f.setStatus(StatusActiv.ATIVO.getDescricao());
		f.setSenha(encoder().encode("123456"));
		f = funcionariosRepository.save(f);
		endereco.setPessoas(f);
		endereco = enderecoRepository.save(endereco);
		f.setEnderecoPrincipal(endereco);
		funcionariosRepository.save(f);
		;
		ContacFuncionarios cf=new ContacFuncionarios();
		cf.setEmpresa(e);cf.setPessoas(f);
		cf.setEmail("marcelo_macedo01@hotmail.com");
		cf.setName("Marcelo M Macedo");
		contactFuncionariosRepository.save(cf);
		
		EnderecoClientes ec = new EnderecoClientes();
		Clientes c = new Clientes();
		c.setName("Cliente1");
		c.setAvatar("HEx1DWBKyf.png");
		c.setEmpresa(e);

		c.setEmail("marcelo_macedo01@hotmail.com");
		c.setStatus(StatusActiv.ATIVO.getDescricao());
		c.setSenha(encoder().encode("123456"));
		c = clientesRepository.save(c);
		ec.setPessoas(c);
		ec = enderecoClientesRepository.save(ec);
		c.setEnderecoPrincipal(ec);
		clientesRepository.save(c);

		impl.loadUserByUsername(c.getEmail());

		/*
		 * Modelo m = new Modelo("XERX X", "0Llgv2MxSy.jpg");
		 * 
		 * m = modeloRepository.save(m); m = new Modelo("XERX X1", "0Llgv2MxSy.jpg"); m
		 * = modeloRepository.save(m); m = new Modelo("XERX X2", "0Llgv2MxSy.jpg"); m =
		 * modeloRepository.save(m);
		 */

		CentroCusto centroCusto = new CentroCusto();
		centroCusto.setName("Centro1");
		centroCusto.setSaldo(12000.112);
		centroCustoRepository.save(centroCusto);

		GrupoFinanceiroContrato financeiroContrato = new GrupoFinanceiroContrato();
		financeiroContrato.setName("financeiroContrato");
		financeiroContrato.setCentrocusto(centroCusto);// entroCusto.setSaldo(120.112);
		grupoFinanceiroContratoRepository.save(financeiroContrato);

		Contrato contrato = new Contrato();
		contrato.setCliente(c);
		contrato.setFinanceiroContrato(financeiroContrato);
		contrato.setName("contrato 1");
		contrato.setImageContrato("5UkuhiWG1v.pdf");
		contrato.setIsFranquia("Não");
		contrato.setValor(1000);
		contrato.setStatus(StatusActiv.ABERTO.getDescricao());
		contrato = contratoService.save(contrato);
		// contratoService.gerarparcelascontrato(contrato.getId());

		HistoricoPadraoSaida historicoPadraoSaida = new HistoricoPadraoSaida();
		historicoPadraoSaida.setName("Coelce");
		historicoPadraoSaida.setCentrocusto(centroCusto);// entroCusto.setSaldo(120.112);
		historicoPadraoSaidaRepository.save(historicoPadraoSaida);

		CategoriaProduto ca = new CategoriaProduto();
		ca.setName("Categoria 1");
		categoriaProdutoService.save(ca);
		Modelo m = new Modelo("XERX X", "0Llgv2MxSy.jpg");
		m = modeloRepository.save(m);
		Produto p = new Produto();
		p.setAvatar("HEx1DWBKyf.png");
		p.setCategoriaproduto(ca);
		p.setModelo(m);
		produtoservice.save(p);

		
		


		ca = new CategoriaProduto();
		ca.setName("Categoria 2");
		categoriaProdutoService.save(ca);
		m = new Modelo("XERX X1", "0Llgv2MxSy.jpg");
		m = modeloRepository.save(m);
		p = new Produto();
		p.setAvatar("HEx1DWBKyf.png");
		p.setCategoriaproduto(ca);
		p.setModelo(m);
		produtoservice.save(p);

		ca = new CategoriaProduto();
		ca.setName("Categoria 3");
		categoriaProdutoService.save(ca);
		m = new Modelo("XERX X2", "0Llgv2MxSy.jpg");
		m = modeloRepository.save(m);
		p = new Produto();
		p.setAvatar("HEx1DWBKyf.png");
		p.setCategoriaproduto(ca);
		p.setModelo(m);
		produtoservice.save(p);

		Patrimonio pat = new Patrimonio();
		pat.setAvatar("0Llgv2MxSy.jpg");
		pat.setImagepatrimonio("fWXDA3oXVU.jpg");
		pat.setModelo(m);
		pat.setStatus(StatusActiv.ATIVO.getDescricao());
		pat.setStatuslocacao(SimNaoEnum.Nao.getDescricao());
		pat.setName("new Patrimonio");
		patrimonioRepository.save(pat);
	}
}
