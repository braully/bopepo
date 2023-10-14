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
 * Created at: 27/10/2010 - 01:40:00
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
 * Criado em: 27/10/2010 - 01:40:00
 * 
 */

package org.jrimum.utilix;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Utilitário para carregamento de recursos da apliacação por meio do classpath.
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 */
public final class ClassLoaders {

	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws IllegalStateException
	 *             Caso haja alguma tentativa de utilização deste construtor.
	 */
	private ClassLoaders() {

		Exceptions.throwIllegalStateException("Instanciação não permitida!");
	}

	/**
	 * Carrega um recurso existente no classpath.
	 * 
	 * @param resourceName
	 *            - O nome do resource a ser carregado
	 * @return URL para o recurso (resource).
	 */
	public static URL getResource(String resourceName) {

		return getResource(resourceName, null);
	}

	/**
	 * Carrega um recurso existente no classpath com ou sem uma classe
	 * informada.
     *
	 * <p>
	 * Informando a classe há necessidade de informar o caminho do pacote, caso
	 * o recurso esteja nele.
	 * </p>
	 * 
	 * @param resourceName
	 *            - O nome do resource a ser carregado
	 * @param callingClass
	 *            - A classe do objeto chamador ou outro.
	 * @return URL para o recurso (resource).
	 */
	public static URL getResource(String resourceName, Class<?> callingClass) {

		URL url = null;

		if (callingClass != null) {

			url = callingClass.getResource(resourceName);

			if (url == null) {

				url = callingClass.getClassLoader().getResource(resourceName);
				
			}else{
				
				return url;
			}

			if (url == null) {

				String inPagckage = format("%s/%s", callingClass.getPackage().getName().replaceAll("\\.", "/"), resourceName);

				url = Thread.currentThread().getContextClassLoader().getResource(inPagckage);
				
			} else {

				return url;
			}
		}

		if (url == null) {

			url = ClassLoaders.class.getResource(resourceName);
			
		}else{
			
			return url;
		}

		if (url == null) {

			url = ClassLoaders.class.getClassLoader().getResource(resourceName);
			
		}else{
			
			return url;
		}

		if (url == null) {

			url = Thread.currentThread().getContextClassLoader().getResource(
					resourceName);
		}

		return url;
	}

	/**
	 * Carrega um recurso existente no classpath sob a forma de stream.
	 * 
	 * @param resourceName
	 *            - O nome do resource a ser carregado
	 * @return Stream aberto para o recurso (resource).
	 */
	public static InputStream getResourceAsStream(String resourceName) {

		return getResourceAsStream(resourceName, null);
	}

	/**
	 * Carrega um recurso existente no classpath sob a forma de stream com ou
	 * sem uma classe informada.
	 * 
	 * @param resourceName
	 *            - O nome do resource a ser carregado
	 * @param callingClass
	 *            - A classe do objeto chamador ou outro.
	 * @return Stream aberto para o recurso (resource).
	 */
	public static InputStream getResourceAsStream(String resourceName,
			Class<?> callingClass) {

		try {

			return getResource(resourceName, callingClass).openStream();

		} catch (IOException e) {

			return Exceptions.throwIllegalStateException(e);
		}
	}

}
