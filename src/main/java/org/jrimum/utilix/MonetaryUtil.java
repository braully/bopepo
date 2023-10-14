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
 * Created at: 30/03/2008 - 18:18:00
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
 * Criado em: 30/03/2008 - 18:18:00
 * 
 */

package org.jrimum.utilix;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


public class MonetaryUtil {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3239576826390112911L;

	/**
	 * DecimalFormatSymbols com localização (Processo de localização) Brasil.
	 */
	public static final DecimalFormatSymbols BRASIL_DECIMAL_SYMBOLS = new DecimalFormatSymbols(new Locale("pt", "BR"));
	
	/**
	 * DecimalFormatSymbols com localização (Processo de localização) England.
	 */
	public static final DecimalFormatSymbols ENGLAND_DECIMAL_SYMBOLS = new DecimalFormatSymbols(new Locale("en", "US"));
	
	/**
	 * NumberFormat usado para formatar valores monetários em real.
	 */
	public static final NumberFormat FORMAT_REAL = new DecimalFormat("#,##0.00", BRASIL_DECIMAL_SYMBOLS);
	
	/**
	 * NumberFormat usado para formatar valores monetários em real com apenas UMA casa decimal.
	 */
	public static final NumberFormat FORMAT_REAL_UMA_CASA_DECIMAL = new DecimalFormat("#,##0.0", BRASIL_DECIMAL_SYMBOLS);
	
	/**
	 * NumberFormat usado para formatar valores monetários em real com o prefixo R$, exemplo: R$ 10,00.
	 */
	public static final NumberFormat FORMAT_REAL_COM_PREFIXO = new DecimalFormat("R$ #,##0.00", BRASIL_DECIMAL_SYMBOLS);

}
