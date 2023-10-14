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
 * Created at: 30/03/2008 - 18:58:26
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
 * Criado em: 30/03/2008 - 18:58:26
 * 
 */


package org.jrimum.domkee.pessoa;



/**
 * <p>
 *  Representação básica de uma Pessoa Jurídica.
 * </p>
 * 
 * @see Pessoa
 * @see org.jrimum.domkee.comum.pessoa.id.cprf.CPRF
 * @see org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class PessoaJuridica extends Pessoa implements org.jrimum.domkee.pessoa.IPessoaJuridica {

	private Long inscricaoEstadual;
	
	private Long inscricaoMunicipal;
	
	/**
	 *  Título do estabelecimento, diferente do <code>nome</code> (NOME EMPRESARIAL). 
	 */
	private String nomeFantasia;

	
	public Long getInscricaoEstadual() {

		return inscricaoEstadual;
	}

	
	public Long getInscricaoMunicipal() {

		return inscricaoMunicipal;
	}

	
	public String getNomeFantasia() {

		return nomeFantasia;
	}

	
	public void setInscricaoEstadual(Long inscricaoEstadual) {

		this.inscricaoEstadual = inscricaoEstadual;
	}

	
	public void setInscricaoMunicipal(Long inscricaoMunicipal) {

		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	
	public void setNomeFantasia(String nomeFantasia) {

		this.nomeFantasia = nomeFantasia;
	}

}
