package br.com.apiestoque.domain.financeiro.movimento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import br.com.apiestoque.domain.financeiro.HistoricoPadraoSaida;
import br.com.apiestoque.domain.intefaces.BaseEntity;
import br.com.apiestoque.enumerador.StatusActiv;
import br.com.apiestoque.enumerador.TipoMovimentoEnum;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MovimentoSaida extends MovimentoFinanceiro implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	public MovimentoSaida(String tipomovimento) {
		super(tipomovimento);
	}

	public MovimentoSaida(int id, int parcelas) {
		super(id, parcelas);
	}

	public MovimentoSaida(int parcelas, HistoricoPadraoSaida hs, double valor) {
		super();
		setHistorico(hs);
		setName(hs.getName());
		setParcela(parcelas);
		setTipomovimento(TipoMovimentoEnum.Saida.getDescricao());
		setStatus(StatusActiv.ABERTO.getDescricao());
		setDataVencimento(new Date());
		setValor(valor);
	}
}
