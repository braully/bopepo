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
 * Created at: 30/03/2008 - 18:15:33
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
 * Criado em: 30/03/2008 - 18:15:33
 * 
 */

package org.jrimum.utilix;
import static org.jrimum.utilix.DateFormat.DDMMYYYY_B;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>
 * Teste unitário para a classe utilitária de coleções.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class TestDates {

	private static Date dataInicial;

	private static Date dataFinal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2007, Calendar.OCTOBER, 16);
		dataInicial = calendar1.getTime();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2007, Calendar.NOVEMBER, 16);
		dataFinal = calendar2.getTime();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		dataInicial = null;
		dataFinal = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculeDiferencaEmDiasDatasNull() {

		Dates.calculeDiferencaEmDias(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculeDiferencaEmDiasDataInicialNull() {

		Dates.calculeDiferencaEmDias(null, dataFinal);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculeDiferencaEmDiasDataFinalNull() {

		Dates.calculeDiferencaEmDias(dataInicial, null);
	}

	@Test
	public void testCalculeDiferencaEmDiasGaranteModulo() {

		assertTrue(Dates.calculeDiferencaEmDias(dataInicial, dataFinal) > 0);
		assertTrue(Dates.calculeDiferencaEmDias(dataFinal, dataInicial) > 0);
	}

	@Test
	public void testCalculeDiferencaEmDias() {

		assertEquals(0, Dates.calculeDiferencaEmDias(dataInicial,
				dataInicial));
		assertEquals(0, Dates.calculeDiferencaEmDias(dataFinal, dataFinal));
		assertEquals(31, Dates
				.calculeDiferencaEmDias(dataInicial, dataFinal));
		assertEquals(31, Dates
				.calculeDiferencaEmDias(dataFinal, dataInicial));
	}

	@Test
	public void testParseFormatoDDMMYYYY() {
		assertNotNull(DDMMYYYY_B.parse("01/01/2000"));
	}

	@Test(expected = NullPointerException.class)
	public void testParseFormatoDDMMYYYYNullPointerException() {
		assertNotNull(DDMMYYYY_B.parse(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseFormatoDDMMYYYYIllegalArgumentException() {
		assertNotNull(DDMMYYYY_B.parse(""));
		assertNotNull(DDMMYYYY_B.parse("2000-01-01"));
	}

	@Test
	public void testParseStringString() {
		assertNotNull(Dates.parse("2000-01-01", "yyyy-MM-dd"));
	}

	@Test(expected = NullPointerException.class)
	public void testParseStringStringDataNull() {
		assertNotNull(Dates.parse(null, "yyyy-MM-dd"));
	}

	@Test(expected = NullPointerException.class)
	public void testParseStringStringFormatoNull() {
		String formato = null;
		assertNotNull(Dates.parse("2000-01-01", formato));
	}

	@Test(expected = NullPointerException.class)
	public void testParseStringStringAmbosNull() {
		String formato = null;
		assertNotNull(Dates.parse(null, formato));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseStringStringIllegalArgumentException() {
		assertNotNull(Dates.parse("", "yyyy-MM-dd"));
		assertNotNull(Dates.parse("01/01/2000", ""));
		assertNotNull(Dates.parse("01/01/2000", "yyyy-MM-dd"));
	}
}
