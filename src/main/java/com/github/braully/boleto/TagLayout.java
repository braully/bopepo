/*
 * Copyright 2019 Projeto JRimum.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.braully.boleto;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.lang3.SerializationUtils;
import org.jrimum.domkee.banco.IBanco;
import org.jrimum.texgit.Fillers;
import org.jrimum.texgit.IFiller;

/**
 *
 * @author strike
 */
public class TagLayout implements Serializable {

	public boolean isPermiteQtdeMoeda() {

		// hcosta: unica maneira que encontrei... tenho certeza que tem um jeito mais
		// inteligente de fazer isso

		try {
			TagLayout tagLayout = null;

			if (this.getNome().equals("layout")) {
				tagLayout = this;
			} else {
				tagLayout = this.get("layout");
			}

			if (tagLayout != null) {
				TagLayout tagUrl = tagLayout.get("url");

				if (tagUrl != null && ((String) tagUrl.value).contains("www.bb.com.br")) {
					return false;
				}
			}
		} catch (Exception e) {
			return true;
		}

		return true;

	}


	public boolean isExigeNumeroDocumento() {

		// hcosta: unica maneira que encontrei... tenho certeza que tem um jeito mais
		// inteligente de fazer isso

		try {
			TagLayout tagLayout = null;

			if (this.getNome().equals("layout")) {
				tagLayout = this;
			} else {
				tagLayout = this.get("layout");
			}

			if (tagLayout != null) {
				TagLayout tagUrl = tagLayout.get("url");

				if (tagUrl != null && ((String) tagUrl.value).contains("bradesco")) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return false;

	}


	boolean isAttr(String id) {
		return is(this.atributos.get(id));
	}

	private boolean is(Object get) {
		boolean ret = false;
		if (get != null) {
			try {
				if (get instanceof Boolean) {
					ret = (Boolean) get;
				} else {
					ret = Boolean.parseBoolean(get.toString());
				}
			} catch (Exception e) {

			}
		}
		return ret;
	}

	public static class TagCreator {

		public static TagLayout field(String texto) {
			return tag(texto);
		}

		/* Fields mais comuns */
		/**
		 * Codigo de 3 digitos do banco na FEBRABAN
		 *
		 * @return
		 */
		public static TagLayout fbancoCodigo() {
			return field("bancoCodigo").padding(Fillers.ZERO_LEFT).length(3);
		}

		/**
		 * Nome do banco
		 *
		 * @return
		 */
		public static TagLayout fbancoNome() {
			return field("bancoNome").padding(Fillers.WHITE_SPACE_RIGHT);
		}

		/**
		 * Agência Mantenedora da Conta 53 57 5-Num*G008. Com Dígito Verificador da
		 * Agência 58 58 1-Alfa*G009 Agencia (5) + DV (1)
		 *
		 * @return
		 */
		public static TagLayout fagencia() {
			return field("agencia").length(6).padding(Fillers.ZERO_LEFT).apenasDigitos(true);
		}

		public static TagLayout favorecidoAgencia() {
			return field("favorecidoAgencia").length(6).padding(Fillers.ZERO_LEFT).apenasDigitos(true);
		}

		/**
		 * Dígito Verificador da Ag/Conta72721-Alfa*G012 Dígito Verificador da Agência /
		 * Conta Corrente Código adotado pelo Banco responsável pela conta corrente,
		 * para verificação da autenticidade do par Código da Agência / Número da Conta
		 * Corrente. Para os Bancos que se utilizam de duas posições para o Dígito
		 * Verificador do Número da Conta Corrente, preencher este campo com a 2ª
		 * posição deste dígito.
		 *
		 * @return
		 */
		public static TagLayout fdac() {
			return field("dac").length(1);
		}

		/**
		 * Código de Barras Código adotado pela FEBRABAN para identificar o Título.
		 * Especificações do Código de Barras do Boleto de Pagamento de Cobrança - Ficha
		 * de Compensação (Modelo CADOC 24044-4, Carta-Circular Bacen Nrº 2.926, de
		 * 25.07.2000).
		 *
		 * @return
		 */
		public static TagLayout fcodigoBarras() {
			return field("codigoBarras").padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Alias para facilitar o campo de acesso ao campo do nome do sacado
		 *
		 * @return
		 */
		public static TagLayout fsacadoNome() {
			return field("sacadoNome").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(30);
		}

		public static TagLayout fendereco() {
			return field("endereco").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(40);
		}

		public static TagLayout fcomplemento() {
			return field("complemento").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(15);
		}

		public static TagLayout fnumero() {
			return field("numero").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(5);
		}

		public static TagLayout fbairro() {
			return field("bairro").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(40);
		}

		public static TagLayout fcep() {
			return field("cep").truncate(true).padding(Fillers.ZERO_RIGHT).length(8).apenasDigitos(true);
		}

		public static TagLayout fcidade() {
			return field("cidade").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(15);
		}

		public static TagLayout fuf() {
			return field("uf").truncate(true).length(2).padding(Fillers.WHITE_SPACE_RIGHT);
		}

		/**
		 * C004: Código de Movimento Remessa Código adotado pela FEBRABAN, para
		 * identificar o tipo de movimentação enviado nos //registros do arquivo de
		 * remessa. Cada Banco definirá os campos a serem alterados para o código de
		 * movimento '31'
		 *
		 * Domínio: '01' = Entrada de Títulos '02' = Pedido de Baixa ‘03’ = Protesto
		 * para Fins Falimentares '04' = Concessão de Abatimento '05' = Cancelamento de
		 * Abatimento '06' = Alteração de Vencimento '07' = Concessão de Desconto '08' =
		 * Cancelamento de Desconto '09' = Protestar '10' = Sustar Protesto e Baixar
		 * Título '11' = Sustar Protesto e Manter em Carteira ‘12’ = Alteração de Juros
		 * de Mora ‘13’ = Dispensar Cobrança de Juros de Mora ‘14’ = Alteração de
		 * Valor/Percentual de Multa ‘15’ = Dispensar Cobrança de Multa ‘16’ = Alteração
		 * de Valor/Data de Desconto ‘17’ = Não conceder Desconto ‘18’ = Alteração do
		 * Valor de Abatimento ‘19’ = Prazo Limite de Recebimento - Alterar ‘20’ = Prazo
		 * Limite de Recebimento - Dispensar ‘21’ = Alterar número do título dado pelo
		 * Beneficiário ‘22’ = Alterar número controle do Participante ‘23’ = Alterar
		 * dados do Pagador ‘24’ = Alterar dados do Sacador/Avalista '30' = Recusa da
		 * Alegação do Pagador '31' = Alteração de Outros Dados '33' = Alteração dos
		 * Dados do Rateio de Crédito '34' = Pedido de Cancelamento dos Dados do Rateio
		 * de Crédito '35' = Pedido de Desagendamento do Débito Automático '40' =
		 * Alteração de Carteira ‘41’ = Cancelar protesto ‘42’ = Alteração de Espécie de
		 * Título ‘43’ = Transferência de carteira/modalidade de cobrança ‘44’ =
		 * Alteração de contrato de cobrança ‘45’ = Negativação Sem Protesto ‘46’ =
		 * Solicitação de Baixa de Título Negativado Sem Protesto ‘47’ = Alteração do
		 * Valor Nominal do Título ‘48’ = Alteração do Valor Mínimo/ Percentual ‘49’ =
		 * Alteração do Valor Máximo/Percentual
		 */
		public static TagLayout fmovimentoCodigo() {
			return field("movimentoCodigo").padding(Fillers.ZERO_LEFT).length(2);
		}

		/**
		 * Na maioria das vezes o sacado ou o pagador é uma pessoa fisica, por tanto
		 * esse metodo de alias.
		 *
		 * @return
		 */
		public static TagLayout fsacadoCpf() {
			return field("sacadoCpf").length(14).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * ConvênioCódigo do Convênio no Banco335220-Alfa*G007. Código adotado pelo
		 * Banco para identificar o Contrato entre este e a Empresa Cliente.
		 *
		 * @return
		 */
		public static TagLayout fconvenio() {
			return field("convenio").apenasDigitos(true).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Tipo de Inscrição: '0' = Isento / Não Informado '1' = CPF '2' = CGC / CNPJ
		 * '3' = PIS / PASEP '9' = Outros
		 */
		public static TagLayout ftipoInscricaoCedente() {
			return field("tipoInscricaoCedente").length(1).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Tipo de Inscrição: '0' = Isento / Não Informado '1' = CPF '2' = CGC / CNPJ
		 * '3' = PIS / PASEP '9' = Outros
		 *
		 * @return
		 */
		public static TagLayout ftipoInscricao() {
			return field("tipoInscricao").length(1).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Tipo de Inscrição: '0' = Isento / Não Informado '1' = CPF '2' = CGC / CNPJ
		 * '3' = PIS / PASEP '9' = Outros
		 *
		 * @return
		 */
		public static TagLayout favorecidoTipoInscricao() {
			return field("favorecidoTipoInscricao").length(1).padding(Fillers.ZERO_LEFT);
		}

		public static TagLayout favorecidoInscricao() {
			return field("favorecidoInscricao").padding(Fillers.ZERO_LEFT).length(14).apenasDigitos(true);
		}

		/**
		 * Alias para facilidade, quase sempre o Favorecido é uma Pessoa Juridica.
		 */
		public static TagLayout fcedenteCnpj() {
			return field("cedenteCnpj").padding(Fillers.ZERO_LEFT).length(14).apenasDigitos(true);
		}

		public static TagLayout fcedenteNome() {
			return field("cedenteNome").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(30);
		}

		public static TagLayout ffavorecidoNome() {
			return field("favorecidoNome").truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).length(30);
		}

		public static TagLayout ffavorecidoCPFCNPJ() {
			return field("favorecidoCPFCNPJ").truncate(true).padding(Fillers.ZERO_LEFT).length(20).apenasDigitos(true);
		}

		public static TagLayout ffavorecidoCodigoBanco() {
			return fdata().nome("favorecidoCodigoBanco").filler(Fillers.ZERO_LEFT).value(0).apenasDigitos(true);
		}

		public static TagLayout ffavorecidoAgencia() {
			return fdata().nome("favorecidoAgencia").filler(Fillers.ZERO_LEFT).value(0).apenasDigitos(true);
		}

		public static TagLayout ffavorecidoConta() {
			return fdata().nome("favorecidoConta").filler(Fillers.ZERO_LEFT).value(0).apenasDigitos(true);
		}

		public static TagLayout fquantidadeRegistros() {
			return field("quantidadeRegistros").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		public static TagLayout fquantidadeLotes() {
			return field("quantidadeLotes").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		public static TagLayout fvalorTotalRegistros() {
			return field("valorTotalRegistros").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		public static TagLayout fformaDeTransferencia() {
			return field("formaDeTransferencia").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		/**
		 *
		 * Código das Ocorrências para Retorno/Remessa.
		 *
		 * Código adotado pela FEBRABAN para identificar as ocorrências detectadas no
		 * processamento. Pode-se informar até 5 ocorrências simultaneamente, cada uma
		 * delas codificada com dois dígitos, conforme relação abaixo.
		 *
		 * Domínio: '00' = Crédito ou Débito Efetivado → Este código indica que o
		 * pagamento foi confirmado '01' = Insuficiência de Fundos - Débito Não Efetuado
		 * '02' = Crédito ou Débito Cancelado pelo Pagador/Credor '03' = Débito
		 * Autorizado pela Agência - Efetuado 'AA' = Controle Inválido 'AB' = Tipo de
		 * Operação Inválido 'AC' = Tipo de Serviço Inválido 'AD' = Forma de Lançamento
		 * Inválida 'AE' = Tipo/Número de Inscrição Inválido 'AF' = Código de Convênio
		 * Inválido 'AG' = Agência/Conta Corrente/DV Inválido 'AH' = Nº Seqüencial do
		 * Registro no Lote Inválido 'AI' = Código de Segmento de Detalhe Inválido 'AJ'
		 * = Tipo de Movimento Inválido 'AK' = Código da Câmara de Compensação do Banco
		 * Favorecido/Depositário Inválido 'AL' = Código do Banco Favorecido,
		 * Instituição de Pagamento ou Depositário Inválido 'AM' = Agência Mantenedora
		 * da Conta Corrente do Favorecido Inválida 'AN' = Conta Corrente/DV/Conta de
		 * Pagamento do Favorecido Inválido 'AO' = Nome do Favorecido Não Informado 'AP'
		 * = Data Lançamento Inválido 'AQ' = Tipo/Quantidade da Moeda Inválido 'AR' =
		 * Valor do Lançamento Inválido 'AS' = Aviso ao Favorecido - Identificação
		 * Inválida 'AT' = Tipo/Número de Inscrição do Favorecido Inválido 'AU' =
		 * Logradouro do Favorecido Não Informado 'AV' = Nº do Local do Favorecido Não
		 * Informado 'AW' = Cidade do Favorecido Não Informada 'AX' = CEP/Complemento do
		 * Favorecido Inválido 'AY' = Sigla do Estado do Favorecido Inválida 'AZ' =
		 * Código/Nome do Banco Depositário Inválido 'BA' = Código/Nome da Agência
		 * Depositária Não Informado 'BB' = Seu Número Inválido 'BC' = Nosso Número
		 * Inválido 'BD' = Inclusão Efetuada com Sucesso 'BE' = Alteração Efetuada com
		 * Sucesso 'BF' = Exclusão Efetuada com Sucesso 'BG' = Agência/Conta Impedida
		 * Legalmente ‘BH’= Empresa não pagou salário ‘BI’ = Falecimento do mutuário
		 * ‘BJ’ = Empresa não enviou remessa do mutuário ‘BK’= Empresa não enviou
		 * remessa no vencimento ‘BL’ = Valor da parcela inválida ‘BM’= Identificação do
		 * contrato inválida ‘BN’ = Operação de Consignação Incluída com Sucesso ‘BO’ =
		 * Operação de Consignação Alterada com Sucesso ‘BP’ = Operação de Consignação
		 * Excluída com Sucesso ‘BQ’ = Operação de Consignação Liquidada com Sucesso
		 * ‘BR’ = Reativação Efetuada com Sucesso ‘BS’ = Suspensão Efetuada com Sucesso
		 * 'CA' = Código de Barras - Código do Banco Inválido 'CB' = Código de Barras -
		 * Código da Moeda Inválido 'CC' = Código de Barras - Dígito Verificador Geral
		 * Inválido 'CD' = Código de Barras - Valor do Título Inválido 'CE' = Código de
		 * Barras - Campo Livre Inválido 'CF' = Valor do Documento Inválido 'CG' = Valor
		 * do Abatimento Inválido 'CH' = Valor do Desconto Inválido 'CI' = Valor de Mora
		 * Inválido 'CJ' = Valor da Multa Inválido 'CK' = Valor do IR Inválido 'CL' =
		 * Valor do ISS Inválido 'CM' = Valor do IOF Inválido 'CN' = Valor de Outras
		 * Deduções Inválido 'CO' = Valor de Outros Acréscimos Inválido 'CP' = Valor do
		 * INSS Inválido 'HA' = Lote Não Aceito 'HB' = Inscrição da Empresa Inválida
		 * para o Contrato 'HC' = Convênio com a Empresa Inexistente/Inválido para o
		 * Contrato 'HD' = Agência/Conta Corrente da Empresa Inexistente/Inválido para o
		 * Contrato 'HE' = Tipo de Serviço Inválido para o Contrato 'HF' = Conta
		 * Corrente da Empresa com Saldo Insuficiente 'HG' = Lote de Serviço Fora de
		 * Seqüência 'HH' = Lote de Serviço Inválido `HI` = Arquivo não aceito `HJ` =
		 * Tipo de Registro Inválido `HK` = Código Remessa / Retorno Inválido `HL` =
		 * Versão de layout inválida `HM` = Mutuário não identificado `HN` = Tipo do
		 * beneficio não permite empréstimo `HO` = Beneficio cessado/suspenso `HP` =
		 * Beneficio possui representante legal `HQ` = Beneficio é do tipo PA (Pensão
		 * alimentícia) `HR` = Quantidade de contratos permitida excedida `HS` =
		 * Beneficio não pertence ao Banco informado `HT` = Início do desconto informado
		 * já ultrapassado `HU`= Número da parcela inválida `HV`= Quantidade de parcela
		 * inválida `HW`= Margem consignável excedida para o mutuário dentro do prazo do
		 * contrato `HX` = Empréstimo já cadastrado `HY` = Empréstimo inexistente `HZ` =
		 * Empréstimo já encerrado `H1` = Arquivo sem trailer `H2` = Mutuário sem
		 * crédito na competência `H3` = Não descontado – outros motivos `H4` = Retorno
		 * de Crédito não pago `H5` = Cancelamento de empréstimo retroativo `H6` =
		 * Outros Motivos de Glosa ‘H7’ = Margem consignável excedida para o mutuário
		 * acima do prazo do contrato ‘H8’ = Mutuário desligado do empregador ‘H9’ =
		 * Mutuário afastado por licença ‘IA’ = Primeiro nome do mutuário diferente do
		 * primeiro nome do movimento do censo ou diferente da base de Titular do
		 * Benefício ‘IB’ = Benefício suspenso/cessado pela APS ou Sisobi ‘IC’ =
		 * Benefício suspenso por dependência de cálculo ‘ID’ = Benefício
		 * suspenso/cessado pela inspetoria/auditoria ‘IE’ = Benefício bloqueado para
		 * empréstimo pelo beneficiário ‘IF’ = Benefício bloqueado para empréstimo por
		 * TBM ‘IG’ = Benefício está em fase de concessão de PA ou desdobramento ‘IH’ =
		 * Benefício cessado por óbito ‘II’ = Benefício cessado por fraude ‘IJ’ =
		 * Benefício cessado por concessão de outro benefício ‘IK’ = Benefício cessado:
		 * estatutário transferido para órgão de origem ‘IL’ = Empréstimo suspenso pela
		 * APS ‘IM’ = Empréstimo cancelado pelo banco ‘IN’ = Crédito transformado em PAB
		 * ‘IO’ = Término da consignação foi alterado ‘IP’ = Fim do empréstimo ocorreu
		 * durante período de suspensão ou concessão ‘IQ’ = Empréstimo suspenso pelo
		 * banco ‘IR’ = Não averbação de contrato – quantidade de parcelas/competências
		 * informadas ultrapassou a data limite da extinção de cota do dependente
		 * titular de benefícios 'TA' = Lote Não Aceito - Totais do Lote com Diferença
		 * 'YA' = Título Não Encontrado 'YB' = Identificador Registro Opcional Inválido
		 * 'YC' = Código Padrão Inválido 'YD' = Código de Ocorrência Inválido 'YE' =
		 * Complemento de Ocorrência Inválido 'YF' = Alegação já Informada Observação:
		 * As ocorrências iniciadas com 'ZA' tem caráter informativo para o cliente 'ZA'
		 * = Agência / Conta do Favorecido Substituída ‘ZB’ = Divergência entre o
		 * primeiro e último nome do beneficiário versus primeiro e último nome na
		 * Receita Federal ‘ZC’ = Confirmação de Antecipação de Valor ‘ZD’ = Antecipação
		 * parcial de valor ‘ZE’ = Título bloqueado na base ‘ZF’ = Sistema em
		 * contingência – título valor maior que referência ‘ZG’ = Sistema em
		 * contingência – título vencido ‘ZH’ = Sistema em contingência – título
		 * indexado ‘ZI’ = Beneficiário divergente ‘ZJ’ = Limite de pagamentos parciais
		 * excedido ‘ZK’ = Boleto já liquidado
		 *
		 */
		public static TagLayout focorrencias() {
			// TODO: Melhorar isso, criar um Enum
			return field("ocorrencias").filler(Fillers.WHITE_SPACE_LEFT).length(10);
		}

		/**
		 * C047: Motivo da OcorrênciaCódigo adotado pela FEBRABAN para identificar as
		 * ocorrências (rejeições, tarifas, custas, liquidação e baixas) em registros
		 * detalhe de títulos de cobrança. Poderão ser informados até cinco ocorrências
		 * distintas, incidente sobre o título.Domínio:A -Códigos de rejeições de '01' a
		 * '95'associados aos códigos de movimento '02', '03', '26' e '30' (Descrição
		 * C044)'01' = Código do Banco Inválido'02' = Código do Registro Detalhe
		 * Inválido'03' = Código do Segmento Inválido'04' = Código de Movimento Não
		 * Permitido para Carteira'05' = Código de Movimento Inválido'06' = Tipo/Número
		 * de Inscrição do BeneficiárioInválidos'07' = Agência/Conta/DV Inválido'08' =
		 * Nosso Número Inválido'09' = Nosso Número Duplicado'10' = Carteira
		 * Inválida'11' = Forma de Cadastramento do Título Inválido'12' = Tipo de
		 * Documento Inválido'13' = Identificação da Emissão do Boleto de
		 * PagamentoInválida'14' = Identificação da Distribuição do Boleto de
		 * PagamentoInválida'15' = Características da Cobrança Incompatíveis'16' = Data
		 * de Vencimento Inválida '17' = Data de Vencimento Anterior a Data de
		 * Emissão'18' = Vencimento Fora do Prazo de Operação'19' = Título a Cargo de
		 * Bancos Correspondentes com Vencimento Inferior a XX Dias'20' = Valor do
		 * Título Inválido'21' = Espécie do Título Inválida'22' = Espécie do Título Não
		 * Permitida para a Carteira'23' = Aceite Inválido'24' = Data da Emissão
		 * Inválida'25' = Data da Emissão Posterior a Data de Entrada'26' = Código de
		 * Juros de Mora Inválido'27' = Valor/Taxa de Juros de Mora Inválido'28' =
		 * Código do Desconto Inválido'29' = Valor do Desconto Maior ou Igual ao Valor
		 * do Título'30' = Desconto a Conceder Não Confere'31' = Concessão de Desconto
		 * -Já Existe Desconto Anterior'32' = Valor do IOF Inválido'33' = Valor do
		 * Abatimento Inválido'34' = Valor do Abatimento Maior ou Igual ao Valor do
		 * Título'35' = Valor a Conceder Não Confere'36' = Concessão de Abatimento -Já
		 * ExisteAbatimento Anterior'37' = Código para Protesto Inválido'38' = Prazo
		 * para Protesto Inválido'39' = Pedido de Protesto Não Permitido para o
		 * Título'40' = Título com Ordem de Protesto Emitida'41' = Pedido de
		 * Cancelamento/Sustação para Títulos sem Instrução de Protesto'42' = Código
		 * para Baixa/Devolução Inválido'43' = Prazo para Baixa/Devolução Inválido'44' =
		 * Código da Moeda Inválido'45' = Nome do PagadorNão Informado'46' = Tipo/Número
		 * de Inscrição do PagadorInválidos'47' = Endereço do PagadorNão Informado'48' =
		 * CEP Inválido'49' = CEP Sem Praça de Cobrança (Não Localizado)'50' = CEP
		 * Referente a um Banco Correspondente'51' = CEP incompatível com a Unidade da
		 * Federação'52' = Unidade da Federação Inválida'53' = Tipo/Número de Inscrição
		 * do Sacador/Avalista Inválidos'54' = Sacador/Avalista Não Informado'55' =
		 * Nosso número no Banco Correspondente Não Informado'56' = Código do Banco
		 * Correspondente Não Informado'57' = Código da Multa Inválido'58' = Data da
		 * Multa Inválida'59' = Valor/Percentual da Multa Inválido'60' = Movimento para
		 * Título Não Cadastrado'61' = Alteração da Agência Cobradora/DV Inválida'62' =
		 * Tipo de Impressão Inválido'63' = Entrada para Título já Cadastrado'64' =
		 * Número da Linha Inválido'65' = Código do Banco para Débito Inválido'66' =
		 * Agência/Conta/DV para Débito Inválido'67' = Dados para Débito incompatível
		 * com a Identificação da Emissão do Boleto de Pagamento'68' = Débito Automático
		 * Agendado'69' = Débito Não Agendado -Erro nos Dados da Remessa'70' = Débito
		 * Não Agendado -PagadorNão Consta do Cadastro de Autorizante'71' = Débito Não
		 * Agendado -BeneficiárioNão Autorizado pelo Pagador'72' = Débito Não Agendado
		 * -BeneficiárioNão Participa da Modalidade Débito Automático'73' = Débito Não
		 * Agendado -Código de Moeda Diferente de Real (R$)'74' = Débito Não Agendado
		 * -Data Vencimento Inválida'75' = Débito Não Agendado, Conforme seu Pedido,
		 * Título Não Registrado '76' = Débito Não Agendado, Tipo/Num. Inscrição do
		 * Debitado, Inválido'77' = Transferência para Desconto Não Permitida para a
		 * Carteira do Título'78' = Data Inferior ou Igual ao Vencimento para Débito
		 * Automático'79' = Data Juros de Mora Inválido'80' = Data do Desconto
		 * Inválida'81' = Tentativas de Débito Esgotadas -Baixado'82' = Tentativas de
		 * Débito Esgotadas -Pendente'83' = Limite Excedido'84' = Número Autorização
		 * Inexistente'85' = Título com Pagamento Vinculado '86' = Seu Número
		 * Inválido‘87’= e-mail/SMS enviado‘88’= e-mail Lido‘89’= e-mail/SMS devolvido
		 * -endereço de e-mail ou número do celular incorreto ‘90’= e-mail devolvido
		 * -caixa postal cheia‘91’= e-mail/número do celular do Pagadornão
		 * informado‘92’= Pagadoroptante por Boleto de PagamentoEletrônico -e-mail não
		 * enviado‘93’= Código para emissão de Boleto de Pagamentonão permite envio de
		 * e-mail‘94’= Código da Carteira inválido para envio e-mail.‘95’=Contrato não
		 * permite o envio de e-mail‘96’= Número de contrato inválido‘97’ = Rejeição da
		 * alteração do prazo limite de recebimento (a data deve ser informada no campo
		 * 28.3.p)‘98’ = Rejeição de dispensa de prazo limite de recebimento‘99’ =
		 * Rejeição da alteração do número do título dado pelo Beneficiário‘A1’ =
		 * Rejeição da alteração do número controle do participante‘A2’ = Rejeição da
		 * alteração dos dados do Pagador‘A3’ = Rejeição da alteração dos dados do
		 * Sacador/avalista‘A4’ = PagadorDDA‘A5’ = Registro Rejeitado –Título já
		 * Liquidado‘A6’ = Código do Convenente Inválido ou Encerrado‘A7’ = Título já se
		 * encontra na situação Pretendida‘A8’ = Valor do Abatimento inválido para
		 * cancelamento‘A9’ = Não autoriza pagamento parcial‘B1’ = Autoriza recebimento
		 * parcial‘B2’ = Valor Nominal do Título Conflitante‘B3’ = Tipo de Pagamento
		 * Inválido‘B4’ = Valor Máximo/Percentual Inválido‘B5’ = Valor Mínimo/Percentual
		 * InválidoB -Códigos de tarifas / custas de '01' a '20' associados ao código de
		 * movimento '28' (Descrição C044)'01' = Tarifa de Extrato de Posição'02' =
		 * Tarifa de Manutenção de Título Vencido'03' = Tarifa de Sustação'04' = Tarifa
		 * de Protesto'05' = Tarifa de Outras Instruções'06' = Tarifa de Outras
		 * Ocorrências'07' = Tarifa de Envio de Duplicata ao Pagador'08' = Custas de
		 * Protesto'09' = Custas de Sustação de Protesto'10' = Custas de Cartório
		 * Distribuidor'11' = Custas de Edital'12' = Tarifa Sobre Devolução de Título
		 * Vencido'13' = Tarifa Sobre Registro Cobrada na Baixa/Liquidação'14' = Tarifa
		 * Sobre Reapresentação Automática'15' = Tarifa Sobre Rateio de Crédito'16' =
		 * Tarifa Sobre Informações Via Fax'17' = Tarifa Sobre Prorrogação de Vencimento
		 * '18' = Tarifa Sobre Alteração de Abatimento/Desconto'19' = Tarifa Sobre
		 * Arquivo mensal (Em Ser)'20' = Tarifa Sobre Emissão de Boleto de
		 * PagamentoPré-Emitido pelo BancoC -Códigos de liquidação / baixa de '01' a
		 * '15' associados aos códigos de movimento '06', '09' e '17' (Descrição
		 * C044)Liquidação:'01' = Por Saldo'02' = Por Conta'03' = Liquidação no Guichê
		 * de Caixa em Dinheiro'04' = Compensação Eletrônica'05' = Compensação
		 * Convencional'06' = Por Meio Eletrônico'07' = Após Feriado Local'08' = Em
		 * Cartório‘30’ = Liquidação no Guichê de Caixa em Cheque‘31’ = Liquidação em
		 * banco correspondente‘32’ = Liquidação Terminal de Auto-Atendimento‘33’ =
		 * Liquidação na Internet (Home banking)‘34’ = Liquidado Office Banking‘35’ =
		 * Liquidado Correspondente em Dinheiro‘36’ = Liquidado Correspondente em
		 * Cheque‘37’ = Liquidado por meio de Central de Atendimento
		 * (Telefone)Baixa:'09' = Comandada Banco'10' = Comandada Cliente Arquivo'11' =
		 * Comandada Cliente On-line'12' = Decurso Prazo -Cliente'13' = Decurso Prazo
		 * -Banco'14' = Protestado'15' = Título Excluído
		 *
		 * @return
		 */
		public static TagLayout frejeicoes() {
			return field("rejeicoes").length(10);
		}

		/**
		 * Lote de Serviço Número seqüencial para identificar univocamente um lote de
		 * serviço. Criado e controlado pelo responsável pela geração magnética dos
		 * dados contidos no arquivo. Preencher com '0001' para o primeiro lote do
		 * arquivo. Para os demais: número do lote anterior acrescido de 1. O número não
		 * poderá ser repetido dentro do arquivo. Se registro for Header do Arquivo
		 * preencher com '0000' Se registro for Trailer do Arquivo preencher com '9999'
		 *
		 * @return
		 */
		public static TagLayout flote() {
			return field("lote").length(4).type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Código da Carteira: Código adotado pela FEBRABAN, para identificar a
		 * característica dos títulos dentro das modalidadesde cobrança existentes no
		 * banco Domínio: '1' = Cobrança Simples '2' = Cobrança Vinculada '3' = Cobrança
		 * Caucionada '4' = Cobrança Descontada ‘5’ = Cobrança Vendor
		 *
		 * @return
		 */
		public static TagLayout fcodigoCarteira() {
			return field("codigoCarteira").length(1).type(Number.class);
		}

		public static TagLayout fcarteira() {
			return field("carteira").length(3).type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fvalor() {
			return field("valor").length(15).type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fvalorPagamento() {
			return field("valorPagamento").length(15).type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fvalorOcorrencia() {
			return field("valorOcorrencia").length(15).type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fvalorDesconto() {
			return fvalor().nome("valorDesconto").value(0);
		}

		public static TagLayout fvalorAbatimento() {
			return fvalor().nome("valorAbatimento").value(0);
		}

		public static TagLayout fvalorIOF() {
			return fvalor().nome("valorIOF").value(0);
		}

		public static TagLayout fvalorLiquido() {
			return fvalor().nome("valorLiquido").value(0);
		}

		public static TagLayout fvalorTarifaCustas() {
			return fvalor().nome("valorTarifaCustas").value(0);
		}

		public static TagLayout fvalorOutrasDespesas() {
			return fvalor().nome("valorOutrasDespesas").value(0);
		}

		public static TagLayout fvalorOutrasReceitas() {
			return fvalor().nome("valorOutrasReceitas").value(0);
		}

		public static TagLayout fvalorAcrescimo() {
			return fvalor().nome("valorAcresciomo").value(0);
		}

		/**
		 * Código do Juros de Mora C018 Código adotado pela FEBRABAN para identificação
		 * do tipo de pagamento de juros de mora. Domínio: '1' = Valor por Dia '2' =
		 * Taxa Mensal '3' = Isento
		 *
		 * @return
		 */
		public static TagLayout fcodigoAcrescimo() {
			return field("codigoAcrescimo").length(1).value(3);
		}

		/**
		 * Código adotado pela FEBRABAN para identificação do tipo de desconto que
		 * deverá ser concedido. Ao se optar por valor, os três descontos devem ser
		 * expresso em valores. Idem ao se optar por percentual, os três descontos devem
		 * ser expressos em percentual. Domínio: '1' = Valor Fixo Até a Data Informada
		 * '2' = Percentual Até a Data Informada '3' = Valor por Antecipação Dia Corrido
		 * '4' = Valor por Antecipação Dia Útil '5' = Percentual Sobre o Valor Nominal
		 * Dia Corrido '6' = Percentual Sobre o Valor Nominal Dia Útil '7' =
		 * Cancelamento de Desconto Para os códigos '1' e '2' será obrigatório a
		 * informação da Data. Para o código '7', somente será válido para o código de
		 * movimento '31' - Alteração de Dados
		 *
		 * @return
		 */
		public static TagLayout fcodigoDesconto() {
			return field("codigoDesconto").length(1).value(0);
		}

		/**
		 * Código para Protesto C026 Código adotado pela FEBRABAN para identificar o
		 * tipo de prazo a ser considerado para o protesto. Domínio: '1' = Protestar
		 * Dias Corridos '2' = Protestar Dias Úteis '3' = Não Protestar ‘4’ = Protestar
		 * Fim Falimentar - Dias Úteis ‘5’ = Protestar Fim Falimentar - Dias Corridos
		 * ‘8’ = Negativação sem Protesto '9' = Cancelamento Protesto Automático
		 * (somente válido p/ CódigoMovimento Remessa = '31' - Descrição C004)
		 *
		 * @return
		 */
		public static TagLayout fcodigoProtesto() {
			return field("codigoProtesto").length(1).value(3);
		}

		/**
		 * Código para Baixa / Devolução C028 Código adotado pela FEBRABAN para
		 * identificar qual o procedimento a ser adotado com o Título. Domínio: '1' =
		 * Baixar / Devolver '2' = Não Baixar / Não Devolver '3' = Cancelar Prazo para
		 * Baixa / Devolução (somente válido p/CódigoMovimento Remessa = '31' -
		 * Descrição C004)
		 *
		 * @return
		 */
		public static TagLayout fcodigoBaixa() {
			return field("codigoBaixa").length(1).value(2);
		}

		public static TagLayout fliteralRemessa() {
			return field("literalRemessa").length(7).truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).value("REMESSA");
		}

		public static TagLayout fliteralRetorno() {
			return field("literalRetorno").length(7).truncate(true).padding(Fillers.WHITE_SPACE_RIGHT).value("RETORNO");
		}

		public static TagLayout fliteralServico() {
			return field("literalServico").length(15).truncate(true).padding(Fillers.WHITE_SPACE_RIGHT)
					.value("COBRANCA");
		}

		public static TagLayout fnumeroDocumento() {
			return field("numeroDocumento").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Número Remessa/Retorno: G079
		 *
		 * @return
		 */
		public static TagLayout fnumeroRemessa() {
			return field("numeroRemessa").type(Number.class).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Tipo de Documento C008 Código adotado pela FEBRABAN para identificar a
		 * existência material do documento no processo. Domínio: '1' = Tradicional '2'
		 * = Escritural
		 *
		 * @return
		 */
		public static TagLayout ftipoDocumento() {
			return field("tipoDocumento").length(1).value(1);
		}

		/**
		 * Identificação da Emissão do Boleto de Pagamento C009 Código adotado pela
		 * FEBRABAN para identificar o responsável e a forma de emissão do Boleto de
		 * Pagamento. Domínio: '1' = Banco Emite '2' = Cliente Emite '3' = Banco
		 * Pré-emite e Cliente Complementa '4' = Banco Reemite '5' = Banco Não Reemite
		 * '7' = Banco Emitente - Aberta '8' = Banco Emitente - Auto-envelopável Os
		 * códigos '4' e '5' só serão aceitos para código de movimento para remessa '31'
		 *
		 * @return
		 */
		public static TagLayout ftipoEmissaoBoleto() {
			return field("tipoEmissaoBoleto").length(1).value(2);
		}

		/**
		 * Identificação da Distribuição C010 Código adotado pela FEBRABAN para
		 * identificar o responsável pela distribuição do Boleto de Pagamento. Domínio:
		 * '1' = Banco Distribui '2' = Cliente Distribui ‘3’ = Banco envia e-mail ‘4’ =
		 * Banco envia SMS
		 *
		 * @return
		 */
		public static TagLayout ftipoDistribuicaoBoleto() {
			return field("tipoDistribuicaoBoleto").length(1).value(2);
		}

		/**
		 * Código adotado pela FEBRABAN para identificar o tipo de título de cobrança.
		 * Domínio: '01' = CH Cheque '02' = DM Duplicata Mercantil '03' = DMI Duplicata
		 * Mercantil p/ Indicação '04' = DS Duplicata de Serviço '05' = DSI Duplicata de
		 * Serviço p/ Indicação '06' = DR Duplicata Rural '07' = LC Letra de Câmbio '08'
		 * = NCC Nota de Crédito Comercial '09' = NCE Nota de Crédito a Exportação '10'
		 * = NCI Nota de Crédito Industrial '11' = NCR Nota de Crédito Rural '12' = NP
		 * Nota Promissória '13' = NPR Nota Promissória Rural '14' = TM Triplicata
		 * Mercantil '15' = TS Triplicata de Serviço '16' = NS Nota de Seguro '17' = RC
		 * Recibo '18' = FAT Fatura '19' = ND Nota de Débito '20' = AP Apólice de Seguro
		 * '21' = ME Mensalidade Escolar '22' = PC Parcela de Consórcio '23' = NF Nota
		 * Fiscal '24' = DD Documento de Dívida ‘25’ = Cédula de Produto Rural ‘26’ =
		 * Warrant ‘27’ = Dívida Ativa de Estado ‘28’ = Dívida Ativa de Município ‘29’ =
		 * Dívida Ativa da União ‘30’ = Encargos condominiais ‘31’ = CC Cartão de
		 * Crédito ‘32’ = BDP – Boleto de Proposta '99' = Outros
		 *
		 * @return
		 */
		public static TagLayout fespecieTitulo() {
			return field("especieTitulo").length(2).value("02");
		}

		/**
		 * Identificação de Título Aceito / Não Aceito C016 Código adotado pela FEBRABAN
		 * para identificar se o título de cobrança foi aceito (reconhecimento da dívida
		 * pelo Pagador). Domínio: 'A' = Aceite 'N' = Não Aceite
		 *
		 * @return
		 */
		public static TagLayout faceite() {
			return field("aceite").length(1).value("N");
		}

		public static TagLayout fnossoNumero() {
			return field("nossoNumero").type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fsequencialRegistro() {
			return field("sequencialRegistro").length(5).type(Number.class).filler(Fillers.ZERO_LEFT);
		}

		/**
		 * Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviada
		 * no arquivo. Domínio: '0' = Indica INCLUSÃO ‘1’ = Indica CONSULTA ‘2’ = Indica
		 * SUSPENSÃO '3' = Indica ESTORNO (somente para retorno) ‘4’ = Indica REATIVAÇÃO
		 * '5' = Indica ALTERAÇÃO ‘7` = Indica LIQUIDAÇAO '9' = Indica EXCLUSÃO
		 */
		public static TagLayout fmovimentoTipo() {
			return field("movimentoTipo").length(1);
		}

		/**
		 * Código adotado pela FEBRABAN para identificar a moeda referenciada no Título.
		 * Domínio: '01' = Reservado para Uso Futuro '02' = Dólar Americano Comercial
		 * (Venda) '03' = Dólar Americano Turismo (Venda) '04' = ITRD '05' = IDTR '06' =
		 * UFIR Diária '07' = UFIR Mensal '08' = FAJ-TR '09' = Real '10' = TR '11' =
		 * IGPM '12' = CDI '13' = Percentual do CDI ‘14’ = Euro
		 */
		public static TagLayout fcodigoMoeda() {
			return field("codigoMoeda").value("09").length(2);
		}

		/**
		 * Domínio: '01' = Crédito em Conta Corrente/Salário '02' = Cheque Pagamento /
		 * Administrativo '03' = DOC/TED (1) (2) '04' = Cartão Salário (somente para
		 * Tipo de Serviço = '30') '05' = Crédito em Conta Poupança '10' = OP à
		 * Disposição ‘11’ = Pagamento de Contas e Tributos com Código de Barras ‘16’ =
		 * Tributo - DARF Normal ‘17’ = Tributo - GPS (Guia da Previdência Social) ‘18’
		 * = Tributo - DARF Simples ‘19’ = Tributo - IPTU – Prefeituras '20' = Pagamento
		 * com Autenticação ‘21’ = Tributo – DARJ ‘22’ = Tributo - GARE-SP ICMS ‘23’ =
		 * Tributo - GARE-SP DR ‘24’ = Tributo - GARE-SP ITCMD ‘25’ = Tributo - IPVA
		 * ‘26’ = Tributo - Licenciamento ‘27’ = Tributo – DPVAT '30' = Liquidação de
		 * Títulos do Próprio Banco '31' = Pagamento de Títulos de Outros Bancos '40' =
		 * Extrato de Conta Corrente '41' = TED – Outra Titularidade (1) '43' = TED –
		 * Mesma Titularidade (1) ‘44’ = TED para Transferência de Conta Investimento
		 * '50' = Débito em Conta Corrente '70' = Extrato para Gestão de Caixa ‘71’ =
		 * Depósito Judicial em Conta Corrente ‘72’ = Depósito Judicial em Poupança ‘73’
		 * = Extrato de Conta Investimento ‘80’= Pagamento de tributos municipais ISS –
		 * LCP 157 – próprio Banco ‘81’= Pagamento de Tributos Municipais ISS – LCP 157
		 * – outros Bancos
		 */
		public static TagLayout fforma() {
			return field("forma").length(2).padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Esse field ira gerar zeros conforme o tamanho do Length.
		 *
		 * @return
		 */
		public static TagLayout fzero() {
			return field("").filler(Fillers.ZERO_LEFT);
		}

		public static TagLayout fsegmento() {
			return field("segmento").length(1);
		}

		/**
		 * 08.4.J52 Código Reg. Opcional Identificação Registro Opcional 18 19 2 - Num
		 * “52” G067
		 *
		 * @return
		 */
		public static TagLayout fidOpcional() {
			return field("idOpcional").length(2);
		}

		/**
		 * Esse field ira gerar espaços em branco.
		 *
		 * @return
		 */
		public static TagLayout fbranco() {
			return field("").filler(Fillers.WHITE_SPACE_LEFT);
		}

		/**
		 * G010 Número da Conta Corrente Conta com DV
		 *
		 * Número adotado pelo Banco, para identificar univocamente a conta corrente
		 * utilizada pelo Cliente. G011 Dígito Verificador da Conta G011 Código adotado
		 * pelo responsável pela conta corrente, para verificação da autenticidade do
		 * Número da Conta Corrente. Para os Bancos que se utilizam de duas posições
		 * para o Dígito Verificador do Número da Conta Corrente, preencher este campo
		 * com a 1a posição deste dígito. Exemplo : Número C/C = 45981-36 Neste caso →
		 * Dígito Verificador da Conta = 3
		 *
		 * @return
		 */
		public static TagLayout fconta() {
			return field("conta").length(13).padding(Fillers.ZERO_LEFT).apenasDigitos(true);
		}

		public static TagLayout favorecidoConta() {
			return field("favorecidoConta").length(13).padding(Fillers.ZERO_LEFT).apenasDigitos(true);
		}

		/**
		 * Código adotado pela FEBRABAN para identificar o tipo de registro. Domínio:
		 * '0' = Header de Arquivo '1' = Header de Lote '2' = Registros Iniciais do Lote
		 * '3' = Detalhe '4' = Registros Finais do Lote '5' = Trailer de Lote '9' =
		 * Trailer de Arquivo
		 *
		 * @return
		 */
		public static TagLayout fcodigoRegistro() {
			return field("codigoRegistro").length(1).id(true);
		}

		/**
		 * Domínio: 'C' = Lançamento a Crédito 'D' = Lançamento a Débito 'E' = Extrato
		 * para Conciliação 'G' = Extrato para Gestão de Caixa 'I' = Informações de
		 * Títulos Capturados do Próprio Banco 'R' = Arquivo Remessa 'T' = Arquivo
		 * Retorno
		 *
		 * @return
		 */
		public static TagLayout foperacao() {
			return field("operacao").length(1);
		}

		/**
		 * Domínio: '01' = Cobrança '03' = Boleto de Pagamento Eletrônico '04' =
		 * Conciliação Bancária '05' = Débitos '06' = Custódia de Cheques '07' = Gestão
		 * de Caixa '08' = Consulta/Informação Margem '09' = Averbação da
		 * Consignação/Retenção '10' = Pagamento Dividendos ‘11’ = Manutenção da
		 * Consignação ‘12’ = Consignação de Parcelas ‘13’ = Glosa da Consignação (INSS)
		 * ‘14’ = Consulta de Tributos a pagar '20' = Pagamento Fornecedor ‘22’ =
		 * Pagamento de Contas, Tributos e Impostos ‘23’ = Interoperabilidade entre
		 * Contas de Instituições de Pagamentos ‘25’ = Compror ‘26’ = Compror Rotativo
		 * '29' = Alegação do Pagador '30' = Pagamento Salários ‘32’ = Pagamento de
		 * honorários ‘33’ = Pagamento de bolsa auxílio ‘34’ = Pagamento de prebenda
		 * (remuneração a padres e sacerdotes) '40' = Vendor '41' = Vendor a Termo '50'
		 * = Pagamento Sinistros Segurados '60' = Pagamento Despesas Viajante em
		 * Trânsito '70' = Pagamento Autorizado '75' = Pagamento Credenciados ‘77’ =
		 * Pagamento de Remuneração '80' = Pagamento Representantes / Vendedores
		 * Autorizados '90' = Pagamento Benefícios '98' = Pagamentos Diversos
		 *
		 * @return
		 */
		public static TagLayout fservico() {
			return field("servico").length(2).padding(Fillers.ZERO_LEFT);
		}

		public static TagLayout fcodigoRetorno() {
			return field("codigoRetorno").length(1);
		}

		public static TagLayout fsequencialArquivo() {
			return field("sequencialArquivo").padding(Fillers.ZERO_LEFT);
		}

		/**
		 * Código Remessa / Retorno 1431431-NumG015 Código adotado pela FEBRABAN para
		 * qualificar o envio ou devolução de arquivo entre a Empresa Cliente e o Banco
		 * prestador dos Serviços. Domínio: '1' = Remessa (Cliente -> Banco) '2' =
		 * Retorno (Banco -> Cliente)
		 *
		 * @return
		 */
		public static TagLayout fcodigoArquivo() {
			return field("codigoArquivo").length(1);
		}

		public static TagLayout fcodigoOcorrencia() {
			return field("codigoOcorrencia").length(1);
		}

		/**
		 * Campo de data generico
		 *
		 * @return
		 */
		public static TagLayout fdata() {
			return field("data").filler(Fillers.ZERO_LEFT).length(8).format(new SimpleDateFormat("ddMMyyyy"));
		}

		public static TagLayout fdataAcrescimo() {
			return fdata().nome("dataAcrescimo").filler(Fillers.ZERO_LEFT).value(0);
		}

		public static TagLayout fdataDesconto() {
			return fdata().nome("dataDesconto").filler(Fillers.ZERO_LEFT).value(0);
		}

		/**
		 * Data Pagamento Data do Pagamento 145 152 8 - Num P009 Data do Pagamento Data
		 * do pagamento do compromisso. Utilizar o formato DDMMAAAA, onde: DD = dia MM =
		 * mês AAAA = ano
		 *
		 * @return
		 */
		public static TagLayout fdataPagamento() {
			return fdata().nome("dataPgamento").filler(Fillers.ZERO_LEFT).value(0);
		}

		public static TagLayout fdataGeracao() {
			return field("dataGeracao").type(Date.class).format(new SimpleDateFormat("ddMMyyyy")).length(8)
					.filler(Fillers.ZERO_LEFT).value(0);
		}

		public static TagLayout fdataOcorrencia() {
			return field("dataOcorrencia").length(8).format(new SimpleDateFormat("ddMMyyyy"));
		}

		public static TagLayout fdataCredito() {
			return field("dataCredito").length(8).format(new SimpleDateFormat("ddMMyyyy"));
		}

		public static TagLayout fdataVencimento() {
			return field("dataVencimento").length(8).format(new SimpleDateFormat("ddMMyyyy"));
		}

		public static TagLayout cabecalho(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout cabecalhoLote(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout titulo(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoA(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoB(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoJ(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoJ52(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoU(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoT(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoP(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoQ(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalheSegmentoR(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout detalhe(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout rodape(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout rodapeLote(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout group(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout descricao(String texto) {
			return tagin().withValue(texto);
		}

		public static TagLayout versao(String texto) {
			return tagin().withValue(texto);
		}

		public static TagLayout nome(String texto) {
			return tagin().withValue(texto);
		}

		public static TagLayout layout(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		public static TagLayout banco(IBanco banco) {
			return tagin().withValue(banco);
		}

		public static TagLayout banco(String banco) {
			return tagin().withValue(banco);
		}

		public static TagLayout servico(CNABServico servico) {
			return tagin().withValue(servico);
		}

		public static TagLayout cnab(CNAB cnab) {
			return tagin().withValue(cnab);
		}

		public static TagLayout flatfile(TagLayout... filhos) {
			return tagin().with(filhos);
		}

		private static TagLayout tagin() {
			String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();
			return tag(nomeMetodoAnterior);
		}

		public static TagLayout tag(String nome) {
			return new TagLayout(nome);
		}
	}

	String nome;
	Object value;
	List<TagLayout> filhos;
	Map<String, Object> atributos;

	public TagLayout nome(String texto) {
		this.nome = texto;
		return this;
	}

	TagLayout get(TagLayout tag) {
		return get(tag.nome);
	}

	TagLayout get(String strfilho) {
		TagLayout fi = null;
		for (TagLayout filho : filhos) {
			if (filho.nome.equalsIgnoreCase(strfilho)) {
				fi = filho;
				break;
			}
		}
		return fi;
	}

	String getAtr(String stratt) {
		String ret = null;
		Object att = atributos.get(stratt);
		if (att != null) {
			ret = att.toString();
		}
		return ret;
	}

	Integer getInt(String stratt) {
		Integer ret = null;
		Object obj = atributos.get(stratt);
		if (obj == null) {
			return null;
		}
		if (obj instanceof Number) {
			if (obj instanceof Integer) {
				ret = (Integer) obj;
			} else {
				ret = ((Number) obj).intValue();
			}
		} else {
			ret = Integer.parseInt(obj.toString());
		}
		return ret;
	}

	Object getObj(String stratt) {
		return atributos.get(stratt);
	}

	Object getValue(String nome) {
		Object ret = null;
		ret = this.atributos.get(nome);
		if (ret == null) {
			TagLayout fi = this.get(nome);
			if (fi != null) {
				ret = fi.getValue();
			}
		}
		return ret;
	}

	public TagLayout(String nome) {
		this.nome = nome;
		this.filhos = new ArrayList<>();
		this.atributos = new TreeMap<>();
	}

	public TagLayout setAttr(String nome, Object valor) {
		return this.atr(nome, valor);
	}

	public TagLayout atr(String nome, Object valor) {
		atributos.put(nome, valor);
		return this;
	}

	public TagLayout type(Class tipo) {
		return setAttr(tipo);
	}

	public TagLayout filler(IFiller padding) {
		return setAttr(padding);
	}

	public TagLayout padding(IFiller padding) {
		return setAttr(padding);
	}

	public TagLayout format(Format padding) {
		return setAttr(padding);
	}

	/**
	 * Set value and lenght from string val
	 */
	public TagLayout valLen(String val) {
		this.value(val);
		this.length(val.length());
		return this;
	}

	public TagLayout id(boolean bol) {
		return setAttr(bol);
	}

	public TagLayout truncate(boolean bol) {
		return setAttr(bol);
	}

	public TagLayout apenasDigitos(boolean bol) {
		return setAttr(bol);
	}

	public TagLayout length(int len) {
		return setAttr(len);
	}

	public TagLayout position(int len) {
		return setAttr(len);
	}

	// TODO: Unificar com o field Value
	public TagLayout value(Object len) {
		return setAttr(len);
	}

	public TagLayout withValue(Object texto) {
		this.value = texto;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public String getNome() {
		return nome;
	}

	public TagLayout with(TagLayout... filhos) {
		if (filhos != null) {
			for (TagLayout filho : filhos) {
				this.filhos.add(filho);
			}
		}
		return this;
	}

	public TagLayout insertAfter(TagLayout holder, TagLayout... filhos) {
		if (holder != null && filhos != null) {
			int indexOf = this.filhos.indexOf(holder);
			if (indexOf >= 0) {
				this.filhos.addAll(indexOf + 1, Arrays.asList(filhos));
			}
		}
		return this;
	}

	public TagLayout insertBefore(TagLayout holder, TagLayout... filhos) {
		if (holder != null && filhos != null) {
			int indexOf = this.filhos.indexOf(holder);
			if (indexOf > 0) {
				this.filhos.addAll(indexOf, Arrays.asList(filhos));
			}
		}
		return this;
	}

	protected TagLayout setAttr(Object valor) {
		// TODO: Melhorar isso;
		String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();
		/* Propriedade a ser setada é o nome do metodo que chamou */
		this.atr(nomeMetodoAnterior, valor);
		return this;
	}

	public TagLayout clone() {
		return SerializationUtils.clone(this);
	}

	// TODO: Melhorar isso, procurar alguma lib que faça o clone e já transforme o
	// objeto em imutavel
	public TagLayout cloneReadonly() {
		TagLayout clone = this.clone();
		colecoesImutaveis(clone);
		return clone;
	}

	private void colecoesImutaveis(TagLayout clone) {
		clone.atributos = Collections.unmodifiableMap(clone.atributos);
		clone.filhos = Collections.unmodifiableList(clone.filhos);
		for (TagLayout tf : clone.filhos) {
			colecoesImutaveis(tf);
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + Objects.hashCode(this.nome);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TagLayout other = (TagLayout) obj;
		if (!Objects.equals(this.nome, other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Tag{" + nome + "=" + value + " filhos=" + filhos + '}';
	}
}
