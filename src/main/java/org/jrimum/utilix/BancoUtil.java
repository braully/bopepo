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
 * Created at: 30/03/2008 - 18:17:40
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
 * Criado em: 30/03/2008 - 18:17:40
 * 
 */

package org.jrimum.utilix;

import static org.jrimum.utilix.ObjectUtil.isNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

import org.jrimum.JRimumException;

/**
 * <p>
 * Esta classe tem a responsabilidade de prover serviços utilitários
 * do universo bancário, como por exemplo calcular o fator de vencimento.</code>
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto 
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class BancoUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Data base para o cálculo do fator de vencimento fixada em 07.10.1997 
	 * (03.07.2000 retrocedidos 1000 dias do início do processo) segundo a FEBRABAN.
	 * </p>
	 */
	private static final Date DATA_BASE_DO_FATOR_DE_VENCIMENTO = new GregorianCalendar(1997, Calendar.OCTOBER, 7).getTime();
	
	private static final Date DATA_LIMITE_DO_FATOR_DE_VENCIMENTO = new GregorianCalendar(2025, Calendar.FEBRUARY, 21).getTime();
	
	/**
	 *
	 *<p>
	 * 		Calcula o fator de vencimento, com base na subtração entre a 
	 * 		DATA DE VENCIMENTO do título e a DATA BASE , fixada em
	 * 		07.10.1997 (03.07.2000 retrocedidos 1000 dias do início do processo).
	 * 		Trata-se de um referencial numérico de 4 dígitos, situado nas
	 * 		quatro primeiras posições do campo “valor”, que representa a
	 * 		quantidade de dias decorridos da data base à data de vencimento
	 * 		do título.
	 *</p>
	 *<p>
	 * 		Os bloquetos de cobrança emitidos a partir de 1º de setembro
	 * 		de 2000 (primeiro dia útil = 03/07/2000 - SEGUNDA) OBRIGATORIAMENTE 
	 * 		devem conter essas características, para que quando
	 * 		forem capturados pela rede bancária, os sistemas possam realizar
	 * 		operação inversa, ou seja, adicionar à data base o fator de
	 * 		vencimento capturado, obtendo, dessa forma, a data do
	 * 		vencimento do bloqueto.<br>
	 * 		
	 * 		Exemplos: 03/07/2000(Fator = 1000), 05/07/2000(Fator = 1002), 
	 * 		01/05/2002 (Fator = 1667), 05/07/2000(Fator = 1002)
	 * 		e 21/02/2025(Fator = 9999).
	 *</p>
	 *
	 *<p>
	 *		Observações: 
	 *		<ul>
	 *			<li>
	 *				A data base para o cálculo do fator de vencimento é 
	 *				07/10/1997 (Fator de vencimento = 0).Caso a data de
	 *              vencimento seja anterior a data base, uma exceção do tipo 
	 *              IllegalArgumentException será lançada.
	 *			</li>
	 *			<li>
	 *				A data limite para o cálculo do fator de vencimento é 
	 *				21/02/2025 (Fator de vencimento = 9999) Caso a data de 
	 *				vencimento seja posterior a data limite, uma exceção do tipo 
	 *				IllegalArgumentException será lançada.
	 *			</li>
	 *		</ul>
	 *</p>
	 *
	 * @param dataVencimento - data de vencimento.
	 * @return fator de vencimento.
	 * 
	 * @throws JRimumException Caso alguma condição de entrada não seja respeitada.
	 */	
	public static int calculceFatorDeVencimento(Date dataVencimento) throws JRimumException {
		
		Date dataVencTruncada = null;
		int fator;
		
		
		if (isNull(dataVencimento)) {
			throw new JRimumException(new IllegalArgumentException("Impossível realizar o cálculo do fator" +
					" de vencimento de uma data nula."));
		}
		else {
			dataVencTruncada = DateUtils.truncate(dataVencimento, Calendar.DATE);
			if (
					 dataVencTruncada.before(DATA_BASE_DO_FATOR_DE_VENCIMENTO)
				  || dataVencTruncada.after(DATA_LIMITE_DO_FATOR_DE_VENCIMENTO)		  
				) {
				throw new JRimumException(new IllegalArgumentException("Para o cálculo do fator de" +
					" vencimento se faz necessário informar uma data entre" +
					" " + DateUtil.FORMAT_DD_MM_YYYY.format(DATA_BASE_DO_FATOR_DE_VENCIMENTO) +
					" e " + DateUtil.FORMAT_DD_MM_YYYY.format(DATA_LIMITE_DO_FATOR_DE_VENCIMENTO)));
			}
			else {
				fator = (int)DateUtil.calculeDiferencaEmDias(DATA_BASE_DO_FATOR_DE_VENCIMENTO, dataVencTruncada);
			}
		}	
		
		return fator; 		
	}
}
