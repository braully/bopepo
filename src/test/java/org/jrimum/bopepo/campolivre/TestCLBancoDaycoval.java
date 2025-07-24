package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.parametro.ParametroBancoDaycoval;
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
 * @author <a href="https://github.com/alessandrobrunolima">Alessandro Bruno
 *         Lima</a>
 * 
 */
public class TestCLBancoDaycoval extends AbstractCampoLivreBaseTest<CLBancoDaycoval> {

	@Before
	public void setUp() {

		titulo.getContaBancaria().setBanco(BancosSuportados.BANCO_DAYCOVAL.create());
		titulo.getContaBancaria().setAgencia(new Agencia(0004, "3"));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(6002006));
		titulo.getContaBancaria().setCarteira(new Carteira(121));
		titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroBancoDaycoval.OPERACAO, 1234567));
		titulo.setNossoNumero("1234567890");
		titulo.setDigitoDoNossoNumero("0");

		createCampoLivreToTest();

		setCampoLivreEsperadoComoString("0004121123456712345678900");
	}

}
