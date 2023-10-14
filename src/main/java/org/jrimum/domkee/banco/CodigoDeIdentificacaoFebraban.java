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
 * Created at: 15/06/2008 - 16:11:16
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
 * Criado em: 15/06/2008 - 16:11:16
 * 
 */
package org.jrimum.domkee.banco;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.texgit.Filler;
import org.jrimum.utilix.Objects;

/**
 * <p>
 * Código de identificação dos órgãos/empresas junto à
 * <a href="http://www.febraban.org.br/">FEBRANBAN</a>.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:valdinei@elotech.com.br">Valdinei Troli</a>
 *
 * @since 0.3
 *
 * @version 0.3
 */
public class CodigoDeIdentificacaoFebraban {

    private static final long serialVersionUID = 4426867246160868026L;

    private Integer codigo;

    /**
     * @param codigo
     */
    public CodigoDeIdentificacaoFebraban(Integer codigo) {
        super();
        setCodigo(codigo);
    }

    /**
     * @param codigo
     */
    public CodigoDeIdentificacaoFebraban(String codigo) {
        super();
        setCodigo(codigo);
    }

    /**
     * @return the codigo formatado ex: "0323"
     */
    public String getCodigoFormatado() {
        return Filler.ZERO_LEFT.fill(getCodigo(), 4);
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        if (Objects.isNull(codigo)) {
            setCodigo("");
        } else {
            setCodigo(String.valueOf(codigo));
        }
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        if (isCodigoValido(codigo)) {
            this.codigo = Integer.parseInt(codigo);
        } else {
            throw new IllegalArgumentException("O código de identificação FEBRABAN tem de ser um número inteiro entre 0 e 9999.");
        }
    }

    private boolean isCodigoValido(String codigo) {
        boolean result = false;

        if (StringUtils.isNotEmpty(codigo) && StringUtils.isNumeric(codigo)) {
            int codigoAsInteger = Integer.parseInt(codigo);

            if (codigoAsInteger > 0 && codigoAsInteger < 9999) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
