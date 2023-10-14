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
 * Created at: 30/03/2008 - 18:57:33
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
 * Criado em: 30/03/2008 - 18:57:33
 * 
 */

package org.jrimum.domkee.banco;

import org.jrimum.utilix.Objects;

/**
 * 
 * <p>
 *  Carteira de Cobrança de uma Conta Corrente.
 * </p>
 * 
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class Carteira {

	/**
	 * @see #setNome(String)
	 */
	private String nome;

	/**
	 * 
	 * 
	 */
	private Integer codigo;

	/**
	 * @see #setTipoCobranca(TipoDeCobranca)
	 */
	private TipoDeCobranca tipoDeCobranca;

	/**
	 * 
	 */
	public Carteira() {
		super();
	}	

	/**
	 * @param codigo
	 */
	public Carteira(Integer codigo) {
		super();
		this.codigo = codigo;
	}


	/**
	 * @param codigo
	 * @param cobranca
	 */
	public Carteira(Integer codigo, TipoDeCobranca cobranca) {
		super();
		this.codigo = codigo;
		this.tipoDeCobranca = cobranca;
	}


	/**
	 * @param codigo
	 * @param cobranca
	 * @param nome
	 */
	public Carteira(Integer codigo, TipoDeCobranca cobranca, String nome) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.tipoDeCobranca = cobranca;
	}

	/**
	 * @see #setNome(String)  
	 * 
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * <p>
	 * Nome da modalidade de cobrança (ou serviços) atribuído a carteira.
	 * </p>
	 * 
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @see #setCodigo(Integer)
	 * 
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * <p>
	 * Código adotado pela <a href="http://www.febraban.org.br">FEBRABAN</a>, para identificar a característica dos
	 * títulos dentro das modalidades de cobrança existentes no banco.
	 * </p>
	 * 
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the tipoCobranca
	 */
	public TipoDeCobranca getTipoCobranca() {
		return tipoDeCobranca;
	}

	
	
	/**
	 * <p>
	 * Define o tipo de cobrança da carteira (Registrada ou não-registrada). 
	 * </p>
	 * 
	 * @param cobranca
	 * 
	 * @since 0.2
	 */
	public void setTipoCobranca(TipoDeCobranca cobranca) {
		this.tipoDeCobranca = cobranca;
	}
	
	/**
	 * <p>
	 * Indica se é uma carteira registrada.
	 * </p>
	 * 
	 * @return true se registrada
	 */
	public boolean isComRegistro() {
		return (this.tipoDeCobranca == TipoDeCobranca.COM_REGISTRO);
	}
	
	/**
	 * <p>
	 * Indica se é uma carteira não registrada.
	 * </p>
	 * 
	 * @return true se sem registro
	 */
	public boolean isSemRegistro() {
		return (this.tipoDeCobranca == TipoDeCobranca.SEM_REGISTRO);
	}	

	@Override
	public String toString() {
		return Objects.toString(this);
	}
}
