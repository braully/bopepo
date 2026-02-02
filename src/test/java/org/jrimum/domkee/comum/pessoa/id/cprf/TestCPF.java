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
 * Created at: 30/03/2008 - 19:11:24
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
 * Criado em: 30/03/2008 - 19:11:24
 *
 */

package org.jrimum.domkee.comum.pessoa.id.cprf;

import org.jrimum.domkee.pessoa.CPF;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Teste da classe CPF.
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @version 0.2
 * @since 0.2
 */
public class TestCPF extends TestAbstractCPRF {

    /*
     * CPF: 070.680.938-68 | LUIZ INACIO LULA DA SILVA
     */
    private final String cpfLulaStrFmt = "070.680.938-68";
    private final String cpfLulaStr = "07068093868";
    private final Long cpfLula = 7068093868L;

    @Before
    public void setUp() {

        setTipo(TipoDeCPRF.CPF);
        setCprfString(cpfLulaStr);
        setCprfStringErr("07068093867");
        setCprfStringFormatada(cpfLulaStrFmt);
        setCprfStringFormatadaErr("070.680.938-67");
        setCprfRaizFormatada("070.680.938");
        setCprfRaizFormatadaErr("070.680.936");
        setCprfDv(68);
        setCprfDvErr(67);
        setCprf(new CPF(cpfLulaStrFmt));
        //FICTICIO
        setCprfOutro(new CPF("222.222.222-22"));
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CPF#CPF(java.lang.Long)}.
     */
    @Test
    public void testCPFLong() {
        CPF cpf = new CPF(cpfLula);
        assertConsistent(cpf);
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CPF#CPF(java.lang.String)}.
     */
    @Test
    public void testCPFStringWithZeros() {
        CPF cpf = new CPF(cpfLulaStr);
        assertConsistent(cpf);
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CPF#CPF(java.lang.String)}.
     */
    @Test
    public void testCPFStringWithFormat() {
        CPF cpf = new CPF(cpfLulaStrFmt);
        assertConsistent(cpf);
    }

    private void assertConsistent(CPF cpf) {

        assertNotNull(cpf);
        assertEquals(cpfLulaStr, cpf.getCodigoComZeros());
        assertEquals(cpfLulaStrFmt, cpf.getCodigoFormatado());
    }
}
