/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 20/04/2008 - 18:11:05
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 20/04/2008 - 18:11:05
 * 
 */
package org.jrimum.vallia;

import static org.jrimum.vallia.TipoDeModulo.MODULO10;
import static org.jrimum.vallia.TipoDeModulo.MODULO11;

import org.apache.commons.lang3.StringUtils;

import org.jrimum.utilix.ObjectUtil;
import static org.jrimum.utilix.ObjectUtil.isNotNull;

/**
 * <p>
 * Representa o módulo no contexto de autenticação, ou seja, uma rotina que
 * auxilia no cálculo do dígito verificador.
 * </p>
 * <p>
 * As rotinas tradicionais são Módulo 10 e Módulo 11.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 * @version 0.2
 *
 * @since 0.2
 */
public class Modulo {

    /**
     *
     */
    private static final long serialVersionUID = -3612368544258125201L;

    /**
     * <p>
     * Mensagem da exceção lançada no método calcular.
     * </p>
     */
    private static final String O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS = "O argumento deve conter apenas números !";

    public static final int MOD10 = TipoDeModulo.MODULO10.valor();

    public static final int MOD11 = TipoDeModulo.MODULO11.valor();

    private TipoDeModulo mod;

    /**
     * <p>
     * Valor inteiro do "peso" mínimo mutiplicador utilizado no cálculo do
     * módulo.
     * </p>
     */
    private int limiteMaximo;

    /**
     * <p>
     * Valor inteiro do "peso" máximo mutiplicador utilizado no cálculo do
     * módulo.
     * </p>
     */
    private int limiteMinimo;

    /**
     * <p>
     * Inicializa um módulo com valores default.
     * </p>
     *
     * @param mod tipo do módulo
     * @see #initDefault
     * @see #calcule(String)
     * @since 0.2
     */
    public Modulo(TipoDeModulo mod) {
        super();

        if (isNotNull(mod, "modulo")) {

            this.mod = mod;
            initDefault();
        }
    }

    /**
     * <p>
     * Inicializa um módulo com o tipo definido com valores default.
     * </p>
     *
     * @param limiteMaximo peso máximo
     * @param limiteMinimo peso mínimo
     * @param mod tipo do módulo
     * @see #calcule(String)
     * @since 0.2
     */
    public Modulo(TipoDeModulo mod, int limiteMaximo, int limiteMinimo) {
        super();

        if (isNotNull(mod, "modulo")) {

            this.limiteMaximo = limiteMaximo;
            this.limiteMinimo = limiteMinimo;
            this.mod = mod;
        }
    }

    /**
     * <p>
     * Retorna o valor da instância do módulo de acordo com a <code>enum</code>
     * da instância.
     * </p>
     *
     * <p>
     * Se por um acaso a instância <code>enum</code> for nula uma
     * <code>NullPointerException</code> será lançada. Caso a <code>enum</code>
     * contenha um módulo não implementado por essa classe o retorno será
     * -1
     * </p>
     *
     * @return valor da instância do módulo.
     *
     * @since 0.2
     */
    public int valor() {

        switch (mod) {

            case MODULO10:

                return MODULO10.valor();

            case MODULO11:

                return MODULO11.valor();

            default:
                return -1;
        }

    }

    /**
     * <p>
     * Executa o cáculo do módulo 11 com os limites definidos.
     * </p>
     *
     * <p>
     * Transforma o <code>numero</code> em string e executa o método
     * calculeMod11
     * </p>
     *
     * @param numero
     * @param limiteMin
     * @param limiteMax
     * @return resultado do cálculo
     *
     * @since 0.2
     * @see #calculeMod11(String, int, int)
     */
    public static int calculeMod11(long numero, int limiteMin, int limiteMax) {

        return calculeMod11(String.valueOf(numero), limiteMin, limiteMax);
    }

    /**
     * <p>
     * Realiza o cálculo da soma na forma do módulo 11.
     * </p>
     * <p>
     * O módulo 11 funciona da seguinte maneira:
     * </p>
     * <p>
     * Cada dígito do número, começando da direita para a esquerda (menos
     * significativo para o mais significativo), é multiplicado pelo números
     * limite mínimo, limite mínimo + 1, limite mínimo + 2 e assim
     * sucessivamente até o limite máxmio definido, então inicia-se novamente a
     * contagem.
     * </p>
     * <p>
     * Exemplo para o número 654321:
     *
     * <pre>
     * +---+---+---+---+---+---+
     * | 6 | 5 | 4 | 3 | 2 | 1 |
     * +---+---+---+---+---+---+
     *   |   |   |   |   |   |
     *  x7  x6  x5  x4  x3  x2
     *   |   |   |   |   |   |
     *  =42 =30 =20 =12 =6  =2
     *   +---+---+---+---+---+-&gt;
     * </pre
     *
     * </p>
     *
     * @param numero
     * @param limiteMin
     * @param limiteMax
     * @return valor do módulo
     * @throws IllegalArgumentException
     *
     * @since 0.2
     */
    public static int calculeMod11(String numero, int limiteMin, int limiteMax)
            throws IllegalArgumentException {

        return (calculeSomaSequencialMod11(numero, limiteMin, limiteMax) % 11);
    }

    public static int calculeSomaSequencialMod11(String numero, int limiteMin,
            int limiteMax) throws IllegalArgumentException {

        int peso = 0;
        int soma = 0;

        if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {

            StringBuilder sb = new StringBuilder(numero);
            sb.reverse();

            peso = limiteMin;

            for (char c : sb.toString().toCharArray()) {
                soma += peso * Character.getNumericValue(c);
                peso++;

                if (peso > limiteMax) {
                    peso = limiteMin;
                }
            }

        } else {
            throw new IllegalArgumentException(
                    O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);
        }

        return soma;
    }

    /**
     * <p>
     * Executa o cáculo do módulo 10 com os limites definidos.
     * </p>
     *
     * <p>
     * Transforma o <code>numero</code> em string e executa o método
     * calculeMod10
     * </p>
     *
     * @param numero
     * @param limiteMin
     * @param limiteMax
     * @return resultado do cálculo
     *
     * @since 0.2
     * @see #calculeMod10(String, int, int)
     */
    public static int calculeMod10(long numero, int limiteMin, int limiteMax) {

        return calculeMod10(String.valueOf(numero), limiteMin, limiteMax);
    }

    /**
     * <p>
     * Executa o cáculo do módulo 10 com os limites definidos.
     * </p>
     *
     * <p>
     * Executa o método <code>calculeSomaSequencialMod10</code> e aplica o
     * resultado em % 10
     * </p>
     *
     * @param numero
     * @param limiteMin
     * @param limiteMax
     * @return resultado de calculeSomaSequencialMod10 % 10
     *
     * @since 0.2
     * @see #calculeSomaSequencialMod10(String, int, int)
     */
    public static int calculeMod10(String numero, int limiteMin, int limiteMax)
            throws IllegalArgumentException {

        return (calculeSomaSequencialMod10(numero, limiteMin, limiteMax) % 10);
    }

    /**
     * <p>
     * Realiza o cálculo da soma na forma do módulo 10.
     * </p>
     * <p>
     * O módulo 10 funciona da seguinte maneira:
     * </p>
     * <p>
     * Cada dígito do número, começando da direita para a esquerda (menos
     * significativo para o mais significativo), é multiplicado pelo números
     * limite mínimo, limite mínimo + 1, limite mínimo + 2 e assim
     * sucessivamente até o limite máxmio definido, então inicia-se novamente a
     * contagem.
     * </p>
     * <p>
     * Exemplo para o número 123456:
     *
     * <pre>
     * +---+---+---+---+---+---+
     * | 1 | 2 | 3 | 4 | 5 | 6 |
     * +---+---+---+---+---+---+
     *   |   |   |   |   |   |
     *  x1  x2  x1  x2  x1  x2
     *   |   |   |   |   |   |
     *  =1  =4  =3  =8  =5  =[ 3 &lt;= ( 1 + 2 &lt;==12 ) ] = 24
     *   +---+---+---+---+---+-&gt; = (24 / 10) = 3, resto 3; Então o módulo é igual a 3.
     * </pre>
     *
     * </p>
     *
     * <p>
     * Geralmente os limites para o módulo 10 são mínimo 1 e máximo 2 apenas.
     * </p>
     *
     * @param numero
     * @param limiteMin
     * @param limiteMax
     * @return soma sequencial usada no cálculo do módulo
     * @throws IllegalArgumentException
     *
     * @since 0.2
     */
    public static int calculeSomaSequencialMod10(String numero, int limiteMin,
            int limiteMax) throws IllegalArgumentException {

        int produto = 0;
        int peso = 0;
        int soma = 0;

        if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {

            StringBuilder sb = new StringBuilder(numero);
            sb.reverse();

            peso = limiteMax;

            for (char c : sb.toString().toCharArray()) {

                produto = peso * Character.getNumericValue(c);

                if (produto > 9) {

                    soma += produto / 10;
                    soma += produto % 10;
                } else {
                    soma += produto;
                }

                peso = (peso == limiteMax) ? limiteMin : limiteMax;
            }

        } else {
            throw new IllegalArgumentException(
                    O_ARGUMENTO_DEVE_CONTER_APENAS_NUMEROS);
        }

        return soma;
    }

    /**
     * <p>
     * Executa o cáculo do módulo da instância.
     * </p>
     *
     * @param numero
     * @return
     * @throws IllegalArgumentException
     *
     * @since 0.2
     */
    public int calcule(String numero) throws IllegalArgumentException {

        int modulo = 0;

        switch (mod) {

            case MODULO10:

                modulo = calculeMod10(numero, getLimiteMinimo(), getLimiteMaximo());

                break;

            case MODULO11:

                modulo = calculeMod11(numero, getLimiteMinimo(), getLimiteMaximo());

                break;
        }

        return modulo;
    }

    /**
     * <p>
     * Executa o cáculo do módulo da instância.
     * </p>
     *
     * @param numero
     * @return
     *
     * @since 0.2
     */
    public int calcule(long numero) {

        return calcule(String.valueOf(numero));
    }

    /**
     * <p>
     * Inicializa as variáveis <code>limiteMaximo</code> e
     * <code>limiteMinimo</code> com os valores padrões de acordo com a
     * instância do módulo da classe.
     * </p>
     *
     * <p>
     * Valores padrões: 
     * 
     * <code>MODULO10</code>: (limiteMinimo = 1 e limiteMaximo = 2)
     *
     * <code>MODULO11</code>: (limiteMinimo = 2 e limiteMaximo = 9)
     * </p>
     *
     * @since 0.2
     */
    private void initDefault() {

        switch (mod) {

            case MODULO10:

                setLimiteMinimo(1);
                setLimiteMaximo(2);

                break;

            case MODULO11:

                setLimiteMinimo(2);
                setLimiteMaximo(9);

                break;
        }
    }

    /**
     * @return the limiteMaximo
     */
    public int getLimiteMaximo() {
        return limiteMaximo;
    }

    /**
     * @param limiteMaximo the limiteMaximo to set
     */
    public void setLimiteMaximo(int limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    /**
     * @return the limiteMinimo
     */
    public int getLimiteMinimo() {
        return limiteMinimo;
    }

    /**
     * @param limiteMinimo the limiteMinimo to set
     */
    public void setLimiteMinimo(int limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }

    /**
     * @return the mod
     */
    public TipoDeModulo getMod() {
        return mod;
    }

    /**
     * @param mod the mod to set
     */
    public void setMod(TipoDeModulo mod) {
        this.mod = mod;
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}
