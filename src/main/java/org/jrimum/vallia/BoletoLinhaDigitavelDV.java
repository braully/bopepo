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
 * Created at: 30/03/2008 - 18:22:21
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
 * Criado em: 30/03/2008 - 18:22:21
 * 
 */
package org.jrimum.vallia;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.texgit.Filler;

/**
 * <p>
 * Segundo o padrão FEBRABAN a linha digitável possui cinco campos, dos quais
 * apenas os três primeiros necessitam de validação com dígito verificador.
 * </p>
 * <p>
 * Para fins de validação é preciso saber o seguinte:
 * <ul>
 * <li>O primerio campo tem tamanho 9 (nove) mais o dígito verificador.</li>
 * <li>O segundo campo tem tamanho 10 (dez) mais o dígito verificador.</li>
 * <li>O terceiro campo tem tamanho 10 (dez) mais o dígito verificador.</li>
 * </ul>
 * </p>
 * <p>
 * Obs1.: Todos os campos listados podem vir com um ponto (.) de separação
 * exatamente após o dígito da 5ª posição. 
 * Exemplo de linha digitável:
 * <code>99997.77213 30530.150082 18975.000003 1 10010000035000</code>
 * </p>
 * <p>
 * O cálculo do dígito verificador é descrito através da expressão
 * <code>DV = 11 - R</code>, onde R é o resultado do cálculo do módulo.
 * Obs1.: O dígito verificador será 0 (zero) se o resto (resultado do módulo)
 * for 0 (zero). 
 * Obs2.: A rotina de módulo utilizada é a módulo 10.
 * </p>
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 * Mercantil</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class BoletoLinhaDigitavelDV extends AbstractDigitoVerificador {

    private static final long serialVersionUID = -9177413216786384292L;

    /**
     * <p>
     * Módulo 10 utilizado no cálculo.
     * </p>
     */
    private static final Modulo modulo10 = new Modulo(TipoDeModulo.MODULO10);

    /**
     * <p>
     * Expressão regular para validação do campo da linha digitável, aceita os
     * seguintes formatos:
     * </p>
     * <ul type="circle"> <li>#########</li>
     * <li>#####.####</li>
     * <li>##########</li> <li>#####.#####</li> </ul>
     *
     */
    private static final String REGEX_CAMPO = "(\\d{9})|(\\d{10})|(\\d{5})\\.(\\d{4})|(\\d{5})\\.(\\d{5})";

    /**
     * <p>
     * Construtor vaizo, o mesmo que super().
     * </p>
     *
     * @since 0.2
     */
    public BoletoLinhaDigitavelDV() {
        super();
    }

    /**
     * @see
     * br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
     * @since 0.2
     */
    @Override
    public int calcule(long numero) {

        return calcule(Filler.ZERO_LEFT.fill(String.valueOf(numero), 10));
    }

    /**
     * @see
     * br.com.nordestefomento.jrimum.vallia.digitoverificador.AbstractDigitoVerificador#calcule(java.lang.String)
     * @since 0.2
     */
    @Override
    public int calcule(String numero) throws IllegalArgumentException {

        int dv = 0;
        int resto = 0;

        if (StringUtils.isNotBlank(numero)
                && Pattern.matches(REGEX_CAMPO, numero)) {

            numero = StringUtils.replaceChars(numero, ".", "");

            resto = modulo10.calcule(numero);

            if (resto != 0) {
                dv = modulo10.valor() - resto;
            }
        } else {
            throw new IllegalArgumentException(
                    "O campo [ "
                    + numero
                    + " ] da linha digitável deve conter apenas números com 9 ou 10 dígitos !");
        }

        return dv;
    }

}
