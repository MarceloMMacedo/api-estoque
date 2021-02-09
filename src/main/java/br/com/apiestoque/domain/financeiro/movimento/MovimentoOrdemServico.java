package br.com.apiestoque.domain.financeiro.movimento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.domain.ordemservico.OrdemServico;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.enumerador.TipoMovimentoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor
@Getter
@Setter
public class MovimentoOrdemServico extends MovimentoFinanceiro implements BaseEntity, Serializable {
	

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(columnDefinition = " int")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private OrdemServico os;
	
	public MovimentoOrdemServico(String tipomovimento) {
		super(tipomovimento);
	}

	public MovimentoOrdemServico(int id,int parcelas) {
		super(id, parcelas);
	}

	
	public MovimentoOrdemServico(int parcelas, OrdemServico os, double valor) {
		super(); 
		setName("Ordem Serviço - " + String.valueOf(os.getId()));
		setParcela(1);
		this.os=os;
		setTipomovimento(TipoMovimentoEnum.EntradaOrdemServico.getDescricao());
		setStatus(StatusActiv.ABERTO.getDescricao());
		setDataVencimento(new Date());
		setValor(valor);
	}
}
