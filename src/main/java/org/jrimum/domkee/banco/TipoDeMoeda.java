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
 * Created at: 30/03/2008 - 19:08:53
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
 * Criado em: 30/03/2008 - 19:08:53
 * 
 */


package org.jrimum.domkee.banco;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Representa as moedas existentes que fazem parte do universo de um título, segundo a FEBRABAN.
 * </p>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a> 
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public enum TipoDeMoeda {
	
	/**
	 * Padrão FEBRABAN
	 */
	
	DOLAR_AMERICANO_COMERCIAL_VENDA(2),
	DOLAR_AMERICANO_TURISMO_VENDA(3),
	ITRD(4),
	IDTR(5),
	UFIR_DIARIA(6),
	UFIR_MENSAL(7),
	FAJ_TR(8),
	REAL(9),
	TR(10),
	IGPM(11),
	CDI(12),
	PERCENTUAL_DO_CDI(13),
	EURO(14);

	private int codigo;
	
	/**
	 * @param codigo
	 */
	private TipoDeMoeda(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		
		return this.codigo;
	}
	
	public String write(){
		
		String s = StringUtils.EMPTY;
		
		switch (this) {
			
			case REAL:
				s = "R$";
				break;
		
			default:
				s += this.getCodigo();
		}
		
		return s;
	}
}
