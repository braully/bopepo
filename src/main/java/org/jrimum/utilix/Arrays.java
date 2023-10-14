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

/**
 * <p>
 * Classe utilitária para validações de arrays em geral.
 * </p>
 * 
 * <p>
 * Inicialmente fornece métodos booleanos para verificação da existência de elementos.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public final class Arrays {
	
	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws IllegalStateException
	 *             Caso haja alguma tentativa de utilização deste construtor.
	 */
	private Arrays() {
		
		Exceptions.throwIllegalStateException("Instanciação não permitida!");
	}

	/**
	 * <p>
	 * Indica se um dado array de chars tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(char[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de booleans tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(boolean[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de bytes tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(byte[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de shorts tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(short[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de ints tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(int[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de longs tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(long[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de floats tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(float[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de doubles tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(double[] a){
		
		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Indica se um dado array de objetos tem elementos sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(a != null && a > 0)</code>.
	 * </p>
	 * 
	 * @param a - O array a ser testado.
	 * 
	 * @return (a != null && a > 0)
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(Object[] a) {

		return (a != null && a.length > 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(char[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(boolean[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(byte[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(short[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(int[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(long[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(float[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(double[] a){
	
		return (a != null ? a.length : 0);
	}
	
	/**
	 * <p>
	 * Retorna a quantidade de elementos de um dado array sem gerar NPE.
	 * </p>
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(a != null ? a.length : 0)</code>.
	 * </p>
	 * 
	 * @param a
	 *            - Array com ou sem elementos.
	 * 
	 * @return (a != null ? a.length : 0)
	 */
	public static int length(Object[] a){
	
		return (a != null ? a.length : 0);
	}

}
