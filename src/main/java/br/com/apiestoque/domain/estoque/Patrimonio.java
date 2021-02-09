package br.com.apiestoque.domain.estoque;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.converters.SimNaoConverter;
import br.com.apiestoque.converters.StatusConverter;
import br.com.apiestoque.converters.TipoPatrimonioConverter;
import br.com.apiestoque.domain.financeiro.contrato.Contrato;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.pessoas.Empresas;
import lombok.Data;

@Data
@Entity
public class Patrimonio implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	private String name;

	@Convert(converter = TipoPatrimonioConverter.class)
	private String tipoPatrimonio;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Modelo modelo;

	private String codPatrimonio;

	private String serial;
	
	@Convert(converter = StatusConverter.class)
	private String status;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataAquisicao;

	private int medidorInstalacao;

	private Integer madidorremocao;

	private int medidorManutencao;

	private int medidorContrato;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataInstalacao;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Contrato contrato;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnore
	private Empresas empresa;

	private String avatar;

	@Transient
	private String avatarView;

	private String imagepatrimonio;

	@Transient
	private String imagepatrimonioView;

	@Convert(converter = SimNaoConverter.class)
	private String statuslocacao;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	private Date dataInatividade;

	private String motivoDescarte;

	//
	// Constructors
	//
	public Patrimonio() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patrimonio other = (Patrimonio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patrimonio [name=" + name + "]";
	}
}
