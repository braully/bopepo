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
 * Created at: 30/03/2008 - 18:15:56
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
 * Criado em: 30/03/2008 - 18:15:56
 * 
 */
package org.jrimum.utilix.excludes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.jrimum.texgit.Field;
import org.jrimum.texgit.IFixedField;

/**
 * <p>
 * Classe para uso em testes com <code>Fields</code>.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class TestUtil {

	public static <G> void testEscritaCampo(IFixedField<G> campo, Class<G> tipo,
			G valorDeEntrada, String strEsperada, int tamanho) {

		// >>>
		assertNotNull(campo);
		assertTrue(tipo.isInstance(campo.getValue()));
		assertEquals(valorDeEntrada, campo.getValue());
		assertNotNull(campo.write());
		assertEquals(strEsperada, campo.write());
		assertTrue(tamanho == campo.getFixedLength());
		assertTrue(tamanho == campo.write().length());
	}

	public static <G> void testLeituraCampo(Field<G> campo, Class<G> tipo,
			G valorEsperado, String strDeEntrada) {

		// <<<
		campo.read(strDeEntrada);
		assertTrue(tipo.isInstance(campo.getValue()));

		if (campo.getValue() instanceof Date)// compareNoFormatoEscrito
			assertEquals(campo.getFormatter().format(valorEsperado), campo.write());
		else
			assertEquals(valorEsperado, campo.getValue());

	}

}
