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
 * Created at: 30/03/2008 - 18:17:40
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
 * Criado em: 30/03/2008 - 18:17:40
 * 
 */
package org.jrimum.utilix;

import static org.jrimum.utilix.Objects.isNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * <p>
 * Serviços utilitários relacionados a manipulação de Objetos
 * <code>Date, Calendar, GregorianCalendar.</code>
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 * Mercantil</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public final class Dates {

    /**
     * <p>
     * Representa uma data inexistente, pode ser usada em casos que não se pode
     * usar <code>null</code> [ <em>é obtida da seguinte forma:
     * <code>Calendar.set(1, 0, 1)</code></em> ]
     * </p>
     */
    private static final Date DATE_NULL;

    static {

        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 0, 1);
        calendar.setLenient(false);
        DATE_NULL = DateUtils.truncate(calendar.getTime(), Calendar.YEAR);
    }

    /**
     * Utility class pattern: classe não instanciável
     *
     * @throws IllegalStateException Caso haja alguma tentativa de utilização
     * deste construtor.
     */
    public static final DateFormat FORMAT_YYMMDD = new SimpleDateFormat(
            "yyMMdd");

    /**
     * <p>
     * Formatador de datas no padrão yyyyMMdd.
     * </p>
     */
    public static final DateFormat FORMAT_YYYYMMDD = new SimpleDateFormat(
            "yyyyMMdd");

    private Dates() {

        Exceptions.throwIllegalStateException("Instanciação não permitida!");
    }

    /**
     * <p>
     * Retorna uma data inexistente, pode ser usada em casos que não se pode
     * usar <code>null</code> [ <em>é obtida da seguinte forma:
     * <code>Calendar.set(1, 0, 1)</code></em> ]
     * </p>
     *
     * @return data invalida - 01/01/0001
     */
    public static Date invalidDate() {

        return (Date) DATE_NULL.clone();
    }

    /**
     * <p>
     * Compara uma dada data qualquer com a data invalida 01/01/0001.
     * </p>
     *
     * @param date - Data qualquer
     *
     * @return igualdade - Se igual a data inválida
     */
    public static boolean equalsInvalidDate(Date date) {

        if (date == null) {

            return false;

        } else {

            return (DATE_NULL.compareTo(date) == 0);
        }
    }

    /**
     * <p>
     * Calcula a diferença de dias entre duas datas. O resultado é modular, ou
     * seja, maior ou igual a zero, logo a data final não precisa ser
     * necessariamente maior que a data inicial.
     * </p>
     *
     * @param dataInicial - data inicial do intervalo.
     * @param dataFinal - data final do intervalo.
     * @return número(módulo) de dias entre as datas.
     *
     * @throws IllegalArgumentException Caso pelo menos uma das duas datas seja
     * <code>null</code>.
     * @since 0.2
     */
    public static long calculeDiferencaEmDias(final Date dataInicial, final Date dataFinal) {

        long fator = 0;

        Date dataInicialTruncada, dataFinalTruncada;

        if (isNotNull(dataInicial) && isNotNull(dataFinal)) {

            dataInicialTruncada = DateUtils.truncate(dataInicial, Calendar.DATE);
            dataFinalTruncada = DateUtils.truncate(dataFinal, Calendar.DATE);

            fator = ((dataFinalTruncada.getTime() - dataInicialTruncada.getTime()) / DateUtils.MILLIS_PER_DAY);

            if (fator < 0) {
                fator *= -1;
            }

        } else {

            Exceptions.throwIllegalArgumentException("A data inicial [" + dataInicial
                    + "] e a data final [" + dataFinal + "] "
                    + "não podem ter valor 'null'.");
        }

        return fator;
    }

    /**
     * <p>
     * Converte um objeto <code>String</code> em um objeto
     * <code>java.util.Date</code> a partir do formato de data especificado.
     * </p>
     * <p>
     * Utiliza a sobrecarca
     * <code>parse(String dateAsString, String dateFormat)</code> para realizar
     * a conversão.
     * </p>
     *
     * @param dateAsString - um valor de data em forma de <code>String</code>.
     * @param dateFormat - formato de data em forma de <code>String</code>.
     * @return Objeto <code>java.util.Date</code> convertido a partir do objeto
     * <code>String</code>
     *
     * @throws IllegalArgumentException caso o objeto <code>String</code> não
     * seja um valor válido de data suportado pelo formato.
     * @since 0.2
     */
    public static Date parse(String dateAsString, String dateFormat) {

        if (dateFormat == null) {
            throw new NullPointerException("O formato da data não pode ter valor [null].");
        }

        return parse(dateAsString, new SimpleDateFormat(dateFormat));
    }

    /**
     * <p>
     * Converte um objeto <code>String</code> em um objeto
     * <code>java.util.Date</code> através do objeto
     * <code>java.text.DateFormat</code> especificado.
     * </p>
     *
     * @param dateAsString - um valor de data em forma de <code>String</code>.
     * @param dateFormat - formatador para objetos <code>java.util.Date</code>.
     * @return Objeto <code>java.util.Date</code> convertido a partir do objeto
     * <code>String</code>
     *
     * @throws IllegalArgumentException caso o objeto <code>String</code> não
     * seja um valor válido de data suportado pelo formatador.
     * @since 0.2
     */
    public static Date parse(String dateAsString, DateFormat dateFormat) {

        Date date = null;

        if (dateAsString == null) {
            throw new NullPointerException("A String a ser convertida não pode ter valor [null].");
        }

        if (dateFormat == null) {
            throw new NullPointerException("O formatador não pode ter valor [null].");
        }

        try {

            date = dateFormat.parse(dateAsString);

        } catch (ParseException e) {

            String msg = "A String [" + dateAsString
                    + "] deve ser uma data válida no formato";
            if (dateFormat instanceof SimpleDateFormat) {
                SimpleDateFormat sdf = (SimpleDateFormat) dateFormat;
                msg += " [" + sdf.toPattern() + "].";

            } else {
                msg += " especificado.";
            }

            IllegalArgumentException iae = new IllegalArgumentException(msg);
            iae.initCause(e);
            throw iae;
        }

        return date;
    }

    /**
     * <p>
     * Retorna um objeto <code>java.util.Date</code> somente com a informação do
     * dia, mes e ano. A informação sobre hora, minuto, segundo e millesegundos
     * é zerada. Isto é altamente recomendado em rotinas nas quais datas são
     * comparadas.
     * </p>
     *
     * @param date - um data em forma de <code>java.util.Date</code>.
     *
     * @return Objeto <code>java.util.Date</code> truncado a partir do objeto
     * <code>java.util.Date</code> informado como parâmetro.
     *
     * @throws NullPointerException Caso o objeto <code>java.util.Date</code>
     * esteja nulo
     * @since 0.3
     */
    public static Date truncarData(Date date) {
        if (date == null) {
            throw new NullPointerException("O data não pode ser nula. Impossível realizar o truncamento da data.");
        }

        return DateUtils.truncate(date, Calendar.DATE);
    }

}
