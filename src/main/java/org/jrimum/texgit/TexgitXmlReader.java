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

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static org.jrimum.utilix.Objects.isNotNull;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jrimum.texgit.Texgit;
import org.jrimum.texgit.TexgitException;
import org.jrimum.utilix.ClassLoaders;
import org.xml.sax.SAXException;



/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
class TexgitXmlReader {

	public static MetaTexgit parse(InputStream xmlDefStream) throws TexgitException {

		MetaTexgit txg = null;

		if (isNotNull(xmlDefStream)) {

			try {

				JAXBContext aJaxbContext = JAXBContext
						.newInstance(MetaTexgit.class);

				Unmarshaller aUnmarshaller = aJaxbContext.createUnmarshaller();

				SchemaFactory aSchemaFactory = SchemaFactory
						.newInstance(W3C_XML_SCHEMA_NS_URI);
				
				Schema schema = aSchemaFactory.newSchema(ClassLoaders.getResource("TexgitSchema.xsd",Texgit.class));

				aUnmarshaller.setSchema(schema);

				aUnmarshaller.setEventHandler(new TexgitSchemaValidator());

				txg = (MetaTexgit) aUnmarshaller.unmarshal(xmlDefStream);

			} catch (SAXException e) {

				throw new TexgitLanguageException(e);

			} catch (JAXBException e) {

				throw new TexgitLanguageException(e);

			}
		}

		return txg;
	}

}
