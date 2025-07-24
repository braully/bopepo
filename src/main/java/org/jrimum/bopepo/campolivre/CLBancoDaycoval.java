/*
 * Copyright 2010 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 11/08/2010 - 10:23:00
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
 * Criado em: 23/07/2025 - 08:00
 */

package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.parametro.ParametroBancoDaycoval;
import org.jrimum.domkee.banco.Titulo;
import org.jrimum.texgit.Fillers;
import org.jrimum.texgit.FixedField;

/**
 * O campo livre do Banco Daycoval deve seguir esta forma:
 * 
 * <table border="1" cellpadding="0" cellspacing="0" style="border-collapse:
 * collapse" bordercolor="#111111" width="60%" id="campolivre">
 * <tr>
 * <thead>
 * <th>Posição</th>
 * <th>Tamanho</th>
 * <th>Picture</th>
 * <th>Conteúdo</th>
 * </thead>
 * </tr>
 * <tr>
 * <td>20-23</td>
 * <td>4</td>
 * <td>4</td>
 * <td>Código da agência</td>
 * </tr>
 * <tr>
 * <tr>
 * <td>24-26</td>
 * <td>3</td>
 * <td>3</td>
 * <td>Carteira</td>
 * </tr>
 * <tr>
 * <td>27-33</td>
 * <td>7</td>
 * <td>7</td>
 * <td>Operação</td>
 * </tr>
 * <tr>
 * <td>34-44</td>
 * <td>11</td>
 * <td>11</td>
 * <td>Nosso número + DV</td>
 * </tr>
 * </table>
 * 
 * 
 * @author <a href="https://github.com/alessandrobrunolima">Alessandro Bruno
 *         Lima</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
class CLBancoDaycoval extends AbstractCLBancoDaycoval {

	/**
	 * 
	 */
	private static final long serialVersionUID = 858563493013156459L;

	/**
	 * Número de campos = 4.
	 */
	private static final Integer FIELDS_LENGTH = 4;

	/**
	 * Tamanho do campo Agência = 4.
	 */
	private static final Integer AGENCIA_LENGTH = Integer.valueOf(4);

	/**
	 * Tamanho do campo Carteira = 3.
	 */
	private static final Integer CARTEIRA_LENGTH = Integer.valueOf(3);

	/**
	 * Tamanho do campo Nosso Número = 11.
	 */
	private static final Integer NOSSO_NUMERO_LENGTH = Integer.valueOf(11);

	/**
	 * Tamanho do campo Operação = 7.
	 */
	private static final Integer OPERACAO_LENGTH = Integer.valueOf(7);

	/**
	 * Cria um campo livre instanciando o número de fields ({@code FIELDS_LENGTH}) deste campo.
	 *
	 * @since 0.2
	 */
	protected CLBancoDaycoval() {
		super(FIELDS_LENGTH);
	}

	@Override
	protected void addFields(Titulo titulo) {
		StringBuilder nossoNumero = new StringBuilder(titulo.getNossoNumero());
		nossoNumero.append(titulo.getDigitoDoNossoNumero());

		this.add(new FixedField<Integer>(titulo.getContaBancaria().getAgencia().getCodigo(), AGENCIA_LENGTH, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(titulo.getContaBancaria().getCarteira().getCodigo(), CARTEIRA_LENGTH, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(titulo.getParametrosBancarios().<Integer>getValor(ParametroBancoDaycoval.OPERACAO), OPERACAO_LENGTH, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(nossoNumero.toString(), NOSSO_NUMERO_LENGTH, Fillers.ZERO_LEFT));
	}

	@Override
	protected void checkValues(Titulo titulo) {
		checkAgenciaNotNull(titulo);
		checkCodigoDaAgencia(titulo);
		checkCodigoDaAgenciaMenorOuIgualQue(titulo, 9999);
		checkCarteiraNotNull(titulo);
		checkCodigoDaCarteira(titulo);
		checkCodigoDaCarteiraMenorOuIgualQue(titulo, 999);
		checkParametroBancario(titulo, ParametroBancoDaycoval.OPERACAO);
		checkParametroBancarioMenorOuIgualQue(titulo, ParametroBancoDaycoval.OPERACAO, 9999999);
		checkNossoNumero(titulo);
		checkTamanhoDoNossoNumero(titulo, NN10);
		checkDigitoDoNossoNumero(titulo);
		checkTamanhoDigitoDoNossoNumero(titulo, 1);
	}
}
