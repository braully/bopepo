package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.leftPad;

import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.NumeroDaConta;

/**
 * @author muriloht
 */
public class BoletoInfoViewDaycoval extends AbstractBoletoInfoCampoView{

	BoletoInfoViewDaycoval(ResourceBundle resourceBundle, Boleto boleto) {
		super(resourceBundle, boleto);
	}

	@Override
	public String getTextoFcLocalPagamento() {
		String textoFcLocalPagamento = super.getTextoFcLocalPagamento();
		return isBlank(textoFcLocalPagamento) ? "ATÉ O VENCIMENTO PAGUE PREFERENCIALMENTE NO DAYCOVAL" : textoFcLocalPagamento;
	}

	@Override
	public String getTextoFcAgenciaCodigoCedente() {
		Agencia agencia = getBoleto().getTitulo().getContaBancaria().getAgencia();
		NumeroDaConta numeroDaConta = getBoleto().getTitulo().getContaBancaria().getNumeroDaConta();

		return leftPad(agencia.getCodigo().toString(), 5, "0")
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
		Agencia agencia = getBoleto().getTitulo().getContaBancaria().getAgencia();
		Carteira carteira = getBoleto().getTitulo().getContaBancaria().getCarteira();
		return leftPad(agencia.getCodigo().toString(), 5, "0")
				+ "/"
				+ leftPad(carteira.getCodigo().toString(), 3, "0")
				+ "/"
				+ getBoleto().getTitulo().getNossoNumero()
				+ "-" + getBoleto().getTitulo().getDigitoDoNossoNumero();
	}

	@Override
	public String getTextoRsNossoNumero() {
		//return getTextoFcNossoNumero();
		//Agencia agencia = getBoleto().getTitulo().getContaBancaria().getAgencia();
		//Carteira carteira = getBoleto().getTitulo().getContaBancaria().getCarteira();
		return getBoleto().getTitulo().getNossoNumero()
				+ "-" + getBoleto().getTitulo().getDigitoDoNossoNumero();
	}

	public String getTextoRsEnderecoCedente() {
		return getEnderecoBeneficiario();
	}
}
