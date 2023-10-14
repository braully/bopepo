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

import static org.jrimum.utilix.Objects.isNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
public class FlatFileBuilder {

	static FlatFile build(MetaFlatFile mFlatFile) {
		
		FlatFile ff = null;
		
		List<MetaRecord> metaRecords = mFlatFile.getGroupOfRecords().getRecords();

		ff = new FlatFile(new RecordFactory(metaRecords));
		
		Set<String> repitables = new HashSet<String>();
		
		List<String> recordsOrder = new ArrayList<String>();
		
		if (isNotNull(metaRecords)) {
			
			if (!metaRecords.isEmpty()) {
				
				for (MetaRecord mRec : metaRecords) {
					
					recordsOrder.add(mRec.getName());
					
					if (mRec.isRepeatable())
						repitables.add(mRec.getName());
				}
			}
		}
			
		ff.setRecordsOrder(recordsOrder);
		ff.setRepitablesRecords(repitables);
		
		return ff;
	}
	
}
