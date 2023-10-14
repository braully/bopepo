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
 * Created at: 30/03/2008 - 19:09:13
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
 * Criado em: 30/03/2008 - 19:09:13
 * 
 */

package org.jrimum.domkee.banco;

import static java.lang.String.format;

import org.jrimum.utilix.Exceptions;

/**
 * 
 * <p>
 * Enumeração de códigos adotados pela <a
 * href="http://www.febraban.org.br/">FEBRABAN</a> para identificar o tipo de
 * título de cobrança. Domínio:
 * </p>
 * 
 * <p>
 * 
 * <ul>
 * <li> '01' = CH Cheque</li>
 * <li> '02' = DM Duplicata Mercantil</li>
 * <li> '03' = DMI Duplicata Mercantil p/ Indicação</li>
 * <li> '04' = DS Duplicata de Serviço</li>
 * <li> '05' = DSI Duplicata de Serviço p/ Indicação</li>
 * <li> '06' = DR Duplicata Rural</li>
 * <li> '07' = LC Letra de Câmbio</li>
 * <li> '08' = NCC Nota de Crédito Comercial</li>
 * <li> '09' = NCE Nota de Crédito a Exportação</li>
 * <li> '10' = NCI Nota de Crédito Industrial</li>
 * <li> '11' = NCR Nota de Crédito Rural</li>
 * <li> '12' = NP Nota Promissória</li>
 * <li> '13' = NPR Nota Promissória Rural</li>
 * <li> '14' = TM Triplicata Mercantil</li>
 * <li> '15' = TS Triplicata de Serviço</li>
 * <li> '16' = NS Nota de Seguro</li>
 * <li> '17' = RC Recibo</li>
 * <li> '18' = FAT Fatura</li>
 * <li> '19' = ND Nota de Débito</li>
 * <li> '20' = AP Apólice de Seguro</li>
 * <li> '21' = ME Mensalidade Escolar</li>
 * <li> '22' = PC Parcela de Consórcio</li>
 * <li> '23' = NF Nota Fiscal</li>
 * <li> '24' = DD Documento de Dívida</li>
 * <li> ‘25’ = Cédula de Produto Rural</li>
 * <li> ‘26’ = Warrant</li>
 * <li> ‘27’ = Dívida Ativa de Estado</li>
 * <li> ‘28’ = Dívida Ativa de Município</li>
 * <li> ‘29’ = Dívida Ativa da União</li>
 * <li> ‘30’ = Cota Condominial</li>
 * <li> '99' = Outros</li>
 * </ul>
 * </p>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

public enum TipoDeTitulo{

	CH_CHEQUE(1,"CH"),
	DM_DUPLICATA_MERCANTIL(2,"DM"), 
	DMI_DUPLICATA_MERCANTIL_PARA_INDICACAO(3,"DMI"), 
	DS_DUPLICATA_DE_SERVICO(4,"DS"), 
	DSI_DUPLICATA_DE_SERVICO_PARA_INDICACAO(5,"DSI"), 
	DR_DUPLICATA_RURAL(6,"DR"), 
	LC_LETRA_DE_CAMBIO(7,"LC"), 
	NCC_NOTA_DE_CREDITO_COMERCIAL(8,"NCC"), 
	NCE_NOTA_DE_CREDITO_A_EXPORTACAO(9,"NCE"), 
	NCI_NOTA_DE_CREDITO_INDUSTRIAL(10,"NCI"), 
	NCR_NOTA_DE_CREDITO_RURAL(11,"NCR"), 
	NP_NOTA_PROMISSORIA(12,"NP"), 
	NPR_NOTA_PROMISSORIA_RURAL(13,"NPR"), 
	TM_TRIPLICATA_MERCANTIL(14,"TM"), 
	TS_TRIPLICATA_DE_SERVICO(15,"TS"), 
	NS_NOTA_DE_SEGURO(16,"NS"), 
	RC_RECIBO(17,"RC"), 
	FAT_FATURA(18,"FAT"), 
	ND_NOTA_DE_DEBITO(19,"ND"), 
	AP_APOLICE_DE_SEGURO(20,"AP"), 
	ME_MENSALIDADE_ESCOLAR(21,"ME"), 
	PC_PARCELA_DE_CONSORCIO(22,"PC"), 
	NF_NOTA_FISCAL(23,"NF"), 
	DD_DOCUMENTO_DE_DIVIDA(24,"DD"), 
	CEDULA_DE_PRODUTO_RURAL(25,"CPR"), 
	WARRANT(26,"W"), 
	DIVIDA_ATIVA_DE_ESTADO(27,"DAE"), 
	DIVIDA_ATIVA_DE_MUNICIPIO(28,"DAM"), 
	DIVIDA_ATIVA_DA_UNIAO(29,"DAU"), 
	COTA_CONDOMINIAL(30,"CC"), 
	OUTROS(99,"O");

	/**
	 * <p>
	 * Código adotado pela FEBRABAN para identificar o tipo de título de
	 * cobrança.
	 * </p>
	 */
	private int codigo;

	/**
	 * <p>
	 * Sigla ou nome compelto de um tipo de título de cobrança.
	 * </p>
	 */
	private String sigla;
	
	
	/**
	 * <p>
	 * Instancia os tipos de a cordo com os valores passados.
	 * </p>
	 * 
	 * @param codigo
	 * @param sigla
	 * 
	 * @since 0.2
	 */
	private TipoDeTitulo(int codigo, String sigla) {
		this.codigo = codigo;
		this.sigla = sigla;
	}

	/**
	 * <p>
	 * Apesar do nome, este método pode retornar não uma sigla, e sim, um nome
	 * compelto de uma espécie de título de cobrança. Isto se justifica pelo
	 * fato de que não há uma sigla determinada pela FEBRABAN para alguns casos.
	 * </p>
	 * 
	 * @return sigla ou descrição completa.
	 * 
	 * @since 0.2
	 */
	public String getSigla(){

		return sigla;
	}

	/**
	 * <p>
	 * Código adotado pela FEBRABAN para identificar o tipo de título de
	 * cobrança.
	 * </p>
	 * 
	 * @return código FEBRABAN
	 * 
	 * @since 0.2
	 */
	public int getCodigo() {
		
		return codigo;
	}
	
	/**
	 * Retorna uma instância que corresponde com a sigla do tipo de título.
	 * Caso não exista um tipo associado a sigla determinada uma {@code
	 * IllegalArgumentException} será lançada.
	 * 
	 * @param sigla
	 *            - Sigla do tipo de título procurado
	 * @return tipo de título correspondente a sigla
	 * 
	 * @since 0.2
	 */
	public static TipoDeTitulo valueOfSigla(String sigla){
		for(TipoDeTitulo t : values()){
			if(t.getSigla().equals(sigla)){
				return t;
			}
		}
		return Exceptions.throwIllegalArgumentException(format("Nenhuma constante enum %s com sigla igual a %s!", TipoDeTitulo.class, sigla));
	}

	/**
	 * Retorna uma instância que corresponde com o código do tipo de título.
	 * Caso não exista um tipo associado ao código determinado uma {@code
	 * IllegalArgumentException} será lançada.
	 * 
	 * @param codigo
	 *            - Código do tipo de título procurado
	 * @return tipo de título correspondente ao código
	 * 
	 * @since 0.2
	 */
	public static TipoDeTitulo valueOf(int codigo){
		for(TipoDeTitulo t : values()){
			if(t.getCodigo() == codigo){
				return t;
			}
		}
		return Exceptions.throwIllegalArgumentException(format("Nenhuma constante enum %s com código igual a %s!", TipoDeTitulo.class, codigo));
	}
	
}
