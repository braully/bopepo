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
 * Created at: 30/03/2008 - 19:07:05
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
 * Criado em: 30/03/2008 - 19:07:05
 * 
 */


package org.jrimum.domkee.pessoa;

import org.jrimum.utilix.Objects;


/**
 * 
 * <p>
 * Representação básica de um CEP.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class CEP {

	/**
	 * Primeiros cinco dígitos.
	 * Exemplo: Para o CEP 59.020-300 o prefixo é o 59020.
	 */
	private Integer prefixo;
	
	/**
	 * Últimos dois dígitos.
	 * Exemplo: Para o CEP 59.020-300 o sufixo é o 300.
	 */
	private Integer sufixo;
	
	/**
	 * CEP completo (prefixo+sufixo).
	 * Exemplo: Para o CEP 59.020-300 o cep pode ser:
	 * <ul>
	 * <li>59.020-300<li>
	 * <li>59020300<li>
	 * </ul>
	 */
	private String cep;
	
	public CEP() {}
	
	public CEP(String cep) {
		
		this.cep = cep;
	}

	public Integer getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Integer prefixo) {
		this.prefixo = prefixo;
	}

	public Integer getSufixo() {
		return sufixo;
	}

	public void setSufixo(Integer sufixo) {
		this.sufixo = sufixo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return Objects.toString(this);
	}
}
