package org.jrimum.domkee.comum.pessoa.id.cprf;

import org.jrimum.domkee.pessoa.CNPJ;
import org.jrimum.vallia.AbstractCPRFValidator.TipoDeCPRF;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestCNPJAlfanumerico extends TestAbstractCPRF {

    private final String cnpjAlfaStr = "SGU1GE72000175";
    private final String cnpjAlfaStrFmt = "SG.U1G.E72/0001-75";
    private final String cnpjAlfaStrErr = "SGU1GE72000100";
    private final String cnpjAlfaStrFmtErr = "SG.U1G.E72/0001-00";

    @Before
    public void setUp() {

        setTipo(TipoDeCPRF.CNPJ);

        setCprfString(cnpjAlfaStr);
        setCprfStringErr(cnpjAlfaStrErr);

        setCprfStringFormatada(cnpjAlfaStrFmt);
        setCprfStringFormatadaErr(cnpjAlfaStrFmtErr);
        setCprfRaizFormatada("SG.U1G.E72");
        setCprfRaizFormatadaErr("SG.U1G.E73");

        setCprfDv(75);
        setCprfDvErr(0);

        setCprf(new CNPJ(cnpjAlfaStrFmt));

        setCprfOutro(new CNPJ("07.237.373/0001-20"));
    }


    @Test
    public void testCNPJAlfanumericoStringSemMascara() {
        CNPJ cnpj = new CNPJ(cnpjAlfaStr);

        assertNotNull(cnpj);
        assertEquals(cnpjAlfaStr, cnpj.getCodigoComZeros());
        assertEquals(cnpjAlfaStrFmt, cnpj.getCodigoFormatado());
    }


    @Test
    public void testCNPJAlfanumericoStringComMascara() {
        CNPJ cnpj = new CNPJ(cnpjAlfaStrFmt);

        assertNotNull(cnpj);
        assertEquals(cnpjAlfaStr, cnpj.getCodigoComZeros());
        assertEquals(cnpjAlfaStrFmt, cnpj.getCodigoFormatado());
    }

    @Test
    public void testCNPJAlfanumericoMinusculoDeveNormalizar() {
        CNPJ cnpj = new CNPJ("sg.u1g.e72/0001-75");

        assertNotNull(cnpj);
        assertEquals(cnpjAlfaStr, cnpj.getCodigoComZeros());
        assertEquals(cnpjAlfaStrFmt, cnpj.getCodigoFormatado());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCNPJAlfanumericoDvInvalidoSemMascaraDeveFalhar() {
        new CNPJ(cnpjAlfaStrErr);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCNPJAlfanumericoDvInvalidoComMascaraDeveFalhar() {
        new CNPJ(cnpjAlfaStrFmtErr);
    }
}
