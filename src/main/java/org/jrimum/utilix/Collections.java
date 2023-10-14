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

import java.util.Collection;
import java.util.Map;

/**
 * Classe utilitária para validações de coleções em geral, com e sem exceções.
 * 
 * <p>
 * Fornece métodos booleanos e métodos que verificam se uma coleção está de
 * acordo com o desejado e, caso não estejam, lançam exceção.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public final class Collections {

	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws IllegalStateException
	 *             Caso haja alguma tentativa de utilização deste construtor.
	 */
	private Collections() {

		Exceptions.throwIllegalStateException("Instanciação não permitida!");
	}

	/**
	 * Verifica se a <code>Collection</code> passada por parâmetro é
	 * <code>null</code> ou <strong>não</strong> possui elementos e lança
	 * exceção caso não preencha estes requisitos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * 
	 * @throws IllegalArgumentException - Caso a coleção <strong>não</strong>
	 *        seja <code>null</code> e possua elementos.
	 * 
	 * @see #checkEmpty(Collection, String)
	 * @see #isEmpty(Collection)
	 * @see #isNotEmpty(Collection)
	 * 
	 * @since 0.2
	 */
	public static void checkEmpty(Collection<?> collection) {

		checkEmpty(collection, "Collection não nulo e com elementos! Valor ["
				+ collection + "].");
	}

	/**
	 * Verifica se a <code>Collection</code> passado por parâmetro é
	 * <code>null</code> ou <strong>não</strong> possui elementos e lança
	 * exceção, com a mensagem informada, caso não preencha estes requisitos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * @param message
	 *            - Mensagem utilizada na exceção.
	 * 
	 * @throws IllegalArgumentException - Caso a coleção <strong>não</strong>
	 *        seja <code>null</code> e possua elementos.
	 * 
	 * @see #isEmpty(Collection)
	 * @see #isNotEmpty(Collection)
	 * 
	 * @since 0.2
	 */
	public static void checkEmpty(Collection<?> collection, String message) {

		if (hasElement(collection)) {

			Exceptions.throwIllegalArgumentException(message);
		}
	}

	/**
	 * Verifica se a <code>Collection</code> passada por parâmetro
	 * <strong>não</strong> é <code>null</code> e possui elementos e lança
	 * exceção caso não preencha estes requisitos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * 
	 * @throws IllegalArgumentException
	 *             - Caso a coleção seja <code>null</code> ou a coleção
	 *             <strong>não</strong> possua elementos.
	 * 
	 * @see #checkNotEmpty(Collection, String)
	 * @see #isEmpty(Collection)
	 * @see #isNotEmpty(Collection)
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Collection<?> collection) {

		checkNotEmpty(collection, "Objeto nulo!", "Collection sem elementos!");
	}

	/**
	 * Verifica se a <code>Collection</code> passada por parâmetro
	 * <strong>não</strong> é <code>null</code> e possui elementos e lança
	 * exceção, com a mensagem informada, caso não preencha estes requisitos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @throws IllegalArgumentException
	 *             - Caso a coleção seja <code>null</code> ou a coleção
	 *             <strong>não</strong> possua elementos.
	 * 
	 * @see #isEmpty(Collection)
	 * @see #isNotEmpty(Collection)
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Collection<?> collection, String message) {

		checkNotEmpty(collection, message, message);
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro é <code>null</code>
	 * ou <strong>não</strong> possui elementos e lança exceção caso não
	 * preencha estes requisitos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * 
	 * @throws IllegalArgumentException - Caso o mapa <strong>não</strong> seja
	 *        <code>null</code> e possua elementos.
	 * 
	 * @see #checkEmpty(Map, String)
	 * @see #isEmpty(Map)
	 * @see #isNotEmpty(Map)
	 * 
	 * @since 0.2
	 */
	public static void checkEmpty(Map<?, ?> map) {

		checkEmpty(map, "Map não nulo e com elementos. Valor [" + map + "]");
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro é <code>null</code>
	 * ou <strong>não</strong> possui elementos e lança exceção, com a mensagem
	 * informada, caso não preencha estes requisitos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * @param message
	 *            - Mensagem utilizada na exceção.
	 * 
	 * @throws IllegalArgumentException - Caso o mapa <strong>não</strong> seja
	 *        <code>null</code> e possua elementos.
	 * 
	 * @see #isEmpty(Map)
	 * @see #isNotEmpty(Map)
	 * 
	 * @since 0.2
	 */
	public static void checkEmpty(Map<?, ?> map, String message) {

		if (hasElement(map)) {

			Exceptions.throwIllegalArgumentException(message);
		}
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro <strong>não</strong>
	 * é <code>null</code> e possui elementos e lança exceção caso não preencha
	 * estes requisitos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * 
	 * @throws IllegalArgumentException
	 *             - Caso o mapa seja <code>null</code> ou o mapa
	 *             <strong>não</strong> possua elementos.
	 * 
	 * @see #checkNotEmpty(Map, String)
	 * @see #isEmpty(Map)
	 * @see #isNotEmpty(Map)
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Map<?, ?> map) {

		checkNotEmpty(map, "Objeto nulo", "Map sem elementos");
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro <strong>não</strong>
	 * é <code>null</code> e possui elementos e lança exceção, com a mensagem
	 * informada, caso não preencha estes requisitos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @throws IllegalArgumentException
	 *             - Caso o mapa seja <code>null</code> ou o mapa
	 *             <strong>não</strong> possua elementos.
	 * 
	 * @see #isEmpty(Map)
	 * @see #isNotEmpty(Map)
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Map<?, ?> map, String message) {

		checkNotEmpty(map, message, message);
	}

	/**
	 * Verifica se a <code>Collection</code> passada por parâmetro é
	 * <code>null</code> ou <strong>não</strong> possui elementos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * @return <code>!hasElement(collection)</code>
	 * 
	 * @since 0.2
	 */
	public static boolean isEmpty(Collection<?> collection) {

		return !hasElement(collection);
	}

	/**
	 * Verifica se a <code>Collection</code> passada por parâmetro
	 * <strong>não</strong> é <code>null</code> e possui elementos.
	 * 
	 * @param collection
	 *            - Instância de <code>Collection</code> analisada.
	 * @return <code>hasElement(collection)</code>
	 * 
	 * @see #isEmpty(Collection)
	 * 
	 * @since 0.2
	 */
	public static boolean isNotEmpty(Collection<?> collection) {

		return hasElement(collection);
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro é <code>null</code>
	 * ou <strong>não</strong> possui elementos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * @return <code>!hasElement(map)</code>
	 * 
	 * @since 0.2
	 */
	public static boolean isEmpty(Map<?, ?> map) {

		return !hasElement(map);
	}

	/**
	 * Verifica se o <code>Map</code> passado por parâmetro <strong>não</strong>
	 * é <code>null</code> e possui elementos.
	 * 
	 * @param map
	 *            - Instância de <code>Map</code> analisada.
	 * @return <code>hasElement(map)</code>
	 * 
	 * @see #isEmpty(Map)
	 * 
	 * @since 0.2
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {

		return hasElement(map);
	}
	
	/**
	 * Indica se pelo menos uma coleção tem algum elemento sem gerar NPE.
	 * 
	 * @param cols
	 *            - Coleções para teste.
	 * 
	 * @return indicativo
	 */
	public static boolean hasElement(Collection<?> ... cols) {

		if(Arrays.hasElement(cols)){
			
			for(Collection<?> c : cols){
				if(hasElement(c)){
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Indica se pelo menos um mapa tem algum elemento sem gerar NPE.
	 * 
	 * @param maps
	 *            - Mapas para teste.
	 * 
	 * @return indicativo
	 */
	public static boolean hasElement(Map<?, ?> ... maps) {

		if(Arrays.hasElement(maps)){
			
			for(Map<?, ?> m : maps){
				if(hasElement(m)){
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Indica se uma dada coleção tem elementos sem gerar NPE.
	 * <p>
	 * Resposta direta para o seguinte código:
	 * <code>(c != null && !c.isEmpty())</code>.
	 * </p>
	 * 
	 * @param c
	 *            - A coleção a ser testada.
	 * 
	 * @return (c != null && !c.isEmpty())
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(Collection<?> c) {

		return (c != null && !c.isEmpty());
	}

	/**
	 * Indica se um dado mapa tem elementos sem gerar NPE.
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(m != null && !m.isEmpty())</code>.
	 * </p>
	 * 
	 * @param m
	 *            - O mapa a ser testado.
	 * 
	 * @return (m != null && !m.isEmpty())
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(Map<?, ?> m) {

		return (m != null && !m.isEmpty());
	}

	/**
	 * Retorna a quantidade de elementos de uma dada coleção sem gerar NPE.
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(c != null ? c.size() : 0)</code>.
	 * </p>
	 * 
	 * @param c
	 *            - Coleção com ou sem elementos.
	 * 
	 * @return (c != null ? c.size() : 0)
	 */
	public static int size(Collection<?> c) {

		return (c != null ? c.size() : 0);
	}

	/**
	 * Retorna a quantidade de elementos de um dado mapa sem gerar NPE.
	 * <p>
	 * Resposta direata para o seguinte código:
	 * <code>(m != null ? m.size() : 0)</code>.
	 * </p>
	 * 
	 * @param m
	 *            - Mapa com ou sem elementos.
	 * 
	 * @return (m != null ? m.size() : 0)
	 */
	public static int size(Map<?, ?> m) {

		return (m != null ? m.size() : 0);
	}

	/**
	 * Verifica se o coleção passado por parâmetro <strong>não</strong> é
	 * <code>null</code> ou se é vazio.
	 * <p>
	 * Caso o objeto seja <code>null</code>, lança
	 * <code>NullPointerException</code> com a mensagem informada no parâmetro
	 * <code>messageNullPointer</code> (primeiro parâmetro String). Caso o
	 * objeto não seja <code>null</code> e não possua elementos, lança
	 * <code>IllegalArgumentException</code> com a mensagem informada no
	 * parâmetro <code>messageIllegalArgument</code> (segundo parâmetro String).
	 * </p>
	 * 
	 * @param collection
	 *            - Objeto analisado.
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção
	 *            <code>IllegalArgumentException</code>.
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção
	 *            <code>IllegalArgumentException</code>.
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Collection<?> collection,
			String messageNullPointer, String messageIllegalArgument) {

		if (collection == null) {

			Exceptions.throwIllegalArgumentException(messageNullPointer);
		}

		if (collection.isEmpty()) {

			Exceptions.throwIllegalArgumentException(messageIllegalArgument);
		}
	}

	/**
	 * Verifica se o mapa passado por parâmetro <strong>não</strong> é
	 * <code>null</code> ou se é vazio.
	 * <p>
	 * Caso o objeto seja <code>null</code>, lança
	 * <code>NullPointerException</code> com a mensagem informada no parâmetro
	 * <code>messageNullPointer</code> (primeiro parâmetro String). Caso o
	 * objeto não seja <code>null</code> e não possua elementos, lança
	 * <code>IllegalArgumentException</code> com a mensagem informada no
	 * parâmetro <code>messageIllegalArgument</code> (segundo parâmetro String).
	 * </p>
	 * 
	 * @param map
	 *            - Objeto analisado.
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção
	 *            <code>IllegalArgumentException</code>.
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção
	 *            <code>IllegalArgumentException</code>.
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 */
	public static void checkNotEmpty(Map<?, ?> map, String messageNullPointer,
			String messageIllegalArgument) {

		if (map == null) {

			Exceptions.throwIllegalArgumentException(messageNullPointer);
		}

		if (map.isEmpty()) {

			Exceptions.throwIllegalArgumentException(messageIllegalArgument);
		}
	}

}
