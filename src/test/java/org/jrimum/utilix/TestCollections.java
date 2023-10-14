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
 * Created at: 31/07/2010 - 23:04:41
 */

package org.jrimum.utilix;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.EMPTY_MAP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
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
public class TestCollections {
	
	private static final Object EMPTY_OBJECT = new Object();
	
	private static final Map<Object, Object> NULL_MAP = null;
	
	private static final Collection<Object> NULL_COLLECTION = null;
	
	private static final Map<Object, Object> MAP_COM_ELEMENTO;
	
	private static final Collection<Object> COLLECTION_COM_ELEMENTO;
	
	static {
		
		MAP_COM_ELEMENTO = new HashMap<Object, Object>();
		MAP_COM_ELEMENTO.put(EMPTY_OBJECT, EMPTY_OBJECT);
		
		COLLECTION_COM_ELEMENTO = new ArrayList<Object>();
		COLLECTION_COM_ELEMENTO.add(EMPTY_OBJECT);
	}

	@Test
	public void testIsEmptyMap() {
		
		assertTrue(Collections.isEmpty(NULL_MAP));
		assertTrue(Collections.isEmpty(EMPTY_MAP));
		
		assertFalse(Collections.isEmpty(MAP_COM_ELEMENTO));
	}
	
	@Test
	public void testIsNotEmptyMap() {
		
		assertTrue(Collections.isNotEmpty(MAP_COM_ELEMENTO));
		
		assertFalse(Collections.isNotEmpty(NULL_MAP));
		assertFalse(Collections.isNotEmpty(EMPTY_MAP));
	}
	
	@Test
	public void testIsEmptyCollection() {
		
		assertTrue(Collections.isEmpty(NULL_COLLECTION));
		assertTrue(Collections.isEmpty(EMPTY_LIST));
		
		assertFalse(Collections.isEmpty(COLLECTION_COM_ELEMENTO));
	}
	
	@Test
	public void testIsNotEmptyCollection() {
		
		assertTrue(Collections.isNotEmpty(COLLECTION_COM_ELEMENTO));
		
		assertFalse(Collections.isNotEmpty(NULL_COLLECTION));
		assertFalse(Collections.isNotEmpty(EMPTY_LIST));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEmptyMap() {
		
		Collections.checkEmpty(MAP_COM_ELEMENTO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEmptyMapComMensagem() {
		Collections.checkEmpty(MAP_COM_ELEMENTO, "Argumento não nulo");
	}
	
	@Test
	public void testMensagemCheckEmptyMapComMensagem() {

		try {
			
			Collections.checkEmpty(MAP_COM_ELEMENTO, "Argumento não nulo");
			Assert.fail("Exceção não disparada");
			
		} catch (IllegalArgumentException e) {
			assertEquals("Argumento não nulo", e.getMessage());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyMapNullPointer() {
		Collections.checkNotEmpty(NULL_MAP);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyMapIllegalArgument() {
		Collections.checkNotEmpty(EMPTY_MAP);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyMapComMensagemNullPointer() {
		Collections.checkNotEmpty(NULL_MAP, "Argumento nulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyMapComMensagemIllegalArgument() {
		Collections.checkNotEmpty(EMPTY_MAP, "Map vazio");
	}
	
	@Test
	public void testMensagemCheckNotEmptyMapComMensagem() {
		
		try {
			
			Collections.checkNotEmpty(NULL_MAP, "Argumento nulo");
			Assert.fail("Exceção não disparada");
						
		} catch (IllegalArgumentException e) {
			assertEquals("Argumento nulo", e.getMessage());
		}
		
		try {
			
			Collections.checkNotEmpty(EMPTY_MAP, "Map vazio");
			Assert.fail("Exceção não disparada");
						
		} catch (IllegalArgumentException e) {
			assertEquals("Map vazio", e.getMessage());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEmptyCollection() {
		Collections.checkEmpty(COLLECTION_COM_ELEMENTO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEmptyCollectionComMensagem() {
		Collections.checkEmpty(COLLECTION_COM_ELEMENTO, "Argumento não nulo");
	}
	
	@Test
	public void testMensagemCheckEmptyCollectionComMensagem() {
		
		try {
			
			Collections.checkEmpty(COLLECTION_COM_ELEMENTO, "Argumento não nulo");
			Assert.fail("Exceção não disparada");
			
		} catch (IllegalArgumentException e) {
			assertEquals("Argumento não nulo", e.getMessage());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyCollectionNullPointer() {
		Collections.checkNotEmpty(NULL_COLLECTION);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyCollectionIllegalArgument() {
		Collections.checkNotEmpty(EMPTY_LIST);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyCollectionComMensagemNullPointer() {
		Collections.checkNotEmpty(NULL_COLLECTION, "Argumento nulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEmptyCollectionComMensagemIllegalArgument() {
		Collections.checkNotEmpty(EMPTY_LIST, "Coleção vazia");
	}
	
	@Test
	public void testMensagemCheckNotEmptyCollectionComMensagem() {
		
		try {
			
			Collections.checkNotEmpty(NULL_COLLECTION, "Argumento nulo");
			Assert.fail("Exceção não disparada");
						
		} catch (IllegalArgumentException e) {
			assertEquals("Argumento nulo", e.getMessage());
		}
		
		try {
			
			Collections.checkNotEmpty(EMPTY_LIST, "Coleção vazia");
			Assert.fail("Exceção não disparada");
						
		} catch (IllegalArgumentException e) {
			assertEquals("Coleção vazia", e.getMessage());
		}
	}
}
