package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.leftPad;

import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.NumeroDaConta;

public class BoletoInfoViewABCBrasil extends AbstractBoletoInfoCampoView {

    BoletoInfoViewABCBrasil(ResourceBundle resourceBundle, Boleto boleto) {
        super(resourceBundle, boleto);
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

    public String getTextoRsEnderecoCedente() {
        return getEnderecoBeneficiario();
    }
}
