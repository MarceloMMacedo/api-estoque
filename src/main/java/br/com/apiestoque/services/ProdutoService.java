package br.com.apiestoque.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.apiestoque.config.services.UserService;
import br.com.apiestoque.domain.estoque.CategoriaProduto;
import br.com.apiestoque.domain.estoque.FornecedorProduto;
import br.com.apiestoque.domain.estoque.Modelo;
import br.com.apiestoque.domain.estoque.Produto;
import br.com.apiestoque.domain.pessoas.ContacFuncionarios;
import br.com.apiestoque.domain.pessoas.Fornecedores;
import br.com.apiestoque.domain.pessoas.Funcionarios;
import br.com.apiestoque.dto.BaseDto;
import br.com.apiestoque.dto.ProdutoDto;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.mail.storage.EmailProperties;
import br.com.apiestoque.repository.ProdutoRepository;
import br.com.apiestoque.security.UserSS;
import br.com.apiestoque.service.security.exceptions.AuthorizationException;
import net.sf.jasperreports.engine.JRException;

@Service
public class ProdutoService extends ServiceImpl<Produto> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoRepository repo;

	@Autowired
	ModeloService serviceModelo;

	@Autowired
	CategoriaProdutoService sevicecategorio;

	@Autowired
	FornecedorProdutoService seviceFornecedorProduto;


	@Autowired
	FornecedoresService fornecedoresService;
	
	@Override
	public JpaRepository<Produto, Integer> repo() {
		return repo;
	}

	/**
	 * insert picture of product
	 */
	@Override
	public String uploadProfilePicture(MultipartFile file, Integer id, String fieldname) {
		return super.uploadProfilePicture(file, id, fieldname);
	}

	 

	/**
	 * @return list all products
	 */
	@Override
	public List<Produto> findAll() {
		return super.SetImg("avatar", "avatarView", super.findAll());
	}

	/**
	 * @return find only pruduct by id
	 */
	@Override
	public Produto find(Integer id) {
		// TODO Auto-generated method stub
		return super.SetImgSingle("avatar", "avatarView", super.find(id));
	}

	/**
	 * @return list all product in format dto
	 */
	@Override
	public List<BaseDto> findBaseAll() {
		List<BaseDto> baseDto = new ArrayList<>();
		for (Produto produto : findAll()) {

			baseDto.add(new BaseDto(produto.getId(), produto.getName(), produto.getSaldo(), produto.getAvatarView(),
					produto.getStatus(), produto.getValorinterno()));
		}
		return baseDto;
	}

	public List<ProdutoDto> getAllprodutos() {
		List<ProdutoDto> baseDto = new ArrayList<>();
		for (Produto produto : findAll()) {

			baseDto.add(new ProdutoDto(produto.getId(), produto.getName(), produto.getSaldo(), produto.getAvatarView(),
					produto.getStatus(), produto.getModelo().getName(), produto.getCategoriaproduto().getName(),
					produto.getTipoproduto(), produto.getValorinterno()));
		}
		return baseDto;
	}

	/**
	 * @return insert new product
	 */
	@Override
	public Produto insert(Produto obj) {
		obj.setStatus(StatusActiv.ABERTO.name());
		obj.setDataEntrada(new Date());
		return super.insert(obj);
	}

	@Override
	public byte[] ViewPdf() throws JRException, IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<?> source = new ArrayList<>();
		String templates = "";

		return filesService.ViewPdf(parameters, getAllprodutos(), "produtos");
	}

	@Override
	public void sendemailreport(EmailProperties properties) {
		UserSS user = UserService.authenticated();
		Map<String, Object> parameters = new HashMap<String, Object>();
		properties.setFrom(user.getEmail());
		String templates = "produtos";
		htmlEmailService.sendemailreport(properties, templates, parameters, getAllprodutos());
	}

	/* modelos */
	/**
	 * @param obj
	 * @return new model product
	 */
	public Modelo insertmodelo(Modelo obj) {
		return serviceModelo.insert(obj);
	}

	/**
	 * @return all models
	 */
	public List<Modelo> modelos() {
		return serviceModelo.findAll();
	}

	public boolean ismodelo(String name) {
		List<Modelo> modelo = serviceModelo.findAllName(name);
		if (modelo.size() > 0)
			return true;
		return false;
	}
	/* Fim Modelos */

	/* Categorias produto */
	/**
	 * @param obj
	 * @return new product category
	 */
	public CategoriaProduto insertcategoriaproduto(CategoriaProduto obj) {
		return sevicecategorio.insert(obj);
	}

	public List<CategoriaProduto> allcategoriaprodutos() {
		return sevicecategorio.findAll();
	}

	public boolean iscategoriaproduto(String name) {
		List<CategoriaProduto> modelo = sevicecategorio.findAllName(name);
		if (modelo.size() > 0)
			return true;
		return false;
	}
	/* fim Categorias produto */

	/* FornecedorProduto */
	/**
	 * @param obj
	 * @return new provider of product
	 */
	public FornecedorProduto insertFornecedorProduto(FornecedorProduto obj  ) {
		return seviceFornecedorProduto.insert(obj);
	}

	/**
	 * @param id delete provider of product
	 */
	public void deleteFornecedorProduto(Integer id) {
		seviceFornecedorProduto.delete(id);

	}

	/**
	 * @return list of providers of product
	 */
	public List<BaseDto> fornecedores() {
		return fornecedoresService.findBaseAll();
	}
	
	/**
	 * @param fornecedorProduto
	 * @return
	 */
	public FornecedorProduto update(FornecedorProduto fornecedorProduto ) {
		return seviceFornecedorProduto.update(fornecedorProduto);
	}
	/* the end FornecedorProduto */

}
