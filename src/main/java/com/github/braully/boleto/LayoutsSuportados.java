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

import static com.github.braully.boleto.CNAB.CNAB_240;
import static com.github.braully.boleto.CNAB.CNAB_400;
import static com.github.braully.boleto.TagLayout.TagCreator.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jrimum.texgit.Fillers;

/**
 *
 * @author Braully Rocha da Silva
 */
//TODO: Revisar layout de terceiros que vieram via pull request
public class LayoutsSuportados {

	private static final TagLayout _LAYOUT_FEBRABAN_CNAB240 = flatfile(
			layout(nome("Layout Padrão Febraban CNAB240"), cnab(CNAB_240), banco("000"),
					tag("url").value("https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240"), versao("05"),
					servico(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA)),
			cabecalho(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					fbancoCodigo(), flote().value("0000"), fcodigoRegistro().value("0"),
					// Uso Exclusivo FEBRABAN / CNAB9179-AlfaBrancosG004
					fbranco().length(9), ftipoInscricao().value("2"),
					fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
					// ConvênioCódigo do Convênio no Banco335220-Alfa*G007
					// Código adotado pelo Banco para identificar o Contrato entre este e a Empresa
					// Cliente.
					fconvenio().length(20),
					// Agência Mantenedora da Conta 53 57 5-Num*G008
					// Dígito Verificador da Agência 58 58 1-Alfa*G009
					fagencia(),
					// Número da Conta Corrente5970 12-Num*G010
					// Dígito Verificador da Conta7171 1-Alfa*G011
					fconta(), // Conta com DV
					// Dígito Verificador da Ag/Conta72721-Alfa*G012
					// Dígito Verificador da Agência / Conta CorrenteCódigo
					// adotado pelo Banco responsável pela conta corrente,
					// para verificação da autenticidade do par Código da Agência / Número da Conta
					// Corrente.
					// Para os Bancos que se utilizam de duas posições para o Dígito Verificador
					// do Número da Conta Corrente, preencher este campo com a 2ª posição deste
					// dígito.
					fdac(), fcedenteNome().length(30), fbancoNome().length(30),
					// Uso Exclusivo FEBRABAN / CNAB
					fbranco().length(10),
					// Código Remessa / Retorno1431431-NumG015
					// Código adotado pela FEBRABAN para qualificar o envio ou devolução de arquivo
					// entre a Empresa Cliente e o Banco prestador dos Serviços.
					// Domínio:'1' = Remessa (Cliente -> Banco) '2' = Retorno (Banco -> Cliente)
					fcodigoArquivo().value(1),
					// Data de Geração do Arquivo1441518-Num
					fdataGeracao(),
					// Hora de Geração do Arquivo1521576
					field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
					// Número Seqüencial do Arquivo1581636-Num*G018
					fsequencialArquivo().length(6), field("versaoLayoutArquivo").valLen("103"),
					// Densidade de gravação (BPI), do arquivo encaminhado. Domínio:1600 BPI ou 6250
					// BPI
					field("densidadeArquivo").value(0).length(5).filler(Fillers.ZERO_LEFT),
					// Para Uso Reservado do Banco17219120-AlfaG021
					fbranco().length(20),
					// Para Uso Reservado da Empresa19221120-AlfaG022
					fbranco().length(20),
					// Uso Exclusivo FEBRABAN / CNAB21224029-AlfaBrancosG004
					fbranco().length(29)),
			// Registro Header de Lote: 3.2.2 -Títulos em Cobrança
			cabecalhoLote(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					fbancoCodigo(),
					// Valor default para o primeiro lote, demais devem ser alterados
					flote().value(1), fcodigoRegistro().value("1"),
					// Código adotado pela FEBRABAN para identificar a transação que será realizada
					// com os registros detalhe do lote.
					/*
					 * Domínio: 'C' = Lançamento a Crédito 'D' = Lançamento a Débito 'E' = Extrato
					 * para Conciliação 'G' = Extrato para Gestão de Caixa 'I' = Informações de
					 * Títulos Capturados do Próprio Banco 'R' = Arquivo Remessa 'T' = Arquivo
					 * Retorno
					 */
					foperacao(),
					// Código adotado pela FEBRABAN para indicar o tipo de serviço / produto
					// (processo) contido no arquivo / lote.
					/*
					 * TODO: Fazer um enum Domínio: '01' = Cobrança '03' = Boleto de Pagamento
					 * Eletrônico '04' = Conciliação Bancária '05' = Débitos '06' = Custódia de
					 * Cheques '07' = Gestão de Caixa '08' = Consulta/Informação Margem '09' =
					 * Averbação da Consignação/Retenção '10' = Pagamento Dividendos ‘11’ =
					 * Manutenção da Consignação ‘12’ = Consignação de Parcelas ‘13’ = Glosa da
					 * Consignação (INSS) ‘14’ = Consulta de Tributos a pagar '20' = Pagamento
					 * Fornecedor ‘22’ = Pagamento de Contas, Tributos e Impostos ‘23’ =
					 * Interoperabilidade entre Contas de Instituições de Pagamentos ‘25’ = Compror
					 * ‘26’ = Compror Rotativo '29' = Alegação do Pagador '30' = Pagamento Salários
					 * ‘32’ = Pagamento de honorários ‘33’ = Pagamento de bolsa auxílio ‘34’ =
					 * Pagamento de prebenda (remuneração a padres e sacerdotes) '40' = Vendor '41'
					 * = Vendor a Termo '50' = Pagamento Sinistros Segurados '60' = Pagamento
					 * Despesas Viajante em Trânsito '70' = Pagamento Autorizado '75' = Pagamento
					 * Credenciados ‘77’ = Pagamento de Remuneração '80' = Pagamento Representantes
					 * / Vendedores Autorizados '90' = Pagamento Benefícios '98' = Pagamentos
					 * Diversos
					 */
					fservico(),
					// Uso Exclusivo FEBRABAN/CNAB
					field("formaDeLancamento").value("  ").length(2),
					// Nº da Versão do Layout do Lote
					field("versaoLayoutLote").value("060").length(3),
					// Uso Exclusivo da FEBRABAN/CNAB 17 17 1 - Alfa Brancos G004
					fbranco().length(1), ftipoInscricao().value("2"), fcedenteCnpj().length(14),
					// ConvênioCódigo do Convênio no Banco335220-Alfa*G007
					// Código adotado pelo Banco para identificar o Contrato entre este e a Empresa
					// Cliente.
					fconvenio().length(20),
					// Agência Mantenedora da Conta 53 57 5-Num*G008
					// Dígito Verificador da Agência 58 58 1-Alfa*G009
					fagencia(),
					// Número da Conta Corrente5970 12-Num*G010
					// Dígito Verificador da Conta7171 1-Alfa*G011
					fconta(), // Conta com DV
					// Dígito Verificador da Ag/Conta72721-Alfa*G012
					// Dígito Verificador da Agência / Conta CorrenteCódigo
					// adotado pelo Banco responsável pela conta corrente,
					// para verificação da autenticidade do par Código da Agência / Número da Conta
					// Corrente.
					// Para os Bancos que se utilizam de duas posições para o Dígito Verificador
					// do Número da Conta Corrente, preencher este campo com a 2ª posição deste
					// dígito.
					fdac(), // Conta com DV
					fcedenteNome().length(30),
					// Texto referente a mensagens que serão impressas nos documentos e/ou avisos a
					// serem emitidos.
					// Informação 1: Genérica. Quando informada constará em todos os avisos e/ou
					// documentos originados dos detalhes desse lote. Informada no Header do Lote.
					field("mensagem1").length(40).filler(Fillers.WHITE_SPACE_LEFT),
					field("mensagem2").length(40).filler(Fillers.WHITE_SPACE_LEFT), fnumeroRemessa().length(8).value(0),
					fdataGeracao().length(8), field("dataCredito").length(8).value("").filler(Fillers.WHITE_SPACE_LEFT),
					// Uso Exclusivo da FEBRABAN/CNAB
					fbranco().length(33)
			// Código das Ocorrências p/ Retorno
			// focorrencias()
			),
			// Registro Detalhe - Segmento J (Obrigatório - Remessa / Retorno)
			detalheSegmentoJ(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					fbancoCodigo(),
					// Número seqüencial para identificar univocamente um lote de serviço. Criado e
					// controlado pelo responsável pela geração magnética dos dados contidos no
					// arquivo.
					// Preencher com '0001' para o primeiro lote do arquivo. Para os demais: número
					// do lote
					// anterior acrescido de 1. O número não poderá ser repetido dentro do arquivo.
					// Se registro for Header do Arquivo preencher com '0000'
					// Se registro for Trailer do Arquivo preencher com '9999'
					flote().value(1), fcodigoRegistro().value("3"), fsequencialRegistro(),
					// Código adotado pela FEBRABAN para identificar o segmento do registro.
					fsegmento().id(true).value("J"),
					// Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviada
					// no arquivo.
					fmovimentoTipo().value(0),
					// Código da Instrução para Movimento
					// Código adotado pela FEBRABAN, para identificar a ação a ser realizada com o
					// lançamento enviado no arquivo.
					fmovimentoCodigo().value("01"), // Padrão entrada de titulo
					// Código adotado pela FEBRABAN para identificar o Título.
					// Especificações do Código de Barras do Boleto de Pagamentode Cobrança -Ficha
					// de Compensação
					fcodigoBarras().length(44), fsacadoNome().length(30), fdataVencimento(), fvalor().length(15),
					// Valor do Desconto + Abatimento
					// Valor de desconto (bonificação) sobre o valor nominal do documento, somado ao
					// Valor
					// do abatimento concedido pelo Beneficiário, expresso em moeda corrente.
					fvalorDesconto().value(0).length(15),
					// Valor da Mora + Multa
					// Valor do juros de mora somado ao Valor da multa, expresso em moeda corrente
					fvalorAcrescimo().value(0).length(15), fdataPagamento(), fvalorPagamento().length(15),
					// Número de unidades do tipo de moeda identificada para cálculo do valor do
					// documento. G041
					field("qtdeMoeda").filler(Fillers.ZERO_LEFT).value(1).length(15),
					// Referência Pagador Nº do Docto Atribuído pela Empresa 183 202 20 - Alfa G064
					fnumeroDocumento().length(20),
					// Nosso Número Nº do Docto Atribuído pelo Banco 203 222 20 - Alfa *G043
					// Número do Documento Atribuído pelo Banco (Nosso Número)
					// Número atribuído pelo Banco para identificar o lançamento, que será utilizado
					// nas manutenções do mesmo.
					fnossoNumero().length(20),
					// G065 Código da Moeda
					// Código adotado pela FEBRABAN para identificar a moeda referenciada no Título
					fcodigoMoeda(),
					// 20.3J CNAB Uso Exclusivo FEBRABAN/CNAB 225 230 6 - Alfa Brancos G004
					fbranco().length(6),
					// Código das Ocorrências para Retorno/Remessa
					// Código adotado pela FEBRABAN para identificar as ocorrências detectadas no
					// processamento.
					// Pode-se informar até 5 ocorrências simultaneamente, cada uma delas codificada
					// com
					// dois dígitos, conforme relação abaixo.
					focorrencias()),
			// Registro Detalhe - Segmento J-52 (Obrigatório – Remessa / Retorno)
			detalheSegmentoJ52(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					// Número seqüencial para identificar univocamente um lote de serviço. Criado e
					// controlado pelo responsável pela geração magnética dos dados contidos no
					// arquivo.
					// Preencher com '0001' para o primeiro lote do arquivo. Para os demais: número
					// do lote
					// anterior acrescido de 1. O número não poderá ser repetido dentro do arquivo.
					// Se registro for Header do Arquivo preencher com '0000'
					// Se registro for Trailer do Arquivo preencher com '9999'
					fbancoCodigo(), flote().value(1), fcodigoRegistro().value("3"), fsequencialRegistro(),
					// Código adotado pela FEBRABAN para identificar o segmento do registro.
					fsegmento().id(true).value("J"),
					// 06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
					fbranco().length(1),
					// C004: Código de Movimento Remessa
					// Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviado
					// nos
					// registros do arquivo de remessa.
					// Cada Banco definirá os campos a serem alterados para o código de movimento
					// '31'
					fmovimentoCodigo().value("01"),
					// 08.4.J52 Código Reg. Opcional Identificação Registro Opcional 18 19 2 - Num
					// “52” G067
					fidOpcional().id(true).value("52"),
					// DADOS DO PAGADOR
					// Tipo de Inscrição: '0' = Isento / Não Informado
					// '1' = CPF
					// '2' = CGC / CNPJ
					// '3' = PIS / PASEP
					// '9' = Outros
					field("tipoInscricaoSacado").valLen("1"), fsacadoCpf().length(15), fsacadoNome().length(40),
					// DADOS DO BENEFICIARIO
					field("tipoInscricaoCedente").valLen("2"), fcedenteCnpj().length(14), fcedenteNome().length(40),
					// DADOS DO PAGADORR
					// Pagadorr - Dados sobre o Beneficiário responsável pela emissão do título
					// original
					field("tipoInscricaoPagadorr").valLen("2"),
					field("pagadorrInscricao").length(15).filler(Fillers.ZERO_LEFT),
					field("pagadorr").length(40).filler(Fillers.WHITE_SPACE_LEFT),
					// Uso Exclusivo FEBRABAN/CNAB
					fbranco().length(53)),
			// Layout Padrão Febraban 240 posições V10.5
			// http://www.febraban.org.br
			// Registro Detalhe -Segmento U (Obrigatório -Retorno)
			detalheSegmentoU(fbancoCodigo(), flote(), fcodigoRegistro().value("3"), fsequencialRegistro(),
					// Código adotado pela FEBRABAN para identificar o segmento do registro.
					fsegmento().id(true).value("U"),
					// 06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
					fbranco().length(1),
					// C004: Código de Movimento Remessa
					// Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviado
					// nos
					// registros do arquivo de remessa.
					// Cada Banco definirá os campos a serem alterados para o código de movimento
					// '31'
					fmovimentoCodigo(),
					// Dadosdo Título
					// Valor da Mora + Multa
					// Valor do juros de mora somado ao Valor da multa, expresso em moeda corrente
					fvalorAcrescimo().length(15), fvalorDesconto().length(15), fvalorAbatimento().length(15),
					fvalorIOF().length(15), fvalorPagamento().length(15), fvalorLiquido().length(15),
					fvalorOutrasDespesas(), fvalorOutrasReceitas(), fdataOcorrencia(),
					fdata().nome("dataOutrasReceitas"),
					// Ocorr. do Pagador
					fcodigoOcorrencia().length(4), fdata().nome("dataOcorrenciaPagador"),
					fvalor().nome("valorOcorrenciaPagador"), field("complementoOcorrencia").length(30),
					field("codBancoCorrespondenteCompens").length(3),
					field("nossoNumeroBancoCorrespondenteCompens").length(20), field("CNAB").length(7)),
			// Registro Detalhe -Segmento T (Obrigatório -Retorno)
			detalheSegmentoT(fbancoCodigo(), flote(), fcodigoRegistro().value("3"), fsequencialRegistro(),
					// Código adotado pela FEBRABAN para identificar o segmento do registro.
					fsegmento().id(true).value("T"),
					// 06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
					fbranco().length(1), fmovimentoCodigo(),
					// C/C
					fagencia(),
					// Número da Conta Corrente5970 12-Num*G010
					// Dígito Verificador da Conta7171 1-Alfa*G011
					fconta(), // Conta com DV
					// Dígito Verificador da Ag/Conta72721-Alfa*G012
					// Dígito Verificador da Agência / Conta CorrenteCódigo
					// adotado pelo Banco responsável pela conta corrente,
					// para verificação da autenticidade do par Código da Agência / Número da Conta
					// Corrente.
					// Para os Bancos que se utilizam de duas posições para o Dígito Verificador
					// do Número da Conta Corrente, preencher este campo com a 2ª posição deste
					// dígito.
					fdac(), fnossoNumero().length(20), fcodigoCarteira(), fnumeroDocumento().length(15),
					fdataVencimento(), fvalor().length(15),
					// *C045
					fbancoCodigo().nome("bancoCodigoRecebedor"),
					// Agencia (5) + DV (1)
					fagencia().nome("agenciaBancoRecebedor"), field("usoEmpresa").length(25), fcodigoMoeda(),
					ftipoInscricao().value(1), // Default 1 CPF
					fsacadoCpf().length(15), fsacadoNome().length(40), field("numeroContrato").length(10),
					fvalorTarifaCustas().length(15), frejeicoes().length(10), field("CNAB").length(17)),
			// Registro Detalhe -Segmento P (Obrigatório -Remessa)
			detalheSegmentoP(fbancoCodigo(), flote().value(1), // o mesmo do cabeçalho do lote
					fcodigoRegistro().value("3"), fsequencialRegistro(), fsegmento().id(true).value("P"),
					fbranco().length(1),
					// C004
					fmovimentoCodigo().value("01"), // Default '01' = Entrada de Títulos
					fagencia(), fconta(), // Conta com DV
					fdac(), fnossoNumero().length(20), fcodigoCarteira().value(1), // Default '1' = Cobrança Simples
					// 1' = Com Cadastramento (Cobrança Registrada)
					// '2' = Sem Cadastramento (Cobrança sem Registro)
					// Obs.: Destina-se somente para emissão de Boleto de Pagamentopelo banco
					// '3' = Com Cadastramento / Recusa do Débito Autom
					field("formaCadastroTitulo").length(1).value(1), ftipoDocumento(), ftipoEmissaoBoleto(),
					// C010 Identificação da Distribuição
					// Código adotado pela FEBRABAN para identificar o responsável pela distribuição
					// do Boleto de Pagamento.
					// Domínio:
					// '1' = Banco Distribui
					// '2' = Cliente Distribui
					// ‘3’ = Banco envia e-mail
					// ‘4’ = Banco envia SMS
					field("formaDistribuicaoTitulo").length(1).value(2), fnumeroDocumento().length(15),
					fdataVencimento(), fvalor(), field("agenciaCobradora").length(6).filler(Fillers.ZERO_LEFT).value(0),
					fespecieTitulo(), faceite(), fdataGeracao(), fcodigoAcrescimo(),
					// Data indicativa do início da cobrança do Juros de Mora de um título de
					// cobrança.
					// A data informada deverá ser maior que a Data de Vencimento do título de
					// cobrança
					// Caso seja inválida ou não informada será assumida a data do vencimento.
					fdataAcrescimo().value(0),
					// Valor ou porcentagem sobre o valor do título a ser cobrada de juros de mora.
					fvalorAcrescimo(), fcodigoDesconto(), fdataDesconto().value(0),
					// Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
					fvalorDesconto(), fvalorIOF(), fvalorAbatimento(),
					// Identificação do Título na Empresa
					fnumeroDocumento().nome("numeroDocumentoEmpresa").length(25).filler(Fillers.ZERO_LEFT).value(0),
					fcodigoProtesto(), field("numeroDiasProtesto").length(2).value("00"), fcodigoBaixa().value("0"),
					field("numeroDiasBaixa").length(3).value("000"), fcodigoMoeda(),
					field("numeroContrato").length(10).filler(Fillers.WHITE_SPACE_LEFT).type(Number.class).value(0),
					fbranco().length(1)),
			// Registro Detalhe -Segmento Q (Obrigatório -Remessa)
			detalheSegmentoQ(fbancoCodigo(), flote().value(1), // o mesmo do cabeçalho do lote
					fcodigoRegistro().value("3"), fsequencialRegistro(), fsegmento().id(true).value("Q"),
					fbranco().length(1),
					// *C004
					fmovimentoCodigo().value("01"), // Default '01' = Entrada de Títulos
					// Tipo de Inscrição
					ftipoInscricao().value(1), fsacadoCpf().length(15), fsacadoNome().length(40),
					fendereco().length(40).value(""), fbairro().length(15).value(""), fcep().length(8).value(0),
					fcidade().truncate(true).length(15).value(""), fuf().length(2).value(""),
					field("tipoInscricaoAvalista").length(1).value(0),
					field("numeroInscricaoAvalista").length(15).filler(Fillers.ZERO_LEFT).value(0),
					field("nomeAvalista").filler(Fillers.WHITE_SPACE_RIGHT).length(40).value(""),
					fbancoCodigo().nome("bancoCodigoCorrespondente").value(0),
					field("nossoNumeroBancoCorrespondente").filler(Fillers.ZERO_LEFT).length(20).value(0),
					fbranco().length(8)),
			// Registro Detalhe - Segmento R (Opcional - Remessa)
			detalheSegmentoR(fbancoCodigo(), flote().value(1), // o mesmo do cabeçalho do lote
					fcodigoRegistro().value("3"), fsequencialRegistro(), fsegmento().id(true).value("R"),
					fbranco().length(1),
					// *C004
					fmovimentoCodigo().value("01"), // Default '01' = Entrada de Títulos
					// Desconto 2
					fcodigoDesconto(), fdataDesconto(),
					// Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
					fvalorDesconto(),
					// Desconto 3
					fcodigoDesconto().nome("codigoDescontoExtra"), fdata().nome("dataDescontoExtra"),
					// Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
					fvalorDesconto().nome("valorDescontoExtra"),
					// Multa/Juros
					/**
					 * G073: Código adotado pela FEBRABAN para identificação do critério de
					 * pagamento de pena pecuniária, a ser aplicada pelo atraso do pagamento do
					 * Título. Domínio:'1' = Valor Fixo'2' = Percentual
					 */
					fcodigoAcrescimo().value(0),
					/**
					 * Caso não tenha multa, informar 'zeros'. Sistema aceita a mesma data do
					 * vencimento, ou dia seguinte.
					 */
					fdataAcrescimo(),
					/**
					 * Caso não tenha multa, informar 'zeros'. Sistema aceita a mesma data do
					 * vencimento, ou dia seguinte.
					 */
					fvalorAcrescimo(),
					/**
					 * Informação ao PagadorTexto de observações destinado ao envio de informações
					 * do Beneficiário ao Pagador.Este campo só poderá ser utilizado, caso haja
					 * troca de arquivos magnéticos entre o Banco e o Pagador.
					 */
					field("infoPagador").length(10).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					field("mensagem3").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					field("mensagem4").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""), fbranco().length(20),
					// C038: Código da Ocorrência do Pagador
					// Código adotado pela FEBRABAN para identificar a ocorrência do Pagador
					// (Descrição A001)
					// a(s) qual(is) o Beneficiário não concorda.
					// Somente será utilizado para o Código de Movimento '30' (Descrição C004).
					fcodigoOcorrencia().nome("codigoOcorrenciaPagador").length(8).filler(Fillers.ZERO_LEFT).value(0),
					// Dados para debito
					fbancoCodigo().nome("bancoCodigoDebito").value(0), fagencia().nome("agenciaDebito").value(0),
					fconta().nome("contaDebito").value(0), fdac().nome("dacDebito").value(0),
					// C0039: Aviso para Débito Automático Código adotado pela FEBRABAN para
					// identificação da emissão do aviso de débito automático em conta corrente.
					// Domínio: '01' = Emite o Aviso com o Endereço Informado no Arquivo Remessa
					// '02' = Não Emite Aviso ao Pagador
					// '03' = Emite Aviso com o Endereço Constante do Cadastro do Banco
					// Para códigos diferentes de '01', '02' e '03' seguir a regra do '03'.
					field("avisoDebitoAutomatico").length(1).value("0"), fbranco().length(9)),
			rodapeLote(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					fbancoCodigo(), flote().value(1), // o mesmo do cabeçalho do lote
					fcodigoRegistro().value("5"),
					// 04.5 CNAB Uso Exclusivo FEBRABAN/CNAB 9 17 9 - Alfa Brancos G004
					fbranco().length(9),
					// Quantidade de Registros do Lote 18 23 6 - Num *G057
					fquantidadeRegistros().length(6).value(0), fvalorTotalRegistros().length(18).value(0),
					// Qtde de Moeda Somatória de Quantidade de Moedas 42 59 13 5 Num G058
					// G058 Somatória de Quantidade de Moedas
					// Valor obtido pela somatória das quantidades de moeda dos registros de detalhe
					// (Registro = '3' / Código de Segmento = {'A' / 'J'}).
					field("qtedMoedas").length(18).filler(Fillers.ZERO_LEFT).value(1),
					// 08.5 Número Aviso Débito Número Aviso Débito 60 65 6 - Num G066
					// Número do Aviso de Débito
					// Número atribuído pelo Banco para identificar um Débito efetuado na Conta
					// Corrente a
					// partir do(s) pagamento(s) efetivado(s), visando facilitar a Conciliação
					// Bancária.
					field("numAvisoDebito").length(6).filler(Fillers.ZERO_LEFT), fbranco().length(165),
					// Código das Ocorrências para Retorno/Remessa G0059
					focorrencias()),
			rodape(
					// Controle: Banco, lote e registro
					// Banco: Código do Banco na Compensação133-NumG001
					fbancoCodigo(), flote().value("9999"), fcodigoRegistro().value("9"),
					// Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
					fbranco().length(9),
					// Qtde. de LotesQuantidade de Lotes do Arquivo18236-NumG049
					field("qtdeLotes").padding(Fillers.ZERO_LEFT).length(6).value(1),
					// Qtde. de RegistrosQuantidade de Registros do Arquivo24296-NumG0
					fquantidadeRegistros().length(6).value(0),
					// Qtde. de Contas Concil.Qtde de Contas p/ Conc. (Lotes)30356-Num*G037
					/**
					 * Número indicativo de lotes de Conciliação Bancária enviados no arquivo.
					 * Somatória dos registros de tipo 1 e Tipo de Operação = 'E'. Campo específico
					 * para o serviço de Conciliação Bancária
					 */
					field("qtedContas").value(0).filler(Fillers.ZERO_LEFT).length(6),
					// Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
					fbranco().length(205)));

	private static final TagLayout _LAYOUT_CAIXA_CNAB240_REMESSA = flatfile(layout(
			nome("Layout Padrão Caixa Econômica Federal CNAB240 Remessa"), cnab(CNAB_240), banco("104"),
			tag("url").value(
					"http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf"),
			versao("17")),
			cabecalho(fbancoCodigo().length(3).value("104"), flote().value(0), fcodigoRegistro().length(1).value("0"),
					fbranco().length(9), ftipoInscricao().length(1).value("2"),
					fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT), fzero().length(20), fagencia().length(6),
					fconvenio().length(7).filler(Fillers.ZERO_LEFT), fzero().length(7),
					fcedenteNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
					fbancoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT), fbranco().length(10),
					fcodigoArquivo().length(1), fdataGeracao().length(8),
					field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
					fsequencialArquivo().length(6), field("versaoLayoutArquivo").length(3).value("107"),
					field("densidadeArquivo").length(5).filler(Fillers.ZERO_LEFT).value("0"), fbranco().length(20),
					field("situacaoArquivo").length(20).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fbranco().length(4), fbranco().length(25)),
			cabecalhoLote(fbancoCodigo().length(3).value("104"), flote().value(1),
					fcodigoRegistro().length(1).value("1"), foperacao().length(1), fservico().length(2),
					fzero().length(2), field("versaoLayoutLote").length(3).value("067"), fbranco().length(1),
					ftipoInscricao().length(1).value("2"), fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
					fconvenio().length(7).filler(Fillers.ZERO_LEFT), fzero().length(13), fagencia().length(6),
					field("codigoBeneficiario").length(6).filler(Fillers.ZERO_LEFT).value(0), fzero().length(7),
					fzero().length(1), fcedenteNome().length(30),
					field("mensagem1").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					field("mensagem2").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fsequencialArquivo().length(8).filler(Fillers.ZERO_LEFT).value("0"), fdataGeracao().length(8),
					fzero().length(8), fbranco().length(33)),
			detalheSegmentoP(fbancoCodigo().length(3).value("104"), flote().value(1),
					fcodigoRegistro().length(1).value("3"), fsequencialRegistro().length(5),
					fsegmento().id(true).value("P"), fbranco().length(1), fmovimentoCodigo().length(2).value("01"),
					fagencia().length(6).filler(Fillers.ZERO_LEFT), fconvenio().length(7).filler(Fillers.ZERO_LEFT),
					fzero().length(7), fzero().length(2), fzero().length(1), field("modalidadeCarteira").length(2),
					fnossoNumero().length(15), fcodigoCarteira().length(1).value("1"),
					field("formaCadastroTitulo").length(1).value("1"), ftipoDocumento().length(1).value("2"),
					field("formaEmissaoTitulo").length(1).value("2"), field("formaEntregaTitulo").length(1).value("0"),
					fnumeroDocumento().length(11), fbranco().length(4), fdataVencimento().length(8),
					fvalor().length(15), field("agenciaCobradora").length(6).filler(Fillers.ZERO_LEFT).value("0"),
					fespecieTitulo().length(2).value("02"), faceite().length(1).value("N"), fdataGeracao().length(8),
					fcodigoAcrescimo().length(1).value("3"),
					fdataAcrescimo().length(8).filler(Fillers.ZERO_LEFT).value("0"),
					fvalorAcrescimo().length(15).filler(Fillers.ZERO_LEFT).value("0"),
					fcodigoDesconto().length(1).value("0"),
					fdataDesconto().length(8).filler(Fillers.ZERO_LEFT).value("0"),
					fvalorDesconto().length(15).filler(Fillers.ZERO_LEFT).value("0"),
					fvalorIOF().length(15).filler(Fillers.ZERO_LEFT).value("0"),
					fvalorAbatimento().length(15).filler(Fillers.ZERO_LEFT).value("0"),
					fnumeroDocumento().nome("numeroDocumentoEmpresa").length(25).filler(Fillers.ZERO_LEFT).value("0"),
					fcodigoProtesto().length(1).value("3"), field("numeroDiasProtesto").length(2).value("00"),
					fcodigoBaixa().length(1).value("1"), field("numeroDiasBaixa").length(3).value("000"),
					fcodigoMoeda().length(2).value("09"), fzero().length(10), fbranco().length(1)),
			detalheSegmentoQ(fbancoCodigo().length(3).value("104"), flote().value(1),
					fcodigoRegistro().length(1).value("3"), fsequencialRegistro().length(5),
					fsegmento().id(true).value("Q"), fbranco().length(1), fmovimentoCodigo().length(2).value("01"),
					ftipoInscricao().length(1).value("1"), fsacadoCpf().length(15).filler(Fillers.ZERO_LEFT),
					fsacadoNome().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
					fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fbairro().length(15).filler(Fillers.WHITE_SPACE_RIGHT).value(""), fcep().length(8).value("0"),
					fcidade().truncate(true).length(15).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fuf().length(2).value(""), field("tipoInscricaoAvalista").length(1).value("0"),
					field("numeroInscricaoAvalista").length(15).filler(Fillers.ZERO_LEFT).value("0"),
					field("nomeAvalista").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fbancoCodigo().nome("codigoBancoCorrespondente").length(3).filler(Fillers.ZERO_LEFT).value("0"),
					field("nossoNumeroBancoCorrespondente").length(20).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fbranco().length(8)),
			rodapeLote(fbancoCodigo().length(3).value("104"), flote().value(1), fcodigoRegistro().length(1).value("5"),
					fbranco().length(9), fquantidadeRegistros().length(6).filler(Fillers.ZERO_LEFT).value("0"),
					field("quantidadeTitulosCobranca").length(6).filler(Fillers.ZERO_LEFT).value("0"),
					fvalorTotalRegistros().length(17).filler(Fillers.ZERO_LEFT).value("0"),
					field("quantidadeTitulosCobrancaCaucionada").length(6).filler(Fillers.ZERO_LEFT).value("0"),
					field("valorTotalTitulosCarteiraCaucionada").length(17).filler(Fillers.ZERO_LEFT).value("0"),
					field("quantidadeTitulosCobrancaDescontada").length(6).filler(Fillers.ZERO_LEFT).value("0"),
					field("quantidadeTitulosCarteiraDescontada").length(17).filler(Fillers.ZERO_LEFT).value("0"),
					fbranco().length(31), fbranco().length(117)),
			rodape(fbancoCodigo().length(3).value("104"), flote().length(4).value("9999"),
					fcodigoRegistro().length(1).value("9"), fbranco().length(9),
					field("quantidadeLotesArquivo").length(6).value("000001"),
					fquantidadeRegistros().length(6).filler(Fillers.ZERO_LEFT).value("0"), fbranco().length(6),
					fbranco().length(205)));

	private static final TagLayout _LAYOUT_CAIXA_CNAB240_RETORNO = flatfile(layout(
			nome("Layout Padrão Caixa Econômica Federal CNAB240 Retorno"), cnab(CNAB_240), banco("104"),
			tag("url").value(
					"http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf"),
			versao("17")),
			cabecalho(fbancoCodigo(), flote(), fcodigoRegistro().length(1).value("0"), fbranco().length(9),
					ftipoInscricao().length(1), fcedenteCnpj().length(14), fzero().length(20), fagencia().length(6),
					fconvenio().length(7), fzero().length(7), fcedenteNome().length(30), fbancoNome().length(30),
					fbranco().length(10), fcodigoArquivo().length(1), fdataGeracao().length(8),
					field("horaGeracao").length(6), fsequencialArquivo().length(6),
					field("versaoLayoutArquivo").length(3), field("densidadeArquivo").length(5), fbranco().length(20),
					field("situacaoArquivo").length(20), fbranco().length(4), fbranco().length(10), fzero().length(3),
					fbranco().length(12)),
			cabecalhoLote(fbancoCodigo(), flote(), fcodigoRegistro().length(1).value("1"), foperacao().length(1),
					fservico().length(2), fzero().length(2), field("versaoLayoutLote").length(3), fbranco().length(1),
					ftipoInscricao().length(1), fcedenteCnpj().length(14), fconvenio().length(7), fzero().length(13),
					fagencia().length(6), fzero().length(6), fzero().length(7), fzero().length(1),
					fcedenteNome().length(30), field("mensagem1").length(40), field("mensagem2").length(40),
					fsequencialArquivo().length(8), fdataGeracao().length(8), field("dataCredito").length(8),
					fzero().length(2), fbranco().length(26), fzero().length(2), fbranco().length(3)),
			detalheSegmentoT(fbancoCodigo(), flote(), fcodigoRegistro().value("3"), fsequencialRegistro(),
					fsegmento().id(true).value("T"), fbranco().length(1), fmovimentoCodigo(), fzero().length(6),
					fconvenio().length(7), fzero().length(2), field("numeroBancoPagadores").length(3),
					fzero().length(1), fbranco().length(2), field("modalidadeCarteiraSINCO").length(1),
					field("modalidadeCarteiraSIGCB").length(2), fnossoNumero().length(15),
					field("digitoVerificadorNossoNumero").length(1), fcodigoCarteira(), fnumeroDocumento().length(11),
					fbranco().length(4), fdataVencimento(), fvalor().length(15), field("bancoCobrador").length(3),
					field("agenciaCobradora").length(5), field("digitoAgenciaCobradora").length(1),
					field("identificacaoTituloEmpresa").length(25), fcodigoMoeda(), ftipoInscricao().value(1),
					fsacadoCpf().length(15), fsacadoNome().length(40), fbranco().length(10),
					fvalorTarifaCustas().length(15), field("motivoOcorrencia").length(10), fbranco().length(17)),
			detalheSegmentoU(fbancoCodigo(), flote(), fcodigoRegistro().value("3"), fsequencialRegistro(),
					fsegmento().id(true).value("U"), fbranco().length(1), fmovimentoCodigo(),
					fvalorAcrescimo().length(15), fvalorDesconto().length(15), fvalorAbatimento().length(15),
					fvalorIOF().length(15), fvalorPagamento().length(15), fvalorLiquido().length(15),
					fvalorOutrasDespesas(), fvalorOutrasReceitas(), fdataOcorrencia(), fdataCredito(),
					fzero().length(4), field("dataDebitoTarifa").length(8), field("codigoPagador").length(15),
					fzero().length(30), field("codigoBancoCompensacao").length(3),
					field("nossoNumeroBancoCorrespondente").length(20), fbranco().length(7)),
			rodapeLote(fbancoCodigo(), flote(), fcodigoRegistro().value("5"), fbranco().length(9),
					fquantidadeRegistros().length(6), field("quantidadeTitulosCobrancaSimples").length(6),
					field("valorTotalTitulosCarteiraCobrancaSimples").length(17),
					field("quantidadeTitulosCobrancaCaucionadas").length(6),
					field("valorTotalTitulosCarteiraCaucionadas").length(17),
					field("quantidadeTitulosCobrancaDescontada").length(6),
					field("quantidadeTitulosCarteiraDescontada").length(17), fzero().length(31), fbranco().length(117)),
			rodape(fbancoCodigo(), flote(), fcodigoRegistro().length(1).value("9"), fbranco().length(9),
					field("quantidadeLotesArquivo").length(6), fquantidadeRegistros().length(6),
					fbranco().length(211)));

	private static final TagLayout _LAYOUT_BRADESCO_CNAB400_REMESSA = flatfile(
			layout(nome("Layout Padrão Bradesco CNAB400 Remessa"), cnab(CNAB_400), banco("237"), tag("url").value(
					"https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
					versao("15")),
			cabecalho(fcodigoRegistro().length(1).value("0"), fcodigoArquivo().length(1).value("1"),
					fliteralRemessa().length(7).value("REMESSA"), fservico().length(2),
					fliteralServico().length(15).value("COBRANCA"), fconvenio().length(20), fcedenteNome().length(30),
					fbancoCodigo().length(3).value("237"), fbancoNome().length(15).value("BRADESCO"),
					fdataGeracao().length(6).format(new SimpleDateFormat("ddMMyy")), fbranco().length(8),
					field("identificacaoSistema").length(2).value("MX"),
					fsequencialArquivo().length(7).filler(Fillers.ZERO_LEFT), fbranco().length(277),
					fsequencialRegistro().length(6)),
			detalhe(fcodigoRegistro().length(1).value("1"),
					field("agenciaDebito").length(5).filler(Fillers.ZERO_LEFT).value(0),
					field("digitoAgenciaDebito").length(1).value(0),
					field("razaoContaCorrente").length(5).filler(Fillers.ZERO_LEFT).value(0),
					field("contaCorrente").length(7).filler(Fillers.ZERO_LEFT).value(0),
					field("digitoContaCorrente").length(1).value(0), fzero().length(1),
					fcarteira().length(3).filler(Fillers.ZERO_LEFT), fagencia().length(5).filler(Fillers.ZERO_LEFT),
					fconta().length(8).filler(Fillers.ZERO_LEFT),
					field("numeroControleParticipante").length(25).filler(Fillers.ZERO_LEFT).value(0),
					fbancoCodigo().length(3).value("237"), field("campoMulta").length(1).value(0),
					field("percentualMulta").length(4).filler(Fillers.ZERO_LEFT).value(0),
					fnossoNumero().length(11).filler(Fillers.ZERO_LEFT), field("digitoNossoNumero").length(1),
					field("descontoBonificacaoDia").length(10).filler(Fillers.ZERO_LEFT).value(0),
					field("codicaoEmissaoPapeladaCobranca").length(1).value(2),
					field("identificacaoBoletoDebito").length(1).value("N"), fbranco().length(10),
					field("rateioCredito").length(1).value(" "), field("avisoDebitoAutomatico").length(1).value(2),
					field("quantidadePagamentos").length(2).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
					fcodigoOcorrencia().length(2).value("01"), fnumeroDocumento().length(10).filler(Fillers.ZERO_LEFT),
					fdataVencimento().length(6).format(new SimpleDateFormat("ddMMyy")),
					fvalor().length(13).filler(Fillers.ZERO_LEFT), fzero().length(3), fzero().length(5),
					fespecieTitulo().length(2).value("01"), field("identificacao").length(1).value("N"),
					fdataGeracao().length(6).format(new SimpleDateFormat("ddMMyy")),
					field("instrucao1").length(2).filler(Fillers.ZERO_LEFT).value(0),
					field("instrucao2").length(2).filler(Fillers.ZERO_LEFT).value(0),
					field("moraDiaria").length(13).filler(Fillers.ZERO_LEFT).value(0),
					fdataDesconto().length(6).format(new SimpleDateFormat("ddMMyy")).value(0),
					fvalorDesconto().length(13).filler(Fillers.ZERO_LEFT).value(0),
					fvalorIOF().length(13).filler(Fillers.ZERO_LEFT).value(0),
					fvalorAbatimento().length(13).filler(Fillers.ZERO_LEFT).value(0),
					ftipoInscricao().length(2).value("01"), fsacadoCpf().length(14).filler(Fillers.ZERO_LEFT),
					fsacadoNome().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
					fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
					field("mensagem1").length(12).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
					field("sacadoCep").length(8),
					field("mensagem2").length(60).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
					fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)),
			rodape(fcodigoRegistro().length(1).value("9"), fbranco().length(393),
					fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)));

	private static final TagLayout _LAYOUT_BRADESCO_CNAB400_RETORNO = flatfile(
			layout(nome("Layout Padrão Bradesco CNAB400 Retorno"), cnab(CNAB_400), banco("237"), tag("url").value(
					"https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
					versao("15")),
			cabecalho(fcodigoRegistro().value("0"), fcodigoArquivo(), fliteralRetorno().length(7), fservico().length(2),
					fliteralServico().length(15), fconvenio().length(20), fcedenteNome().length(30), fbancoCodigo(),
					fbancoNome().length(15), fdataGeracao().length(6), field("densidadeArquivo").length(8),
					field("numeroAvisoBancario").length(5), fbranco().length(266), fdataCredito().length(6),
					fbranco().length(9), fsequencialRegistro().length(6)),
			detalhe(fcodigoRegistro().value("1"), ftipoInscricaoCedente().length(2), fcedenteCnpj().length(14),
					fzero().length(3), fzero().length(1), fcarteira().length(3), fagencia().length(5),
					fconta().length(8), field("numeroControleParticipante").length(25), fzero().length(8),
					fnossoNumero().length(11), field("digitoNossoNumero").length(1), fzero().length(22),
					field("rateioCredito").length(1), field("quantidadePagamentos").length(2),
					fcodigoCarteira().length(1), fmovimentoCodigo().length(2), fdataOcorrencia().length(6),
					fnumeroDocumento().length(10), field("identificacaoTituloBanco").length(20),
					fdataVencimento().length(6), fvalor().length(13), field("bancoCobrador").length(3),
					field("agenciaCobradora").length(5), fespecieTitulo().length(2), fvalorTarifaCustas().length(13),
					fvalorOutrasDespesas().length(13), field("jurosOperacaoAtraso").length(13), fvalorIOF().length(13),
					fvalorAbatimento().length(13), fvalorDesconto().length(13), fvalorPagamento().length(13),
					fvalorAcrescimo().length(13), fvalorOutrasReceitas().length(13), fbranco().length(2),
					field("codigoOcorrenciaProtesto").length(1), fdataCredito().length(6),
					field("origemPagamento").length(3), fbranco().length(10), field("codigoBanco").length(4),
					field("motivoRejeicao").length(10), fbranco().length(40), field("numeroCartorio").length(2),
					field("numeroProtocolo").length(10), fbranco().length(14), fsequencialRegistro().length(6)),
			rodape(fcodigoRegistro().value("9"), fcodigoArquivo(), field("identificacaoTipoRegistro").length(2),
					fbancoCodigo(), fbranco().length(10), field("quantidadeTitulosCobranca").length(8),
					field("valorTotalCobranca").length(14), field("numeroAvisoBancario").length(8),
					fbranco().length(10), field("quantidadeRegistrosEntradaConfirmada").length(5),
					field("valorRegistrosEntradaConfirmada").length(12), field("valorRegistrosLiquidacao").length(12),
					field("quantidadeRegistrosLiquidacao").length(5), field("valorRegistrosLiquidacao").length(12),
					field("quantidadeRegistrosBaixados").length(5), field("valorRegistrosBaixados").length(12),
					field("quantidadeRegistrosAbatimentoCancelado").length(5),
					field("valorRegistrosAbatimentoCancelado").length(12),
					field("quantidadeRegistrosVencimentoAlterado").length(5),
					field("valorRegistrosVencimentoAlterado").length(12),
					field("quantidadeRegistrosAbatimentoConcedido").length(5),
					field("valorRegistrosAbatimentoConcedido").length(12),
					field("quantidadeRegistrosConfirmacaoProtesto").length(5),
					field("valorRegistrosConfirmacaoProtesto").length(12), fbranco().length(174),
					field("valorTotalRateios").length(15), field("quantidadeRateios").length(8), fbranco().length(9),
					fsequencialRegistro().length(6)));

	static final TagLayout _LAYOUT_SICREDI_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

	static {
		// Layout
		TagLayout layoutSicredi = _LAYOUT_SICREDI_CNAB240.get(layout());
		layoutSicredi.get("nome").value("Layout Padrão SICREDI CNAB240");
		layoutSicredi.get("banco").value("748");
		layoutSicredi.get("url").value("https://www.sicredi.com.br/html/para-sua-empresa/recebimentos/cobranca/");
	}

	/**
	 * Particularidades BB CNAB 240 2019
	 * https://www.bb.com.br/docs/pub/emp/empl/dwn/CNAB240SegPQRSTY.pdf
	 */
	static final TagLayout _LAYOUT_BB_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

	static {

		// Layout
		TagLayout layout = _LAYOUT_BB_CNAB240.get(layout());
		TagLayout tnome = layout.get("nome");
		tnome.withValue("Layout Padrão Banco do Brasil CNAB240");
		layout.get("banco").withValue("001");
		layout.get("url").withValue("https://www.bb.com.br/docs/pub/emp/empl/dwn/CbrVer04BB.pdf");

		// Cabeçalho
		TagLayout cabecalho = _LAYOUT_BB_CNAB240.get(cabecalho());
		// 07.0 BB1 Nùmero do convênio de cobrança, 9 digitos,
		// Numérico Informar o número do convênio de cobrança, alinhado à direita com
		// zeros à esquerda.
		TagLayout fconvenioCabecalho = cabecalho.get(fconvenio()).length(9);
		/*
		 * 07.0 BB2 Cobrança Cedente BB 42 45 4 Numérico Informar 0014 para cobrança
		 * cedente 07.0 BB3 Número da carteira de cobrança BB 46 47 2 Numérico Informar
		 * o número da carteira de cobrança 07.0 BB4 Número da variação da carteira de
		 * 48 50 3 Numérico Informar o número da variação da carteira de cobrança
		 * cobrança BB 07.0 BB5 Campo reservado BB 51 52 2 Alfanumérico Informar
		 * brancos.
		 */
		cabecalho.insertAfter(fconvenioCabecalho, field("cobrancaCedente").valLen("0014"), field("carteira").length(2),
				field("variacao").length(3).filler(Fillers.ZERO_LEFT), fbranco().length(2));

		TagLayout cabecalhoLote = _LAYOUT_BB_CNAB240.get(cabecalhoLote());
		TagLayout fconvenioCabecalhoLote = cabecalhoLote.get(fconvenio()).length(9);

		/*
		 * 11.1 BB1 Nùmero do convênio de cobrança BB 34 42 9 Numérico Informar o número
		 * do convênio de cobrança, alinhado à direita com zeros à esquerda. 11.1 BB2
		 * Cobrança Cedente BB 43 46 4 Numérico Informar 0014 para cobrança cedente 11.1
		 * BB3 Número da carteira de cobrança BB 47 48 2 Numérico Informar o número da
		 * carteira de cobrança 11.1 BB4 Número da variação da carteira de 49 51 3
		 * Numérico Informar o número da variação da carteira de cobrança
		 */
		cabecalhoLote.insertAfter(fconvenioCabecalhoLote, field("cobrancaCedente").valLen("0014"),
				field("carteira").length(2), field("variacao").length(3).filler(Fillers.ZERO_LEFT),
				/*
				 * informar brancos; ou para tratamento de arquivo teste: cliente, antes de
				 * realizar os procedimentos abaixo,entre em contato com sua agência, pois a
				 * situação de seu intercâmbio eletrônico de dados deverá ser alterado de ATIVO
				 * para TESTE. 11.1 BB5 Campo que identifica remessa de testes 52 53 2
				 * Alfanumérico Importante que nesse caso não deverá ser enviado arquivos para a
				 * produção, pois sua condição foi alterada para TESTE. Obs.: Caso a empresa
				 * queira efetuar TESTE pelo sistema, com geração de arquivo retorno TESTE pelo
				 * Gerenciador Financeiro, basta substituir os espaços em branco (posições 52 e
				 * 53) por "TS". Caso não queira realizar os testes, informe brancos
				 */
				fbranco().length(2));

		TagLayout detalheSegmentoP = _LAYOUT_BB_CNAB240.get(detalheSegmentoP());
		// Importante:todos os "nosso número" devem ser alinhados à esquerda com brancos
		// à direita.
		TagLayout fnossoNumeroDetalhe = detalheSegmentoP.get(fnossoNumero());
		fnossoNumeroDetalhe.filler(Fillers.WHITE_SPACE_RIGHT);
//        fnossoNumeroDetalhe.length(11);
//        detalheSegmentoP.insertBefore(fnossoNumeroDetalhe, fconvenio().length(9));
		// System.out.println(detalheSegmentoP);
		// System.out.println(fnossoNumeroDetalhe);

	}

	private static final TagLayout _LAYOUT_SANTANDER_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

	static {
		// personalizações Santander

	}

	static final TagLayout _LAYOUT_ITAU_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

	static {
		// TODO: Fazer personalizações Itau
	}

	public static final TagLayout LAYOUT_FEBRABAN_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.cloneReadonly();

	public static final TagLayout LAYOUT_SICREDI_CNAB240 = _LAYOUT_SICREDI_CNAB240.cloneReadonly();

	public static final TagLayout LAYOUT_BB_CNAB240 = _LAYOUT_BB_CNAB240.cloneReadonly();

	public static final TagLayout LAYOUT_SANTANDER_CNAB240 = _LAYOUT_SANTANDER_CNAB240.cloneReadonly();

	public static final TagLayout LAYOUT_ITAU_CNAB240 = _LAYOUT_ITAU_CNAB240.cloneReadonly();

	private static final TagLayout _LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA = _LAYOUT_FEBRABAN_CNAB240.clone();

	private static final TagLayout _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA = flatfile(
			layout(nome("Layout Padrão Febraban CNAB240 Remessa"), cnab(CNAB_240), banco("###"),
					tag("url").value("http"), versao("##"),
					servico(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA)),

			cabecalho(fbancoCodigo().length(3).value("###"),
					flote().length(4).value("0000"),
					fcodigoRegistro().length(1).value("0"),
					fbranco().length(9),
					ftipoInscricao().length(1).value("2"),
					fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT), fconvenio().length(20),
					fagencia().length(6), // agenca com DV
					fconta().length(13), // Conta com DV
					fdac(), fcedenteNome().length(30),
					fbancoNome().length(30),
					fbranco().length(10),
					fcodigoArquivo().value(1),
					fdataGeracao(),
					field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
					fsequencialArquivo().length(6),
					field("versaoLayoutArquivo").valLen("###"),
					field("densidadeArquivo").value(0).length(5).filler(Fillers.ZERO_LEFT),
					fbranco().length(20),
					fbranco().length(20),
					fbranco().length(29)),

			cabecalhoLote(fbancoCodigo().length(3).value("###"),
					flote().value("0000"),
					fcodigoRegistro().length(1).value("1"),
					// Crédito em Conta  Corrente
					foperacao().length(1).value("C"),
					// Pagamento a fornecedores
					fservico().length(2).value(20),
					// 01 = credito em conta 03 = Transferência para outros bancos (DOC/TED)
					fforma().length(2),
					field("versaoLayoutLote").length(3).value("###"),
					fbranco().length(1),
					ftipoInscricao().length(1).value("2"),
					fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
					fconvenio().length(20).filler(Fillers.ZERO_LEFT),
					fagencia().length(6), // agenca com DV
					fconta().length(13), // Conta com DV
					fbranco().length(1), fcedenteNome().length(30),
					fbranco().length(40),
					fendereco().length(30).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fnumero().length(5).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fcomplemento().length(15).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fcidade().length(20).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fcep().length(8).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fuf().length(2).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
					fbranco().length(18)),

			detalheSegmentoA(
					fbancoCodigo().length(3).value("###"),
					flote().value("0000"),
					fcodigoRegistro().length(1).value("3"),
					fsequencialRegistro().length(5).value("#####"),
					fsegmento().id(true).value("A"),
					fzero().length(3).filler(Fillers.ZERO_LEFT),
					/* Código Câmara Compensação 000 = CC | 018 = TED | 700 = DOC */
					fformaDeTransferencia().length(3).filler(Fillers.ZERO_LEFT),
					ffavorecidoCodigoBanco().length(3).value("###"),
					ffavorecidoAgencia().length(6).filler(Fillers.ZERO_LEFT),
					ffavorecidoConta().length(13).filler(Fillers.ZERO_LEFT),
					fbranco().length(1),
					ffavorecidoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),

					// Número de Documento Cliente que identifica o pagto. ex: nota fiscal incrementar de 1 em 1
					field("numeroDocumento").length(6).filler(Fillers.WHITE_SPACE_LEFT),
					field("").length(14).filler(Fillers.WHITE_SPACE_RIGHT),
					fdataPagamento().length(8),
					field("moeda").length(3).value("BRL"),
					field("qtdeMoeda").length(15).value("000000000000000"),
					fvalor().length(15),
					field("nossoNumero").length(20).filler(Fillers.WHITE_SPACE_RIGHT),
					field("dataRealEfetivacaoPagto").length(8).filler(Fillers.WHITE_SPACE_RIGHT),
					field("valorRealEfetivacaoPagto").length(15).filler(Fillers.WHITE_SPACE_RIGHT),
					field("outrasInfos").length(40).filler(Fillers.WHITE_SPACE_RIGHT),
					field("complTipoServico").length(2).value("01"), // crédito em conta
					field("codigoFinalidadeDaTED").length(5).value("00010"), // crédito em conta
					field("finalidadePagamento").length(2).value("  "), // finalidade pagamento alguns casos CC
					fbranco().length(3),
					field("avisoAoFavorecido").length(1).value("0"), // crédito em conta
					// Códigos das Ocorrências p/ Retorno
					field("codigoDasOcorrenciasParaRetorno").length(10).filler(Fillers.WHITE_SPACE_RIGHT)

			),
			detalheSegmentoB(fbancoCodigo().length(3).value("###"), flote().value("#"),
					fcodigoRegistro().length(1).value("3"), fsequencialRegistro().length(5).value("#####"),
					fsegmento().id(true).value("B"), fbranco().length(3),
					// Tipo Inscrição Favorecido | CPF = 1, CNPJ = 2
					favorecidoTipoInscricao().length(1).value("#"),
					// Endereço do Favorecido - opcional
					ffavorecidoCPFCNPJ().length(14).filler(Fillers.ZERO_LEFT),
					fbranco().length(95),
					fdata(), // Data do Vencimento
					fvalor().length(15), // Valor do Documento
					fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor do Abatimento
					fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor do Desconto
					fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor da Mora
					fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor da Multa
					fbranco().length(15), // Código/Documento do Favorecido - Número interno sem tratamento para o banco
					fbranco().length(15)// Exclusivo FEBRABAN / CNAB

			), rodapeLote(fbancoCodigo().length(3).value("###"),
					flote().value("#"), // contador sequencial do lote
					field("tipoRegistro").length(1).value("5"), // 5 = trailer de lote
					fbranco().length(9), // filler
					// quantidade de registros no lote (Registros Tipo 1, 3, 5)
					fquantidadeRegistros().length(6).filler(Fillers.ZERO_LEFT),
					fvalorTotalRegistros().length(18), // somatoria

					// Somatória Quantidade Moeda (Registro Tipo 3)
					field("qtdeMoeda").length(18).value("##################").filler(Fillers.ZERO_LEFT),
					// filler Número Aviso de Débito
					field("numeroAvisoDeDebito").length(6).value("      ").filler(Fillers.WHITE_SPACE_LEFT),
					fbranco().length(175) // filler

			),


			rodape(
					fbancoCodigo().length(3).value("###"),
					//valor fixo do banco
					field("loteDeServico").length(4).value("9999"),
					// 5 = trailer de arquivo
					field("tipoRegistro").length(1).value("9"),
					fbranco().length(9), // filler em branco
					// Registros do Tipo 1
					fquantidadeLotes().length(6).value("######").filler(Fillers.ZERO_LEFT),
					// quantidade de Registros dos Tipos 0, 1,3, 5 e 9
					fquantidadeRegistros().length(6).value("######").filler(Fillers.ZERO_LEFT),
					field("qtdContasParaConciliacao").length(6).filler(Fillers.ZERO_LEFT).value("000000"),
					fbranco().length(205) // filler

			));

	private static final TagLayout _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
			.clone();

	static {
		// personalizações Itau

		String codigoBanco = "341";
		String campoBancoNome = "bancoNome";
		String campoBancoCodigo = "bancoCodigo";

		// Layout
		TagLayout layout = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(layout());
		layout.get("nome").value("Layout Padrão Itau CNAB240 Pagamento Remessa");
		layout.get("banco").value(codigoBanco);
		layout.get("url").withValue("https://download.itau.com.br/bankline/SISPAG_CNAB.pdf");
		layout.get("versao").value("80");

		// Cabeçalho
		TagLayout cabecalho = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(cabecalho());
		cabecalho.get(campoBancoNome).value("BANCO ITAU SA");
		cabecalho.get(campoBancoCodigo).value(codigoBanco);

		cabecalho.get("versaoLayoutArquivo").value("080");

		// cabecalhoLote
		TagLayout cabecalhoLote = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote());
		cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
		cabecalhoLote.get("versaoLayoutLote").value("040");

		// SegmentoA
		TagLayout segmentoA = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoA());
		segmentoA.get(campoBancoCodigo).value(codigoBanco);

		// SegmentoB
		TagLayout segmentoB = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoB());
		segmentoB.get(campoBancoCodigo).value(codigoBanco);

		// RodapeLote
		TagLayout rodapeLote = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(rodapeLote());
		rodapeLote.get(campoBancoCodigo).value(codigoBanco);

		// RodapeArquivo
		TagLayout rodapeArquivo = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(rodape());
		rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);

	}

	private static final TagLayout _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
			.clone();

	static {
		// personalizações Bradesco

		String codigoBanco = "237";
		String campoBancoNome = "bancoNome";
		String campoBancoCodigo = "bancoCodigo";

		// Layout
		TagLayout layout = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(layout());
		layout.get("nome").value("Layout Padrão Bradesco CNAB240 Pagamento Remessa");
		layout.get("banco").value(codigoBanco);
		layout.get("url").withValue(
				"https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0339-mp-operacionais-troca-arquivos-240-posicoes.pdf");
		layout.get("versao").value("89");

		// Cabeçalho
		TagLayout cabecalho = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(cabecalho());
		cabecalho.get(campoBancoNome).value("Bradesco");
		cabecalho.get(campoBancoCodigo).value(codigoBanco);		
		cabecalho.get("versaoLayoutArquivo").value("089");
		
		/*
		* o bradesco utiliza apenas 6 charas para o codigo do convenio.. preencher à esquerda e deixar espaco em branco a direita 
		*/
		cabecalho.get("convenio").padding(Fillers.WHITE_SPACE_RIGHT);

		//REMOVENDO - A densidade do arquivo atual (filho indice 18) E incluindo com valor 01600
		cabecalho.filhos.remove(18);
		cabecalho.insertAfter(cabecalho.get("versaoLayoutArquivo"), field("densidadeArquivo").value("01600").length(5));

		// cabecalhoLote
		TagLayout cabecalhoLote = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote());
		cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
		cabecalhoLote.get("versaoLayoutLote").value("045");
		cabecalhoLote.get("convenio").padding(Fillers.WHITE_SPACE_RIGHT);
	
		
		//REMOVENDO - A forma de pagamento atual (filho indice 22) E incluindo com valor 01
		cabecalhoLote.filhos.remove(22);		
		TagLayout cabecalhoLoteUF = cabecalhoLote.get("uf");
		cabecalhoLote.insertAfter(cabecalhoLoteUF, field("formaPagamento").value("01").length(2), fbranco().length(16));

		// SegmentoA
		TagLayout segmentoA = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoA());
		segmentoA.get(campoBancoCodigo).value(codigoBanco);

		segmentoA.get("complTipoServico").value("  ");
		segmentoA.get("finalidadePagamento").value("CC");
		

		// SegmentoB
		TagLayout segmentoB = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoB());
		segmentoB.get(campoBancoCodigo).value(codigoBanco);

		// RodapeLote
		TagLayout rodapeLote = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(rodapeLote());
		rodapeLote.get(campoBancoCodigo).value(codigoBanco);

		// RodapeArquivo
		TagLayout rodapeArquivo = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.get(rodape());
		rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);

	}

	private static final TagLayout _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
			.clone();

	static {
		// personalizações Santander

		String codigoSantander = "033";
		String campoBancoNome = "bancoNome";
		String campoBancoCodigo = "bancoCodigo";

		// Layout
		TagLayout layout = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(layout());
		layout.get("nome").value("Layout Padrão Santander CNAB240 Pagamento Remessa");
		layout.get("banco").value(codigoSantander);
		layout.get("url").withValue(
				"https://cms.santander.com.br/sites/WPS/documentos/arq-layout-de-arquivos-1/17-10-26_171722_258-37-pagamento+a+fornecedores+layout+cnab+240+-+v10.pdf");
		layout.get("versao").value("80");

		// Cabeçalho
		TagLayout cabecalho = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(cabecalho());
		cabecalho.get(campoBancoNome).value("Banco Santander (Brasil) S.A.");
		cabecalho.get(campoBancoCodigo).value(codigoSantander);

		cabecalho.get("versaoLayoutArquivo").value("080");

		// cabecalhoLote
		TagLayout cabecalhoLote = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote());
		cabecalhoLote.get(campoBancoCodigo).value(codigoSantander);
		cabecalhoLote.get("versaoLayoutLote").value("031");

		// SegmentoA
		TagLayout segmentoA = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoA());
		segmentoA.get(campoBancoCodigo).value(codigoSantander);

		// SegmentoB
		TagLayout segmentoB = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoB());
		segmentoB.get(campoBancoCodigo).value(codigoSantander);

		// RodapeLote
		TagLayout rodapeLote = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(rodapeLote());
		rodapeLote.get(campoBancoCodigo).value(codigoSantander);

		// RodapeArquivo
		TagLayout rodapeArquivo = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA.get(rodape());
		rodapeArquivo.get(campoBancoCodigo).value(codigoSantander);

	}

	private static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
			.clone();

	static {
		// personalizações BB

		String codigoBB = "001";
		String campoBancoNome = "bancoNome";
		String campoBancoCodigo = "bancoCodigo";

		// Layout
		TagLayout layout = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(layout());
		layout.get("nome").value("Layout Padrão BB CNAB240 Pagamento Remessa");
		layout.get("banco").value(codigoBB);
		layout.get("url").withValue("https://www.bb.com.br/docs/pub/emp/mpe/dwn/TitulosRemessa.pdf");
		layout.get("versao").value("40");

		// Cabeçalho
		TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho());
		cabecalho.get(campoBancoNome).value("BANCO DO BRASIL S.A.");
		cabecalho.get(campoBancoCodigo).value(codigoBB);
		cabecalho.get("versaoLayoutArquivo").value("040");

		/*
		 * o banco do brasil utiliza apenas 9 charas para o codigo do convenio.. depois
		 * deve-se colocar '0126' e depois completar com 7 espacos em branco
		 */
		cabecalho.get("convenio").length(9).filler(Fillers.ZERO_LEFT);
		TagLayout convenioCabecalho = cabecalho.get("convenio");

		cabecalho.insertAfter(convenioCabecalho, field("codigoBB2").value("0126").length(4), fbranco().length(7));

		// cabecalhoLote
		TagLayout cabecalhoLote = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote());
		cabecalhoLote.get(campoBancoCodigo).value(codigoBB);
		cabecalhoLote.get("versaoLayoutLote").value("030");
		cabecalhoLote.get("convenio").length(9).filler(Fillers.ZERO_LEFT);

		/*
		 * o banco do brasil utiliza apenas 9 charas para o codigo do convenio.. depois
		 * deve-se colocar '0126' e depois completar com 7 espacos em branco
		 */
		TagLayout convenioCabecalhoLote = cabecalhoLote.get("convenio");
		cabecalhoLote.insertAfter(convenioCabecalhoLote, field("codigoBB2").value("0126").length(4),
				fbranco().length(7));

		// SegmentoA
		TagLayout segmentoA = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoA());
		segmentoA.get(campoBancoCodigo).value(codigoBB);
		segmentoA.get("dataRealEfetivacaoPagto").filler(Fillers.ZERO_LEFT).value("00000000");
		segmentoA.get("valorRealEfetivacaoPagto").filler(Fillers.ZERO_LEFT).value("000000000000000");

		// SegmentoB
		TagLayout segmentoB = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoB());
		segmentoB.get(campoBancoCodigo).value(codigoBB);

		// RodapeLote
		TagLayout rodapeLote = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(rodapeLote());
		rodapeLote.get(campoBancoCodigo).value(codigoBB);
		rodapeLote.get("qtdeMoeda").value("000000000000000000");
		rodapeLote.get("numeroAvisoDeDebito").value("000000").filler(Fillers.ZERO_LEFT);


		// RodapeArquivo
		TagLayout rodapeArquivo = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(rodape());
		rodapeArquivo.get(campoBancoCodigo).value(codigoBB);

	}

	private static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA = _LAYOUT_SICREDI_CNAB240.clone();

	private static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_REMESSA = _LAYOUT_BB_CNAB240.clone();

	private static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA = _LAYOUT_SANTANDER_CNAB240.clone();

	private static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA = _LAYOUT_ITAU_CNAB240.clone();

	private static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA = _LAYOUT_CAIXA_CNAB240_REMESSA.clone();

	private static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA = _LAYOUT_BRADESCO_CNAB400_REMESSA.clone();

	static {
		_LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

		_LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');
	}

	public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA = _LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA = _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_REMESSA = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA = _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA = _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA
			.cloneReadonly();

	public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA = _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA
			.cloneReadonly();

	private static final TagLayout _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO = _LAYOUT_FEBRABAN_CNAB240.clone();

	private static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO = _LAYOUT_SICREDI_CNAB240.clone();

	private static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_RETORNO = _LAYOUT_BB_CNAB240.clone();

	private static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO = _LAYOUT_SANTANDER_CNAB240.clone();

	private static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO = _LAYOUT_ITAU_CNAB240.clone();

	private static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO = _LAYOUT_CAIXA_CNAB240_RETORNO.clone();

	private static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO = _LAYOUT_BRADESCO_CNAB400_RETORNO.clone();

	static {
		_LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

		_LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');
	}

	public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO = _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO = _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_RETORNO = _LAYOUT_BB_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO = _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO = _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO = _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO
			.cloneReadonly();

	public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO = _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO
			.cloneReadonly();

	private static final List<TagLayout> layoutsSuportados;

	static {
		List<TagLayout> layoutsSuportadosTmp = new ArrayList<>();
		/* */
		// TODO: Adicionar teste e layouts homologados
		layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240);
		layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240);
		layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240);
		layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240);
		layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240);
		layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO);
		layoutsSuportadosTmp.add(LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA);
		layoutsSuportadosTmp.add(LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO);

		/* */
		layoutsSuportados = Collections.unmodifiableList(layoutsSuportadosTmp);
	}

	public static TagLayout getLayoutArquivoBancarioRemessaCobranca(String codBanco, String numConvenio, String agencia,
			String conta, String carteira, Boolean registrado) {
		return getLayoutArquivoBancario(CNABServico.COBRANCA_REMESSA, CNAB_240, codBanco, numConvenio, agencia, conta,
				carteira, registrado);
	}

	public static TagLayout getLayoutArquivoBancarioRemessaPagamento(String codBanco, String numConvenio,
			String agencia, String conta, String carteira, Boolean registrado) {
		return getLayoutArquivoBancario(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA, CNAB_240, codBanco, numConvenio,
				agencia, conta, carteira, registrado);
	}

	public static TagLayout getLayoutArquivoBancario(String codBanco) {
		return getLayoutArquivoBancario(null, CNAB_240, codBanco, null, null, null, null, null);
	}

	public static TagLayout getLayoutArquivoBancarioRemessaCobranca(String codBanco) {
		return getLayoutArquivoBancario(CNABServico.COBRANCA_REMESSA, CNAB_240, codBanco, null, null, null, null, null);
	}

	// TODO: Implementar um metodo de busca por layouts, a partir de atributos
	// relevantes
	public static TagLayout getLayoutArquivoBancario(CNABServico servico, CNAB cnab, String codBanco, String convenio,
			String agencia, String conta, String carteira, Boolean registrado) {
		TagLayout ret = null;
		for (TagLayout layout : layoutsSuportados) {
			TagLayout descritor = layout.get("layout");
			if (descritor != null && eq(descritor.getValue("banco"), codBanco) && eq(descritor.getValue("cnab"), cnab)
					&& (servico == null || eq(descritor.getValue("servico"), servico))
					&& (convenio == null || eq(descritor.getValue("convenio"), convenio))
					&& (carteira == null || eq(descritor.getValue("carteira"), carteira))) {
				ret = layout;
				break;
			}
		}
		return ret;
	}

	public static TagLayout getLayoutCNAB240PagamentoRemessa(String codBanco) {


		switch (codBanco) {
		case "001":
			return LayoutsSuportados.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA;
		case "033":
			return LayoutsSuportados.LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA;
		case "237":
			return LayoutsSuportados.LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA;
		case "341":
			return LayoutsSuportados.LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA;
		default:
			return LayoutsSuportados._LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA;
		}
	}

	public static boolean eq(Object value1, Object value2) {
		return value1 == value2 || value1 != null && value1.equals(value2) || value2 != null && value2.equals(value1);
	}
}