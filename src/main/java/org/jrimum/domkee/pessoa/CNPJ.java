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
 * Created at: 30/03/2008 - 19:06:56
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
 * Criado em: 30/03/2008 - 19:06:56
 * 
 */
package org.jrimum.domkee.pessoa;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import org.jrimum.ConfiguracaoJRimum;
import static org.jrimum.utilix.Strings.fillWithZeroLeft;

import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;
import org.jrimum.utilix.Strings;
import org.jrimum.vallia.AbstractCPRFValidator;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;

/**
 *
 * <p>
 * Representa o cadastro nacional de pssoa jurídica (CNPJ), um número
 * identificador de uma pessoa jurídica junto à Receita Federal, necessário para
 * que a pessoa jurídica tenha capacidade de fazer contratos e processar ou ser
 * processada.
 * </p>
 *
 * <p>
 * O formato do CNPJ é "##.###.###/####-XX", onde XX é o dígito verificador do
 * número.
 * </p>
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 * Mercantil</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class CNPJ extends AbstractCPRF {

    public CNPJ(Long numCNPJ) {

        try {

            if (AbstractCPRFValidator.isParametrosValidos(
                    String.valueOf(numCNPJ), TipoDeCPRF.CNPJ)) {

                this.autenticadorCP = AbstractCPRFValidator.create(fillWithZeroLeft(numCNPJ, 14));

                if (autenticadorCP.isValido()) {

                    initFromNumber(numCNPJ);

                } else {

                    Exceptions.throwIllegalArgumentException("O cadastro de pessoa [ \""
                            + numCNPJ + "\" ] não é válido.");
                }
            }

        } catch (Exception e) {
            if (!(e instanceof CNPJException)) {
                throw new CNPJException(e);
            }
        }

    }

    public CNPJ(String strCNPJ) {
        this.autenticadorCP = AbstractCPRFValidator.create(strCNPJ);
        if (autenticadorCP.isValido()) {
            init(strCNPJ);
        } else {

            if (ConfiguracaoJRimum.falharEmCPRFInvalido) {
                throw new CNPJException(new IllegalArgumentException(
                        "O cadastro de pessoa [ \"" + strCNPJ + "\" ] não é válido."));
            } else {
                init(strCNPJ);
            }

        }
    }

    protected void init(String strCNPJ) {
        if (isNumeric(strCNPJ)) {
            initFromNotFormattedString(strCNPJ);
        } else {
            initFromFormattedString(strCNPJ);
        }
    }

    public boolean isMatriz() {

        return getSufixoFormatado().equals("0001");
    }

    public boolean isSufixoEquals(String sufixoFormatado) {

        Strings.checkNotNumeric(sufixoFormatado, String.format("O sufixo [%s] deve ser um número natural diferente de zero!", sufixoFormatado));

        return isSufixoEquals(Integer.valueOf(sufixoFormatado));
    }

    public boolean isSufixoEquals(Integer sufixo) {

        Objects.checkNotNull(sufixo, "Sufixo nulo!");
        Objects.checkArgument(sufixo > 0, String.format("O sufixo [%s] deve ser um número natural diferente de zero!", sufixo));

        return getSufixo().equals(sufixo);
    }

    public Integer getSufixo() {

        return Integer.valueOf(getSufixoFormatado());
    }

    public String getSufixoFormatado() {

        return getCodigoFormatado().split("-")[0].split("/")[1];
    }

    private void initFromNumber(Long numCNPJ) {

        try {

            this.setCodigoFormatado(format(fillWithZeroLeft(numCNPJ, 14)));
            this.setCodigo(numCNPJ);

        } catch (Exception e) {
            throw new CNPJException(e);
        }
    }

    private void initFromFormattedString(String strCNPJ) {

        try {

            this.setCodigoFormatado(strCNPJ);
            this.setCodigo(Long.parseLong(removeFormat(strCNPJ)));

        } catch (Exception e) {
            throw new CNPJException(e);
        }
    }

    private void initFromNotFormattedString(String strCNPJ) {

        try {

            this.setCodigoFormatado(format(strCNPJ));
            this.setCodigo(Long.parseLong(strCNPJ));

        } catch (Exception e) {
            throw new CNPJException(e);
        }
    }

    private String format(String strCNPJ) {

        StringBuilder codigoFormatado = new StringBuilder(strCNPJ);

        codigoFormatado.insert(2, '.');
        codigoFormatado.insert(6, '.');
        codigoFormatado.insert(10, '/');
        codigoFormatado.insert(15, '-');

        return codigoFormatado.toString();
    }

    private String removeFormat(String codigo) {

        codigo = codigo.replace(".", "");
        codigo = codigo.replace("/", "");
        codigo = codigo.replace("-", "");

        return codigo;
    }

}
