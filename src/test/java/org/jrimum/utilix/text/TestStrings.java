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
 * Created at: 30/03/2008 - 18:15:10
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
 * Criado em: 30/03/2008 - 18:15:10
 * 
 */

package org.jrimum.utilix.text;

import org.jrimum.utilix.Strings;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * <p>
 * Teste unitário para a classe utilitária de strings.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class TestStrings {

	@Test
	public void testEliminateSymbols() {

		assertEquals(EMPTY, Strings.eliminateSymbols("><,;.:!*&%+-_<>[]\\/"));
	}

	@Test
	public void testEliminateAccent() {

		assertEquals("c", Strings.eliminateAccent("ç"));
		assertEquals("C", Strings.eliminateAccent("Ç"));

		assertEquals("a", Strings.eliminateAccent("à"));
		assertEquals("a", Strings.eliminateAccent("á"));
		assertEquals("a", Strings.eliminateAccent("â"));
		assertEquals("a", Strings.eliminateAccent("ã"));
		assertEquals("a", Strings.eliminateAccent("ä"));

		assertEquals("e", Strings.eliminateAccent("è"));
		assertEquals("e", Strings.eliminateAccent("é"));
		assertEquals("e", Strings.eliminateAccent("ê"));
		assertEquals("e", Strings.eliminateAccent("ë"));

		assertEquals("i", Strings.eliminateAccent("ì"));
		assertEquals("i", Strings.eliminateAccent("í"));
		assertEquals("i", Strings.eliminateAccent("î"));
		assertEquals("i", Strings.eliminateAccent("ï"));

		assertEquals("o", Strings.eliminateAccent("ò"));
		assertEquals("o", Strings.eliminateAccent("ó"));
		assertEquals("o", Strings.eliminateAccent("ô"));
		assertEquals("o", Strings.eliminateAccent("õ"));
		assertEquals("o", Strings.eliminateAccent("ö"));

		assertEquals("u", Strings.eliminateAccent("ù"));
		assertEquals("u", Strings.eliminateAccent("ú"));
		assertEquals("u", Strings.eliminateAccent("û"));
		assertEquals("u", Strings.eliminateAccent("ü"));

		assertEquals("A", Strings.eliminateAccent("À"));
		assertEquals("A", Strings.eliminateAccent("Á"));
		assertEquals("A", Strings.eliminateAccent("Â"));
		assertEquals("A", Strings.eliminateAccent("Ã"));
		assertEquals("A", Strings.eliminateAccent("Ä"));

		assertEquals("E", Strings.eliminateAccent("È"));
		assertEquals("E", Strings.eliminateAccent("É"));
		assertEquals("E", Strings.eliminateAccent("Ê"));
		assertEquals("E", Strings.eliminateAccent("Ë"));

		assertEquals("I", Strings.eliminateAccent("Ì"));
		assertEquals("I", Strings.eliminateAccent("Í"));
		assertEquals("I", Strings.eliminateAccent("Î"));
		assertEquals("I", Strings.eliminateAccent("Ï"));

		assertEquals("O", Strings.eliminateAccent("Ò"));
		assertEquals("O", Strings.eliminateAccent("Ó"));
		assertEquals("O", Strings.eliminateAccent("Ô"));
		assertEquals("O", Strings.eliminateAccent("Õ"));
		assertEquals("O", Strings.eliminateAccent("Ö"));

		assertEquals("U", Strings.eliminateAccent("Ù"));
		assertEquals("U", Strings.eliminateAccent("Ú"));
		assertEquals("U", Strings.eliminateAccent("Û"));
		assertEquals("U", Strings.eliminateAccent("Ü"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNumericNull() {
		Strings.checkNotNumeric(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNumericBlank() {
		Strings.checkNotNumeric(" 192343 ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotNumericWithAlpha() {
		Strings.checkNotNumeric("A192343B");
	}
	
	@Test
	public void testCheckNotNumeric() {
		Strings.checkNotNumeric("123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotBlankNull() {
		Strings.checkNotBlank(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotBlankEmpty() {
		Strings.checkNotBlank("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotBlankWithBlank() {
		Strings.checkNotBlank("   ");
	}
	
	@Test
	public void testCheckNotBlank() {
		Strings.checkNotBlank("Ok, Not Blank!");
	}
	
	@Test
	public void testFillWithZeroLeftString(){
		assertEquals("012",Strings.fillWithZeroLeft("12", 3));
	}
	
	@Test
	public void testFillWithZeroLeftInt(){
		assertEquals("012345678",Strings.fillWithZeroLeft(12345678, 9));
	}
	
	@Test
	public void testFillWithZeroLeftLong(){
		assertEquals("012345678901",Strings.fillWithZeroLeft(12345678901L, 12));
	}
}
