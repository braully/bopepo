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

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jrimum.utilix.Objects.isNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
public class RecordFactory implements org.jrimum.texgit.IRecordFactory<Record> {

    private Map<String, MetaRecord> name_record;

    RecordFactory(List<MetaRecord> metaRecords) {

        if (isNotNull(metaRecords)) {
            if (!metaRecords.isEmpty()) {

                name_record = new HashMap<String, MetaRecord>(metaRecords
                        .size());

                for (MetaRecord mRecord : metaRecords) {

                    name_record.put(mRecord.getName(), mRecord);

                    if (isNotNull(mRecord.getGroupOfInnerRecords())) {
                        loadInnerRecords(name_record, mRecord
                                .getGroupOfInnerRecords().getRecords());
                    }
                }
            }
        }
    }

    private void loadInnerRecords(Map<String, MetaRecord> name_record,
            List<MetaRecord> innerMetaRecords) {

        if (isNotNull(innerMetaRecords)) {
            if (!innerMetaRecords.isEmpty()) {

                for (MetaRecord iMetaRecord : innerMetaRecords) {

                    name_record.put(iMetaRecord.getName(), iMetaRecord);

                    if (isNotNull(iMetaRecord.getGroupOfInnerRecords())) {
                        loadInnerRecords(name_record, iMetaRecord
                                .getGroupOfInnerRecords().getRecords());
                    }
                }
            }
        }

    }

    public Record create(String name) {

        Record record = null;

        if (isNotBlank(name)) {
            if (name_record.containsKey(name)) {
                record = RecordBuilder.build(name_record.get(name));
            }
        }

        return record;
    }
}
