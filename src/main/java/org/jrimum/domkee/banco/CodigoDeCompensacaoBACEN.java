/* 
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created at: 15/06/2008 - 16:11:16
 *
 * ================================================================================
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode 
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma 
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que 
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta 
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam 
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões 
 * e limitações sob esta LICENÇA.
 * 
 * Criado em: 15/06/2008 - 16:11:16
 * 
 */

package org.jrimum.domkee.banco;

import static org.jrimum.utilix.Strings.fillWithZeroLeft;

import org.jrimum.utilix.Objects;
import org.jrimum.vallia.CodigoDeCompensacaoBancosBACENDV;
/**
 * <p>
 * Código de compensação para bancos supervisionados pelo <a
 * href="http://www.bcb.gov.br/?CHEQUESCOMPE">BACEN</a>
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class CodigoDeCompensacaoBACEN {

	private static final CodigoDeCompensacaoBancosBACENDV dv4Compensacao = new CodigoDeCompensacaoBancosBACENDV();
	
	private Integer codigo;

	private Integer digito;

	/**
	 * @param codigo
	 */
	public CodigoDeCompensacaoBACEN(Integer codigo) {
		super();
		
		setCodigo(codigo);
	}

	/**
	 * @param codigo
	 */
	public CodigoDeCompensacaoBACEN(String codigo) {
		super();
		
		setCodigo(codigo);
	}

	private void initDV(){
		
		if(dv4Compensacao.isCodigoValido(codigo)){
			this.digito = dv4Compensacao.calcule(codigo);
		}
	}
	
	/**
	 * @return the codigo formatado ex: "001"
	 */
	public String getCodigoFormatado() {
		return fillWithZeroLeft(getCodigo(), 3);
	}
	
	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		
		if(dv4Compensacao.isCodigoValido(codigo)){
			this.codigo = codigo;
			initDV();
		}
	}
	
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		
		if(dv4Compensacao.isCodigoValido(codigo)){
			this.codigo = Integer.valueOf(codigo);
			initDV();
		}
	}

	/**
	 * @return the digito
	 */
	public Integer getDigito() {
		return digito;
	}

	@Override
	public String toString() {
		return Objects.toString(this);
	}
}