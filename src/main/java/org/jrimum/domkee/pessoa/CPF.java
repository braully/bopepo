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
 * Created at: 30/03/2008 - 19:07:16
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
 * Criado em: 30/03/2008 - 19:07:16
 *
 */
package org.jrimum.domkee.pessoa;

import org.jrimum.utilix.Exceptions;
import org.jrimum.vallia.AbstractCPRFValidator;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.jrimum.utilix.Strings.fillWithZeroLeft;

/**
 * <p>
 * Representa o cadastro de pessoa física (CPF), um número identificador de todo
 * contribuinte (pessoas físicas brasileiras ou estrangeiras com negócios no
 * Brasil).
 * </p>
 * <p>
 * O formatador do CPF é "###.###.###-XX", onde XX é o dígito verificador do
 * número.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento Mercantil</a>
 * @author <a href="mailto:roda7x@gmail.com">Rodrigo Carvalho</a>
 * @version 0.3
 * @since 0.2
 */
public class CPF extends AbstractCPRF {

    /**
     *
     */
    private static final long serialVersionUID = 5910970842832308496L;

    public CPF(String strCPF) {
        this.autenticadorCP = AbstractCPRFValidator.create(strCPF);
        if (!autenticadorCP.isValido()) {
            throw new IllegalArgumentException(
                    "O cadastro de pessoa [ " + strCPF + " ] não é válido.");
        }
        init(strCPF);
    }

    protected void init(String strCPF) {
        if (isNumeric(strCPF)) {
            initFromNotFormattedString(strCPF);
        } else {
            initFromFormattedString(strCPF);
        }
    }

    public CPF(Long numCPF) {
        try {
            if (AbstractCPRFValidator.isParametrosValidos(String.valueOf(numCPF),
                    TipoDeCPRF.CPF)) {
                this.autenticadorCP = AbstractCPRFValidator.create(fillWithZeroLeft(numCPF, 11));
                if (autenticadorCP.isValido()) {
                    initFromFormattedString(autenticadorCP.getCodigoDoCadastro());
                } else {
                    throw new IllegalArgumentException(
                            "O cadastro de pessoa [ " + numCPF + " ] não é válido.");
                }
                if (autenticadorCP.isValido()) {
                    initFromNumber(numCPF);
                } else {
                    Exceptions.throwIllegalArgumentException("O cadastro de pessoa [ \"" + numCPF + "\" ] não é válido.");
                }
            }
        } catch (Exception e) {
            if (!(e instanceof CPFException)) {
                throw new CPFException(e);
            }
        }
    }

    private void initFromNumber(Long numCPF) {
        try {
            this.setCodigoFormatado(format(fillWithZeroLeft(numCPF, 11)));
            this.setCodigo(String.valueOf(numCPF));
        } catch (Exception e) {
            throw new CPFException(e);
        }
    }

    private void initFromFormattedString(String strCPF) {
        try {
            this.setCodigoFormatado(strCPF);
            this.setCodigo(removeFormat(strCPF));
        } catch (Exception e) {
            throw new CPFException(e);
        }
    }

    private void initFromNotFormattedString(String strCPF) {
        try {
            this.setCodigoFormatado(format(strCPF));
            this.setCodigo(strCPF);
        } catch (Exception e) {
            throw new CPFException(e);
        }
    }

    private String format(String strCPF) {

        StringBuilder codigoFormatado = new StringBuilder(strCPF);

        codigoFormatado.insert(3, '.');
        codigoFormatado.insert(7, '.');
        codigoFormatado.insert(11, '-');

        return codigoFormatado.toString();
    }

    private String removeFormat(String codigo) {

        codigo = codigo.replace(".", "");
        codigo = codigo.replace("-", "");

        return codigo;
    }

}
