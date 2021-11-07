/*
 * Copyright 2021 strike.
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
import static com.github.braully.boleto.TagLayout.TagCreator.banco;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalhoLote;
import static com.github.braully.boleto.TagLayout.TagCreator.cnab;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoJ;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoJ52;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoP;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoQ;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoR;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoT;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoU;
import static com.github.braully.boleto.TagLayout.TagCreator.faceite;
import static com.github.braully.boleto.TagLayout.TagCreator.fagencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fbairro;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoCodigo;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fbranco;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteCnpj;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fcep;
import static com.github.braully.boleto.TagLayout.TagCreator.fcidade;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoAcrescimo;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoBaixa;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoBarras;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoCarteira;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoDesconto;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoMoeda;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoOcorrencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoProtesto;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoRegistro;
import static com.github.braully.boleto.TagLayout.TagCreator.fconta;
import static com.github.braully.boleto.TagLayout.TagCreator.fconvenio;
import static com.github.braully.boleto.TagLayout.TagCreator.fdac;
import static com.github.braully.boleto.TagLayout.TagCreator.fdata;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataAcrescimo;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataDesconto;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataGeracao;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataOcorrencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataPagamento;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataVencimento;
import static com.github.braully.boleto.TagLayout.TagCreator.fendereco;
import static com.github.braully.boleto.TagLayout.TagCreator.fespecieTitulo;
import static com.github.braully.boleto.TagLayout.TagCreator.fidOpcional;
import static com.github.braully.boleto.TagLayout.TagCreator.field;
import static com.github.braully.boleto.TagLayout.TagCreator.flatfile;
import static com.github.braully.boleto.TagLayout.TagCreator.flote;
import static com.github.braully.boleto.TagLayout.TagCreator.fmovimentoCodigo;
import static com.github.braully.boleto.TagLayout.TagCreator.fmovimentoTipo;
import static com.github.braully.boleto.TagLayout.TagCreator.fnossoNumero;
import static com.github.braully.boleto.TagLayout.TagCreator.fnumeroDocumento;
import static com.github.braully.boleto.TagLayout.TagCreator.fnumeroRemessa;
import static com.github.braully.boleto.TagLayout.TagCreator.focorrencias;
import static com.github.braully.boleto.TagLayout.TagCreator.foperacao;
import static com.github.braully.boleto.TagLayout.TagCreator.fquantidadeRegistros;
import static com.github.braully.boleto.TagLayout.TagCreator.frejeicoes;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoCpf;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fsegmento;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialRegistro;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoDocumento;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoEmissaoBoleto;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricao;
import static com.github.braully.boleto.TagLayout.TagCreator.fuf;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalor;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorAbatimento;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorAcrescimo;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorDesconto;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorIOF;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorLiquido;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorOutrasDespesas;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorOutrasReceitas;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorPagamento;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorTarifaCustas;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorTotalRegistros;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;
import static com.github.braully.boleto.TagLayout.TagCreator.nome;
import static com.github.braully.boleto.TagLayout.TagCreator.rodape;
import static com.github.braully.boleto.TagLayout.TagCreator.rodapeLote;
import static com.github.braully.boleto.TagLayout.TagCreator.tag;
import static com.github.braully.boleto.TagLayout.TagCreator.versao;
import java.text.SimpleDateFormat;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author Braully Rocha da Silva
 */
public class LayoutFebraban {
      static final TagLayout _LAYOUT_FEBRABAN_CNAB240 = flatfile(
            layout(nome("Layout Padrão Febraban CNAB240"),
                    cnab(CNAB_240),
                    banco("000"),
                    tag("url").value("https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240"),
                    versao("05")
            ),
            cabecalho(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo(),
                    flote().value("0000"),
                    fcodigoRegistro().value("0"),
                    //Uso Exclusivo FEBRABAN / CNAB9179-AlfaBrancosG004
                    fbranco().length(9),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    fconvenio().length(20),
                    //Agência Mantenedora da Conta 53 57 5-Num*G008
                    //Dígito Verificador da Agência 58 58 1-Alfa*G009
                    fagencia(),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta(), //Conta com DV
                    //Dígito Verificador da Ag/Conta72721-Alfa*G012
                    //Dígito Verificador da Agência / Conta CorrenteCódigo  
                    //adotado  pelo  Banco  responsável  pela  conta  corrente,
                    //para  verificação  da autenticidade do par Código da Agência / Número da Conta Corrente.
                    //Para os Bancos que se utilizam de duas posições para o Dígito Verificador 
                    //do Número da Conta Corrente, preencher este campo com a 2ª posição deste dígito. 
                    fdac(), fcedenteNome().length(30), fbancoNome().length(30),
                    //Uso Exclusivo FEBRABAN / CNAB
                    fbranco().length(10),
                    //Código Remessa / Retorno1431431-NumG015
                    //Código adotado pela FEBRABAN para qualificar o envio ou devolução de arquivo 
                    //entre a Empresa  Cliente e o Banco prestador dos Serviços.
                    //Domínio:'1'  =  Remessa (Cliente -> Banco) '2'  =  Retorno (Banco -> Cliente)
                    fcodigoArquivo().value(1),
                    //Data de Geração do Arquivo1441518-Num
                    fdataGeracao(),
                    //Hora de Geração do Arquivo1521576
                    field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
                    //Número Seqüencial do Arquivo1581636-Num*G018
                    fsequencialArquivo().length(6),
                    field("versaoLayoutArquivo").valLen("103"),
                    //Densidade de gravação (BPI), do arquivo encaminhado. Domínio:1600 BPI ou 6250 BPI
                    field("densidadeArquivo").value(0).length(5).filler(Fillers.ZERO_LEFT),
                    //Para Uso Reservado do Banco17219120-AlfaG021
                    fbranco().length(20),
                    //Para Uso Reservado da Empresa19221120-AlfaG022
                    fbranco().length(20),
                    //Uso Exclusivo FEBRABAN / CNAB21224029-AlfaBrancosG004
                    fbranco().length(29)
            ),
            //Registro Header de Lote: 3.2.2 -Títulos em Cobrança
            cabecalhoLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo(),
                    //Valor default para o primeiro lote, demais devem ser alterados
                    flote().value(1),
                    fcodigoRegistro().value("1"),
                    //Código adotado pela FEBRABAN para identificar a transação que será realizada com os registros detalhe do lote.
                    /* 
                    Domínio:
                        'C' = Lançamento a Crédito
                        'D' = Lançamento a Débito
                        'E' = Extrato para Conciliação
                        'G' = Extrato para Gestão de Caixa
                        'I' = Informações de Títulos Capturados do Próprio Banco
                        'R' = Arquivo Remessa
                        'T' = Arquivo Retorno
                     */
                    foperacao(),
                    //Código adotado pela FEBRABAN para indicar o tipo de serviço / produto (processo) contido no arquivo / lote.
                    /*
                    TODO: Fazer um enum
                     Domínio:
                            '01' = Cobrança
                            '03' = Boleto de Pagamento Eletrônico
                            '04' = Conciliação Bancária
                            '05' = Débitos
                            '06' = Custódia de Cheques
                            '07' = Gestão de Caixa
                            '08' = Consulta/Informação Margem
                            '09' = Averbação da Consignação/Retenção
                            '10' = Pagamento Dividendos
                            ‘11’ = Manutenção da Consignação
                            ‘12’ = Consignação de Parcelas
                            ‘13’ = Glosa da Consignação (INSS)
                            ‘14’ = Consulta de Tributos a pagar
                            '20' = Pagamento Fornecedor
                            ‘22’ = Pagamento de Contas, Tributos e Impostos
                            ‘23’ = Interoperabilidade entre Contas de Instituições de Pagamentos
                            ‘25’ = Compror
                            ‘26’ = Compror Rotativo
                            '29' = Alegação do Pagador
                            '30' = Pagamento Salários
                            ‘32’ = Pagamento de honorários
                            ‘33’ = Pagamento de bolsa auxílio
                            ‘34’ = Pagamento de prebenda (remuneração a padres e sacerdotes)
                            '40' = Vendor
                            '41' = Vendor a Termo
                            '50' = Pagamento Sinistros Segurados
                            '60' = Pagamento Despesas Viajante em Trânsito
                            '70' = Pagamento Autorizado
                            '75' = Pagamento Credenciados
                            ‘77’ = Pagamento de Remuneração
                            '80' = Pagamento Representantes / Vendedores Autorizados
                            '90' = Pagamento Benefícios
                            '98' = Pagamentos Diversos                     
                     */
                    fservico(),
                    //Uso Exclusivo FEBRABAN/CNAB
                    fbranco().length(2),
                    //Nº da Versão do Layout do Lote
                    field("versaoLayoutLote").value("060").length(3),
                    //Uso Exclusivo da FEBRABAN/CNAB 17 17 1 - Alfa Brancos G004
                    fbranco().length(1),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(15),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    fconvenio().length(20),
                    //Agência Mantenedora da Conta 53 57 5-Num*G008
                    //Dígito Verificador da Agência 58 58 1-Alfa*G009
                    fagencia(),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta(), //Conta com DV
                    //Dígito Verificador da Ag/Conta72721-Alfa*G012
                    //Dígito Verificador da Agência / Conta CorrenteCódigo  
                    //adotado  pelo  Banco  responsável  pela  conta  corrente,
                    //para  verificação  da autenticidade do par Código da Agência / Número da Conta Corrente.
                    //Para os Bancos que se utilizam de duas posições para o Dígito Verificador 
                    //do Número da Conta Corrente, preencher este campo com a 2ª posição deste dígito. 
                    fdac(), //Conta com DV
                    fcedenteNome().length(30),
                    //Texto referente a mensagens que serão impressas nos documentos e/ou avisos a serem emitidos.
                    //Informação 1: Genérica. Quando informada constará em todos os avisos e/ou
                    //documentos originados dos detalhes desse lote. Informada no Header do Lote.
                    field("mensagem1").length(40).filler(Fillers.WHITE_SPACE_LEFT),
                    field("mensagem2").length(40).filler(Fillers.WHITE_SPACE_LEFT),
                    fnumeroRemessa().length(8).value(0),
                    fdataGeracao().length(8),
                    field("dataCredito").length(8).value("").filler(Fillers.WHITE_SPACE_LEFT),
                    //Uso Exclusivo da FEBRABAN/CNAB
                    fbranco().length(33)
            //Código das Ocorrências p/ Retorno 
            //focorrencias()
            ),
            //Registro Detalhe - Segmento J (Obrigatório - Remessa / Retorno)
            detalheSegmentoJ(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo(),
                    //Número seqüencial para identificar univocamente um lote de serviço. Criado e
                    //controlado pelo responsável pela geração magnética dos dados contidos no arquivo.
                    //Preencher com '0001' para o primeiro lote do arquivo. Para os demais: número do lote
                    //anterior acrescido de 1. O número não poderá ser repetido dentro do arquivo.
                    //Se registro for Header do Arquivo preencher com '0000'
                    //Se registro for Trailer do Arquivo preencher com '9999'
                    flote().value(1),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    //Código adotado pela FEBRABAN para identificar o segmento do registro.
                    fsegmento().id(true).value("J"),
                    //Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviada no arquivo.
                    fmovimentoTipo().value(0),
                    //Código da Instrução para Movimento
                    //Código adotado pela FEBRABAN, para identificar a ação a ser realizada com o
                    //lançamento enviado no arquivo. 
                    fmovimentoCodigo().value("01"),//Padrão entrada de titulo
                    //Código adotado pela FEBRABAN para identificar o Título.
                    //Especificações do Código de Barras do Boleto de Pagamentode Cobrança -Ficha de Compensação
                    fcodigoBarras().length(44),
                    fsacadoNome().length(30),
                    fdataVencimento(),
                    fvalor().length(15),
                    //Valor do Desconto + Abatimento
                    //Valor de desconto (bonificação) sobre o valor nominal do documento, somado ao Valor
                    //do abatimento concedido pelo Beneficiário, expresso em moeda corrente.
                    fvalorDesconto().value(0).length(15),
                    //Valor da Mora + Multa
                    //Valor do juros de mora somado ao Valor da multa, expresso em moeda corrente
                    fvalorAcrescimo().value(0).length(15),
                    fdataPagamento(), fvalorPagamento().length(15),
                    //Número de unidades do tipo de moeda identificada para cálculo do valor do documento. G041
                    field("qtdeMoeda").filler(Fillers.ZERO_LEFT).value(1).length(15),
                    //Referência Pagador Nº do Docto Atribuído pela Empresa 183 202 20 - Alfa G064
                    fnumeroDocumento().length(20),
                    //Nosso Número Nº do Docto Atribuído pelo Banco 203 222 20 - Alfa *G043
                    //Número do Documento Atribuído pelo Banco (Nosso Número)
                    //Número atribuído pelo Banco para identificar o lançamento, que será utilizado nas manutenções do mesmo. 
                    fnossoNumero().length(20),
                    //G065 Código da Moeda
                    //Código adotado pela FEBRABAN para identificar a moeda referenciada no Título
                    fcodigoMoeda(),
                    //20.3J CNAB Uso Exclusivo FEBRABAN/CNAB 225 230 6 - Alfa Brancos G004
                    fbranco().length(6),
                    //Código das Ocorrências para Retorno/Remessa
                    //Código adotado pela FEBRABAN para identificar as ocorrências detectadas no
                    //processamento.
                    //Pode-se informar até 5 ocorrências simultaneamente, cada uma delas codificada com
                    //dois dígitos, conforme relação abaixo.
                    focorrencias()
            ),
            //Registro Detalhe - Segmento J-52 (Obrigatório – Remessa / Retorno)
            detalheSegmentoJ52(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    //Número seqüencial para identificar univocamente um lote de serviço. Criado e
                    //controlado pelo responsável pela geração magnética dos dados contidos no arquivo.
                    //Preencher com '0001' para o primeiro lote do arquivo. Para os demais: número do lote
                    //anterior acrescido de 1. O número não poderá ser repetido dentro do arquivo.
                    //Se registro for Header do Arquivo preencher com '0000'
                    //Se registro for Trailer do Arquivo preencher com '9999'
                    fbancoCodigo(), flote().value(1),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    //Código adotado pela FEBRABAN para identificar o segmento do registro.
                    fsegmento().id(true).value("J"),
                    //06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
                    fbranco().length(1),
                    //C004: Código de Movimento Remessa 
                    //Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviado nos
                    //registros do arquivo de remessa.
                    //Cada Banco definirá os campos a serem alterados para o código de movimento '31'
                    fmovimentoCodigo().value("01"),
                    //08.4.J52 Código Reg. Opcional Identificação Registro Opcional 18 19 2 - Num “52” G067
                    fidOpcional().id(true).value("52"),
                    //DADOS DO PAGADOR
                    //Tipo de Inscrição: '0'  =  Isento / Não Informado
                    //                   '1'  =  CPF
                    //                   '2'  =  CGC / CNPJ
                    //                   '3'  =  PIS / PASEP
                    //                   '9'  =   Outros
                    field("tipoInscricaoSacado").valLen("1"),
                    fsacadoCpf().length(15),
                    fsacadoNome().length(40),
                    //DADOS DO BENEFICIARIO
                    field("tipoInscricaoCedente").valLen("2"),
                    fcedenteCnpj().length(15),
                    fcedenteNome().length(40),
                    //DADOS DO PAGADORR
                    //Pagadorr - Dados sobre o Beneficiário responsável pela emissão do título original
                    field("tipoInscricaoPagadorr").valLen("2"),
                    field("pagadorrInscricao").length(15).filler(Fillers.ZERO_LEFT),
                    field("pagadorr").length(40).filler(Fillers.WHITE_SPACE_LEFT),
                    //Uso Exclusivo FEBRABAN/CNAB
                    fbranco().length(53)
            ),
            //Layout Padrão Febraban 240 posições V10.5
            //http://www.febraban.org.br
            //Registro Detalhe -Segmento U (Obrigatório -Retorno)
            detalheSegmentoU(
                    fbancoCodigo(), flote(),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    //Código adotado pela FEBRABAN para identificar o segmento do registro.
                    fsegmento().id(true).value("U"),
                    //06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
                    fbranco().length(1),
                    //C004: Código de Movimento Remessa 
                    //Código adotado pela FEBRABAN, para identificar o tipo de movimentação enviado nos
                    //registros do arquivo de remessa.
                    //Cada Banco definirá os campos a serem alterados para o código de movimento '31'
                    fmovimentoCodigo(),
                    //Dadosdo Título
                    //Valor da Mora + Multa
                    //Valor do juros de mora somado ao Valor da multa, expresso em moeda corrente
                    fvalorAcrescimo().length(15),
                    fvalorDesconto().length(15),
                    fvalorAbatimento().length(15),
                    fvalorIOF().length(15),
                    fvalorPagamento().length(15),
                    fvalorLiquido().length(15),
                    fvalorOutrasDespesas(),
                    fvalorOutrasReceitas(),
                    fdataOcorrencia(),
                    fdata().nome("dataOutrasReceitas"),
                    //Ocorr. do Pagador
                    fcodigoOcorrencia().length(4),
                    fdata().nome("dataOcorrenciaPagador"),
                    fvalor().nome("valorOcorrenciaPagador"),
                    field("complementoOcorrencia").length(30),
                    field("codBancoCorrespondenteCompens").length(3),
                    field("nossoNumeroBancoCorrespondenteCompens").length(20),
                    field("CNAB").length(7)
            ),
            //Registro Detalhe -Segmento T (Obrigatório -Retorno)
            detalheSegmentoT(
                    fbancoCodigo(), flote(),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    //Código adotado pela FEBRABAN para identificar o segmento do registro.
                    fsegmento().id(true).value("T"),
                    //06.4.J52 CNAB Uso Exclusivo FEBRABAN/CNAB 15 15 1 - Alfa Brancos G004
                    fbranco().length(1),
                    fmovimentoCodigo(),
                    //C/C
                    fagencia(),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta(), //Conta com DV
                    //Dígito Verificador da Ag/Conta72721-Alfa*G012
                    //Dígito Verificador da Agência / Conta CorrenteCódigo  
                    //adotado  pelo  Banco  responsável  pela  conta  corrente,
                    //para  verificação  da autenticidade do par Código da Agência / Número da Conta Corrente.
                    //Para os Bancos que se utilizam de duas posições para o Dígito Verificador 
                    //do Número da Conta Corrente, preencher este campo com a 2ª posição deste dígito. 
                    fdac(),
                    fnossoNumero().length(20),
                    fcodigoCarteira(),
                    fnumeroDocumento().length(15),
                    fdataVencimento(),
                    fvalor().length(15),
                    //*C045
                    fbancoCodigo().nome("bancoCodigoRecebedor"),
                    //Agencia (5) + DV (1)
                    fagencia().nome("agenciaBancoRecebedor"),
                    field("usoEmpresa").length(25),
                    fcodigoMoeda(),
                    ftipoInscricao().value(1),//Default 1 CPF
                    fsacadoCpf().length(15),
                    fsacadoNome().length(40),
                    field("numeroContrato").length(10),
                    fvalorTarifaCustas().length(15),
                    frejeicoes().length(10),
                    field("CNAB").length(17)
            ),
            //Registro Detalhe -Segmento P (Obrigatório -Remessa)
            detalheSegmentoP(
                    fbancoCodigo(),
                    flote().value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("P"),
                    fbranco().length(1),
                    //C004
                    fmovimentoCodigo().value("01"),//Default '01' = Entrada de Títulos
                    fagencia(),
                    fconta(), //Conta com DV
                    fdac(),
                    fnossoNumero().length(20),
                    fcodigoCarteira().value(1),//Default '1' = Cobrança Simples
                    //1'  =  Com Cadastramento (Cobrança Registrada)
                    //'2'  =  Sem Cadastramento (Cobrança sem Registro) 
                    //Obs.: Destina-se somente para emissão de Boleto de Pagamentopelo banco
                    //'3'  =  Com Cadastramento / Recusa do Débito Autom
                    field("formaCadastroTitulo").length(1).value(1),
                    ftipoDocumento(),
                    ftipoEmissaoBoleto(),
                    //C010 Identificação da Distribuição
                    //Código adotado pela FEBRABAN para identificar o responsável pela distribuição do Boleto de Pagamento.
                    // Domínio:
                    //        '1' = Banco Distribui
                    //        '2' = Cliente Distribui
                    //        ‘3’ = Banco envia e-mail
                    //        ‘4’ = Banco envia SMS
                    field("formaDistribuicaoTitulo").length(1).value(2),
                    fnumeroDocumento().length(15),
                    fdataVencimento(),
                    fvalor(),
                    field("agenciaCobradora")
                            .length(6).filler(Fillers.ZERO_LEFT)
                            .value(0),
                    fespecieTitulo(),
                    faceite(),
                    fdataGeracao(),
                    fcodigoAcrescimo(),
                    //Data indicativa do início da cobrança do Juros de Mora de um título de cobrança.
                    //A data informada deverá ser maior que a Data de Vencimento do título de cobrança
                    //Caso seja inválida ou não informada será assumida a data do vencimento.
                    fdataAcrescimo().value(0),
                    //Valor ou porcentagem sobre o valor do título a ser cobrada de juros de mora.
                    fvalorAcrescimo(),
                    fcodigoDesconto(),
                    fdataDesconto().value(0),
                    //Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
                    fvalorDesconto(),
                    fvalorIOF(),
                    fvalorAbatimento(),
                    //Identificação do Título na Empresa
                    fnumeroDocumento().nome("numeroDocumentoEmpresa")
                            .length(25).filler(Fillers.ZERO_LEFT)
                            .value(0),
                    fcodigoProtesto(),
                    field("numeroDiasProtesto").length(2).value("00"),
                    fcodigoBaixa().value("0"),
                    field("numeroDiasBaixa").length(3).value("000"),
                    fcodigoMoeda(),
                    field("numeroContrato").length(10)
                            .filler(Fillers.WHITE_SPACE_LEFT)
                            .type(Number.class).value(0),
                    fbranco().length(1)
            ),
            //Registro Detalhe -Segmento Q (Obrigatório -Remessa)
            detalheSegmentoQ(
                    fbancoCodigo(),
                    flote().value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("Q"),
                    fbranco().length(1),
                    //*C004
                    fmovimentoCodigo().value("01"),//Default '01' = Entrada de Títulos
                    //Tipo de Inscrição 
                    ftipoInscricao().value(1),
                    fsacadoCpf().length(15),
                    fsacadoNome().length(40),
                    fendereco().length(40).value(""),
                    fbairro().length(15).value(""),
                    fcep().length(8).value(0),
                    fcidade().truncate(true).length(15).value(""),
                    fuf().length(2).value(""),
                    field("tipoInscricaoAvalista").length(1).value(0),
                    field("numeroInscricaoAvalista")
                            .length(15).filler(Fillers.ZERO_LEFT)
                            .value(0),
                    field("nomeAvalista").filler(Fillers.WHITE_SPACE_RIGHT)
                            .length(40).value(""),
                    fbancoCodigo()
                            .nome("bancoCodigoCorrespondente")
                            .value(0),
                    field("nossoNumeroBancoCorrespondente").filler(Fillers.ZERO_LEFT)
                            .length(20).value(0),
                    fbranco().length(8)
            ),
            //Registro Detalhe - Segmento R (Opcional - Remessa)
            detalheSegmentoR(
                    fbancoCodigo(),
                    flote().value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("R"),
                    fbranco().length(1),
                    //*C004
                    fmovimentoCodigo().value("01"),//Default '01' = Entrada de Títulos
                    //Desconto 2
                    fcodigoDesconto(),
                    fdataDesconto(),
                    //Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
                    fvalorDesconto(),
                    //Desconto 3
                    fcodigoDesconto().nome("codigoDescontoExtra"),
                    fdata().nome("dataDescontoExtra"),
                    //Valor ou percentual de desconto a ser concedido sobre o título de cobrança.
                    fvalorDesconto().nome("valorDescontoExtra"),
                    //Multa/Juros
                    /**
                     * G073: Código adotado pela FEBRABAN para identificação do
                     * critério de pagamento de pena pecuniária, a ser aplicada
                     * pelo atraso do pagamento do Título. Domínio:'1' = Valor
                     * Fixo'2' = Percentual
                     */
                    fcodigoAcrescimo().value(0),
                    /**
                     * Caso não tenha multa, informar 'zeros'. Sistema aceita a
                     * mesma data do vencimento, ou dia seguinte.
                     */
                    fdataAcrescimo(),
                    /**
                     * Caso não tenha multa, informar 'zeros'. Sistema aceita a
                     * mesma data do vencimento, ou dia seguinte.
                     */
                    fvalorAcrescimo(),
                    /**
                     * Informação ao PagadorTexto de observações destinado ao
                     * envio de informações do Beneficiário ao Pagador.Este
                     * campo só poderá ser utilizado, caso haja troca de
                     * arquivos magnéticos entre o Banco e o Pagador.
                     */
                    field("infoPagador").length(10).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
                    field("mensagem3").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
                    field("mensagem4").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
                    fbranco().length(20),
                    //C038: Código da Ocorrência do Pagador
                    //Código adotado pela FEBRABAN para identificar a ocorrência do Pagador (Descrição A001) 
                    // a(s) qual(is) o Beneficiário não concorda.
                    //Somente será utilizado para o Código de Movimento '30' (Descrição C004).
                    fcodigoOcorrencia().nome("codigoOcorrenciaPagador").length(8)
                            .filler(Fillers.ZERO_LEFT).value(0),
                    //Dados para debito
                    fbancoCodigo().nome("bancoCodigoDebito").value(0),
                    fagencia().nome("agenciaDebito").value(0),
                    fconta().nome("contaDebito").value(0),
                    fdac().nome("dacDebito").value(0),
                    //C0039: Aviso para Débito Automático Código adotado pela FEBRABAN para 
                    //identificação da emissão do aviso de débito automático em conta corrente.
                    //Domínio: '01' = Emite o Aviso com o Endereço Informado no Arquivo Remessa
                    //'02' = Não Emite Aviso ao Pagador
                    //'03' = Emite Aviso com o Endereço Constante do Cadastro do Banco
                    //Para códigos diferentes de '01', '02' e '03' seguir a regra do '03'.
                    field("avisoDebitoAutomatico").length(1).value("0"),
                    fbranco().length(9)
            ),
            rodapeLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo(),
                    flote().value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("5"),
                    //04.5 CNAB Uso Exclusivo FEBRABAN/CNAB 9 17 9 - Alfa Brancos G004
                    fbranco().length(9),
                    //Quantidade de Registros do Lote 18 23 6 - Num *G057
                    fquantidadeRegistros().length(6).value(0), fvalorTotalRegistros().length(18).value(0),
                    //Qtde de Moeda Somatória de Quantidade de Moedas 42 59 13 5 Num G058
                    //G058 Somatória de Quantidade de Moedas
                    //Valor obtido pela somatória das quantidades de moeda dos registros de detalhe
                    //(Registro = '3' / Código de Segmento = {'A' / 'J'}).
                    field("qtedMoedas").length(18).filler(Fillers.ZERO_LEFT).value(1),
                    //08.5 Número Aviso Débito Número Aviso Débito 60 65 6 - Num G066
                    //Número do Aviso de Débito
                    //Número atribuído pelo Banco para identificar um Débito efetuado na Conta Corrente a
                    //partir do(s) pagamento(s) efetivado(s), visando facilitar a Conciliação Bancária.
                    field("numAvisoDebito").length(6).filler(Fillers.ZERO_LEFT),
                    fbranco().length(165),
                    //Código das Ocorrências para Retorno/Remessa G0059
                    focorrencias()
            ),
            rodape(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo(), flote().value("9999"), fcodigoRegistro().value("9"),
                    //Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
                    fbranco().length(9),
                    //Qtde. de LotesQuantidade de Lotes do Arquivo18236-NumG049
                    field("qtdeLotes").padding(Fillers.ZERO_LEFT).length(6).value(1),
                    //Qtde. de RegistrosQuantidade de Registros do Arquivo24296-NumG0
                    fquantidadeRegistros().length(6).value(0),
                    //Qtde. de Contas Concil.Qtde de Contas p/ Conc. (Lotes)30356-Num*G037
                    /**
                     * Número indicativo de lotes de Conciliação Bancária
                     * enviados no arquivo. Somatória dos registros de tipo 1 e
                     * Tipo de Operação = 'E'. Campo específico para o serviço
                     * de Conciliação Bancária
                     */
                    field("qtedContas").value(0).filler(Fillers.ZERO_LEFT).length(6),
                    //Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
                    fbranco().length(205)
            )
    );
}
