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
 * Created at: 01/08/2010 - 17:31:00
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
 * Criado em: 01/08/2010 - 17:31:00
 * 
 */

package org.jrimum.utilix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jrimum.utilix.Exceptions;

/**
 * <p>
 * Formatadores de data thread-safe. Para uma mesma categoria, os formatadores
 * atualmente são diferenciados pelos seus separadores ("formato_separador")
 * exemplos:
 * <ul>
 * <li>DDMMYY <code>default:ddMMyy</code></li>
 * <li>DDMMYY_B <code>barr:dd/MM/yy</code></li>
 * <li>DDMMYY_H <code>hyphen:dd-MM-yy</code></li>
 * <li>DDMMYY_U <code>underline:dd_MM_yy</code></li>
 * <li>HHMMSS_C <code>colon:"hh:mm:ss"</code></li>
 * <li>etc.</li>
 * </ul>
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public enum DateFormat implements Format<Date, SimpleDateFormat>{

	/**
	 * <p>
	 * Formatador de datas no padrão "ddMMyy".
	 * </p>
	 */
	DDMMYY("ddMMyy"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd/MM/yy".
	 * </p>
	 */
	DDMMYY_B("dd/MM/yy"), 
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd-MM-yy".
	 * </p>
	 */
	DDMMYY_H("dd-MM-yy"), 
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd_MM_yy".
	 * </p>
	 */
	DDMMYY_U("dd_MM_yy"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "ddMMyyyy".
	 * </p>
	 */
	DDMMYYYY("ddMMyyyy"), 
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd/MM/yyyy".
	 * </p>
	 */
	DDMMYYYY_B("dd/MM/yyyy"), 
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd-MM-yyyy".
	 * </p>
	 */
	DDMMYYYY_H("dd-MM-yyyy"), 
	
	/**
	 * <p>
	 * Formatador de datas no padrão "dd_MM_yyyy".
	 * </p>
	 */
	DDMMYYYY_U("dd_MM_yyyy"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yyMMdd".
	 * </p>
	 */
	YYMMDD("yyMMdd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yy/MM/dd".
	 * </p>
	 */
	YYMMDD_B("yy/MM/dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yy/MM/dd".
	 * </p>
	 */
	YYMMDD_H("yy-MM-dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yy_MM_dd".
	 * </p>
	 */
	YYMMDD_U("yy_MM_dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yyyyMMdd".
	 * </p>
	 */
	YYYYMMDD("yyyyMMdd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yyyy/MM/dd".
	 * </p>
	 */
	YYYYMMDD_B("yyyy/MM/dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yyyy-MM-dd".
	 * </p>
	 */
	YYYYMMDD_H("yyyy-MM-dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "yyyy_MM_dd".
	 * </p>
	 */
	YYYYMMDD_U("yyyy_MM_dd"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "hhmmss".
	 * </p>
	 */
	HHMMSS("hhmmss"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "HHmmss".
	 * </p>
	 */
	HHMMSS_24("HHmmss"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "hh:mm:ss".
	 * </p>
	 */
	HHMMSS_C("hh:mm:ss"),
	
	/**
	 * <p>
	 * Formatador de datas no padrão "HH:mm:ss".
	 * </p>
	 */
	HHMMSS_24C("HH:mm:ss"),
	;
	
	private final ThreadLocalFormat<SimpleDateFormat> DATE_FORMAT;

	private DateFormat(String format) {
	
		DATE_FORMAT = new ThreadLocalFormat<SimpleDateFormat>(format){

			@Override
			protected SimpleDateFormat initialValue() {
				
		       return new SimpleDateFormat(format);
			}
	        
	    };
	}

	/**
	 * @see org.jrimum.utilix.text.Format#format(java.lang.Object)
	 */
	public String format(Date obj) {
	
		return DATE_FORMAT.get().format(obj);
	}
	
	/**
	 * @see org.jrimum.utilix.text.Format#parse(java.lang.String)
	 */
	public Date parse(String text) {
		
		try {
			
			return DATE_FORMAT.get().parse(text);
			
		} catch (ParseException e) {
			
			return Exceptions.throwIllegalArgumentException("DateFormat Exception!", e);
		}
	}
	
	/**
	 * @see org.jrimum.utilix.text.Format#copy()
	 */
	public SimpleDateFormat copy(){
			
		return (SimpleDateFormat) DATE_FORMAT.get().clone();
	}
}
