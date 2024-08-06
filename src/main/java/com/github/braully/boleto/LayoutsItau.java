/*
 * Copyright 2023 strike.
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

import static com.github.braully.boleto.TagLayout.TagCreator.*;
import static com.github.braully.boleto.CNAB.CNAB_240;
import static com.github.braully.boleto.CNAB.CNAB_400;
import java.text.SimpleDateFormat;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author strike
 */
public class LayoutsItau {

    public static final TagLayout _LAYOUT_ITAU_CNAB400_REMESSA = flatfile(
            layout(nome("Layout ITAU CNAB400 Remessa"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("341"),
                    tag("url"),
                    //     .withValue("https://..."),

                    versao(
                            "15")
            ),
            /* 
0	LINHA 1 - Header de Arquivo				
0	POSIÇÃO	NOME DO CAMPO	CONTEÚDO OBSERVADO	ERRO/ALERTA  Erros de estrutura: Impedem o processamento  Erros: impedem o processamento  Alertas: recomendação de ajuste  (não impede o processamento)	SOLUÇÃO/RECOMENDAÇÃO
12	47 a 76	12.0 - Nome do Beneficiário	[EMP BRASILEIRA]		
15	101 a 107	15.0 - Número da remessa	[0000037]		
16	108 a 129	16.0 - Brancos	[]		
17	130 a 136	17.0 - Número do Convênio	[3595376]		
18	137 a 394	18.0 - Brancos	[]		
19	395 a 400	19.0 - Sequencial do Registro	[000001]	
             */
            cabecalho(
                    fcodigoRegistro().length(1).value("0"),
                    fcodigoArquivo().length(1).value("1"),
                    fliteralRemessa().length(7).value("REMESSA"),
                    fservico().length(2).value("01"),
                    fliteralServico().length(15).value("COBRANCA"),
                    fagencia().length(4),
                    fzero().length(2),
                    fconta().length(5),
                    //     fconvenio().length(6),
                    fdac(),
                    fbranco().length(8),
                    fcedenteNome().length(30),
                    fbancoCodigo().length(3).value("341"),
                    fbancoNome().length(15).value("BANCO ITAUSA"),
                    fdataGeracaoCurta(),
                    fbranco().length(294),
                    //                    fsequencialArquivo().length(6),
                    fsequencialRegistro().val("1").length(6)
            ),
            detalhe(
                    fcodigoRegistro().length(1).value("1"),
                    //     fzero().length(16),
                    ftipoInscricaoCedente().value("02").length(2),
                    fcedenteCnpj(),
                    fagencia().length(4),
                    fzero().length(2),
                    fconta().length(5),
                    fdac(),
                    fbranco().length(4),
                    //                    fconvenio().length(7),
                    //                    instrução : Deve ser preenchido na remessa somente quando utilizados, na posição 109-110, os códigos de
                    //ocorrência 35 – Cancelamento de Instrução e 38 – Beneficiário não concorda com alegação do
                    //pagador. Para os demais códigos de ocorrência este campo deverá ser preenchido com zeros.
                    //Obs.: No arquivo retorno será informado o mesmo código da instrução cancelada, e para o
                    //cancelamento de alegação de pagador não há retorno da informação.
                    field("instrucao").length(4).value("0000"),
                    fnumeroDocumento().length(25),
                    fnossoNumero().length(8),
                    fqtdeMoeda().length(13).value(0),
                    //Codigo da carteira, conferir isso
                    fcarteira().length(3),
                    field("usoItau").length(21).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcodigoCarteira().value("E"),
                    fcodigoOcorrencia().length(2).value("01"),
                    fnumeroDocumento().length(10),
                    fdataVencimentoCurta(),
                    fvalor().length(13).filler(Fillers.ZERO_LEFT),
                    fbancoCodigo().length(3).value("341"),
                    field("agenciaCobradora").length(5).value("00000"),
                    //                    fbranco().length(1),
                    fespecieTitulo().length(2).value("01"),
                    faceite().valLen("N"),
                    // 51	151 a 156	31.7 - Date de Emissão DDMMAA	[110823]		
                    // 52	157 a 158	32.7 - Instrução Codificada	[00]		
                    // 53	159 a 160	33.7 - Instrução Codificada	[07]		
                    // 54	161 a 173	34.7 - Juros de Mora por dia de atraso	[0000000000000]		
                    // 55	174 a 179	35.7 - Date de Limite para Concessão de Desconto	[000000]		
                    // 56	180 a 192	36.7 - Valor do Desconto	[0000000000000]		
                    // 57	193 a 205	37.7 - Valor do IOF	[0000000000000]		
                    // 58	206 a 218	38.7 - Valor do Abatimento	[0000000000000]		
                    fdataGeracao().length(6).format(new SimpleDateFormat("ddMMyy")),
                    //05 - Receber conforme instruções no próprio título
                    field("instrucao1").length(2).value("05"),
                    //IMPORTANTE: - 07 - Não protestar. Consultar melhor manual
                    field("instrucao2").length(2).value("00"),
                    field("jurosDia").length(13).filler(Fillers.ZERO_LEFT).value(0),
                    //                    field("moraDiaria").length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fdataDesconto().length(6).format(new SimpleDateFormat("ddMMyy")).value(0),
                    fvalorDesconto().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorIOF().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorAbatimento().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    // 59	219 a 220	39.7 - Indicador de 01-CPF / 02-CNPJ	[02]		
                    // 60	221 a 234	40.7 - Número do CNPJ do Pagador	[]		
                    // 61	235 a 271	41.7 - Nome do Pagador	[]		
                    // 62	272 a 274	42.7 - Complemento do Registro	[]		
                    // 63	275 a 314	43.7 - Endereço do Pagador	[AV. PATRIA2058 1351 1351                ]		
                    // 64	315 a 326	44.7 - Bairro do Pagador	[SOMMER      ]		
                    // 65	327 a 334	45.7 - CEP do Pagador	[99500000]		
                    // 66	335 a 349	46.7 - Cidade do Pagador	[CARAZINHO      ]		
                    // 67	350 a 351	47.7 - UF do Pagador	[RS]		
                    // 68	352 a 391	48.7 - Observações/Mensagem ou Sacador/Avalista	[                                        ]		
                    // 69	392 a 393	49.7 - Número de dias para Protesto ou Negativação	[00]		
                    // 70	394 a 394	50.7 - Indicador Recebimento Parcial	[ ]		
                    // 71	395 a 400	51.7 - Sequencial do Registro	[000002]		
                    ftipoInscricao().length(2).value("02"),
                    fsacadoCpf().length(14).filler(Fillers.ZERO_LEFT),
                    //PAREI AQUI

                    fsacadoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbranco().length(10),
                    //                    fcomplemento().length(3).value("   ").filler(Fillers.WHITE_SPACE_RIGHT),
                    fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbairro().length(12).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcep().length(8).filler(Fillers.ZERO_RIGHT),
                    fcidade().length(15).filler(Fillers.WHITE_SPACE_RIGHT),
                    fuf().filler(Fillers.WHITE_SPACE_RIGHT).length(2),
                    fcedenteNome().length(30),
                    fbranco().length(4),
                    fdataCurta("dataMora"),
                    field("diasParaProtesto").length(2).value("00"),
                    field("indicadorRecebimentoParcial").length(1).value(" "),
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            ),
            rodape(
                    fcodigoRegistro().length(1).value("9"),
                    fbranco().length(393),
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            )
    );

    private static final TagLayout _LAYOUT_ITAU_CNAB240 = flatfile(
            layout(nome("Layout Padrão Febraban CNAB240"),
                    cnab(CNAB_240),
                    banco("000"),
                    servico(null),
                    tag("url").value("https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240"),
                    versao("05")
            ),
            cabecalho(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
                    flote().valLen("0000"),
                    fcodigoRegistro().value("0"),
                    //Uso Exclusivo FEBRABAN / CNAB9179-AlfaBrancosG004
                    fbranco().length(9),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    //                    fconvenio().length(20),
                    fbranco().length(20),
                    //Agência Mantenedora da Conta 53 57 5-Num*G008
                    //Dígito Verificador da Agência 58 58 1-Alfa*G009
                    fzero().length(1),
                    fagencia().length(4),
                    fbranco().length(1),
                    fzero().length(7),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta().length(5), //Conta com DV
                    fbranco().length(1),
                    //Dígito Verificador da Ag/Conta72721-Alfa*G012
                    //Dígito Verificador da Agência / Conta CorrenteCódigo  
                    //adotado  pelo  Banco  responsável  pela  conta  corrente,
                    //para  verificação  da autenticidade do par Código da Agência / Número da Conta Corrente.
                    //Para os Bancos que se utilizam de duas posições para o Dígito Verificador 
                    //do Número da Conta Corrente, preencher este campo com a 2ª posição deste dígito. 
                    fdac(),
                    fcedenteNome().length(30),
                    fbancoNome().value("BANCO ITAU SA").length(30),
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
                    field("versaoLayoutArquivo").valLen("040"),
                    //Densidade de gravação (BPI), do arquivo encaminhado. Domínio:1600 BPI ou 6250 BPI
                    fzero().length(5),
                    //Para Uso Reservado do Banco17219120-AlfaG021
                    fbranco("reservadoEmpresa").length(54),
                    //Para Uso Reservado da Empresa19221120-AlfaG022
                    fzero().length(3),
                    //Uso Exclusivo FEBRABAN / CNAB21224029-AlfaBrancosG004
                    fbranco("reservadoFebraban").length(12)
            ),
            //Registro Detalhe -Segmento P (Obrigatório -Remessa)
            detalheSegmentoP(
                    fbancoCodigo().value("341"),
                    flote().value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("P"),
                    fbranco().length(1),
                    //C004
                    fmovimentoCodigo().value("01"),//Default '01' = Entrada de Títulos
                    fzero().length(1),
                    fagencia().length(4),
                    fbranco().length(1),
                    fzero().length(7),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta().length(5), //Conta com DV
                    fbranco().length(1),
                    fdac(),
                    fcarteira().length(3),
                    fnossoNumero().length(9),//nosso numero com dv
                    fbranco().length(8),
                    fzero().length(5),
                    fnumeroDocumento().length(10),
                    fbranco().length(5),
                    fdataVencimento(),
                    fvalor(),
                    field("agenciaCobradora")
                            .length(6).filler(Fillers.ZERO_LEFT)
                            .value(0),
                    fespecieTitulo(),
                    faceite(),
                    fdataGeracao(),
                    fzero().length(1),
                    //                    fcodigoAcrescimo(),
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
                            .type(Number.class
                            ).value(0),
                    fbranco().length(1)
            ),
            //Registro Detalhe -Segmento Q (Obrigatório -Remessa)
            detalheSegmentoQ(
                    fbancoCodigo().value("341"),
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
            //Registro Header de Lote: 3.2.2 -Títulos em Cobrança
            cabecalhoLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
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
                    foperacao().value("C"),
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
                    fservico().value("20"),
                    //Uso Exclusivo FEBRABAN/CNAB
                    fforma().length(2).value("45"),
                    //Nº da Versão do Layout do Lote
                    field("versaoLayoutLote").value("030").length(3),
                    //Uso Exclusivo da FEBRABAN/CNAB 17 17 1 - Alfa Brancos G004
                    fbranco().length(1),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(15),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    //                    fconvenio().length(20),
                    fbranco().length(20),
                    //Agência Mantenedora da Conta 53 57 5-Num*G008
                    //Dígito Verificador da Agência 58 58 1-Alfa*G009
                    fzero().length(1),
                    fagencia().length(4),
                    fbranco().length(1),
                    fzero().length(7),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta().length(5), //Conta com DV
                    fbranco().length(1),
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
                    fdataCredito().value("").filler(Fillers.WHITE_SPACE_LEFT),
                    //Uso Exclusivo da FEBRABAN/CNAB
                    fbranco().length(33)
            //Código das Ocorrências p/ Retorno 
            //focorrencias()
            ),
            rodapeLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
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
                    fbancoCodigo().value("341"), flote().value("9999"), fcodigoRegistro().value("9"),
                    //Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
                    fbranco().length(9),
                    //Qtde. de LotesQuantidade de Lotes do Arquivo18236-NumG049
                    field("totalLotes").padding(Fillers.ZERO_LEFT).length(6).value(1),
                    //Qtde. de RegistrosQuantidade de Registros do Arquivo24296-NumG0
                    fquantidadeRegistros().length(6).value(0),
                    //Qtde. de Contas Concil.Qtde de Contas p/ Conc. (Lotes)30356-Num*G037
                    /**
                     * Número indicativo de lotes de Conciliação Bancária
                     * enviados no arquivo. Somatória dos registros de tipo 1 e
                     * Tipo de Operação = 'E'. Campo específico para o serviço
                     * de Conciliação Bancária
                     */
                    //                    field("qtedContas").value(0).filler(Fillers.ZERO_LEFT).length(6),
                    //Uso Exclusivo FEBRABAN/CNAB9179-AlfaBrancosG004
                    fbranco().length(211)
            )
    );

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240.clone();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240.clone();

    static {
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).withValue('2');

        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);
        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).withValue('1');

    }

    public static final TagLayout LAYOUT_ITAU_CNAB240
            = _LAYOUT_ITAU_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA = _LAYOUT_ITAU_CNAB400_REMESSA
            .cloneReadonly();

    public static final TagLayout _LAYOUT_ITAU_CNAB400_RETORNO = flatfile(
            layout(nome("Layout ITAU CNAB400 Retorno"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_RETORNO),
                    banco("341"),
                    tag("url"),
                    //     .withValue("https://..."),

                    versao(
                            "15")
            ),
            cabecalho(
                    fcodigoRegistro().length(1).value("0"),
                    fcodigoArquivo().length(1).value("2"),
                    fliteralRemessa().length(7).value("RETORNO"),
                    fservico().length(2).value("01"),
                    fliteralServico().length(15).value("COBRANCA"),
                    fagencia().length(4),
                    fzero().length(2),
                    fconta().length(5),
                    //     fconvenio().length(6),
                    fdac(),
                    fbranco().length(8),
                    fcedenteNome().length(30),
                    fbancoCodigo().length(3).value("341"),
                    fbancoNome().length(15).value("BANCO ITAUSA"),
                    fdataGeracaoCurta(),
                    field("densidadeArquivo").length(8),
                    fsequencialArquivo().length(5),
                    fdataCredito().length(6).format(new SimpleDateFormat("ddMMyy")),
                    fbranco().length(275),
                    //                    fsequencialArquivo().length(6),
                    fsequencialRegistro().val("1").length(6)
            ),
            detalhe(
                    fcodigoRegistro().length(1).value("1"),
                    //     fzero().length(16),
                    ftipoInscricaoCedente().value("02").length(2),
                    fcedenteCnpj(),
                    fagencia().length(4),
                    fzero().length(2),
                    fconta().length(5),
                    fdac(),
                    fbranco().length(8),
                    fnumeroDocumento().length(25),
                    fnossoNumero().length(8),
                    fbranco().length(12),
                    //Codigo da carteira, conferir isso
                    fcarteira().length(3),
                    fnossoNumero().length(8),
                    field("dacNossoNumero").length(1),
                    field("usoItau").length(13).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcodigoCarteira().value("E"),
                    fcodigoOcorrencia().length(2).value("01"),
                    //                    field("comandoRetorno").length(2).value("01"),                    
                    fdataOcorrencia().format(new SimpleDateFormat("ddMMyy")).length(6),
                    fnumeroDocumento().length(10),
                    field("confirmacaoNossoNumero").length(8),
                    fbranco().length(12),
                    fdataVencimentoCurta(),
                    fvalor().length(13).filler(Fillers.ZERO_LEFT),
                    fbancoCodigo().length(3).value("341"),
                    field("agenciaCobradora").length(5).value("00000"),
                    //                    fbranco().length(1),
                    fespecieTitulo().length(2).value("01"),
                    fvalorTarifaCustas().length(13),
                    fbranco().length(26),
                    fvalorIOF().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorAbatimento().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorDesconto().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorLiquido().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorAcrescimo().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorOutrasReceitas().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    field("indicadorDDA").length(1),
                    fbranco().length(2),
                    fdataCredito().format(new SimpleDateFormat("ddMMyy")).length(6),
                    field("codInstrucaoCancelada").length(4),
                    fbranco().length(6),
                    fzero().length(13),
                    fsacadoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbranco().length(23),
                    focorrencias().length(8),
                    fbranco().length(7),
                    field("codigoLiquidaacao").length(2),
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            ),
            rodape(
                    fcodigoRegistro().length(1).value("9"),
                    fbranco().length(393),
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            )
    );

    public static final TagLayout LAYOUT_ITAU_CNAB400_COBRANCA_RETORNO = _LAYOUT_ITAU_CNAB400_RETORNO
            .cloneReadonly();

    private static final TagLayout _LAYOUT_ITAU_PAGAMENTO = flatfile(
            layout(nome("Layout SISPAG Itau"),
                    cnab(CNAB_240),
                    banco("341"),
                    servico(null),
                    tag("url").value("https://download.itau.com.br/bankline/SISPAG_CNAB.pdf"),
                    versao("05")
            ),
            cabecalho(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
                    flote().valLen("0000"),
                    fcodigoRegistro().value("0"),
                    //Uso Exclusivo FEBRABAN / CNAB9179-AlfaBrancosG004
                    fbranco().length(6),
                    field("versaoLayoutArquivo").valLen("080"),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
                    fbranco().length(20),
                    fagencia().length(5), fbranco().length(1),
                    fconta().length(12), fbranco().length(1),
                    fdac(), fcedenteNome().length(30),
                    fbancoNome().length(30).value("BANCO ITAU SA"),
                    fbranco().length(10),
                    fcodigoArquivo().value(1),
                    fdataGeracao(),
                    field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
                    fzero().length(9),
                    //Densidade de gravação (BPI), do arquivo encaminhado. Domínio:1600 BPI ou 6250 BPI
                    field("densidadeArquivo").value(0).length(5).filler(Fillers.ZERO_LEFT),
                    fbranco().length(69)
            ),
            cabecalhoLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
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
                    foperacao().val("C"),
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
                    fservico().length(2).value(20),
                    //Uso Exclusivo FEBRABAN/CNAB
                    fforma().length(2).value(45),
                    //Nº da Versão do Layout do Lote
                    //                    .value("040") 040 para transferências e 030 para boletos
                    field("versaoLayoutLote").length(3).value("040"),
                    //Uso Exclusivo da FEBRABAN/CNAB 17 17 1 - Alfa Brancos G004
                    fbranco().length(1),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14),
                    field("IdentificacaoNoExtratoFavorecido").length(4).value("    "),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    fbranco().length(16),
                    //Agência Mantenedora da Conta 53 57 5-Num*G008
                    //Dígito Verificador da Agência 58 58 1-Alfa*G009
                    fagencia().length(5), fbranco().length(1),
                    //Número da Conta Corrente5970 12-Num*G010
                    //Dígito Verificador da Conta7171 1-Alfa*G011
                    fconta().length(12), fbranco().length(1), //Conta com DV
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
                    field("finalidadeDoLote").length(30).filler(Fillers.WHITE_SPACE_LEFT),
                    field("complementoHistoricoCC").length(10).filler(Fillers.WHITE_SPACE_LEFT),
                    fendereco().length(30).value("RUA 1"),
                    fnumero().value("1"),
                    fcomplemento().value(" "),
                    fcidade().length(20).value("SAO PAULO"),
                    fcep().value("01153000"),
                    fuf().value("SP"),
                    fbranco().length(8),
                    //Código das Ocorrências p/ Retorno 
                    focorrencias()
            ),
            detalheSegmentoA(
                    fbancoCodigo().length(3).value("341"),
                    flote().value(1),
                    fcodigoRegistro().length(1).value("3"),
                    fsequencialRegistro().length(5),
                    fsegmento().id(true).value("A"),
                    field("tipoMovimento").length(3).filler(Fillers.ZERO_LEFT),
                    /* Código Câmara Compensação 000 = CC | 018 = TED | 700 = DOC */
                    fformaDeTransferencia().length(3)
                            .filler(Fillers.ZERO_LEFT).value(9),
                    //                    Quando se tratar de uma transferência via PIX (“Código de Compensação 009”), deve-se
                    //tratar o campo CONTA CORRENTE DO FAVORECIDO da seguinte forma:
                    ffavorecidoCodigoBanco().length(3),
                    //                    ffavorecidoAgencia().length(6).filler(Fillers.ZERO_LEFT),
                    ffavorecidoConta().length(20).filler(Fillers.ZERO_LEFT),
                    //                    fbranco().length(1),
                    ffavorecidoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
                    // Número de Documento Cliente que identifica o pagto. ex: nota fiscal incrementar de 1 em 1
                    fnumeroDocumento().length(20),
                    //                    field("").length(14).filler(Fillers.WHITE_SPACE_RIGHT),
                    fdataPagamento().length(8),
                    fmoeda().length(3).value("REA"),
                    field("codigoISPB").length(8).filler(Fillers.WHITE_SPACE_RIGHT),
                    field("identificacaoPagamentoPix").length(2).value("04"),
                    fzero().length(5),
                    //                    fqtdeMoeda().value("000000000000000"),
                    fvalor().length(15),
                    field("nossoNumero").length(15).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbranco().length(5),
                    field("dataRealEfetivacaoPagto").length(8).filler(Fillers.ZERO_LEFT),
                    field("valorRealEfetivacaoPagto").length(15).filler(Fillers.WHITE_SPACE_RIGHT),
                    field("finalidadeDetalhe").length(20).filler(Fillers.WHITE_SPACE_RIGHT),
                    field("numeroDocumentoTED").length(6).filler(Fillers.ZERO_LEFT), // crédito em conta
                    ffavorecidoCPFCNPJ().length(14),
                    field("codigoFinalidadeDaTED").length(2).value("01"), // crédito em conta
                    field("finalidadePagamento").length(5).value("00005"),//.value("  "), // finalidade pagamento alguns casos CC
                    fbranco().length(5),
                    field("avisoAoFavorecido").length(1).value("0"), // crédito em conta
                    // Códigos das Ocorrências p/ Retorno
                    focorrencias().length(10).filler(Fillers.WHITE_SPACE_RIGHT)
            ),
            detalheSegmentoB(fbancoCodigo().length(3).value("341"),
                    flote().value(1),
                    fcodigoRegistro().length(1).value("3"), fsequencialRegistro().length(5).value("#####"),
                    fsegmento().id(true).value("B"),
                    //                    fbranco().length(3),
                    // Tipo Inscrição Favorecido | CPF = 1, CNPJ = 2
                    //                    favorecidoTipoInscricao().length(1).value("#"),
                    // Endereço do Favorecido - opcional
                    //                    ffavorecidoCPFCNPJ().length(14).filler(Fillers.ZERO_LEFT),
                    //                    fbranco().length(95),
                    //                    fdata(), // Data do Vencimento
                    //                    fvalor().length(15), // Valor do Documento
                    field("tipoChave").length(2).value("03"),
                    fbranco().length(1),
                    favorecidoTipoInscricao().length(1),
                    ffavorecidoCPFCNPJ().length(14).filler(Fillers.ZERO_LEFT), // Valor do Abatimento
                    field("txid").length(30).filler(Fillers.WHITE_SPACE_RIGHT), // Valor do Desconto
                    field("mensagemPix").length(65).filler(Fillers.WHITE_SPACE_RIGHT), // Valor da Mora
                    field("chavePix").length(100).filler(Fillers.WHITE_SPACE_RIGHT), // Valor da Multa
                    fbranco().length(3), // Código/Documento do Favorecido - Número interno sem tratamento para o banco
                    focorrencias().length(10)// Exclusivo FEBRABAN / CNAB

            ),
            detalheSegmentoJ(
                    fbancoCodigo().value("341"),
                    flote().value(1),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("J"),
                    fmovimentoTipo().value(0),
                    fmovimentoCodigo().value("01"),//Padrão entrada de 
                    //                    fcodigoBarras().length(44),
                    ffavorecidoCodigoBanco().length(3),
                    fmoeda().length(1),
                    fdigitoVerificador().length(1),
                    ffatorVencimento().length(4),
                    fvalorBoleto().length(10),
                    fcampoLivre().length(25),
                    fsacadoNome().length(30),
                    fdataVencimento(),
                    //                    fdataPagamento(),
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
                    //                    fqtdeMoeda().value(1),
                    fzero().length(15),
                    //Referência Pagador Nº do Docto Atribuído pela Empresa 183 202 20 - Alfa G064
                    fnumeroDocumento().length(20),
                    //Nosso Número Nº do Docto Atribuído pelo Banco 203 222 20 - Alfa *G043
                    //Número do Documento Atribuído pelo Banco (Nosso Número)
                    //Número atribuído pelo Banco para identificar o lançamento, que será utilizado nas manutenções do mesmo. 
                    fbranco().length(13),
                    fnossoNumero().length(15),
                    //G065 Código da Moeda
                    //Código adotado pela FEBRABAN para identificar a moeda referenciada no Título
                    //                    fcodigoMoeda(),
                    //20.3J CNAB Uso Exclusivo FEBRABAN/CNAB 225 230 6 - Alfa Brancos G004
                    //                    fbranco().length(6),
                    //Código das Ocorrências para Retorno/Remessa
                    //Código adotado pela FEBRABAN para identificar as ocorrências detectadas no
                    //processamento.
                    //Pode-se informar até 5 ocorrências simultaneamente, cada uma delas codificada com
                    //dois dígitos, conforme relação abaixo.
                    focorrencias()
            ),
            detalheSegmentoJ52(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    //Número seqüencial para identificar univocamente um lote de serviço. Criado e
                    //controlado pelo responsável pela geração magnética dos dados contidos no arquivo.
                    //Preencher com '0001' para o primeiro lote do arquivo. Para os demais: número do lote
                    //anterior acrescido de 1. O número não poderá ser repetido dentro do arquivo.
                    //Se registro for Header do Arquivo preencher com '0000'
                    //Se registro for Trailer do Arquivo preencher com '9999'
                    fbancoCodigo().value("341"), flote().value(1),
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
                    fmovimentoCodigo().value(0),
                    //08.4.J52 Código Reg. Opcional Identificação Registro Opcional 18 19 2 - Num “52” G067
                    fidOpcional().id(true).value("52"),
                    //DADOS DO PAGADOR
                    //Tipo de Inscrição: '0'  =  Isento / Não Informado
                    //                   '1'  =  CPF
                    //                   '2'  =  CGC / CNPJ
                    //                   '3'  =  PIS / PASEP
                    //                   '9'  =   Outros
                    // Os campos Tipo e Número de Inscrição do Cedente (posições 076 a 091) são de preenchimento obrigatório,
                    //caso não informados o agendamento do pagamento será rejeitado (ocorrência “BI” – Para inconsistências
                    //específicas do J52 que são os dados do cedente e sacado). Também será motivo de rejeição nos casos do
                    //Tipo de Inscrição não corresponder com o Número de Inscrição apresentado.
                    ftipoInscricaoSacado().valLen("2"),
                    fsacadoCpfCnpj().length(15),
                    fsacadoNome().length(40),
                    //DADOS DO BENEFICIARIO
                    ftipoInscricaoCedente().valLen("2"),
                    fcedenteCnpj().length(15),
                    fcedenteNome().length(40),
                    //DADOS DO PAGADORR
                    //Pagadorr - Dados sobre o Beneficiário responsável pela emissão do título original
                    ftipoInscricaoSacador().valLen("0"),
                    fsacadorCnpj().length(15).filler(Fillers.ZERO_LEFT).value("0"),
                    fsacadorNome().length(40).filler(Fillers.WHITE_SPACE_LEFT).value("0"),
                    //Uso Exclusivo FEBRABAN/CNAB
                    fbranco().length(53)
            ),
            // CÓDIGO DO BANCOCÓDIGO BANCO NA COMPENSAÇÃO001 0039(03)341
            // CÓDIGO DO LOTELOTE DE SERVIÇO004 0079(04)NOTA 3
            // TIPO DE REGISTROREGISTRO DETALHE DE LOTE008 0089(01)3
            // NÚMERO DO REGISTRONo SEQUENCIAL REGISTRO NO LOTE009 0139(05)NOTA 9
            // SEGMENTOCÓDIGO SEGMENTO REG. DETALHE014 014X(01)O
            // TIPO DE MOVIMENTOTIPO DE MOVIMENTO015 0179(03)NOTA 10
            // CÓDIGO DE BARRASCÓDIGO DE BARRAS018 065X(48)NOTA 18
            // NOMENOME DA CONCESSIONÁRIA / CONTRIBUINTE066 095X(30)DATA VENCTODATA DO VENCIMENTO (NOMINAL)096 1039(08)DDMMAAAA
            // MOEDATIPO DE MOEDA104 106X(03)REA
            // QUANTIDADE MOEDAQUANTIDADE DE MOEDA107 1219(07)V9(08) NOTA 19
            // VALOR A PAGARVALOR PREVISTO DO PAGAMENTO122 1369(13)V9(02)DATA PAGAMENTODATA DO PAGAMENTO137 1449(08)VALOR PAGOVALOR DE EFETIVAÇÃO DO PAGAMENTO145 1599(13)V9(02)BRANCOSCOMPLEMENTO DE REGISTRO160 162X(03)NOTA FISCALNÚMERO DA NOTA FISCAL163 1719(09)BRANCOSCOMPLEMENTO DE REGISTRO172 174X(03)SEU NÚMERONo DOCTO ATRIBUÍDO PELA EMPRESA175 194X(20)
            // DDMMAAAA
            // NOTA 33
            // BRANCOSCOMPLEMENTO DE REGISTRO195 215X(21)(*)NOSSO NÚMERONÚMERO ATRIBUÍDO PELO BANCO216 230X(15)NOTA 12
            // (*)OCORRÊNCIASCÓDIGO DE OCORRÊNCIAS P/ RETORNO231 240X(10)NOTA 8
            detalheSegmentoO(
                    fbancoCodigo().value("341"), flote().value(1),
                    fcodigoRegistro().value("3"),
                    fsequencialRegistro(),
                    fsegmento().id(true).value("O"),
                    fbranco().length(1),
                    fmovimentoCodigo().value("01"),
                    fcodigoBarras().length(48),
                    ffavorecidoNome(),
                    fdataVencimento(),
                    fmoeda(),
                    fqtdeMoeda(),
                    fdataPagamento(),
                    fvalorPagamento(),
                    fbranco().length(3),
                    field("numeroNotaFiscal").length(9).value(0).filler(Fillers.ZERO_LEFT),
                    fbranco().length(3),
                    fnumeroDocumento().length(20),
                    fbranco().length(21),
                    fnossoNumero().length(15),
                    focorrencias().length(10)
            ),
            rodapeLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"),
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
                    //                    field("qtedMoedas").length(18).filler(Fillers.ZERO_LEFT).value(1),
                    //08.5 Número Aviso Débito Número Aviso Débito 60 65 6 - Num G066
                    //Número do Aviso de Débito
                    //Número atribuído pelo Banco para identificar um Débito efetuado na Conta Corrente a
                    //partir do(s) pagamento(s) efetivado(s), visando facilitar a Conciliação Bancária.
                    fzero().length(18),
                    //                    field("numAvisoDebito").length(6).filler(Fillers.ZERO_LEFT),
                    fbranco().length(171),
                    //Código das Ocorrências para Retorno/Remessa G0059
                    focorrencias()
            ),
            rodape(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("341"), flote().value("9999"), fcodigoRegistro().value("9"),
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

    static {
        _LAYOUT_ITAU_PAGAMENTO.get(layout()).get(fservico()).withValue(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
        _LAYOUT_ITAU_PAGAMENTO.get(cabecalho()).get(fcodigoArquivo()).withValue('1');

    }

    static final TagLayout _LAYOUT_ITAU_CNAB240_PAGAMENTO
            = _LAYOUT_ITAU_PAGAMENTO;

    public static final TagLayout LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA
            = _LAYOUT_ITAU_CNAB240_PAGAMENTO.cloneReadonly();

    public static void main(String... args) {
        TagLayout cabecalho = LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoJ52());
        System.out.println(cabecalho.toStringDetalhado());
    }

}
