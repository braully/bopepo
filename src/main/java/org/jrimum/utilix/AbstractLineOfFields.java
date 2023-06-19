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
 * Created at: 30/03/2008 - 18:17:32
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
 * Criado em: 30/03/2008 - 18:17:32
 *
 */
package org.jrimum.utilix;

import static org.jrimum.utilix.ObjectUtil.isNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jrimum.texgit.Field;
import org.jrimum.texgit.TextStream;

/**
 *
 * Descrição:
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 *
 * @since 0.2
 * @version 0.2
 */
public abstract class AbstractLineOfFields implements TextStream, List<Field<?>> {

	// TODO implementar isConsistent para os methods do tipo List em função de
	// fieldsLength e stringLength.
	/**
	 *
	 */
	private static final long serialVersionUID = 9071816265288953266L;

	private static Logger log = LogManager.getLogger(AbstractLineOfFields.class);

	/**
	 *
	 */
	private Integer fieldsLength;

	/**
	 *
	 */
	private Integer stringLength;

	/**
	 *
	 */
	private List<Field<?>> fields;

	/**
	 * @param fieldsLength
	 * @param stringLength
	 */
	protected AbstractLineOfFields() {
		fields = new ArrayList<Field<?>>();
	}

	public AbstractLineOfFields(Integer fieldsLength, Integer stringLength) {
		if (log.isTraceEnabled()) {
			log.trace("Initializing");
		}
		if (log.isDebugEnabled()) {
			log.debug("Parameters fieldsLength: " + fieldsLength);
			log.debug("Parameters stringLength: " + stringLength);
		}
		if (isNotNull(fieldsLength, "fieldsLength") && isNotNull(stringLength, "stringLength")) {

			if (fieldsLength > 0) {
				if (stringLength > 0) {
					fields = new ArrayList<Field<?>>(fieldsLength);
					this.fieldsLength = fieldsLength;
					this.stringLength = stringLength;
				} else {
					IllegalArgumentException e = new IllegalArgumentException(
							"O tamanho da String [ " + stringLength + " ] deve ser maior que 0!");
					log.error(StringUtils.EMPTY, e);
					throw e;
				}
			} else {
				IllegalArgumentException e = new IllegalArgumentException(
						"O tamanho dos campos [ " + fieldsLength + " ] deve ser maior que 0!");
				log.error(StringUtils.EMPTY, e);
				throw e;
			}
		}

		if (log.isTraceEnabled()) {
			log.trace("LineOfFields Initialized.");
		}

		if (log.isDebugEnabled()) {
			log.debug("Instance fieldsLength: " + fieldsLength);
			log.debug("Instance stringLength: " + stringLength);
		}
	}

	/**
	 * @param lineOfFields
	 */
	public void read(String lineOfFields) {

		if (isNotNull(lineOfFields, "lineOfFields")) {

			isConsistent(lineOfFields);

			StringBuilder builder = new StringBuilder(lineOfFields);

			for (Field<?> field : fields) {

				field.read(builder.substring(0, field.getLength()));
				builder.delete(0, field.getLength());
			}

			builder = null;
		}
	}

	public String write() {
		StringBuilder lineOfFields = new StringBuilder(StringUtils.EMPTY);
		if (isNotNull(fields, "fields")) {
			for (Field<?> field : fields) {
				lineOfFields.append(field.write());
			}
			isConsistent(lineOfFields);
		}
		return lineOfFields.toString();
	}

	protected final boolean isConsistent(StringBuilder lineOfFields) {
		boolean is = false;
		if (isConsistent(lineOfFields.toString())) {
			if (fieldsLength == size()) {
				is = true;
			} else {
				IllegalStateException e = new IllegalStateException("O tamanho dos campos [ " + size()
						+ " ] é incompatível com o especificado [" + fieldsLength + "]!");
				log.error(StringUtils.EMPTY, e);
				throw e;
			}
		}
		return is;
	}

	protected final boolean isConsistent(String lineOfFields) {
		boolean is = false;
		if (lineOfFields.length() == stringLength) {
			is = true;
		} else {
			IllegalStateException e = new IllegalStateException("O tamanho da String de campos [ "
					+ lineOfFields.length() + " ] é incompatível com o especificado [" + stringLength + "]!");
			log.error(StringUtils.EMPTY, e);
			throw e;
		}
		return is;
	}

	/**
	 * @return length of line as string.
	 */
	public int stringSize() {
		return write().length();
	}

	/**
	 * @return the fieldsLength
	 */
	public Integer getFieldsLength() {
		return fieldsLength;
	}

	/**
	 * @param fieldsLength the fieldsLength to set
	 */
	public void setFieldsLength(Integer fieldsLength) {
		this.fieldsLength = fieldsLength;
	}

	/**
	 * @return the stringLength
	 */
	public Integer getStringLength() {
		return stringLength;
	}

	/**
	 * @param stringLength the stringLength to set
	 */
	public void setStringLength(Integer stringLength) {
		this.stringLength = stringLength;
	}

	/**
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Field<?> e) {

		return fields.add(e);
	}

	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, Field<?> element) {
		fields.add(index, element);
	}

	/**
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Field<?>> c) {
		return fields.addAll(c);
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends Field<?>> c) {
		return fields.addAll(index, c);
	}

	/**
	 * @see java.util.List#clear()
	 */
	public void clear() {
		fields.clear();
	}

	/**
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return fields.contains(o);
	}

	/**
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return fields.containsAll(c);
	}

	/**
	 * @see java.util.List#get(int)
	 */
	public Field<?> get(int index) {
		return fields.get(index);
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return fields.indexOf(o);
	}

	/**
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty() {
		return fields.isEmpty();
	}

	/**
	 * @see java.util.List#iterator()
	 */
	public Iterator<Field<?>> iterator() {

		return fields.iterator();
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {

		return fields.indexOf(o);
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<Field<?>> listIterator() {

		return fields.listIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<Field<?>> listIterator(int index) {

		return fields.listIterator(index);
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	public Field<?> remove(int index) {

		return fields.remove(index);
	}

	/**
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {

		return fields.remove(o);
	}

	/**
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {

		return fields.removeAll(c);
	}

	/**
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {

		return fields.retainAll(c);
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public Field<?> set(int index, Field<?> element) {

		return fields.set(index, element);
	}

	/**
	 * @see java.util.List#size()
	 */
	public int size() {

		return fields.size();
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	public List<Field<?>> subList(int fromIndex, int toIndex) {

		return fields.subList(fromIndex, toIndex);
	}

	/**
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {

		return fields.toArray();
	}

	/**
	 * @see java.util.List#toArray(Object[])
	 */
	public <T> T[] toArray(T[] a) {

		return fields.toArray(a);
	}

}
