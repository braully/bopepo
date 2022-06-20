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

import org.jrimum.utilix.Strings;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
public enum Fillers implements IFiller {

    /**
     * Filler padrão para preenchimento com zeros a esquerda.
     */
    ZERO_LEFT(new Filler<Integer>(0, Side.LEFT)),
    /**
     * Filler padrão para preenchimento com zeros a direita.
     */
    ZERO_RIGHT(new Filler<Integer>(0, Side.RIGHT)),
    /**
     * Filler padrão para preenchimento com espaços em branco a esquerda.
     */
    WHITE_SPACE_LEFT(new Filler<String>(Strings.WHITE_SPACE, Side.LEFT)),
    /**
     * Filler padrão para preenchimento com espaços em branco a direita.
     */
    WHITE_SPACE_RIGHT(new Filler<String>(Strings.WHITE_SPACE, Side.RIGHT));

    private final Filler<?> filler;

    private Fillers(Filler<?> filler) {
        this.filler = filler;
    }

    public Filler<?> getFiller() {
        return filler;
    }

    /**
     * @param toFill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(java.lang.String, int)
     */
    public String fill(String toFill, int length) {
        return filler.fill(toFill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(long, int)
     */
    public String fill(long tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(int, int)
     */
    public String fill(int tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(short, int)
     */
    public String fill(short tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(byte, int)
     */
    public String fill(byte tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(char, int)
     */
    public String fill(char tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(double, int)
     */
    public String fill(double tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(float, int)
     */
    public String fill(float tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see org.jrimum.texgit.Filler#fill(java.lang.Object, int)
     */
    public String fill(Object tofill, int length) {
        return filler.fill(tofill, length);
    }

    /**
     * @param tofill
     * @param length
     * @return String preenchida
     * @see
     * org.jrimum.texgit.Filler#fill(org.jrimum.texgit.TextStream,
     * int)
     */
    public String fill(TextStream tofill, int length) {
        return filler.fill(tofill, length);
    }

}
