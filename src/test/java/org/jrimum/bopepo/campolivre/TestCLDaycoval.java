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

import static org.jrimum.bopepo.parametro.ParametroDaycoval.OPERACAO;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.NumeroDaConta;
import org.jrimum.domkee.banco.ParametrosBancariosMap;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Teste unitário do campo livre DAYCOVAL.
 * </p>
 *
 * @author muriloht
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class TestCLDaycoval extends AbstractCampoLivreBaseTest<CLDaycoval> {

	private final int NOSSO_NUMERO_LENGTH = 11;

	@Before
	public void setUp(){

		titulo.getContaBancaria().setBanco(BancosSuportados.DAYCOVAL.create());
		titulo.getContaBancaria().setAgencia(new Agencia(1,"9"));
		titulo.getContaBancaria().setCarteira(new Carteira(121));
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(734788));
		titulo.setNossoNumero("0084580106");
		titulo.setDigitoDoNossoNumero("0");
		titulo.setParametrosBancarios(new ParametrosBancariosMap(OPERACAO, 1537229));

		createCampoLivreToTest();

		setCampoLivreEsperadoComoString("0001121153722900845801060");
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteParametroBancarioNulo() {

		testeSeNaoPermiteParametroBancarioNulo();
	}

	@Test(expected = IllegalArgumentException.class)
	public void seNaoPermiteParametroBancarioCodigoDoConvenioSemValor() {

		testeSeNaoPermiteParametroBancarioSemValor(OPERACAO);
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
	public void seNaoPermiteCarteiraNull() {

		testeSeNaoPermiteCarteiraNula();
	}

	@Test(expected = CampoLivreException.class)
	public void seNaoPermiteCarteiraComCodigoNegativo() {

		testeSeNaoPermiteCarteiraComCodigoNegativo();
	}

}
