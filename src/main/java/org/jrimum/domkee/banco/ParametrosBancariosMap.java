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
 * Created at: 14/02/2010 - 18:00:18
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
 * Criado em: 14/02/2010 - 18:00:18
 * 
 */

package org.jrimum.domkee.banco;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jrimum.utilix.Objects;

/**
 * Mapa de parâmetros com dados bancário para condições específicas de
 * determinados bancos ou implementações. <strong>PARÂMETROS NULOS NÃO SÃO
 * PERMITIDOS.</strong>
 * 
 * <p>
 * Mais dados bancários podem ser necessários a um título para gerar um boleto,
 * por exemplo. Assim, dependendo do banco, talvez seja necessário informar mais
 * dados além de:
 * </p>
 * 
 * <ul>
 * <li>Valor do título;</li>
 * <li>Vencimento;</li>
 * <li>Nosso número;</li>
 * <li>Código do banco;</li>
 * <li>Data de vencimento;</li>
 * <li>Agência/Código do cedente</li>;
 * <li>Código da carteira;</li>
 * <li>Código da moeda;</li>
 * </ul>
 * 
 * <p>
 * Definidos como padrão pela FEBRABAN.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @version 0.2
 * @since 0.2
 */
public final class ParametrosBancariosMap {

	private Map<ParametroBancario<?>, Number> dadosMap;
	
	/**
	 * Instancia um mapa sem parâmetros.
	 * 
	 * @since 0.2
	 */
	public ParametrosBancariosMap() {
		getInstance();
	}

	/**
	 * Instancia o mapa com um mapeamento já especificado. Nomes e valores nulos
	 * não são permitidos, o nome deve ser único.
	 * 
	 * @param nome
	 *            do parâmetro a ser associado
	 * @param valor
	 *            do parâmetro a ser associado
	 * 
	 * @throws IllegalArgumentException
	 *             se nome ou valor == NULL
	 * 
	 * @since 0.2
	 */
	public ParametrosBancariosMap(ParametroBancario<?> nome, Number valor) {

		adicione(nome, valor);
	}
	
	/**
	 * Verifica se o {@code ParametrosBancariosMap} passado por parâmetro
	 * <strong>não</strong> é <code>null</code> e possui elementos.
	 * 
	 * 
	 * @param params
	 *            - Instância de {@code ParametrosBancariosMap} testada.
	 * @return {@code params != null && params.isNaoVazio()}
	 * 
	 * @since 0.2
	 */
	public static boolean hasElement(ParametrosBancariosMap params){
		
		return params != null && params.isNaoVazio();
	}

	/**
	 * Indica se o mapa contém um mapeamento para o nome de parâmetro
	 * especificado.
	 * 
	 * @param nome
	 *            do parâmetro cuja existência no mapa será testada.
	 * 
	 * @return true se o mapa contém um mapeamento para parâmetro
	 * 
	 * @throws NullPointerException
	 *             se nome == NULL
	 * 
	 * @since 0.2
	 */
	public boolean contemComNome(ParametroBancario<?> nome) {

		Objects.checkNotNull(nome);

		return dadosMap.containsKey(nome);
	}

	/**
	 * Indica se o mapa contém o valor especificado.
	 * 
	 * @param valor
	 *            cuja existência no mapa será testada
	 * 
	 * @return true se o mapa contém uma ou mais associações de parâmetros ao
	 *         valor especificado
	 * 
	 * @throws IllegalArgumentException
	 *             se valor == NULL
	 * 
	 * @since 0.2
	 */
	public <V extends Number> boolean contemComValor(V valor) {

		Objects.checkNotNull(valor);

		return dadosMap.containsValue(valor);
	}

	/**
	 * Retorna o valor para o qual o parâmetro bancário especificada está
	 * mapeado, ou nulo, se este mapa contém não contém nenhum mapeamento para
	 * tal parâmetro. Nomes nulos não são permitidos.
	 * 
	 * @param <V>
	 *            Tipo de retorno genérico do valor contido no mapa com nome
	 *            especificado
	 * @param nome
	 *            do parâmetro especificado
	 * @return o valor associado com o parâmetro bancário especificado, ou nulo
	 *         se não houver nenhum mapeamento para o mesmo
	 * 
	 * @throws IllegalArgumentException
	 *             se nome == NULL
	 * 
	 * @since 0.2
	 */
	@SuppressWarnings("unchecked")
	public <V extends Number> V getValor(ParametroBancario<?> nome) {

		Objects.checkNotNull(nome);

		return (V) dadosMap.get(nome);
	}

	/**
	 * Indica se o mapa não contém associações (parâmetro,valor)
	 * 
	 * @return true se vazio
	 * 
	 * @since 0.2
	 */
	public boolean isVazio() {

		return dadosMap.isEmpty();
	}

	/**
	 * Indica se o mapa contém alguma associação (parâmetro,valor)
	 * 
	 * @return !isVazio()
	 * 
	 * @since 0.2
	 */
	public boolean isNaoVazio() {

		return !isVazio();
	}

	/**
	 * Retorna um conjuto de nomes de parâmetro do mapa.
	 * 
	 * @return conjuto de nomes de parâmetro do mapa.
	 * 
	 * @since 0.2
	 */
	public Set<ParametroBancario<?>> nomes() {

		return dadosMap.keySet();
	}

	/**
	 * Retorna uma coleção com os valores contidos no mapa.
	 * 
	 * @return coleção de valores do mapa.
	 * 
	 * @since 0.2
	 */
	public Collection<? extends Number> valores() {

		return dadosMap.values();
	}

	/**
	 * Retorna um conjuto de entradas (parâmetro,valor) do mapa. Assim qualquer
	 * modificação nestes valores também reflete no mapa de parâmetros
	 * bancários.
	 * 
	 * @return conjunto dos mapeamentos contidos.
	 * 
	 * @since 0.2
	 */
	public Set<Entry<ParametroBancario<?>,Number>> entradas() {
		return dadosMap.entrySet();
	}

	/**
	 * Associa o valor especificado a um nome de parâmetro também especificado
	 * que deve ser único neste mapa. Nomes e valores nulos não são permitidos.
	 * 
	 * @param nome
	 *            do parâmetro a ser associado
	 * @param valor
	 *            do parâmetro a ser associado
	 * @return referência a este mapa
	 * 
	 * @throws NullPointerException
	 *             se nome ou valor == NULL
	 * 
	 * @since 0.2
	 */
	public ParametrosBancariosMap adicione(ParametroBancario<?> nome, Number valor) {

		Objects.checkNotNull(nome);
		Objects.checkNotNull(valor);

		getInstance();

		dadosMap.put(nome, valor);

		return this;
	}

	/**
	 * Adiciona todos os dados bancários de um outro mapeamento neste mapeamento.
	 * Parâmetros nulos não são permitidos.
	 * 
	 * <p>
	 * O efeito desta chamada é equivalente a de adiciona("parametro", Valor)
	 * neste mapa, só que uma vez para cada mapeamento.
	 * </p>
	 * 
	 * @param dados
	 *            - outros parâmetros bancários a serem mapeados neste mapa.
	 * @return referência a este mapa
	 * 
	 * @throws NullPointerException
	 *             se dados == NULL
	 * 
	 * @since 0.2
	 */
	public ParametrosBancariosMap adicione(ParametrosBancariosMap dados) {

		Objects.checkNotNull(dados);

		this.dadosMap.putAll(dados.dadosMap);

		return this;
	}

	/**
	 * Retorna o valor removido associado ao nome do parâmetro bancário
	 * informado, ou nulo se o mapa não contiver nenhum mapeamento. Nomes nulos
	 * não são permitidos.
	 * 
	 * <p>
	 * Um retorno nulo indica necessariamente que não existia nenhum mapeamento
	 * para o nome do parâmetro bancário.
	 * </p>
	 * 
	 * @param <V>
	 *            Tipo de retorno genérico do valor
	 * @param nome
	 *            do parâmetro bancário que deve ser removido do mapa
	 * @return o valor anterior associado com o parâmetro bancário, ou nulo se
	 *         não houver nenhum mapeamento para o mesmo
	 * 
	 * @throws NullPointerException
	 *             se nome == NULL
	 * 
	 * @since 0.2
	 */
	@SuppressWarnings("unchecked")
	public <V extends Number> V remova(ParametroBancario<?> nome) {

		Objects.checkNotNull(nome);

		return (V) dadosMap.remove(nome);
	}

	/**
	 * Remove todos os os parâmetros desta instância.
	 * 
	 * @since 0.2
	 */
	public void limpe() {

		dadosMap.clear();
	}

	/**
	 * @return retorna o número de parâmetros contidos na instância.
	 * 
	 * @since 0.2
	 */
	public int quantidade() {

		return dadosMap.size();
	}

	/**
	 * Instancia um HashMap<String,Object> para uso da deste map bancário.
	 * 
	 * @since 0.2
	 */
	private void getInstance() {

		if (dadosMap == null) {
			dadosMap = new HashMap<ParametroBancario<?>, Number>();
		}
	}

}