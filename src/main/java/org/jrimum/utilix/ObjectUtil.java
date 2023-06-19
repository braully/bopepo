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
 * Created at: 30/03/2008 - 18:02:40
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
 * Criado em: 30/03/2008 - 18:02:40
 *
 */

package org.jrimum.utilix;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * <p>
 * Classe raiz para todo o projeto detentora de metodos básicos e necessários.
 * </p>
 *
 * <p>
 * Todas as classes do projeto devem herdar de <code>ObjectUtil</code> para
 * reutilizar métodos como o <code>toString()</code> e
 * <code>isNull(Object, String)</code>.
 * </p>
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 * @since 0.2
 *
 * @version 0.2
 *
 * @see #isNull(Object, String)
 * @see #toString()
 * @see <a href="http://pt.wikipedia.org/wiki/Cucurbita" >Cucurbita</a>
 */

public class ObjectUtil implements Serializable {

	private static final long serialVersionUID = -6790981191128287923L;

	private static Logger log = LogManager.getLogger(ObjectUtil.class);

	private ObjectUtil() {
		throw new AssertionError();
	}

	/**
	 * <p>
	 * Verifica a referência ao objeto e lança uma exceção para casos onde a
	 * referência é nula incluíndo na mensagem de exceção o nome do parâmetro
	 * passado.
	 * </p>
	 *
	 * @param object
	 * @param name
	 *
	 * @return (object == null ? true : false)
	 *
	 * @throws IllegalArgumentException
	 *
	 * @see #isNotNull(Object, String)
	 *
	 * @since 0.2
	 */
	public static boolean isNull(Object object, String name) throws IllegalArgumentException {

		boolean is = true;

		if (object != null) {
			is = false;

		} else {

			IllegalArgumentException e = new IllegalArgumentException(
					(name != null ? name : "Objeto ") + " inválido : [" + object + "]!");

			log.error(StringUtils.EMPTY, e);

			throw e;
		}

		return is;
	}

	/**
	 * <p>
	 * Verifica a referência ao objeto e lança uma exceção para casos onde a
	 * referência é nula incluíndo na mensagem de exceção o nome do parâmetro
	 * passado.
	 * </p>
	 *
	 * <p>
	 * É o mesmo que usar <code>!isNull(obj1,"obj1")</code> a diferença é que
	 * utiliza-se um símbolo <code>!</code> a menos.
	 * </p>
	 *
	 * @param object
	 * @param name
	 *
	 * @return (object != null ? true : false)
	 *
	 * @throws IllegalArgumentException
	 *
	 * @see #isNull(Object, String)
	 *
	 * @since 0.2
	 */
	public static boolean isNotNull(Object object, String name) throws IllegalArgumentException {
		return !isNull(object, name);
	}

	/**
	 * <p>
	 * Verifica a referência ao objeto.
	 * </p>
	 *
	 * @param object
	 *
	 * @return (object != null ? true : false)
	 *
	 * @see #isNull(Object)
	 *
	 * @since 0.2
	 */
	public static boolean isNotNull(Object object) {
		return (object != null);
	}

	/**
	 * <p>
	 * Verifica se existe uma referência ao objeto dado.
	 * </p>
	 *
	 * @param object
	 *
	 * @return (object != null ? true : false)
	 *
	 * @see #isNotNull(Object)
	 *
	 * @since 0.2
	 */
	public static boolean exists(Object object) {
		return (object != null);
	}

	/**
	 * <p>
	 * Verifica a referência ao objeto.
	 * </p>
	 *
	 * @param object
	 *
	 * @return (object == null ? true : false)
	 *
	 * @see #isNotNull(Object)
	 *
	 * @since 0.2
	 */
	public static boolean isNull(Object object) {
		return (object == null);
	}

	/**
	 * Exibe os valores de instância para um objeto JRimum.
	 *
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#reflectionToString
	 *
	 * @since 0.2
	 *
	 * @see #toString()
	 */
	public static String toString(Object obj) {
		return "JRimumObject: " + ToStringBuilder.reflectionToString(obj);
	}
}
