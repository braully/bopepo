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
 * Created at: 30/03/2008 - 18:58:20
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
 * Criado em: 30/03/2008 - 18:58:20
 * 
 */

package org.jrimum.domkee.pessoa;

import static org.jrimum.utilix.Objects.isNotNull;
import static org.jrimum.utilix.Objects.isNull;

import java.util.Collection;

import org.jrimum.domkee.pessoa.RG;


/**
 * <p>
 * Representação básica de uma Pessoa Física.
 * </p>
 * 
 * @see Pessoa
 * @see org.jrimum.domkee.pessoa.CPRF
 * @see org.jrimum.domkee.pessoa.CPF
 * 
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

public class PessoaFisica extends Pessoa implements
		org.jrimum.domkee.pessoa.IPessoaFisica {

	private org.jrimum.domkee.pessoa.IPessoaFisica conjuge;

	private String estadoCivil;

	private Collection<RG> rgs;

	public PessoaFisica() {
	}

	public org.jrimum.domkee.pessoa.IPessoaFisica getConjuge() {

		return conjuge;
	}

	public String getEstadoCivil() {

		return estadoCivil;
	}

	public Collection<RG> getRgs() {

		return rgs;
	}

	public void setConjuge(
			org.jrimum.domkee.pessoa.IPessoaFisica conjuge) {

		this.conjuge = conjuge;

		if (isNotNull(conjuge) && isNull(conjuge.getConjuge()))
			conjuge.setConjuge(this);
	}

	public void setEstadoCivil(String estadoCivil) {

		this.estadoCivil = estadoCivil;
	}

	public void setRgs(Collection<RG> rgs) {

		this.rgs = rgs;
	}

}
