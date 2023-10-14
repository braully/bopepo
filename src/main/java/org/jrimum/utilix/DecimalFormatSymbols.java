/*
 * Copyright 2010 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 01/08/2010 - 21:30:00
 * 
 * ================================================================================
 * 
 * Direitos autorais 2010 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 01/08/2010 - 21:30:00
 * 
 */

package org.jrimum.utilix;

import java.util.Currency;

import org.jrimum.utilix.Locales;

/**
 * <p>
 * DecimalFormatSymbols com localização (Processo de localização) inicialmente
 * do Brasil.
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public enum DecimalFormatSymbols {

	/**
	 * DecimalFormatSymbols relacionados aos padrões pt-BR.
	 */
	BRASIL;

	private final ThreadLocal<java.text.DecimalFormatSymbols> DF_SYMBOLS;

	private DecimalFormatSymbols() {

		DF_SYMBOLS = new ThreadLocal<java.text.DecimalFormatSymbols>() {

			protected java.text.DecimalFormatSymbols initialValue() {

				java.text.DecimalFormatSymbols dfSymbols = new java.text.DecimalFormatSymbols(
						Locales.BRASIL);

				dfSymbols.setZeroDigit('0');
				dfSymbols.setDecimalSeparator(',');
				dfSymbols.setMonetaryDecimalSeparator(',');
				dfSymbols.setDigit('#');
				dfSymbols.setGroupingSeparator('.');
				dfSymbols.setCurrency(Currency.getInstance(Locales.BRASIL));

				return dfSymbols;
			}

		};
	}

	/**
	 * <p>
	 * Cópia de símbolos utilizados em <code>BRASIL</code>.
	 * </p>
	 * 
	 * @return símbolos - Instância de
	 *         <code>java.text.DecimalFormatSymbols</code> estilo pt-BR
	 * 
	 * @since 0.2
	 */
	public java.text.DecimalFormatSymbols copy() {

		return (java.text.DecimalFormatSymbols) DF_SYMBOLS.get().clone();
	}
}
