package br.com.apiestoque.domain.estoque;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data
@NoArgsConstructor
public class AnuncioMaoObra    extends Produto implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valor;
 


}
