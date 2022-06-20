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

import java.text.ParseException;

import org.jrimum.utilix.Exceptions;

/**
 * <p>
 * Formatadores thread-safe relacionados a moeda e números decimais.
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public enum DecimalFormat implements Format<Number, java.text.DecimalFormat>{

	/**
	 * <p>
	 * Formatador de decimais pt-BR para <code>Float,Double,BigDecimal</code> no padrão
	 * "#,##0.0". Exemplo: "1.500,9".
	 * </p>
	 */
	NUMBER_D_BR("#,##0.0", DecimalFormatSymbols.BRASIL.copy()),
	
	/**
	 * <p>
	 * Formatador de decimais pt-BR para <code>Float,Double,BigDecimal</code> no padrão
	 * "#,##0.00". Exemplo: "1.500,99".
	 * </p>
	 */
	NUMBER_DD_BR("#,##0.00", DecimalFormatSymbols.BRASIL.copy()),
	
	/**
	 * <p>
	 * Formatador de decimais <code>Float,Double,BigDecimal</code> no padrão de
	 * moeda (Real pt-BR): "R$ #,##0.0". Usado para formatar valores
	 * monetários em real com o prefixo R$, exemplo: R$ 10,0.
	 * </p>
	 */
	MONEY_D_BR("\u00A4 #,##0.0", DecimalFormatSymbols.BRASIL.copy()),
	
	/**
	 * <p>
	 * Formatador de decimais <code>Float,Double,BigDecimal</code> no padrão de
	 * moeda (Real pt-BR): "R$ #,##0.00". Usado para formatar valores
	 * monetários em real com o prefixo R$, exemplo: R$ 10,00.
	 * </p>
	 */
	MONEY_DD_BR("\u00A4 #,##0.00", DecimalFormatSymbols.BRASIL.copy()),
	
	;
	
	private final ThreadLocalLocalizedFormat<java.text.DecimalFormat, java.text.DecimalFormatSymbols> DECIMAL_FORMAT;

	private DecimalFormat(String format, java.text.DecimalFormatSymbols formatSymbols) {
	
		DECIMAL_FORMAT = new ThreadLocalLocalizedFormat<java.text.DecimalFormat, java.text.DecimalFormatSymbols>(format, formatSymbols){

			@Override
			protected java.text.DecimalFormat initialValue() {
				
				return new java.text.DecimalFormat(format, formatSymbols);
			}
	    };
	}

	/**
	 * @see org.jrimum.utilix.text.Format#format(java.lang.Object)
	 */
	public String format(Number obj) {
	
		return DECIMAL_FORMAT.get().format(obj);
	}
	
	/**
	 * @see org.jrimum.utilix.text.Format#parse(java.lang.String)
	 */
	public Number parse(String text) {
		
		try {
			
			return DECIMAL_FORMAT.get().parse(text);
			
		} catch (ParseException e) {
			
			return Exceptions.throwIllegalArgumentException("DecimalFormat Exception!", e);
		}
	}
	
	/**
	 * @see org.jrimum.utilix.text.Format#copy()
	 */
	public java.text.DecimalFormat copy(){
		
		return (java.text.DecimalFormat) DECIMAL_FORMAT.get().clone();
	}
}
