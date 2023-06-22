package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.jrimum.utilix.Objects.isNotNull;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.parametro.ParametroCaixaEconomicaFederal;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.bopepo.view.AbstractBoletoInfoCampoView;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.TipoDeCobranca;

/**
 * View para o convênio SICOB, Nosso número com 10 dígitos, da CAIXA.
 *
 * @author Rômulo Augusto
 */
public class BoletoInfoViewCaixaSICOB10 extends AbstractBoletoInfoCampoView {

	public BoletoInfoViewCaixaSICOB10(ResourceBundle resourceBundle, Boleto boleto) {
		super(resourceBundle, boleto);
	}

	@Override
	public String getTextoFcCarteira() {
		String textoCarteira = EMPTY;
		Carteira carteira = getBoleto().getTitulo().getContaBancaria().getCarteira();
		if (isNotNull(carteira) && isNotNull(carteira.getTipoCobranca())) {
			textoCarteira = (carteira.getTipoCobranca().equals(TipoDeCobranca.COM_REGISTRO)) ? "CR" : "SR";
		}
		return textoCarteira;
	}

	@Override
	public String getTextoFcAgenciaCodigoCedente() {
		Integer agencia = getBoleto().getTitulo().getContaBancaria().getAgencia().getCodigo();
		Integer codigoOperacao = getBoleto().getTitulo().getParametrosBancarios().getValor(ParametroCaixaEconomicaFederal.CODIGO_OPERACAO);
		Integer codigoBeneficiario = getBoleto().getTitulo().getContaBancaria().getNumeroDaConta().getCodigoDaConta();
		String digitoDaConta = getBoleto().getTitulo().getContaBancaria().getNumeroDaConta().getDigitoDaConta();

		return String.format("%04d.%03d.%08d-%s", agencia, codigoOperacao, codigoBeneficiario, digitoDaConta);
	}

	public String getTextoRsEnderecoCedente() {
		return getEnderecoBeneficiario();
	}
}
