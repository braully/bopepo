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
 * Created at: 14/02/2010 - 15:00:00
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
 * Criado em: 14/02/2010 - 15:00:00
 * 
 */

package org.jrimum.domkee.banco;

import static java.lang.String.format;

import org.jrimum.utilix.Exceptions;

/**
 * 
 * <p>
 * Tipo do identificador do nosso número utilizado nos títulos de boletos
 * bancários de Cobrança Não Registrada (CNR).
 * </p>
 * 
 * <p>
 * Basicamente são dois os tipos de nosso número (NN):
 * </p>
 * 
 * <ul>
 * <li>O que vincula “vencimento”, “código do cedente” e “código do documento”.
 * Enumerado como {@link #COM_VENCIMENTO } (constante 4);</li>
 * <li>O que vincula “código do cedente” e “código do documento”. Enumerado como
 * {@link #SEM_VENCIMENTO } (constante 5).</li>
 * </ul>
 * 
 * <p>
 * <strong>OBS:</strong> vale lembrar que é o tipo do identificador que
 * determina se o boleto HSBC CNR tem vencimento ou se é do tipo com vencimento
 * “À Vista” ou “Contra Apresentação”.
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

public enum TipoIdentificadorCNR {
	
	/**
	 * Identificador do <strong>"tipo 4"</strong> que vincula “vencimento”,
	 * “código do cedente” e “código do documento”.
	 * 
	 * @since 0.2
	 */
	COM_VENCIMENTO {
		/**
		 * @see org.jrimum.domkee.financeiro.banco.hsbc.TipoIdentificadorCNR#getConstante()
		 */
		@Override
		public int getConstante() {
			return VALOR_COM_VENCIMENTO;
		}
	},

	/**
	 * Identificador do <strong>"tipo 5"</strong> que vincula “código do
	 * cedente” e “código do documento”.
	 * 
	 * @since 0.2
	 */
	SEM_VENCIMENTO {
		/**
		 * @see org.jrimum.domkee.financeiro.banco.hsbc.TipoIdentificadorCNR#getConstante()
		 */
		@Override
		public int getConstante() {
			return VALOR_SEM_VENCIMENTO;
		}
	};
	
	private static final int VALOR_SEM_VENCIMENTO = 5;
	private static final int VALOR_COM_VENCIMENTO = 4;

	/**
	 * <p>
	 * Retorna a constante numérica bancária do tipo (4 ou 5)
	 * </p>
	 * 
	 * @return constante do tipo (4 ou 5)
	 * 
	 * @since 0.2
	 */
	public abstract int getConstante();
	

	public static TipoIdentificadorCNR valueOf(int valor) {
		switch (valor) {

		case VALOR_SEM_VENCIMENTO:

			return SEM_VENCIMENTO;

		case VALOR_COM_VENCIMENTO:

			return COM_VENCIMENTO;

		default:
			return Exceptions.throwIllegalStateException(format(
					"Tipo de identificador CNR \"%d\" desconhecido!", valor));
		}
	}
}