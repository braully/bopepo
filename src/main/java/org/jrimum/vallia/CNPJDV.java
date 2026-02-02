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
 * Created at: 30/03/2008 - 18:22:39
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
 * Criado em: 30/03/2008 - 18:22:39
 *
 */
package org.jrimum.vallia;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.texgit.Filler;

import java.util.regex.Pattern;

/**
 * <p>
 * O cálculo do dígito verificador do CNPJ é realizado em duas etapas e é
 * auxiliado pela rotina de módulo 11.
 * </p>
 * <p>
 * Abaixo é demonstrado como esse cálculo é feito:
 * </p>
 * <h3>Exemplo para um número hipotético 11.222.333/0001-XX:</h3>
 * <p>
 * Primeiramente obtém-se um número R, calculado através da rotina de módulo 11,
 * a partir dos doze primeiros números do CNPJ, nesse caso 112223330001.
 * Para obter o primeiro dígito verificador deve-se seguir a seguinte lógica:
 * <p>
 * <p>
 * Se o número R for menor que 2, o dígito terá valor 0 (zero); senão, será a
 * subtração do valor do módulo (11) menos o valor do número R, ou seja,
 * <code>DV = 11 - R</code>.
 * </p>
 * <p>
 * Para obter o segundo dígito verificador é da mesma forma do primeiro, porém
 * deve ser calculado a partir dos treze primeiros números do CNPJ, ou seja,
 * 112223330001 + primeiro dígito.
 * </p>
 * <p>
 * Obs.: O limite mínimo e máximo do módulo 11 são 2 e 9, respectivamente.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento Mercantil</a>
 * @author <a href="mailto:roda7x@gmail.com">Rodrigo Carvalho</a>
 * @version 0.3
 * @see Modulo
 * @since 0.2
 */
public class CNPJDV extends AbstractDigitoVerificador {

    /**
     *
     */
    private static final long serialVersionUID = -4702398145481452503L;

    /**
     * <p>
     * Liminte mínimo do para cálculo no módulo 11.
     * </p>
     */
    private static final int LIMITE_MINIMO = 2;

    /**
     * <p>
     * Liminte máximo do para cálculo no módulo 11.
     * </p>
     */
    private static final int LIMITE_MAXIMO = 9;

    /**
     * <p>
     * Expressão regular para validação dos doze primeiros números do CNPJ sem
     * formatação: "############".
     * </p>
     */
    private static final String REGEX_CNPJ_DV = "[A-Z0-9]{12}";

    /**
     * <p>
     * Expressão regular para validação dos doze primeiros digitos alfanuméricos do CNPJ
     * formatado: "##.###.###/####".
     * </p>
     */
    private static final String REGEX_CNPJ_DV_FORMATTED = "[A-Za-z0-9]{2}\\.[A-Za-z0-9]{3}\\.[A-Za-z0-9]{3}/[A-Za-z0-9]{4}";

    /**
     * @see br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(long)
     * @since 0.2
     */
    @Override
    public int calcule(long numero) {

        return calcule(Filler.ZERO_LEFT.fill(String.valueOf(numero), 12));
    }

    /**
     * Calcula os dois dígitos verificadores (DV) de um CNPJ a partir da base informada.
     * <p>
     * Aceita CNPJ numérico ou alfanumérico, com ou sem máscara. O valor é normalizado
     * para maiúsculas e tem os caracteres não alfanuméricos removidos antes do cálculo.
     * O método valida se a base possui exatamente 12 caracteres e identifica
     * automaticamente se o cálculo deve seguir a regra numérica (tradicional) ou
     * alfanumérica (novo padrão).
     * </p>
     *
     * @param numero CNPJ base (12 caracteres), podendo estar formatado ou não.
     * @return Os dois dígitos verificadores concatenados (ex: {@code 75}).
     * @throws IllegalArgumentException Se o CNPJ for nulo, vazio, inválido ou não
     *                                  atender ao formato esperado após normalização.
     * @since 0.3
     */
    @Override
    public int calcule(String numero) throws IllegalArgumentException {

        if (StringUtils.isBlank(numero)) {
            throw new IllegalArgumentException(
                    "O CNPJ esta vazio ou invalido");
        }

        String base12 = numero.toUpperCase().replaceAll("[^0-9A-Z]", "");

        if (!Pattern.matches(REGEX_CNPJ_DV, base12)) {
            throw new IllegalArgumentException(
                    "O CNPJ [ "
                            + numero
                            + " ] é invalido");
        }

        boolean isNumerico = base12.matches("[0-9]{12}");

        int dv1 = calculeDigito(base12, 12, isNumerico);
        int dv2 = calculeDigito(base12 + dv1, 13, isNumerico);

        if (dv1 < 0 || (dv2 < 0)) {
            throw new IllegalArgumentException(
                    "O CNPJ [ "
                            + numero
                            + " ] é invalido");
        }

        return Integer.parseInt(dv1 + "" + dv2);

    }


    private static int calculeDigito(String cnpj, int tamanho, boolean isNumerico) {
        int soma = 0;
        int peso = 2;

        for (int i = tamanho - 1; i >= 0; i--) {
            char c = cnpj.charAt(i);

            int valor;
            if (isNumerico) {
                if (c < '0' || c > '9') return -1;
                valor = c - '0';
            } else {
                if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'))) return -1;
                valor = ((int) c) - 48;
            }

            soma += valor * peso;
            peso++;
            if (peso > 9) peso = 2;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

}
