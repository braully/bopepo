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
                    fbancoCodigo(),
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
    private static final TagLayout _LAYOUT_ITAU_PAGAMENTO = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA();

    static {
        // personalizações Itau

        String codigoBanco = "341";
        String campoBancoNome = "bancoNome";
        String campoBancoCodigo = "bancoCodigo";

//        // Layout
//        TagLayout layout = _LAYOUT_ITAU_CNAB240.get(layout());
//        layout.get("nome").value("Layout Padrão Itau CNAB240");
//        layout.get("banco").value(codigoBanco);
//
//        // Cabeçalho
//        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240.get(cabecalho());
//        cabecalho.get(campoBancoNome).value("BANCO ITAU SA");
//        cabecalho.get(campoBancoCodigo).value(codigoBanco);
//
//        cabecalho.get("versaoLayoutArquivo").value("080");
//
//        // cabecalhoLote
//        TagLayout cabecalhoLote = _LAYOUT_ITAU_CNAB240.get(cabecalhoLote());
//        cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
//        cabecalhoLote.get("versaoLayoutLote").value("040");
//
//        // RodapeLote
//        TagLayout rodapeLote = _LAYOUT_ITAU_CNAB240.get(rodapeLote());
//        rodapeLote.get(campoBancoCodigo).value(codigoBanco);
//
//        // RodapeArquivo
//        TagLayout rodapeArquivo = _LAYOUT_ITAU_CNAB240.get(rodape());
//        rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);
//
//        layout = _LAYOUT_ITAU_PAGAMENTO.get(layout());
//        layout.get("banco").value(codigoBanco);
//        layout.get("versao").value("80");
//        layout.get("url").value("https://download.itau.com.br/bankline/SISPAG_CNAB.pdf");
//
//        // cabecalhoLote
//        cabecalhoLote = _LAYOUT_ITAU_PAGAMENTO.get(cabecalhoLote());
//        cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
//        cabecalhoLote.get("versaoLayoutLote").value("040");
//
//        // RodapeLote
//        rodapeLote = _LAYOUT_ITAU_PAGAMENTO.get(rodapeLote());
//        rodapeLote.get(campoBancoCodigo).value(codigoBanco);
//
//        // RodapeArquivo
//        rodapeArquivo = _LAYOUT_ITAU_PAGAMENTO.get(rodape());
//        rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);
//
//        // SegmentoA
//        TagLayout segmentoA = _LAYOUT_ITAU_PAGAMENTO.get(detalheSegmentoA());
//        segmentoA.get(campoBancoCodigo).value(codigoBanco);
//
//        // SegmentoB
//        TagLayout segmentoB = _LAYOUT_ITAU_PAGAMENTO.get(detalheSegmentoB());
//        segmentoB.get(campoBancoCodigo).value(codigoBanco);
    }

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240.clone();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240.clone();

    static final TagLayout _LAYOUT_ITAU_CNAB240_PAGAMENTO
            = _LAYOUT_ITAU_PAGAMENTO;

    static {
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).withValue('2');

        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);
        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).withValue('1');

        _LAYOUT_ITAU_PAGAMENTO.get(layout()).get(fservico()).withValue(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
        _LAYOUT_ITAU_PAGAMENTO.get(cabecalho()).get(fcodigoArquivo()).withValue('1');

    }

    public static final TagLayout LAYOUT_ITAU_CNAB240
            = _LAYOUT_ITAU_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA
            = _LAYOUT_ITAU_CNAB240_PAGAMENTO.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA = _LAYOUT_ITAU_CNAB400_REMESSA
            .cloneReadonly();

    public static void main(String... args) {
//        TagLayout cabecalho = LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA.get(cabecalho());
        TagLayout cabecalho = LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA.get(detalhe());
        // System.out.println(_LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.toString());
        // _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.
//        TagLayout cabecalho = LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho());
//        TagLayout cabecalho = LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalhoLote());detalheSegmentoP
//        TagLayout cabecalho = LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(detalheSegmentoP());
//        TagLayout cabecalho = LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(detalhe());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB400_RETORNO.get(detalhe());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB400_RETORNO.get(rodape());
//        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA_PAGAMENTO_REMESSA.get(cabecalho());
//        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA_PAGAMENTO_REMESSA.get(detalheSegmentoA());
//        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA_PAGAMENTO_REMESSA.get(detalheSegmentoB());
//        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA_PAGAMENTO_REMESSA.get(rodapeLote());
        System.out.println(cabecalho.toStringDetalhado());
    }

}
