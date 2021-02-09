package br.com.apiestoque.domain.estoque;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;

/**
 * Class Modelo
 */
@Entity
public class Modelo implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String name;
	
	private String avatar;
	
	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnore
	private Empresas empresa;
	
	@Transient
	private String avatarView;

	//
	// Constructors
	//
	public Modelo() {
	};

	//
	// Methods
	//

	//
	// Accessor methods
	//

	public Modelo(String name, String avatar) {
		super();
		this.name = name;
		this.avatar = avatar;
	}

	/**
	 * Set the value of id
	 * 
	 * @param newVar the new value of id
	 */
	@Override
	public void setId(Integer newVar) {
		id = newVar;
	}

	/**
	 * Get the value of id
	 * 
	 * @return the value of id
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * Set the value of name
	 * 
	 * @param newVar the new value of name
	 */
	public void setName(String newVar) {
		name = newVar;
	}

	/**
	 * Get the value of name
	 * 
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of avatar
	 * 
	 * @param newVar the new value of avatar
	 */
	public void setAvatar(String newVar) {
		avatar = newVar;
	}

	/**
	 * Get the value of avatar
	 * 
	 * @return the value of avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Set the value of avatarView
	 * 
	 * @param newVar the new value of avatarView
	 */
	public void setAvatarView(String newVar) {
		avatarView = newVar;
	}

	/**
	 * Get the value of avatarView
	 * 
	 * @return the value of avatarView
	 */
	public String getAvatarView() {
		return avatarView;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}
 

	//
	// Other methods
	//

}
