package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.parametro.ParametroBancoSofisa;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.NumeroDaConta;
import org.jrimum.domkee.banco.ParametrosBancariosMap;
import org.junit.Before;

/**
 * <p>
 * Teste unitário do campo livre do Banco Sofisa.
 * </p>
 *
 * @author <a href="https://github.com/lucassolerne">Lucas Solerne</a>
 *
 */
public class TestCLBancoSofisa extends AbstractCampoLivreBaseTest<CLBancoSofisa> {

    @Before
    public void setUp() {

        titulo.getContaBancaria().setBanco(BancosSuportados.BANCO_SOFISA.create());
        titulo.getContaBancaria().setAgencia(new Agencia(0004, "3"));
        titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(1234567));
        titulo.getContaBancaria().setCarteira(new Carteira(121));
        titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroBancoSofisa.OPERACAO, 1234567));
        titulo.setNossoNumero("1234567890");
        titulo.setDigitoDoNossoNumero("0");

        createCampoLivreToTest();

        // campo livre = agência(4) + carteira(3) + operação(7) + nosso número + DV(11)
        // = 0004 + 121 + 1234567 + 12345678900 = 25 dígitos
        setCampoLivreEsperadoComoString("0004121123456712345678900");
    }
}
