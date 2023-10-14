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
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jrimum.utilix.Objects.isNotNull;
import static org.jrimum.utilix.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
@SuppressWarnings("serial")
public class FlatFile implements org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> {

    private List<Record> records;

    private Set<String> repitablesRecords;

    private List<String> recordsOrder;

    private IRecordFactory<Record> recordFactory;

    public FlatFile(IRecordFactory<Record> iFac4Rec) {

        this.recordFactory = iFac4Rec;
        this.records = new ArrayList<Record>();
    }

    public Record getRecord(String idName) {

        Record record = null;

        if (isNotBlank(idName)) {
            if (!isRepitable(idName)) {
                if (!records.isEmpty()) {
                    for (Record rec : records) {
                        if (idName.equals(rec.getName())) {
                            record = rec;
                        }
                    }
                }
            }
        }

        return record;
    }

    public boolean isRepitable(String idName) {

        return (isNotNull(repitablesRecords) && !repitablesRecords.isEmpty() && repitablesRecords.contains(idName));
    }

    public org.jrimum.texgit.IRecord createRecord(String idName) {
        return recordFactory.create(idName);
    }

    public void addRecord(Record record) {
        if (isNotNull(record)) {
            if (isMyRecord(record.getName())) {
                records.add(record);
            } else {
                throw new IllegalArgumentException("Record fora de scopo!");
            }
        }
    }

    public boolean isMyRecord(String idName) {
        boolean is = false;
        if (isNotBlank(idName)) {
            if (!recordsOrder.isEmpty()) {
                if (recordsOrder.contains(idName)) {
                    is = true;
                }
            }
        }
        return is;
    }

    public void read(List<String> str) {
        if (isNotNull(str)) {
            if (!str.isEmpty()) {
                String line = null;
                int lineIndex = 0;
                FixedField<String> typeRecord = null;
                Record record = null;

                for (String id : recordsOrder) {
                    record = recordFactory.create(id);
                    try {
                        if (isRepitable(id)) {
                            boolean read = true;
                            while (read) {
                                if (isNull(record)) {
                                    record = recordFactory.create(id);
                                }

                                if (lineIndex < str.size()) {
                                    line = str.get(lineIndex);
                                }

                                typeRecord = record.readID(line);
                                read = record.getIdType().getValue().equals(typeRecord.getValue()) && (lineIndex < str.size());

                                if (read) {
                                    record.read(line);
                                    lineIndex++;
                                    addRecord(record);

                                    if (record.isHeadOfGroup()) {
                                        lineIndex = record.readInnerRecords(str, lineIndex, recordFactory);
                                    }
                                    record = null;
                                }
                            }

                        } else {
                            if ((lineIndex < str.size())) {
                                line = str.get(lineIndex);
                                typeRecord = record.readID(line);
                                if (record.getIdType().getValue().equals(typeRecord.getValue())) {
                                    record.read(line);
                                    lineIndex++;
                                    addRecord(record);
                                    if (record.isHeadOfGroup()) {
                                        lineIndex = record.readInnerRecords(str, lineIndex, recordFactory);
                                    }
                                    record = null;
                                }
                            }
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException(format(
                                "Erro ao tentar ler o registro \"%s\".", record.getName()), e);
                    }
                }
            }
        }
    }

    public List<String> write() {
        return write(EMPTY);
    }

    public List<String> write(String lineEnding) {
        ArrayList<String> out = new ArrayList<String>(records.size());
        for (String id : recordsOrder) {
            if (isRepitable(id)) {
                Record rec = null;
                for (org.jrimum.texgit.IRecord record : getRecords(id)) {

                    rec = Record.class.cast(record);

                    try {

                        out.add(rec.write() + lineEnding);

                    } catch (Exception e) {

                        throw new IllegalStateException(format(
                                "Erro ao tentar escrever o registro \"%s\".", rec.getName()), e);
                    }

                    if (rec.isHeadOfGroup() && rec.hasInnerRecords()) {
                        out.addAll(rec.writeInnerRecords(lineEnding));
                    }
                }

            } else {

                Record rec = getRecord(id);

                try {

                    out.add(rec.write() + lineEnding);

                } catch (Exception e) {

                    throw new IllegalStateException(format(
                            "Erro ao tentar escrever o registro \"%s\".", rec.getName()), e);
                }

                if (rec.isHeadOfGroup() && rec.hasInnerRecords()) {
                    out.addAll(rec.writeInnerRecords(lineEnding));
                }
            }
        }

        return out;
    }

    public org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> addRecord(org.jrimum.texgit.IRecord record) {

        if (isNotNull(record)) {
            Record rec = Record.class.cast(record);
            addRecord(rec);
        }

        return this;
    }

    public Collection<org.jrimum.texgit.IRecord> getRecords(String idName) {

        List<org.jrimum.texgit.IRecord> secRecords = new ArrayList<org.jrimum.texgit.IRecord>();

        if (isNotBlank(idName)) {
            if (isRepitable(idName)) {
                if (!records.isEmpty()) {
                    for (Record rec : records) {
                        if (idName.equals(rec.getName())) {
                            secRecords.add(rec);
                        }
                    }
                }
            }
        }

        return secRecords;
    }

    public org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> addAllRecords(Collection<org.jrimum.texgit.IRecord> records) {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> addRecords(String idName, Collection<org.jrimum.texgit.IRecord> records) {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public Collection<org.jrimum.texgit.IRecord> getAllRecords() {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public org.jrimum.texgit.IRecord removeRecord(String idName) {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> setAllRecords(Collection<org.jrimum.texgit.IRecord> records) {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public org.jrimum.texgit.IFlatFile<org.jrimum.texgit.IRecord> setRecords(String idName, Collection<org.jrimum.texgit.IRecord> records) {
        // TODO IMPLEMENTAR
        throw new UnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
    }

    public Set<String> getRepitablesRecords() {
        return repitablesRecords;
    }

    public void setRepitablesRecords(Set<String> repitablesRecords) {
        this.repitablesRecords = repitablesRecords;
    }

    public List<String> getRecordsOrder() {
        return recordsOrder;
    }

    public void setRecordsOrder(List<String> recordsOrder) {
        this.recordsOrder = recordsOrder;
    }

}
