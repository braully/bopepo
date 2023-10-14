/* 
 * Copyright 2008 JRimum Project
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
 * Created at: 15/06/2008 - 12:00:00
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 15/06/2008 - 12:00:00
 * 
 */

package org.jrimum.vallia;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Cálculo para o dígito verificador do código de compensação dos bancos
 * supervisionados pelo <a href="http://www.bcb.gov.br/?CHEQUESCOMPE">BACEN</a>
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author Misael
 * @author Rômulo Augusto
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class CodigoDeCompensacaoBancosBACENDV extends AbstractDigitoVerificador {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5250684561237486022L;
	
	/**
	 * Valor mínimo do código de compensação
	 */
	public static final int LIMITE_MINIMO = 1;
	
	/**
	 * Valor máximo do código de compensação
	 */
	public static final int LIMITE_MAXIMO = 999; 

	/**
	 * <p>
	 * Mesagem padrão para erro.
	 * </p>
	 */
	private static final String MSG = "O código de compensação do banco deve ser um número entre 1 e 999.";

	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
	 * 
	 * @param numero
	 * @return int digito
	 * 
	 * @throws IllegalArgumentException Caso não seja um código válido
	 * 
	 * @since 0.2
	 */
	@Override
	public int calcule(String numero) {
		
		if (!isCodigoValido(numero)) {
			throw new IllegalArgumentException(MSG);
		}
		
		return calcule(Integer.valueOf(numero.trim()));
	}

	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @param numero
	 * 
	 * @return int digito
	 * 
	 * @since 0.2
	 */
	public int calcule(int numero) {

		return calcule((long) numero);
	}

	/**
	 * <p>
	 * Calcula o dígito verificador para código de compensação passado.
	 * </p>
	 * 
	 * @param numero
	 * 
	 * @return int digito
	 
	 * @since 0.2
	 * 
	 * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(long)
	 */
	@Override
	public int calcule(long numero) {

		int dv = -1;

		if (!isCodigoValido(numero)) {
			throw new IllegalArgumentException(MSG);
		}

		int soma = Modulo.calculeSomaSequencialMod11(
				String.valueOf(numero), 2, 9);

		soma *= 10;

		dv = soma % 11;

		dv = (dv == 10) ? 0 : dv;

		return dv;
	}

	/**
	 * <p>
	 * Retorna se um código de compensação passado é válido, ou seja, se está entre os 
	 * valores inteiros de 1 a 999.
	 * </p>
	 * 
	 * @param codigo - Código de compensação
	 * @return true se for númerio entre 1 e 999; false caso contrário
	 * 
	 * @since 0.2
	 */
	public boolean isCodigoValido(String codigo) {

		boolean codigoValido = false;
		
		if (StringUtils.isNotBlank(codigo) && StringUtils.isNumeric(codigo)) {
			codigoValido = isCodigoValido(Integer.valueOf(codigo.trim()));
		}
		
		return codigoValido;
	}

	/**
	 * <p>
	 * Retorna se um código de compensação passado é válido.
	 * </p>
	 * 
	 * @param codigo - Código de compensação
	 * @return true se entre 0 (não incluso) e 999; false caso contrário
	 * 
	 * @since 0.2
	 */
	public boolean isCodigoValido(int codigo) {
		
		return isCodigoValido((long) codigo);
	}
	
	/**
	 * <p>
	 * Retorna se um código de compensação passado é válido.
	 * </p>
	 * 
	 * @param codigo - Código de compensação
	 * @return true se entre 0 (não incluso) e 999; false caso contrário
	 * 
	 * @since 0.2
	 */
	public boolean isCodigoValido(long codigo) {
		
		return (codigo >= LIMITE_MINIMO) && (codigo <= LIMITE_MAXIMO);
	}
}
