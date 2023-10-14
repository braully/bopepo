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
 * Created at: 30/03/2008 - 18:15:42
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
 * Criado em: 30/03/2008 - 18:15:42
 * 
 */

package org.jrimum.texgit.type.component;

import org.jrimum.texgit.Side;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jrimum.texgit.TextStream;
import org.jrimum.texgit.Filler;
import org.jrimum.utilix.Strings;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Teste unitário para a classe <code>Filler</code>.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class TestFiller {

	private static final String CAMPO = "TESTE";

	private static final int TAMANHO = 10;

	private Filler<String> fillerString;
	private Filler<Integer> fillerInteger;
	private Filler<Double> fillerDouble;
	private Filler<Side> fillerSide;
	private Filler<String> whiteSpaceLeft;
	private Filler<Integer> zeroLeft ;
	private Filler<Integer> zeroRight;
	
	@Before
	public void setup(){
		whiteSpaceLeft =  new Filler<String>(Strings.WHITE_SPACE, Side.LEFT);
		zeroLeft =  new Filler<Integer>(0, Side.LEFT);
		zeroRight =  new Filler<Integer>(0, Side.RIGHT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSideNullValue() {

		whiteSpaceLeft.setSideToFill(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetToFillNullValue() {

		whiteSpaceLeft.setPadding(null);
	}

	@Test
	public void testSetToFill() {

		whiteSpaceLeft.setPadding(CAMPO);

		assertTrue(whiteSpaceLeft.getPadding() instanceof String);
		assertEquals(whiteSpaceLeft.getPadding(), CAMPO);
	}

	@Test
	public void testSetSide() {

		whiteSpaceLeft.setSideToFill(Side.RIGHT);

		assertEquals(whiteSpaceLeft.getSideToFill(), Side.RIGHT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFiller() {

		new Filler<String>(null, null);
		new Filler<String>("", null);
		new Filler<String>(null, Side.LEFT);
	}

	@Test
	public void testFillString() {

		assertEquals(CAMPO + "00000", zeroRight.fill(CAMPO, TAMANHO));
		assertEquals("00000" + CAMPO, zeroLeft.fill(CAMPO, TAMANHO));
	}

	@Test
	public void testFillLong() {

		assertEquals(1L + "000000000", zeroRight.fill(1L, TAMANHO));
		assertEquals("000000000" + 1L, zeroLeft.fill(1L, TAMANHO));
	}

	@Test
	public void testFillInt() {

		assertEquals(1 + "000000000", zeroRight.fill(1, TAMANHO));
		assertEquals("000000000" + 1, zeroLeft.fill(1, TAMANHO));
	}

	@Test
	public void testFillShort() {

		assertEquals((short) 1 + "000000000", zeroRight.fill((short) 1,
				TAMANHO));
		assertEquals("000000000" + (short) 1, zeroLeft.fill((short) 1,
				TAMANHO));
	}

	@Test
	public void testFillByte() {

		assertEquals((byte) 1 + "000000000", zeroRight.fill((byte) 1,
				TAMANHO));
		assertEquals("000000000" + (byte) 1, zeroLeft.fill((byte) 1,
				TAMANHO));
	}

	@Test
	public void testFillChar() {

		assertEquals('1' + "000000000", zeroRight.fill('1', TAMANHO));
		assertEquals("000000000" + '1', zeroLeft.fill('1', TAMANHO));
	}

	@Test
	public void testFillDouble() {

		assertEquals(1.0 + "0000000", zeroRight.fill(1.0, TAMANHO));
		assertEquals("0000000" + 1.0, zeroLeft.fill(1.0, TAMANHO));
	}

	@Test
	public void testFillFloat() {

		assertEquals(1.0f + "0000000", zeroRight.fill(1.0f, TAMANHO));
		assertEquals("0000000" + 1.0f, zeroLeft.fill(1.0f, TAMANHO));
	}

	@Test
	public void testFillObject() {

		Object object = new Object() {

			@Override
			public String toString() {
				return CAMPO;
			}
		};

		assertEquals(object + "00000", zeroRight.fill(object, TAMANHO));
		assertEquals("00000" + object, zeroLeft.fill(object, TAMANHO));
	}

	@Test
	public void testFillITextStream() {

		TextStream textStream = new TextStream() {

			private static final long serialVersionUID = 1L;

			public void read(String g) {
			}

			public String write() {

				return CAMPO;
			}
		};

		assertEquals(textStream.write() + "00000", zeroRight.fill(
				textStream, TAMANHO));
		assertEquals("00000" + textStream.write(), zeroLeft.fill(
				textStream, TAMANHO));
	}

	@Test
	public void testFill() {

		fillerString = new Filler<String>("ABC", Side.LEFT);
		assertTrue(fillerString.getPadding() instanceof String);
		assertEquals("ABCAB" + CAMPO, fillerString.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerString.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerString.fill(CAMPO, -TAMANHO));

		fillerString.setSideToFill(Side.RIGHT);
		assertEquals(CAMPO + "ABCAB", fillerString.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerString.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerString.fill(CAMPO, -TAMANHO));

		fillerInteger = new Filler<Integer>(new Integer(TAMANHO),
				Side.LEFT);
		assertTrue(fillerInteger.getPadding() instanceof Integer);
		assertEquals("10101" + CAMPO, fillerInteger.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerInteger.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerInteger.fill(CAMPO, -TAMANHO));

		fillerInteger.setSideToFill(Side.RIGHT);
		assertEquals(CAMPO + "10101", fillerInteger.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerInteger.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerInteger.fill(CAMPO, -TAMANHO));

		fillerDouble = new Filler<Double>(new Double(10.9), Side.LEFT);
		assertTrue(fillerDouble.getPadding() instanceof Double);
		assertEquals("10.91" + CAMPO, fillerDouble.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerDouble.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerDouble.fill(CAMPO, -TAMANHO));

		fillerDouble.setSideToFill(Side.RIGHT);
		assertEquals(CAMPO + "10.91", fillerDouble.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerDouble.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerDouble.fill(CAMPO, -TAMANHO));

		fillerSide = new Filler<Side>(Side.LEFT, Side.LEFT);
		assertTrue(fillerSide.getPadding() instanceof Side);
		assertEquals("LEFTL" + CAMPO, fillerSide.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerSide.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerSide.fill(CAMPO, -TAMANHO));

		fillerSide.setSideToFill(Side.RIGHT);
		assertEquals(CAMPO + "LEFTL", fillerSide.fill(CAMPO, TAMANHO));
		assertEquals(CAMPO, fillerSide.fill(CAMPO, 0));
		assertEquals(CAMPO, fillerSide.fill(CAMPO, -TAMANHO));
	}

}
