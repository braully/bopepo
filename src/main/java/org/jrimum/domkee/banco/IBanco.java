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
 * Created at: 30/03/2008 - 18:59:18
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
 * Criado em: 30/03/2008 - 18:59:18
 * 
 */
package org.jrimum.domkee.banco;

import java.awt.Image;
import org.jrimum.domkee.pessoa.IPessoaJuridica;


/**
 *
 * <p>
 * Interface que define o contrato para a criação de um novo banco (instituição
 * financeira) supervisionada pelo <a href="http://www.bcb.gov.br/">BACEN</a>.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public interface IBanco extends IPessoaJuridica {

    /**
     * @see #setCodigoDeCompensacaoBACEN(CodigoDeCompensacaoBACEN)
     *
     * @return CodigoDeCompensacaoBACEN
     *
     * @since 0.2
     */
    CodigoDeCompensacaoBACEN getCodigoDeCompensacaoBACEN();

    /**
     * @see #setSegmento(String)
     *
     * @return Segmento
     *
     * @since 0.2
     */
    String getSegmento();

    /**
     * @see #setImgLogo(Image)
     *
     * @return Imagem
     *
     * @since 0.2
     */
    Image getImgLogo();

    /**
     * <p>
     * Código de compensação <a href="http://www.bcb.gov.br/">BACEN</a>
     * </p>
     *
     * @param codigoDeCompensacaoBACEN
     *
     * @since 0.2
     */
    void setCodigoDeCompensacaoBACEN(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN);

    /**
     * <p>
     * Segmento registrado no <a href="http://www.bcb.gov.br/">BACEN</a>.
     * </p>
     *
     * <p>
     * Exemplos:
     * <ul>
     * <li>Banco Comercial Estrangeiro - Filial no país</li>
     * <li>Banco Múltiplo</li>
     * <li>Banco Comercial</li>
     * <li>Banco Múltiplo Cooperativo</li>
     * </ul>
     * </p>
     *
     * @param segmento
     *
     * @since 0.2
     */
    void setSegmento(String segmento);

    /**
     * <p>
     * Imagem da logo-marca do banco.
     * </p>
     *
     * @param imgLogo
     *
     * @since 0.2
     */
    void setImgLogo(Image imgLogo);
}
