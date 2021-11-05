/*
 * Copyright 2014 JRimum Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created at: 21/01/2014 - 14:48:31
 *
 * ================================================================================
 *
 * Direitos autorais 2014 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões
 * e limitações sob esta LICENÇA.
 *
 * Criado em: 21/01/2014 - 14:48:31
 *
 */

package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Teste unitário do campo livre Banco B2S.
 * </p>
 *
 * @author jdalfre
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class TestCLBancoB2s extends AbstractCampoLivreBaseTest<CLBancoB2s> {

	private final int NOSSO_NUMERO_LENGTH = 10;

	@Before
	public void setUp(){

		titulo.getContaBancaria().setBanco(BancosSuportados.BANCO_B2S.create());
		titulo.getContaBancaria().setAgencia(new Agencia(001, "0"));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(111111));
		titulo.getContaBancaria().setCarteira(new Carteira(21));
		titulo.setNossoNumero("9000000001");
		titulo.setDigitoDoNossoNumero("4");

		createCampoLivreToTest();

		setCampoLivreEsperadoComoString("0010000111111900000000148");
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteAgenciaNula() {

		testeSeNaoPermiteAgenciaNula();
	}

	@Test(expected = IllegalArgumentException.class)
	public void seNaoPermiteAgenciaComCodigoNegativo() {

		testeSeNaoPermiteAgenciaComCodigoNegativo();
	}


	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNumeroDaAgenciaAcimaDe4Digitos() {

		testeSeNaoPermiteNumeroDaAgenciaComDigitosAcimaDoLimite(10000);
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteCarteiraNull() {

		testeSeNaoPermiteCarteiraNula();
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteCarteiraComCodigoNegativo() {

		testeSeNaoPermiteCarteiraComCodigoNegativo();
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteCarteiraComCodigoAcimaDe2Digitos() {

		testeSeNaoPermiteCarteiraComCodigoAcimaDoLimite(111);
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNossoNumeroNulo() {

		testeSeNaoPermiteNossoNumeroNulo();
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNossoNumeroComBrancos() {

		testeSeNaoPermiteNossoNumeroComBrancos(NOSSO_NUMERO_LENGTH);
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNossoNumeroComEspacos() {

		testeSeNaoPermiteNossoNumeroComEspacos(NOSSO_NUMERO_LENGTH);
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNossoNumeroComTamanhoDiferenteDe11() {

		testeSeNaoPermiteNossoNumeroComTamanhoDiferenteDoEspecificado(NOSSO_NUMERO_LENGTH - 1);
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNumeroDaContaNulo() {

		testeSeNaoPermiteNumeroDaContaNulo();
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteNumeroDaContaComCodigoNegativo() {

		testeSeNaoPermiteNumeroDaContaComCodigoNegativo();
	}

}
