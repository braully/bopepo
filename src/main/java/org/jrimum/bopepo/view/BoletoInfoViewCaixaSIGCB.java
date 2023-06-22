package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.isBlank;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.ResourceBundle;
import org.jrimum.bopepo.view.AbstractBoletoInfoCampoView;

/**
 * View para o convênio SIGCB da CAIXA.
 *
 * @author Rômulo Augusto
 */
public class BoletoInfoViewCaixaSIGCB extends AbstractBoletoInfoCampoView {

    private static final String EMISSAO_BENEFICIARIO = "4";

    public BoletoInfoViewCaixaSIGCB(ResourceBundle resourceBundle, Boleto boleto) {
        super(resourceBundle, boleto);
    }

    @Override
    public String getTextoFcNossoNumero() {
        return getBoleto().getTitulo().getContaBancaria().getCarteira().getCodigo()
                + EMISSAO_BENEFICIARIO
                + super.getTextoFcNossoNumero();
    }

    @Override
    public String getTextoFcCarteira() {
        return (getBoleto().getTitulo().getContaBancaria().getCarteira().isComRegistro()) ? "RG" : "SR";
    }

    @Override
    public String getTextoFcLocalPagamento() {
        String textoFcLocalPagamento = super.getTextoFcLocalPagamento();
        return isBlank(textoFcLocalPagamento) ? "PREFERENCIALMENTE NAS CASAS LOTÉRICAS ATÉ O VALOR LIMITE" : textoFcLocalPagamento;
    }

	public String getTextoRsEnderecoCedente() {
		return getEnderecoBeneficiario();
}
}
