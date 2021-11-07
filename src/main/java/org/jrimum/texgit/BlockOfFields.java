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
 * Created at: 26/07/2008 - 12:44:41
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
 * Criado em: 26/07/2008 - 12:44:41
 *
 */
package org.jrimum.texgit;

import static java.lang.String.format;
import static org.jrimum.utilix.Objects.isNotNull;

import org.jrimum.utilix.Collections;
import org.jrimum.utilix.Objects;
import org.jrimum.utilix.StringUtil;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
@SuppressWarnings("serial")
public class BlockOfFields extends AbstractStringOfFields<FixedField<?>> implements IFixedSize, IFixedLength {

    /**
     * Definição
     */
    private Integer length;

    /**
     * Definição
     */
    private Integer size;

    /**
     * <p>
     * Tamanho da string de escrita do bloco.
     * </p>
     */
    private Integer instantLength;

    /**
     * <p>
     * Ao ultrapassar o tamanho, define se pode truncar ou se dispara uma
     * exceção.
     * </p>
     */
    private boolean truncate;

    /**
     * <p>
     * Quando definido, todos os caracteres não numericos são
     * desprezados/removidos
     * </p>
     */
    private boolean apenasDigitos;

    /* Default values, by Braully Rocha */
    {
        size = 0;
        length = 0;
    }

    protected void incSize() {
        size++;
    }

    protected void incLength(Integer len) {
        if (len != null) {
            length = length + len;
        }
    }

    /**
     *
     */
    public BlockOfFields() {
        super();
    }

    /**
     * @param length
     * @param size
     */
    public BlockOfFields(Integer length, Integer size) {

        super(size);

        Objects.checkNotNull(length, "length");

        if (length > 0) {

            setLength(length);
            setSize(size);

        } else {
            throw new IllegalArgumentException(format("O comprimento do bloco [%s] deve ser um número natural > 0!", length));
        }
    }

    @Override
    public BlockOfFields clone() throws CloneNotSupportedException {

        return (BlockOfFields) super.clone();
    }

    @Override
    public void read(String lineOfFields) {
        Objects.checkNotNull(lineOfFields, "String de leitura nula!");
        Objects.checkNotNull(getFields(), "Fields == null");
        Collections.checkNotEmpty(getFields(), "Coleção de fields vazia!");

        if (isSizeAsDefinaed() && isLengthWithDefinaed(lineOfFields.length())) {
            StringBuilder builder = new StringBuilder(lineOfFields);
            for (FixedField<?> field : getFields()) {
                try {
                    field.read(builder.substring(0, field.getFixedLength()));
                    builder.delete(0, field.getFixedLength());
                } catch (Exception e) {
                    throw new IllegalStateException(
                            format("Erro ao tentar ler o campo \"%s\" na posição [%s] no layout do registro.",
                                    field.getName(), getFields().indexOf(field) + 1), e);
                }
            }
            builder = null;
        }
    }

    @Override
    public String write() {
        Objects.checkNotNull(getFields(), "Fields == null");
        Collections.checkNotEmpty(getFields(), "Coleção de fields vazia!");
        String str = null;
        isSizeAsDefinaed();
        str = super.write();

        if (this.apenasDigitos) {
            str = StringUtil.eliminateSymbols(str);
        }
        instantLength = str.length();

        if (isTruncate() && instantLength > getFixedLength()) {
            str = str.substring(0, getFixedLength());
            instantLength = getFixedLength();
        }

        isFixedAsDefined();
        return str;
    }

    public boolean isFixedAsDefined() throws IllegalStateException {
        return (isSizeAsDefinaed() && isLengthWithDefinaed());
    }

    private boolean isLengthWithDefinaed() {
        return isLengthWithDefinaed(instantLength);
    }

    private boolean isLengthWithDefinaed(int length) {
        if (length == getFixedLength()) {
            return true;
        } else {
            throw new IllegalStateException(
                    format("O comprimento da string [%s] é incompátivel com o definido [%s] no layout do registro!",
                            length, getFixedLength())
            );
        }
    }

    private boolean isSizeAsDefinaed() {
        if (size() == getFixedSize()) {
            return true;
        } else {
            throw new IllegalStateException(format("O número de fields [%s] é incompátivel com o definido [%s]!", size(), getFixedSize()));
        }
    }

    /**
     * @return the length
     */
    public Integer getFixedLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    protected void setLength(Integer length) {
        if (isNotNull(length)) {
            this.length = length;
        } else {
            throw new IllegalArgumentException(format("Comprimento inválido [%s]!", length));
        }
    }

    /**
     * @return the size
     */
    public Integer getFixedSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    protected void setSize(Integer size) {
        if (isNotNull(size)) {
            this.size = size;
        } else {
            throw new IllegalArgumentException(format("Tamanho inválido [%s]!", size));
        }
    }

    public boolean isTruncate() {
        return this.truncate;
    }

    public void setTruncate(boolean truncate) {
        this.truncate = truncate;
    }

}
