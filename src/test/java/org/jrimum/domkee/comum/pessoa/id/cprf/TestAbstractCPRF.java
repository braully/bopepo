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
 * Created at: 30/03/2008 - 19:11:04
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
 * Criado em: 30/03/2008 - 19:11:04
 *
 */


package org.jrimum.domkee.comum.pessoa.id.cprf;

import org.jrimum.domkee.pessoa.AbstractCPRF;
import org.jrimum.domkee.pessoa.CPRF;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;
import org.junit.Test;

import static org.jrimum.utilix.Strings.fillWithZeroLeft;
import static org.junit.Assert.*;


/**
 * Teste da classe {@linkplain AbstractCPRF} e base para as sublcasses {@linkplain CPF} e {@linkplain CNPJ}.
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @version 0.2
 * @since 0.2
 */
public abstract class TestAbstractCPRF {

    private TipoDeCPRF tipo;
    private String cprfStringFormatada;
    private String cprfStringFormatadaErr;
    private String cprfString;
    private String cprfStringErr;
    private String cprfRaizFormatada;
    private String cprfRaizFormatadaErr;
    private Integer cprfDv;
    private Integer cprfDvErr;

    private CPRF cprf;
    private CPRF cprfOutro;


    @Test
    public void testCreateStringTipoDeCPRF() {
        assertNotNull(AbstractCPRF.create(cprfString, tipo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoNotCreateStringTipoDeCPRF() {
        assertNotNull(AbstractCPRF.create(cprfStringErr, tipo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoNotCreateStringFormatadaTipoDeCPRF() {
        assertNotNull(AbstractCPRF.create(cprfStringFormatada, tipo));
    }

    @Test
    public void testCreateString() {
        assertNotNull(AbstractCPRF.create(cprfString));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoNotCreateString() {
        assertNotNull(AbstractCPRF.create(cprfStringErr));
    }

    @Test
    public void testCreateStringFormatada() {
        assertNotNull(AbstractCPRF.create(cprfStringFormatada));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoNotCreateStringFormatada() {
        assertNotNull(AbstractCPRF.create(cprfStringFormatadaErr));
    }

    @Test
    public void testIsFisica() {
        assertTipo();
    }

    @Test
    public void testIsJuridica() {
        assertTipo();
    }


    @Test
    public void testGetCodigoFormatado() {
        assertEquals(cprfStringFormatada, cprf.getCodigoFormatado());
        assertTrue(!cprf.getCodigoFormatado().equals(cprfStringFormatadaErr));
    }
	

    @Test
    public void testGetRaizFormatada() {
        assertEquals(cprfRaizFormatada, cprf.getRaizFormatada());
        assertTrue(!cprf.getRaizComZeros().equals(cprfRaizFormatadaErr));
    }

    @Test
    public void testGetDv() {
        assertEquals(cprfDv, cprf.getDv());
        assertTrue(!cprf.getDv().equals(cprfDvErr));
    }

    @Test
    public void testGetDvComZeros() {
        assertEquals(fillWithZeroLeft(cprf.getDv(), 2), cprf.getDvComZeros());
        assertTrue(!cprf.getDvComZeros().equals(fillWithZeroLeft(cprfDvErr, 2)));
    }

    @Test
    public void testCompareTo() {
        int c1 = this.cprf.compareTo(this.cprfOutro);
        int c2 = this.cprfOutro.compareTo(this.cprf);
        assertTrue(c1 != c2);
        assertTrue(cprf.compareTo(AbstractCPRF.create(cprf.getCodigoFormatado())) == 0);
    }

    @Test
    public void testEqualsObject() {
        assertEquals(cprf, AbstractCPRF.create(cprf.getCodigoFormatado()));
        assertTrue(!cprf.equals(cprfOutro));
    }

    @Test
    public void testHashCode() {
        assertEquals(cprf.hashCode(), AbstractCPRF.create(cprf.getCodigoFormatado()).hashCode());
        assertTrue(cprf.hashCode() != cprfOutro.hashCode());
    }

    @Test
    public void testToString() {

        assertEquals(cprfStringFormatada, cprf.getCodigoFormatado());
        assertTrue(!cprf.getCodigoFormatado().equals(cprfStringFormatadaErr));
    }
    /*
     * HELPERS
     */

    private void assertTipo() {

        if (tipo.equals(TipoDeCPRF.CPF)) {
            assertTrue(cprf.isFisica());
            assertTrue(!cprf.isJuridica());
        } else {

            if (tipo.equals(TipoDeCPRF.CNPJ)) {
                assertTrue(cprf.isJuridica());
                assertTrue(!cprf.isFisica());
            } else {
                fail("TIPO NÃO PREVISTO PELO PROGRAMA!");
            }
        }
    }

    /*
     * DEFINERS
     */

    /**
     * @param tipo the tipo to set
     */
    public final void setTipo(TipoDeCPRF tipo) {
        this.tipo = tipo;
    }

    /**
     * @param cprfStringFormatada the cprfStringFormatada to set
     */
    public final void setCprfStringFormatada(String cprfStringFormatada) {
        this.cprfStringFormatada = cprfStringFormatada;
    }

    /**
     * @param cprfStringFormatadaErr the cprfStringFormatadaErr to set
     */
    public final void setCprfStringFormatadaErr(String cprfStringFormatadaErr) {
        this.cprfStringFormatadaErr = cprfStringFormatadaErr;
    }

    /**
     * @param cprfString the cprfString to set
     */
    public final void setCprfString(String cprfString) {
        this.cprfString = cprfString;
    }

    /**
     * @param cprfStringErr the cprfStringErr to set
     */
    public final void setCprfStringErr(String cprfStringErr) {
        this.cprfStringErr = cprfStringErr;
    }

    /**
     * @param cprfRaizFormatada the cprfRaizFormatado to set
     */
    public final void setCprfRaizFormatada(String cprfRaizFormatada) {
        this.cprfRaizFormatada = cprfRaizFormatada;
    }

    /**
     * @param cprfRaizFormatadaErr the cprfRaizFormatadoErr to set
     */
    public final void setCprfRaizFormatadaErr(String cprfRaizFormatadaErr) {
        this.cprfRaizFormatadaErr = cprfRaizFormatadaErr;
    }

    /**
     * @param cprfDv the cprfDv to set
     */
    public final void setCprfDv(Integer cprfDv) {
        this.cprfDv = cprfDv;
    }

    /**
     * @param cprfDvErr the cprfDvErr to set
     */
    public final void setCprfDvErr(Integer cprfDvErr) {
        this.cprfDvErr = cprfDvErr;
    }

    /**
     * @param cprf the cprf to set
     */
    public final void setCprf(CPRF cprf) {
        this.cprf = cprf;
    }

    /**
     * @param cprfOutro the cprfOutro to set
     */
    public final void setCprfOutro(CPRF cprfOutro) {
        this.cprfOutro = cprfOutro;
    }
}
