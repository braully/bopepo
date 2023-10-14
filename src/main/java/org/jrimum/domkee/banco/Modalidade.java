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
 * Created at: 30/03/2008 - 18:59:18
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
 * Criado em: 30/03/2008 - 18:59:18
 * 
 */


package org.jrimum.domkee.banco;

import org.apache.commons.lang3.StringUtils;
import org.jrimum.utilix.Objects;


/**
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 *
 */
public class Modalidade {

	private String codigo;
	
	private String nome;
	
	public Modalidade() {
	}
	
	public Modalidade(String codigo) {
		this.codigo = codigo;	
	}
	
	public Modalidade(Integer codigo) {
		this.codigo = String.valueOf(codigo);	
	}	
	
	public Modalidade(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;		
	}

	public Modalidade(Integer codigo, String nome) {
		this.codigo = String.valueOf(codigo);
		this.nome = nome;
	}	
	
	public String getCodigo() {
		return codigo;
	}

	public Integer getCodigoAsInteger() {
		
		if (StringUtils.isNumeric(codigo)) {
			return Integer.parseInt(codigo);
			
		} else {
			return null;
		}
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = String.valueOf(codigo);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return Objects.toString(this);
	}	
}
