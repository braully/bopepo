/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:22:11
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:22:11
 * 
 */

package org.jrimum.vallia;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Lógica de cálculo do dígito verificador do código de barras de um boleto.
 * A lógica funciona da seguinte forma:
 * </p>
 * <p>
 * Utilizando-se o módulo 11, considerando-se os 43 dígitos que compõem o código
 * de barras, já excluída a 5ª posição (posição do dígito verificador), segue-se
 * o procedimento abaixo:
 * </p>
 * <p>
 * Calcula-se o dígito verificador através da expressão <code>DV = 11 - R</code>
 * , onde R é o resultado do cálculo do módulo.
 * Observação: O dígito verificador será 1 para os restos (resultado do módulo):
 * 0 , 10 ou 1 (zero, dez, um).
 * </p>
 * <p>
 * Obs.: A rotina de módulo utilizada é o módulo 11.
 * </p>
 * 
 * @see Modulo
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @version 0.2
 * 
 * @since 0.2
 */
public class BoletoCodigoDeBarrasDV extends AbstractDigitoVerificador {

	private static final long serialVersionUID = 7977220668336110040L;

	private static final int TAMANHO_SEM_DV = 43;

	private static final Modulo modulo11 = new Modulo(TipoDeModulo.MODULO11);

	/**
	 * <p>
	 * Construtor vaizo, o mesmo que super().
	 * </p>
	 * 
	 * @since 0.2
	 */
	public BoletoCodigoDeBarrasDV() {

		super();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(String)
	 * @since 0.2
	 */
	@Override
	public int calcule(String numero) throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;

		if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)
				&& (numero.length() == TAMANHO_SEM_DV)) {

			// Realizando o cálculo do dígito verificador utilizando módulo 11.
			// Obtendo o resto da divisão por 11.
			resto = modulo11.calcule(numero);

			// Seguindo as especificações da FEBRABAN, caso o resto seja
			// (0), (1) ou (10), será atribuído (1) ao digito verificador.
			if ((resto == 0) || (resto == 1) || (resto == 10))
				dv = 1;
			// Caso contrário, dv = 11 - resto.
			else
				dv = modulo11.valor() - resto;

		} else {
			throw new IllegalArgumentException("O código de barras " + "[ "
					+ numero + " ] deve conter apenas números e "
					+ TAMANHO_SEM_DV + " caracteres.");
		}

		return dv;
	}

}
