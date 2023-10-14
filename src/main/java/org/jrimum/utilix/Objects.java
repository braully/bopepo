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

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Classe utilitária para validações de objetos em geral, como verificações de
 * objetos nulos, com e sem exceções.
 *
 * <p>
 * Fornece métodos booleanos e métodos que verificam se o valor do objeto está
 * de acordo com o desejado e, caso não estejam, lançam exceção.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public final class Objects {

    /**
     * Utility class pattern: classe não instanciável
     *
     * @throws IllegalStateException Caso haja alguma tentativa de utilização
     * deste construtor.
     */
    private Objects() {

        Exceptions.throwIllegalStateException("Instanciação não permitida!");
    }

    /**
     * Verifica se o objeto passado por parâmetro é {@code null}.
     *
     * @param object - Objeto analisado
     *
     * @return (object == null)
     *
     * @since 0.2
     */
    public static boolean isNull(Object object) {

        return (object == null);
    }

    /**
     * Verifica se o objeto passado por parâmetro <strong>não</strong> é
     * {@code null}.
     * <p>
     * É o mesmo que usar o método {@code isNull} da forma {@code !isNull(obj)},
     * porém de forma mais legível.
     * </p>
     *
     * @param object - Objeto analisado
     *
     * @return (object != null)
     *
     * @see #isNull(Object)
     *
     * @since 0.2
     */
    public static boolean isNotNull(Object object) {

        return (object != null);
    }

    /**
     * Verifica se existe uma referência ao objeto dado.
     *
     * @param object - Objeto analisado
     *
     * @return (object != null);
     *
     * @see #isNotNull(Object)
     *
     * @since 0.2
     */
    public static boolean exists(Object object) {

        return (object != null);
    }

    /**
     * Verifica a verdade de uma expressão e lança uma
     * {@code IllegalArgumentException}, caso a expressão seja
     * <strong>false</strong>.
     *
     * @param expression - Qualquer expressão booleana
     * @throws IllegalArgumentException se {@code expression == false}
     *
     * @since 0.2
     */
    public static void checkArgument(boolean expression) {

        if (!expression) {
            Exceptions.throwIllegalArgumentException();
        }
    }

    /**
     * Verifica a verdade de uma expressão e lança uma
     * {@code IllegalArgumentException}, com a mensagem iformada, caso a
     * expressão seja <strong>false</strong>.
     *
     * @param expression - Qualquer expressão booleana
     * @param message - Mensagem utilizada na exceção
     * @throws IllegalArgumentException se {@code expression == false}
     *
     * @since 0.2
     */
    public static void checkArgument(boolean expression, String message) {

        if (!expression) {
            Exceptions.throwIllegalArgumentException(message);
        }
    }

    /**
     * Verifica a verdade de uma expressão e lança uma
     * {@code IllegalStateException}, caso a expressão seja
     * <strong>false</strong>.
     *
     * @param expression - Qualquer expressão booleana
     * @throws IllegalStateException se {@code expression == false}
     *
     * @since 0.2
     */
    public static void checkState(boolean expression) {

        if (!expression) {
            Exceptions.throwIllegalStateException();
        }
    }

    /**
     * Verifica a verdade de uma expressão e lança uma
     * {@code IllegalStateException}, com a mensagem iformada, caso a expressão
     * seja <strong>false</strong>.
     *
     * @param expression - Qualquer expressão booleana
     * @param message - Mensagem utilizada na exceção
     * @throws IllegalStateException se {@code expression == false}
     *
     * @since 0.2
     */
    public static void checkState(boolean expression, String message) {

        if (!expression) {
            Exceptions.throwIllegalStateException(message);
        }
    }

    /**
     * Verifica se o objeto é nulo e lança {@code IllegalArgumentException} ,
     * com a mensagem informada, caso <strong>não</strong> seja.
     *
     * @throws IllegalArgumentException - Caso o objeto <strong>não</strong>
     * seja {@code null}.
     *
     * @see #isNull(Object)
     * @see #isNotNull(Object)
     *
     * @since 0.2
     *
     * @param object - Objeto analisado
     * @param message - Mensagem utilizada na exceção
     */
    public static void checkNull(Object object, String message) {

        if (object != null) {

            Exceptions.throwIllegalArgumentException(message);
        }
    }

    /**
     * Verifica se o objeto é nulo e lança {@code IllegalArgumentException} caso
     * <strong>não</strong> seja.
     *
     * @param object - Objeto analisado
     *
     * @throws IllegalArgumentException - Caso o objeto <strong>não</strong>
     * seja {@code null}.
     *
     * @see #checkNull(Object, String)
     * @see #isNull(Object)
     * @see #isNotNull(Object)
     *
     * @since 0.2
     */
    public static void checkNull(Object object) {

        checkNull(object, new StringBuilder("Objeto não nulo! Valor [ ")
                .append(object).append(" ].").toString());
    }

    /**
     * Verifica se o objeto <strong>não</strong> é nulo e lança
     * {@code IllegalArgumentException}, com a mensagem informada, caso seja.
     *
     * @param object - Objeto analisado
     * @param message - Mensagem utilizada na exceção
     *
     * @throws IllegalArgumentException - Caso o objeto seja {@code null}.
     *
     * @see #isNull(Object)
     * @see #isNotNull(Object)
     *
     * @since 0.2
     */
    public static void checkNotNull(Object object, String message) {

        if (object == null) {

            Exceptions.throwIllegalArgumentException(message);
        }
    }

    /**
     * Verifica se o objeto <strong>não</strong> é nulo e lança
     * {@code IllegalArgumentException} caso seja.
     *
     * @param object - Objeto analisado
     *
     * @throws IllegalArgumentException - Caso o objeto seja {@code null}.
     *
     * @see #checkNotNull(Object, String)
     * @see #isNull(Object)
     * @see #isNotNull(Object)
     *
     * @since 0.2
     */
    public static void checkNotNull(Object object) {

        checkNotNull(object, "Objeto nulo!");
    }

    /**
     * Retorna um dado valor padrão quando o determinado objeto for nulo.
     *
     * <pre>
     * whenNull(null, null)      = null
     * whenNull(null, "")        = ""
     * whenNull(null, "ex")      = "ex"
     * whenNull("abc", *)        = "abc"
     * whenNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param <T> tipo do objeto pretendido
     * @param object - O {@code objeto} a testar, pode ser {@code null}
     * @param defaultValue - O valor padrão retornado, pode ser {@code null}
     * @return {@code object} - Se não nulo, caso contrário {@code defaultValue}
     *
     * @since 0.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T whenNull(Object object, Object defaultValue) {

        return (T) (object != null ? object : defaultValue);
    }

    /**
     * Retorna um dado valor padrão quando o determinado objeto for nulo e outro
     * valor quando contrário.
     *
     * <pre>
     * whenNull(null, null, null)       = null
     * whenNull(null, "1", "2")         = "1"
     * whenNull("ok", "1", "2")         = "2"
     * </pre>
     *
     * @param <T> tipo do objeto pretendido
     * @param object - O {@code objeto} a testar, pode ser {@code null}
     * @param defaultValue - O valor padrão retornado, pode ser {@code null}
     * @param notDefaultValue - O valor quando {@code object} é nulo, pode ser
     * {@code null}
     * @return {@code defaultValue} - Se não nulo, caso contrário
     * {@code notDefaultValue}
     *
     * @since 0.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T whenNull(Object object, Object defaultValue,
            Object notDefaultValue) {
        return (T) (object == null ? defaultValue : notDefaultValue);
    }

    /**
     * Exibe os valores de instância para um objeto JRimum.
     *
     * @see org.apache.commons.lang3.builder.ToStringBuilder#reflectionToString
     *
     * @since 0.2
     *
     * @see java.lang.Object#toString()
     */
    public static String toString(Object obj) {

        return "JRimumObject: " + ToStringBuilder.reflectionToString(obj);
    }
}
