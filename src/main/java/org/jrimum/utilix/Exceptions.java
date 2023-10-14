/*
 * Copyright 2011 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 15/05/2011 - 17:54:00
 * 
 * ================================================================================
 * 
 * Direitos autorais 2011 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Created at: 15/05/2011 - 17:54:00
 * 
 */

package org.jrimum.utilix;

/**
 * Classe utilitária para lançar exceções de modo ecapsulado.
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class Exceptions {

	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws IllegalStateException
	 *             Caso haja alguma tentativa de utilização deste construtor.
	 */
	private Exceptions() {

		throwIllegalStateException("Instanciação não permitida!");
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalArgumentException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalArgumentException() {

		throw new IllegalArgumentException();
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalArgumentException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param message
	 *            Mensagem usada na exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalArgumentException(String message) {

		throw new IllegalArgumentException(message);
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalArgumentException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalArgumentException(Throwable cause) {

		throw new IllegalArgumentException(cause);
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalArgumentException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @param message
	 *            Mensagem usada na exceção
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalArgumentException(String message,
			Throwable cause) {

		throw new IllegalArgumentException(message, cause);
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalStateException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalStateException() {

		throw new IllegalStateException();
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalStateException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param message
	 *            Mensagem usada na exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalStateException(String message) {

		throw new IllegalStateException(message);
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalStateException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalStateException(Throwable cause) {

		throw new IllegalStateException(cause);
	}

	/**
	 * Lança imediatamente uma {@linkplain IllegalStateException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @param message
	 *            Mensagem usada na exceção
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwIllegalStateException(String message,
			Throwable cause) {

		throw new IllegalStateException(message, cause);
	}

	/**
	 * Lança imediatamente uma {@linkplain UnsupportedOperationException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwUnsupportedOperationException() {

		throw new UnsupportedOperationException();
	}

	/**
	 * Lança imediatamente uma {@linkplain UnsupportedOperationException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param message
	 *            Mensagem usada na exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwUnsupportedOperationException(String message) {

		throw new UnsupportedOperationException(message);
	}

	/**
	 * Lança imediatamente uma {@linkplain UnsupportedOperationException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwUnsupportedOperationException(Throwable cause) {

		throw new UnsupportedOperationException(cause);
	}

	/**
	 * Lança imediatamente uma {@linkplain UnsupportedOperationException}.
	 * 
	 * @param <T>
	 *            Qualquer tipo.
	 * 
	 * @param message
	 *            Mensagem usada na exceção
	 * @param cause
	 *            Causa da exceção
	 * 
	 * @return Não retorna nada, criado apenas para satisfazer alguma condição
	 *         de retorno.
	 */
	public static <T> T throwUnsupportedOperationException(String message,
			Throwable cause) {

		throw new UnsupportedOperationException(message, cause);
	}
}
