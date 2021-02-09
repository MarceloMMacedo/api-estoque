package br.com.apiestoque.security;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.apiestoque.domain.pessoas.Empresas;
import br.com.apiestoque.enumerador.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String senha;
	private Empresas empresa;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String senha, Empresas empresa,  String  perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.empresa = empresa; 
		Collection c = new LinkedList<>();
		Perfil p =Perfil.findById(perfis);
		c.add(new SimpleGrantedAuthority(p.getId()));
		 
		this.authorities =c;// perfis.stream().map(x -> new SimpleGrantedAuthority(x.getId())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}

	/*
	 * public boolean hasRole(Perfil perfil) { return getAuthorities().contains(new
	 * SimpleGrantedAuthority(perfil.getDescricao())); }
	 */

	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
}
