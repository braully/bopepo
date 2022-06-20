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
 * Created at: 30/03/2008 - 18:22:21
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
 * Criado em: 30/03/2008 - 18:22:21
 * 
 */

package org.jrimum.vallia;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;


public class GuiaLinhaDigitavelDV extends AbstractDigitoVerificador implements Serializable {

	private static final long serialVersionUID = -1093744246886652909L;

	/**
	 *<p>
	 * Módulo utilizado no cálculo.
	 * </p>
	 */
	private Modulo modulo;

	/**
	 *<p>
	 * Construtor vaizo, o mesmo que super().
	 * </p>
	 * 
	 * @since 0.3
	 */
	public GuiaLinhaDigitavelDV(Modulo modulo) {
		super();
		this.modulo = modulo;
	}

	/**
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
	 * @since 0.3
	 */
	@Override
	public int calcule(String numeroStr) throws IllegalArgumentException {

		int dv = 0;
		int resto = 0;

		if (
			 StringUtils.isNotBlank(numeroStr)
			 && StringUtils.isNumeric(numeroStr)
			 && (numeroStr.length() == 11)
		   ) {

			resto = modulo.calcule(numeroStr);
			
			
			if (modulo.getMod() == TipoDeModulo.MODULO11) {
				// Seguindo as especificações da FEBRABAN, caso o resto seja
				// (0), (1) ou (10), será atribuído (1) ao digito verificador.			
				if ((resto == 0) || (resto == 1) || (resto == 10))
					dv = 1;
				// Caso contrário, dv = 11 - resto.
				else
					dv = modulo.valor() - resto;
			}
			
			else if (modulo.getMod() == TipoDeModulo.MODULO10) {
				// Seguindo as especificações da FEBRABAN, caso o resto seja
				// (0) ou (10), será atribuido o valor zero.
				if (  (resto == 0) || (resto == 10)  )
					dv = 0;
				// Caso contrário, dv = 10 - resto.
				else
					dv = modulo.valor() - resto;
			}			
		} else
			throw new IllegalArgumentException(
					"O campo [ " + numeroStr + " ] da linha digitável deve " +
					"conter apenas números, com exatamento 11 dígitos, para que " +
					"o cálculo do dígito verificador possa ser executado !");

		return dv;
	}

}
