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
 * Created at: 30/03/2008 - 19:11:13
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
 * Criado em: 30/03/2008 - 19:11:13
 *
 */

package org.jrimum.domkee.comum.pessoa.id.cprf;

import org.jrimum.domkee.pessoa.CNPJ;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Teste da classe CNPJ.
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @version 2.0
 * @since 2.0
 */
public class TestCNPJ extends TestAbstractCPRF {

    /*
     * CNPJ: 60.746.948/0001-12 | BANCO BRADESCO
     */
    private final String cnpjBradescoFilialStrFmt = "60.746.948/0005-46";
    private final String cnpjBradescoStrFmt = "60.746.948/0001-12";
    private final String cnpjBradescoStr = "60746948000112";

    @Before
    public void setUp() {

        setTipo(TipoDeCPRF.CNPJ);
        setCprfString(cnpjBradescoStr);
        setCprfStringErr("60746948000113");
        setCprfStringFormatada(cnpjBradescoStrFmt);
        setCprfStringFormatadaErr("60.746.948/0001-13");
        setCprfRaizFormatada("60.746.948");
        setCprfRaizFormatadaErr("60.746.941");
        setCprfDv(12);
        setCprfDvErr(13);
        setCprf(new CNPJ(cnpjBradescoStrFmt));
        //BANCO DO NORDESTE
        setCprfOutro(new CNPJ("07.237.373/0001-20"));
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#CNPJ(java.lang.String)}.
     */
    @Test
    public void testCNPJStringWithZeros() {
        CNPJ cnpj = new CNPJ(cnpjBradescoStr);
        assertConsistent(cnpj);
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#CNPJ(java.lang.String)}.
     */
    @Test
    public void testCNPJStringWithFormat() {
        CNPJ cnpj = new CNPJ(cnpjBradescoStrFmt);
        assertConsistent(cnpj);
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#getSufixo()}.
     */
    @Test
    public void testGetSufixo() {
        assertEquals(Integer.valueOf(1), new CNPJ(cnpjBradescoStrFmt).getSufixo());

    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#getSufixoFormatado()}.
     */
    @Test
    public void testGetSufixoFormatado() {
        assertEquals("0001", new CNPJ(cnpjBradescoStrFmt).getSufixoFormatado());
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#isMatriz()}.
     */
    @Test
    public void testIsMatriz() {
        assertTrue(new CNPJ(cnpjBradescoStrFmt).isMatriz());
        assertTrue(!new CNPJ(cnpjBradescoFilialStrFmt).isMatriz());
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#isSufixoEquals(java.lang.String)}.
     */
    @Test
    public void testIsSufixoEqualsString() {

        assertTrue(new CNPJ(cnpjBradescoStrFmt).isSufixoEquals("0001"));
        assertTrue(!new CNPJ(cnpjBradescoFilialStrFmt).isSufixoEquals("0001"));
    }

    /**
     * Test method for {@link org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ#isSufixoEquals(java.lang.Integer)}.
     */
    @Test
    public void testIsSufixoEqualsInteger() {
        assertTrue(new CNPJ(cnpjBradescoFilialStrFmt).isSufixoEquals(5));
        assertTrue(!new CNPJ(cnpjBradescoStrFmt).isSufixoEquals(5));
    }
    
    private void assertConsistent(CNPJ cnpj) {
        assertNotNull(cnpj);
        assertEquals(cnpjBradescoStr, cnpj.getCodigoComZeros());
        assertEquals(cnpjBradescoStrFmt, cnpj.getCodigoFormatado());
    }
}
