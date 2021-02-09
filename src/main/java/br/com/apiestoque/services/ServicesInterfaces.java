package br.com.apiestoque.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.dto.BaseDto;
import net.sf.jasperreports.engine.JRException;

public interface ServicesInterfaces<T extends BaseEntity> {

	Class<T> getClasse();

	public T find(Integer id);

	public T insert(T obj);

	public T update(T obj);

	public void delete(Integer id);

	public List<T> findAll();

	public T findByEmail(String email);

	public Page<T> findPage(String name, Integer page, Integer linesPerPage, String orderBy, String direction);

	// public List<> getPerfilsUser();

	public String uploadProfilePicture(MultipartFile multipartFile, Integer id, String fieldname);

	public List<T> findAllName(String name);

	public List<T> SetImg(String FieldImage, String FieldView, List<T> list);

	public T SetImgSingle(String FieldImage, String FieldView, T obj);

	public List<BaseDto> findBaseAll();

	public List<BaseDto> SetListBaseImg(String FieldImage, String FieldView, List<T> list);

	public byte[] ViewPdf() throws JRException, IOException;
}
