package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.leftPad;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.bopepo.view.AbstractBoletoInfoCampoView;
import org.jrimum.bopepo.view.BoletoInfoCampoView;

/**
 * Define campos iguais para as cobranças registrada e não registrada.
 * @author Rômulo Augusto
 */
public class AbstractBoletoInfoViewHSBC extends AbstractBoletoInfoCampoView {

	public static BoletoInfoCampoView create(ResourceBundle resourceBundle, Boleto boleto) {
		if (boleto.getTitulo().getContaBancaria().getCarteira().isComRegistro()) {
			return new BoletoInfoViewHSBCRegistrada(resourceBundle, boleto);
		}
		return new BoletoInfoViewHSBCNaoRegistrada(resourceBundle, boleto);
	}

	public AbstractBoletoInfoViewHSBC(ResourceBundle resourceBundle, Boleto boleto) {
		super(resourceBundle, boleto);
	}

	@Override
	public String getTextoFcLocalPagamento() {
		String textoFcLocalPagamento = super.getTextoFcLocalPagamento();
		return isBlank(textoFcLocalPagamento) ? "PAGAR PREFERENCIALMENTE EM AGÊNCIA DO HSBC" : textoFcLocalPagamento;
	}

	@Override
	public String getTextoFcAgenciaCodigoCedente() {
		return leftPad(getBoleto().getTitulo().getContaBancaria().getNumeroDaConta().getCodigoDaConta().toString(), 7, "0");
	}

	@Override
	public String getTextoFcNossoNumero() {
		return getBoleto().getTitulo().getNossoNumero() + getBoleto().getTitulo().getDigitoDoNossoNumero();
	}

	@Override
	public String getTextoRsNossoNumero() {
		return getTextoFcNossoNumero();
	}

	public String getTextoRsEnderecoCedente() {
		return getEnderecoBeneficiario();
	}
}
