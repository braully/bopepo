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
 * Created at: 30/03/2008 - 18:18:19
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
 * Criado em: 30/03/2008 - 18:18:19
 * 
 */

package org.jrimum.utilix;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.replaceChars;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.jrimum.utilix.Objects.isNotNull;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;

/**
 * Esta classe tem a responsabilidade de prover serviços utilitários
 * relacionados a manipulação de <code>Strings</code>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public final class Strings {

	public static final String WHITE_SPACE = " ";

	/**
	 * Utility class pattern: classe não instanciável
	 * 
	 * @throws AssertionError
	 *             caso haja alguma tentativa de utilização deste construtor.
	 */
	private Strings() {

		Exceptions.throwIllegalStateException("Instanciação não permitida!");
	}

	/**
	 * Elimina simbolos como:
	 * 
	 * <pre>>
	 * <,;.:!*&%+-_<>[]\/
	 * </pre>
	 * 
	 * @param str
	 *            String com os símbolos a serem removidos.
	 * @return String sem símbolos.
	 * @since 0.2
	 */
	public static String eliminateSymbols(final String str) {

		String modifiedStr = str;

		if (isNotBlank(modifiedStr)) {

			modifiedStr = replace(modifiedStr, "-", EMPTY);
			modifiedStr = replace(modifiedStr, "_", EMPTY);
			modifiedStr = replace(modifiedStr, "=", EMPTY);
			modifiedStr = replace(modifiedStr, "+", EMPTY);
			modifiedStr = replace(modifiedStr, "%", EMPTY);
			modifiedStr = replace(modifiedStr, "*", EMPTY);
			modifiedStr = replace(modifiedStr, "@", EMPTY);
			modifiedStr = replace(modifiedStr, "#", EMPTY);
			modifiedStr = replace(modifiedStr, "&", EMPTY);
			modifiedStr = replace(modifiedStr, ":", EMPTY);
			modifiedStr = replace(modifiedStr, ".", EMPTY);
			modifiedStr = replace(modifiedStr, ";", EMPTY);
			modifiedStr = replace(modifiedStr, ",", EMPTY);
			modifiedStr = replace(modifiedStr, "!", EMPTY);
			modifiedStr = replace(modifiedStr, "?", EMPTY);
			modifiedStr = replace(modifiedStr, "(", EMPTY);
			modifiedStr = replace(modifiedStr, ")", EMPTY);
			modifiedStr = replace(modifiedStr, "{", EMPTY);
			modifiedStr = replace(modifiedStr, "}", EMPTY);
			modifiedStr = replace(modifiedStr, "[", EMPTY);
			modifiedStr = replace(modifiedStr, "]", EMPTY);
			modifiedStr = replace(modifiedStr, "/", EMPTY);
			modifiedStr = replace(modifiedStr, "\\", EMPTY);
			modifiedStr = replace(modifiedStr, ">", EMPTY);
			modifiedStr = replace(modifiedStr, "<", EMPTY);
			modifiedStr = replace(modifiedStr, "\"", EMPTY);
			modifiedStr = replace(modifiedStr, "'", EMPTY);
			modifiedStr = replace(modifiedStr, "`", EMPTY);
		}

		return modifiedStr;
	}

	/**
	 * Remove os zeros iniciais de uma <code>String</code>, seja ela numérica ou
	 * não.
	 * <p>
	 * <code>removeStartWithZeros("00000") => 0</code>
	 * <code>removeStartWithZeros("00023") => 23</code>
	 * <code>removeStartWithZeros("02003") => 2003</code>
	 * <p>
	 * 
	 * @param str
	 * @return a string sem zeros inicias ou um único zero.
	 * 
	 * @since 0.2
	 */

	public static String removeStartWithZeros(final String str) {

		String withoutZeros = EMPTY;
		final String zero = "0";

		if (isNotNull(str)) {

			if (startsWith(str, zero)) {

				withoutZeros = removeStart(str, zero);

				while (startsWith(withoutZeros, zero)) {
					withoutZeros = removeStart(withoutZeros, zero);
				}

				if (withoutZeros.trim().length() == 0) {
					withoutZeros = zero;
				}

			} else {
				withoutZeros = str;
			}
		}

		return withoutZeros;
	}

	/**
	 * Remove a acentuação do texto, que inclui os acentos:
	 * <ul>
	 * <li>Agudo. ex.: á</li>
	 * <li>Grave. ex.: à</li>
	 * <li>Til. ex.: ã</li>
	 * <li>Trema. ex.: ä</li>
	 * <li>Circunflexo. ex.: â</li>
	 * </ul>
	 * e o Cedilha (ç).
	 * <p>
	 * Os acentos são removidos tanto para letras minúsculas como para letras
	 * maiúsculas.
	 * </p>
	 * 
	 * @param value
	 *            String com os caracteres a serem removidos.
	 * @return String sem acentuação.
	 * @since 0.2
	 */
	public static String eliminateAccent(final String value) {

		String modifiedValue = value;

		// Para ç e Ç
		modifiedValue = replaceChars(modifiedValue, '\u00E7', 'c');
		modifiedValue = replaceChars(modifiedValue, '\u00C7', 'C');

		// Para à, á, â, ã e ä
		modifiedValue = replaceChars(modifiedValue, '\u00E0', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E1', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E2', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E3', 'a');
		modifiedValue = replaceChars(modifiedValue, '\u00E4', 'a');

		// Para è, é, ê e ë
		modifiedValue = replaceChars(modifiedValue, '\u00E8', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00E9', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00EA', 'e');
		modifiedValue = replaceChars(modifiedValue, '\u00EB', 'e');

		// Para ì, í, î e ï
		modifiedValue = replaceChars(modifiedValue, '\u00EC', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00ED', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00EE', 'i');
		modifiedValue = replaceChars(modifiedValue, '\u00EF', 'i');

		// Para ò, ó, ô, õ e ö
		modifiedValue = replaceChars(modifiedValue, '\u00F2', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F3', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F4', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F5', 'o');
		modifiedValue = replaceChars(modifiedValue, '\u00F6', 'o');

		// Para ù, ú, û e ü
		modifiedValue = replaceChars(modifiedValue, '\u00F9', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FA', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FB', 'u');
		modifiedValue = replaceChars(modifiedValue, '\u00FC', 'u');

		// Para À, Á, Â, Ã e Ä
		modifiedValue = replaceChars(modifiedValue, '\u00C0', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C1', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C2', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C3', 'A');
		modifiedValue = replaceChars(modifiedValue, '\u00C4', 'A');

		// Para È, É, Ê e Ë
		modifiedValue = replaceChars(modifiedValue, '\u00C8', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00C9', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00CA', 'E');
		modifiedValue = replaceChars(modifiedValue, '\u00CB', 'E');

		// Para Ì, Í, Î e Ï
		modifiedValue = replaceChars(modifiedValue, '\u00CC', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CD', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CE', 'I');
		modifiedValue = replaceChars(modifiedValue, '\u00CF', 'I');

		// Para Ò, Ó, Ô, Õ e Ö
		modifiedValue = replaceChars(modifiedValue, '\u00D2', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D3', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D4', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D5', 'O');
		modifiedValue = replaceChars(modifiedValue, '\u00D6', 'O');

		// Para Ù, Ú, Û e Ü
		modifiedValue = replaceChars(modifiedValue, '\u00D9', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DA', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DB', 'U');
		modifiedValue = replaceChars(modifiedValue, '\u00DC', 'U');

		return modifiedValue;
	}

	/**
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	public static void checkNotNumeric(String value, String message) {

		checkNotNumeric(value, message, message);
	}

	/**
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	public static void checkNotNumeric(String value) {

		checkNotNumeric(value, "String nula!", format(
				"Valor inválido. String [\"%s\"] não numérica!", value));
	}

	/**
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>EMPTY</code>) e não possui apenas
	 * espaços em branco.
	 * <p>
	 * Lança exceção, com a mensagem passada por parâmetro (segundo parâmetro
	 * String), caso não preencha estes requisitos.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param message
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	public static void checkNotBlank(String value, String message) {

		checkNotBlank(value, message, message);
	}

	/**
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>EMPTY</code>) e não possui apenas
	 * espaços em branco. Lança exceção caso não preencha estes requisitos.
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	public static void checkNotBlank(String value) {

		checkNotBlank(
				value,
				"String nula!",
				format(
						"Valor inválido. String [\"%s\"] vazia ou contendo somente espaços em branco!",
						value));
	}
	
	/**
	 * Retorna uma dado valor padrão quando a string informada for considerada
	 * como blank por {@linkplain StringUtils#isBlank(String)}.
	 * 
	 * <pre>
	 * whenBlank(null, null)  = null
	 * whenBlank(null, "")    = ""
	 * whenBlank("", "")      = ""
	 * whenBlank("", "a")     = "a"
	 * whenBlank(" ", "ex")   = "ex"
	 * whenBlank("abc", *)    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            String a testar e ser usada como valor de retorno se não for
	 *            blank, pode ser <code>null</code>
	 * @param defaultValue
	 *            - O valor padrão retornado caso a string testada seja blank,
	 *            pode ser <code>null</code>
	 * @return <code>String</code> - Se não for blank, caso contrário
	 *         <code>defaultValue</code>
	 * 
	 * @since 0.2
	 */
	public static String whenBlank(String str, String defaultValue) {

		return (isNotBlank(str) ? str : defaultValue);
	}

	/**
	 * Método privado para fins de reutilização de código.
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code> e não é numérica, ou seja, se a string não contém
	 * somente dígitos unicode.
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageNullPointer</code> (segundo parâmetro String), caso o valor
	 * passado seja <code>null</code>
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageIllegalArgument</code> (terceiro parâmetro String), caso o
	 * valor passado não seja numérico.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção.
	 * 
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string não seja numérica.
	 * 
	 * @since 0.2
	 */
	private static void checkNotNumeric(String value,
			String messageNullPointer, String messageIllegalArgument) {

		Objects.checkNotNull(value, messageNullPointer);

		if (!isNumeric(value)) {
			Exceptions.throwIllegalArgumentException(messageIllegalArgument);
		}
	}

	/**
	 * Método privado para fins de reutilização de código.
	 * <p>
	 * Verifica se a <code>String</code> passada por parâmetro não é
	 * <code>null</code>, não é vazia (<code>StringUtils.EMPTY</code>) e não
	 * possui apenas espaços em branco.
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageNullPointer</code> (segundo parâmetro String), caso o valor
	 * passado seja <code>null</code>
	 * </p>
	 * <p>
	 * Lança <code>IllegalArgumentException</code>, com a mensagem definida em
	 * <code>messageIllegalArgument</code> (terceiro parâmetro String), caso o
	 * valor passado seja vazio.
	 * </p>
	 * 
	 * @param value
	 *            - String analisada
	 * 
	 * @param messageNullPointer
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @param messageIllegalArgument
	 *            - Mensagem utiliada na exceção.
	 * 
	 * @throws IllegalArgumentException - Caso a string seja <code>null</code> ou
	 *        caso a string seja vazia.
	 * 
	 * @since 0.2
	 */
	private static void checkNotBlank(String value, String messageNullPointer,
			String messageIllegalArgument) {

		Objects.checkNotNull(value, messageNullPointer);

		if (isBlank(value)) {
			Exceptions.throwIllegalArgumentException(messageIllegalArgument);
		}
	}
	
	/**
	 * Retorna uma string de tamanho informado {@code length} com o
	 * preenchimento de zeros à esquerda para o número informado.
	 * 
	 * @param numberToFill
	 *            Número a ser transformado em string com zeros à esquerda
	 * @param length
	 *            Tamanho final da string com zeros à esquerda
	 * @return string com zeros a esquerda
	 */
	public static String fillWithZeroLeft(int numberToFill, int length){
		return fillWithZeroLeft(String.valueOf(numberToFill), length);
	}
	
	/**
	 * Retorna uma string de tamanho informado {@code length} com o
	 * preenchimento de zeros à esquerda para o número informado.
	 * 
	 * @param numberToFill
	 *            Número a ser transformado em string com zeros à esquerda
	 * @param length
	 *            Tamanho final da string com zeros à esquerda
	 * @return string com zeros a esquerda
	 */
	public static String fillWithZeroLeft(long numberToFill, int length){
		return fillWithZeroLeft(String.valueOf(numberToFill), length);
	}

	/**
	 * Retorna uma string de tamanho informado {@code length} com o
	 * preenchimento de zeros à esquerda para a string informada.
	 * 
	 * @param stringToFill
	 *            String a ser preenchida com zeros à esquerda
	 * @param length
	 *            Tamanho final da string com zeros à esquerda
	 * @return string com zeros a esquerda
	 */
	public static String fillWithZeroLeft(String stringToFill, int length){
		return leftPad(stringToFill, length, "0");
	}
}
