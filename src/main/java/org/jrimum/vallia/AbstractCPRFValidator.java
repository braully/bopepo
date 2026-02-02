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
 * Created at: 30/03/2008 - 18:19:24
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
 * Criado em: 30/03/2008 - 18:19:24
 *
 */
package org.jrimum.vallia;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.ConfiguracaoJRimum;
import org.jrimum.utilix.ObjectUtil;

import java.io.Serializable;
import java.util.regex.Pattern;

import static org.jrimum.utilix.Objects.isNotNull;

/**
 * <p>
 * Representa a família de validadores para o cadastro de pessoa na receita
 * federal (CPRF).
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 * Mercantil</a>
 * @version 0.2
 * @since 0.2
 */
public abstract class AbstractCPRFValidator {

    private static final long serialVersionUID = -3107505512223559948L;

    /**
     * <p>
     * Cadastro de pessoa para validação.
     * </p>
     */
    protected StringBuilder codigoDoCadastro;

    /**
     * <p>
     * Validador de dígito verificador do cadastro de Pessoa.
     * </p>
     */
    protected AbstractDigitoVerificador digitoVerificador;

    /**
     * <p>
     * Expressão regular para validação de CPF: "###.###.###-##" ou
     * "###########".
     * </p>
     */
    private static final String REGEX_CPF = "(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})|(\\d{11})";

    /**
     * <p>
     * Expressão regular para validação de CNPJ: "##.###.###/####-##" ou
     * "##############".
     * </p>
     */
    private static final String REGEX_CNPJ = "([A-Za-z0-9]{2}\\.[A-Za-z0-9]{3}\\.[A-Za-z0-9]{3}/[A-Za-z0-9]{4}-[A-Za-z0-9]{2})|([A-Za-z0-9]{14})";
    /**
     * <p>
     * Expressão regular para validação de um cadastro: "###" ou
     * "##############".
     * </p>
     */
    private static final String REGEX_CADASTRO = "[A-Za-z0-9]{3,14}";

    /**
     * <p>
     * Representa o tipo de cadastro e fornece o autenticador correto de a cordo
     * com este tipo.
     * </p>
     *
     * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
     * @author Misael Barreto
     * @author Rômulo Augusto
     * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
     * Mercantil</a>
     * @version 0.2
     * @since 0.2
     */
    public enum TipoDeCPRF implements Serializable {

        CPF, CNPJ;

        public AbstractCPRFValidator getAutenticador() {

            AbstractCPRFValidator validador = null;

            switch (this) {

                case CPF:

                    validador = new CPFValidator();
                    validador.digitoVerificador = new CPFDV();

                    break;

                case CNPJ:

                    validador = new CNPJValidator();
                    validador.digitoVerificador = new CNPJDV();

                    break;
            }

            return validador;
        }

        /**
         * @see br.com.nordestefomento.jrimum.utilix.ObjectUtil#toString()
         * @see java.lang.Enum#toString()
         */
        public String toString() {
            return ObjectUtil.toString(this);
        }
    }

    /**
     * @see br.com.nordestefomento.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF
     */
    @SuppressWarnings("unused")
    private TipoDeCPRF tipoDeCadastro;

    /**
     * <p>
     * Valida o dígito verificador do cadastro de pessoa passado durante a
     * instanciação do validador.
     * </p>
     *
     * @return verdadeiro se o dígito verificador for válido.
     */
    public abstract boolean isValido();

    /**
     * <p>
     * Revome a formatação existente em <code>códigoDoCadastro</code>.
     * </p>
     */
    protected abstract void removeFormatacao();

    /**
     * <p>
     * Recupera uma instância de um validador para o cadastro de pessoa a partir
     * de um identificador.
     * </p>
     * <p>
     * Primeiro é feita uma pré-validação que consiste em:
     * <ul>
     * <li>Verificar se o parâmetro não é nulo.</li>
     * <li>Verificar se o parâmetro não é vazio.</li>
     * <li>Verificar se o parâmetro está em algum formatador válido para
     * cadastro de pessoa.</li>
     * </ul>
     * </p>
     *
     * @param codigoDoCadastro - identificador do cadastro de pessoa.
     * @return uma instância de <code>AbstractCPRFValidator</code>.
     * @throws IllegalArgumentException - caso o parâmetro não esteja em um
     *                                  formatador válido de cadastro de pessoa.
     * @since 0.2
     */
    public static AbstractCPRFValidator create(String codigoDoCadastro)
            throws IllegalArgumentException {
        AbstractCPRFValidator validatorCPRF = null;
        validatorCPRF = create(selectTipoDeCadastro(codigoDoCadastro));
        validatorCPRF.codigoDoCadastro = new StringBuilder(codigoDoCadastro);
        validatorCPRF.removeFormatacao();
        return validatorCPRF;
    }

    /**
     * <p>
     * Cria um validador a partir do tipo de CPRF.
     * </p>
     *
     * @param tipoDeCadastro
     * @return um validador
     * @since 0.2
     */
    public static AbstractCPRFValidator create(TipoDeCPRF tipoDeCadastro) {
        AbstractCPRFValidator validatorCPRF = null;
        if (isNotNull(tipoDeCadastro)) {
            validatorCPRF = tipoDeCadastro.getAutenticador();
            validatorCPRF.tipoDeCadastro = tipoDeCadastro;
        } else {
            throw new IllegalArgumentException("Tipo de Cadastro [ "
                    + tipoDeCadastro + " ] nulo !");
        }

        return validatorCPRF;
    }

    /**
     * <p>
     * Faz a pré-validação e se correto identifica o tipo de cadastro.
     * </p>
     *
     * @param codigoDoCadastro
     * @return
     * @throws IllegalArgumentException
     * @since 0.2
     */
    private static TipoDeCPRF selectTipoDeCadastro(String codigoDoCadastro)
            throws IllegalArgumentException {

        TipoDeCPRF tipo = null;

        switch_Tipo:
        {

            if (StringUtils.isNotBlank(codigoDoCadastro)) {

                /*
                 * FILTRO
                 */
                if (Pattern.matches(REGEX_CPF, codigoDoCadastro)) {

                    tipo = TipoDeCPRF.CPF;

                    break switch_Tipo;
                }

                if (Pattern.matches(REGEX_CNPJ, codigoDoCadastro)) {

                    tipo = TipoDeCPRF.CNPJ;

                    break switch_Tipo;
                }

            }

            if (ConfiguracaoJRimum.falharEmCPRFInvalido) {
                throw new IllegalArgumentException("O código de cadastro [ "
                        + codigoDoCadastro
                        + " ] não está em um formatador válido !");
            }
        }

        return tipo;
    }

    /**
     * <p>
     * Define se os parâmetros válidos em relação a nulidade e formato de CPRF.
     * </p>
     *
     * @param codigoDoCadastro
     * @param tipoDeCadastro
     * @return indicação de aprovação
     * @throws IllegalArgumentException
     * @since 0.2
     */
    public static boolean isParametrosValidos(String codigoDoCadastro,
                                              TipoDeCPRF tipoDeCadastro) throws IllegalArgumentException {

        boolean isValido = false;

        if (isNotNull(codigoDoCadastro) && isNotNull(tipoDeCadastro)) {

            if (Pattern.matches(REGEX_CADASTRO, codigoDoCadastro)) {

                isValido = true;

            } else {

                throw new IllegalArgumentException(
                        "O cadastro está em um tamanho incorreto ou não exsite: [ "
                                + codigoDoCadastro + " ]");
            }
        } else {

            throw new IllegalArgumentException(
                    "O tipo de cadastro está incorreto: [ " + tipoDeCadastro
                            + " ] ou o cadastro não exsite: [ "
                            + codigoDoCadastro + " ]");
        }

        return isValido;
    }

    /**
     * <p>
     * Recupera o cadastro de pessoa a ser validado.
     * Obs.: A String retornada não possui formatação, ou seja, possui apenas os
     * dígitos.
     * </p>
     *
     * @return cadastro de pessoa a ser validado.
     * @since 0.2
     */
    public String getCodigoDoCadastro() {

        return codigoDoCadastro.toString();
    }

    /**
     * <p>
     * Indica se o validador é de pessoa física.
     * </p>
     *
     * @return verdadeiro se for de pessoa física.
     * @since 0.2
     */
    public boolean isFisica() {

        return this instanceof CPFValidator;
    }

    /**
     * </p> Indica se o validador é de pessoa jurídica. </p>
     *
     * @return verdadeiro se for de pessoa jurídica.
     * @since 0.2
     */
    public boolean isJuridica() {

        return this instanceof CNPJValidator;
    }

}
