package org.jrimum.bopepo.view.info.campo;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.leftPad;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;

/**
 * @author jdalfre
 */
public class BoletoInfoViewBancoB2s extends AbstractBoletoInfoCampoView{

	BoletoInfoViewBancoB2s(ResourceBundle resourceBundle, Boleto boleto) {
		super(resourceBundle, boleto);
	}

	@Override
	public String getTextoFcLocalPagamento() {
		String textoFcLocalPagamento = super.getTextoFcLocalPagamento();
		return isBlank(textoFcLocalPagamento) ? "PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO" : textoFcLocalPagamento;
	}

	@Override
	public String getTextoFcAgenciaCodigoCedente() {
		Agencia agencia = getBoleto().getTitulo().getContaBancaria().getAgencia();
		NumeroDaConta numeroDaConta = getBoleto().getTitulo().getContaBancaria().getNumeroDaConta();

		return leftPad(agencia.getCodigo().toString(), 4, "0")
				+ "-" + agencia.getDigitoVerificador()
				+ " / "
				+ leftPad(numeroDaConta.getCodigoDaConta().toString(), 7, "0")
				+ "-" + numeroDaConta.getDigitoDaConta();
	}

	@Override
	public String getTextoRsAgenciaCodigoCedente() {
		return getTextoFcAgenciaCodigoCedente();
	}

	@Override
	public String getTextoFcNossoNumero() {
		return getBoleto().getTitulo().getNossoNumero()
				+ "-" + getBoleto().getTitulo().getDigitoDoNossoNumero();
	}

	@Override
	public String getTextoRsNossoNumero() {
		return getTextoFcNossoNumero();
	}

	public String getTextoRsEnderecoCedente() {
		return getEnderecoBeneficiario();
	}
}
