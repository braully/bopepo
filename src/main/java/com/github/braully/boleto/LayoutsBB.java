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

import static com.github.braully.boleto.CNAB.CNAB_240;
import static com.github.braully.boleto.CNAB.CNAB_400;
import static com.github.braully.boleto.TagLayout.TagCreator.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author strike
 */
public class LayoutsBB {

    //
    public static final TagLayout _LAYOUT_BB_CNAB400_RETORNO = flatfile(
            layout(nome("Layout BB CNAB400 Retorno"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("001"),
                    tag("url")
                            .withValue("https://www.bb.com.br/docs/pub/emp/empl/dwn/Doc2628CBR643Pos7.pdf"),
                    versao("15")
            ),
            cabecalho(
                    fcodigoRegistro().length(1).value("0"),
                    fcodigoArquivo().length(1).value("2"),
                    fliteralRetorno().length(7).value("RETORNO"),
                    fservico().length(2),
                    fliteralServico().length(8).value("COBRANCA"),
                    fbranco().length(7),
                    fagencia().length(5),
                    fconta().length(9),
                    //     fconvenio().length(6),
                    fzero().length(6),
                    fcedenteNome().length(30),
                    fbancoCodigo().length(3).value("001"),
                    fbancoNome().length(15).value("BANCODOBRASIL"),
                    fdataGeracaoCurta(),
                    fsequencialArquivo().length(7),
                    field("complementoUsoBB").length(42),
                    fconvenio().length(7),
                    fbranco().length(238),
                    fsequencialRegistro().length(6)
            ),
            detalhe(
                    fcodigoRegistro().length(1).value("7"),
                    fzero().length(16),
                    //     ftipoInscricaoCedente().length(2),
                    //     fcedenteCnpj(),
                    fagencia().length(5),
                    fconta().length(9),
                    fconvenio().length(7),
                    fnumeroDocumento().length(25),
                    fnossoNumero().length(17),
                    /* TIPO DE COBRANÇA NOTA 02: 
                    - 1 – Simples
                    - 2 – Vinculada
                    - 4 – Descontada
                    - 7 – Cobrança Simples Carteira 17
                    - 8 – Vendor 
                     */
                    ftipoCobranca().length(1),
                    /* TIPO DE COBRANÇA PARA COMANDO 72 NOTA 03: 
                        - 0 – Caso não haja alteração de tipo de cobrança
                        - 1 – Simples
                        - 2 – Vinculada
                        - 4 – Descontada
                        - 7 – Cobrança Simples Carteira 17
                        - 8 – Vendor
                     */
                    ftipoCobrancaComando72().length(1),
                    /* a) carteiras 11, 12, 15, 16, 7, 18 e 31: quando o boleto for liquidado, é preenchido com o número de dias decorridos desde o vencimento até a liquidação.
                    b) carteira 51: igual ao número de dias sobre os quais foram calculados o desconto e o IOF */
                    field("diasParaCalculo").length(4),
                    field("naturezaRecebimento").length(2),
                    field("prefixoBoleto").val("   ").length(3),
                    fvariacao().length(3),
                    field("contaCaucao").length(1),
                    field("taxaDesconto").length(6),
                    field("taxaIOF").length(4),
                    fbranco().length(1),
                    fcarteira().length(2),
                    field("comandoRetorno").length(2),
                    fdataOcorrenciaCurta(),
                    fnumeroDocumento().length(10),
                    fbranco().length(20),
                    fdataVencimento().length(6).format(new SimpleDateFormat("ddMMyy")),
                    fvalor().length(13),
                    field("codigoBancoRecebedor").length(3),
                    field("agenciaRecebedora").length(5),
                    fespecieTitulo().length(2),
                    field("dataCredito").length(6),
                    field("valorTarifa").length(7),
                    field("outrasDespesas").length(13),
                    field("jurosDesconto").length(13),
                    field("IOFDesconto").length(13),
                    field("valorAbatimento").length(13),
                    field("descontoConcedido").length(13),
                    field("valorRealEfetivacaoPagto").length(13),
                    field("jurosMora").length(13),
                    field("outrosRecebimentos").length(13),
                    field("abatimentoNaoAproveitado").length(13),
                    field("valorLancamento").length(13),
                    field("indicadorDebitoCredito").length(1),
                    field("indicadorValor").length(1),
                    field("valorAjuste").length(12),
                    field("informacaoCobrancaCompartilhada").length(58),
                    field("indicativoAutorizacaoLiquidacaoParcial").length(1),
                    fbranco().length(1),
                    field("canalPagamento").length(2),
                    //                    field("meioApresentacao").length(2),
                    //
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            ),
            rodape(
                    fcodigoRegistro().length(1).value("9"),
                    fcodigoArquivo().length(1).value("2"),
                    field("constante").valLen("01"),
                    fbancoCodigo().val("001"),
                    fbranco()
                            .length(10),
                    //                    field("quantidadeCobrancaSimples").length(8),
                    field("quantidadeRegistros").length(8),
                    field("valorTotalCobrancaSimples").length(14),
                    field("numeroAvisoCobrancaSimples").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaVinculada").length(8),
                    field("valorTotalCobrancaVinculada").length(14),
                    field("numeroAvisoCobrancaVinculada").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaCaucionada").length(8),
                    field("valorTotalCobrancaCaucionada").length(14),
                    field("numeroAvisoCobrancaCaucionada").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaDescontada").length(8),
                    field("valorTotalCobrancaDescontada").length(14),
                    field("numeroAvisoCobrancaDescontada").length(8),
                    fbranco().length(50),
                    field("quantidadeCobrancaVendor").length(8),
                    field("valorTotalCobrancaVendor").length(14),
                    field("numeroAvisoCobrancaVendor").length(8),
                    fbranco().length(147),
                    //     ...
                    fsequencialRegistro().length(6)
            )
    );

    public static final TagLayout _LAYOUT_BB_CNAB400_REMESSA = flatfile(
            layout(nome("Layout BB CNAB400 Retorno"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("001"),
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
                    fliteralServico().length(8).value("COBRANCA"),
                    fbranco().length(7),
                    fagencia().length(5),
                    fconta().length(9),
                    //     fconvenio().length(6),
                    fzero().length(6),
                    fcedenteNome().length(30),
                    fbancoCodigo().length(3).value("001"),
                    fbancoNome().length(15).value("BANCODOBRASIL"),
                    fdataGeracaoCurta(),
                    fsequencialArquivo().length(7),
                    fbranco().length(22),
                    fconvenio().length(7),
                    fbranco().length(258),
                    fsequencialRegistro().val("1").length(6)
            ),
            detalhe(
                    // 20	LINHA 2 - Registro Detalhe 7				
                    // 20	POSIÇÃO	NOME DO CAMPO	CONTEÚDO OBSERVADO	ERRO/ALERTA  Erros de estrutura: Impedem o processamento  Erros: impedem o processamento  Alertas: recomendação de ajuste  (não impede o processamento)	SOLUÇÃO/RECOMENDAÇÃO
                    // 21	1 a 1	01.7 - Tipo de registro 7	[7]		
                    // 22	2 a 3	02.7 - Indicador de 01-CPF / 02-CNPJ	[02]		
                    // 23	4 a 17	03.7 - Número do CNPJ do Beneficiário	[]		
                    // 24	18 a 21	04.7 - Prefixo da agência	[000]		
                    // 25	22 a 22	05.7 - Dv da agência	[0]		
                    // 26	23 a 30	06.7 - Número da conta	[000]		
                    // 27	31 a 31	07.7 - DV da conta	[0]		
                    // 28	32 a 38	08.7 - Número do Convênio	[0000]		
                    // 29	39 a 63	09.7 - Código de Controle da Empresa	[CR00020350000001         ]		
                    // 30	64 a 80	10.7 - Nosso Número	[35953760000000117]		
                    // 31	81 a 82	11.7 - Número da Prestação	[00]		
                    // 32	83 a 84	12.7 - Grupo de Valor	[00]		
                    // 33	85 a 86	13.7 - Tipo de Moeda	[  ]		
                    // 34	87 a 87	14.7 - Complemento de Registro	[ ]		
                    // 35	88 a 88	15.7 - Indicativo de Mensagem ou Sacador Avalista	[ ]		
                    // 36	89 a 91	16.7 - Prefixo do Título	[]		
                    // 37	92 a 94	17.7 - Variação da Carteira	[019]		
                    // 38	95 a 95	18.7 - Conta Caução	[0]		
                    fcodigoRegistro().length(1).value("7"),
                    //     fzero().length(16),
                    ftipoInscricaoCedente().value("02").length(2),
                    fcedenteCnpj(),
                    fagencia().length(5),
                    fconta().length(9),
                    fconvenio().length(7),
                    fnumeroDocumento().length(25),
                    fnossoNumero().length(17),
                    field("numeroPrestacao").valLen("00"),
                    field("grupoValor").valLen("00"),
                    ftipoMoeda().length(2).value("  "),
                    field("complementoRegistro").length(1).value(" "),
                    field("tipoMensagenOuScadaor").valLen(" "),
                    field("prefixoTitulo").length(3).value("   "),
                    fvariacao().length(3).filler(Fillers.ZERO_LEFT),
                    field("contaCaucao").valLen("0"),
                    field("numeroBordero").valLen("000000"),
                    ftipoCobranca().length(5).value("     "),
                    fcarteira().length(2).filler(Fillers.ZERO_LEFT),
                    // 42	109 a 110	22.7 - Comando	[01]		
                    // 43	111 a 120	23.7 - Seu Número/Número do título atribuído pelo  Beneficiário	[0024500024]		
                    // 44	121 a 126	24.7 - Data de Vencimento DDMMAA	[300823]		
                    // 45	127 a 139	25.7 - Valor do Titulo	[0000007786372]		
                    // 46	140 a 142	26.7 - Número do Banco	[001]		
                    // 47	143 a 146	27.7 - Prefixo da Agência Cobradora	[0000]		
                    // 48	147 a 147	28.7 - Branco	[]		
                    // 49	148 a 149	29.7 - Espécie do Título	[01]		
                    // 50	150 a 150	30.7 - Aceite do Título	[N]		
                    fespecieTitulo().length(2).value("01"),
                    fnumeroDocumento().length(10).filler(Fillers.ZERO_LEFT),
                    fdataVencimento().length(6).format(new SimpleDateFormat("ddMMyy")),
                    fvalor().length(13).filler(Fillers.ZERO_LEFT),
                    fbancoCodigo().length(3).value("001"),
                    fzero().length(4),
                    fbranco().length(1),
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
                    field("instrucao1").length(2).value("00"),
                    //IMPORTANTE: - 07 - Não protestar. Consultar melhor manual
                    field("instrucao2").length(2).value("07"),
                    field("moraDiaria").length(13).filler(Fillers.ZERO_LEFT).value(0),
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

                    fsacadoNome().length(37).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcomplemento().length(3).value("   ").filler(Fillers.WHITE_SPACE_RIGHT),
                    fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbairro().length(12).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcep().length(8).filler(Fillers.ZERO_RIGHT),
                    fcidade().length(15).filler(Fillers.WHITE_SPACE_RIGHT),
                    fuf().filler(Fillers.WHITE_SPACE_RIGHT).length(2),
                    field("mensagemAoSacador").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
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

    // Especializar
//    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_REMESSA = _LAYOUT_BB_CNAB240.clone();
//
//    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_RETORNO = _LAYOUT_BB_CNAB240.clone();
//    static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BB_CNAB240.clone();
//    static {
//        // Alterar o cabeçalho e o tipo de serviço no layout
//        _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(cabecalho())
//                .get(fcodigoArquivo()).withValue('1');
//        TagLayout layout = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(layout());
//        layout.get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);
//
//        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho())
//                .get(fcodigoArquivo()).withValue('2');
//        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(layout())
//                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);
//
//        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho())
//                .get(fcodigoArquivo()).withValue('1');
//        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho())
//                .get(fcodigoArquivo()).withValue("0126");
//        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote())
//                .get("cobrancaCedente").withValue("0126");
//
//        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(layout())
//                .get(fservico()).withValue(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
//    }
    public static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = flatfile(
            layout(nome("Layout Padrão Febraban CNAB240"),
                    cnab(CNAB_240),
                    banco("001"),
                    servico(null),
                    tag("url").value("https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240"),
                    versao("05")
            ),
            cabecalho(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("001"),
                    flote().valLen("0000"),
                    fcodigoRegistro().value("0"),
                    //Uso Exclusivo FEBRABAN / CNAB9179-AlfaBrancosG004
                    fbranco().length(9),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    fconvenio().length(9),
                    field("cobrancaCedente").valLen("0126"),
                    fbranco("reservadoBanco1").length(5),//Reservado
                    fbranco().length(2), //Em produção = Brancos Em teste = 'TS
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
                    fdac().value("0"), fcedenteNome().length(30),
                    fbancoNome().value("BANCO DO BRASIL").length(30),
                    //Uso Exclusivo FEBRABAN / CNAB
                    fbranco("reservadoFebraban").length(10),
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
                    fbranco("reservadoBanco2").length(20),
                    fbranco("reservadoEmpresa").length(20),
                    //Para Uso Reservado da Empresa19221120-AlfaG022
                    fbranco("reservadoFebraban").length(11),
                    fbranco("cobrancaSemPapel").length(3),
                    fzero("reservadoVans").length(3),
                    fzero("tipoS").length(2),
                    //Uso Exclusivo FEBRABAN / CNAB21224029-AlfaBrancosG004
                    fzero("ocorrencias").length(10)
            ),
            //Registro Header de Lote: 3.2.2 -Títulos em Cobrança
            cabecalhoLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("001"),
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
                    fservico().value("98"),
                    //Uso Exclusivo FEBRABAN/CNAB
                    //                    Conta Corrente = '01'
                    //                    DOC/TED = '03'*
                    //                    Poupança = '05'
                    //                    TED Outra Titularidade = '41'*
                    //                    TED Mnesma Titularidade = '43'*
                    //                    * Necessidade de complementação de informação do campo 'Código da Câmara de Compensação', posições18 a 20 do
                    //                    Segmento A
                    field("formaLancamento").length(2).value("03"),
                    //Nº da Versão do Layout do Lote
                    field("versaoLayoutLote").value("060").length(3),
                    //Uso Exclusivo da FEBRABAN/CNAB 17 17 1 - Alfa Brancos G004
                    fbranco().length(1),
                    ftipoInscricao().value("2"),
                    fcedenteCnpj().length(14),
                    //ConvênioCódigo do Convênio no Banco335220-Alfa*G007
                    //Código adotado pelo Banco para identificar o Contrato entre este e a Empresa Cliente.
                    fconvenio().length(9),
                    field("cobrancaCedente").valLen("0126"),
                    fbranco("reservadoBanco1").length(5),//Reservado
                    fbranco().length(2),
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
                    field("enderecoRua").length(30).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoNumeroRua").length(5).filler(Fillers.ZERO_LEFT),
                    field("enderecoComplemento").length(15).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoCidade").length(20).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoCEP").length(5).filler(Fillers.ZERO_LEFT),
                    field("enderecoComplementoCEP").length(3).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoUf").length(2).filler(Fillers.WHITE_SPACE_LEFT),
                    //Uso Exclusivo da FEBRABAN/CNAB
                    fbranco().length(8),
                    //Código das Ocorrências p/ Retorno 
                    focorrencias().length(10).filler(Fillers.ZERO_LEFT)
            ),
            // Remessa de pagamento
            detalheSegmentoA(
                    fbancoCodigo().length(3).value("001"),
                    flote().value("0001"),
                    fcodigoRegistro().length(1).value("3"),
                    fsequencialRegistro().length(5).value("#####"),
                    fsegmento().id(true).value("A"),
                    field("tipoMovimento").length(1).value("0"),
                    field("instrucaoMovimento").length(2).value("00"),
                    /* Código Câmara Compensação 000 = CC | 018 = TED | 700 = DOC */
                    fformaDeTransferencia().length(3).filler(Fillers.ZERO_LEFT).value("018"),
                    ffavorecidoCodigoBanco().length(3).value("000"),
                    ffavorecidoAgencia().length(6).filler(Fillers.ZERO_LEFT),
                    ffavorecidoConta().length(13).filler(Fillers.ZERO_LEFT),
                    fdac().value(" ").length(1),
                    ffavorecidoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
                    // Número de Documento Cliente que identifica o pagto. ex: nota fiscal incrementar de 1 em 1
                    /**
                     * Os número colocados nas posições 74 a 79 aparecerão como
                     * número do documento no extrato do favorecido, e os
                     * números das posições 80 a 85, serão utilizadas como
                     * número do documento no extrato do pagador. Obs.: Como os
                     * lançamentos ocorridos na conta do pagador são aglutinados
                     * num mesmo lote as posições 80 a 85 de todos os detalhes
                     * devem ser iguais, caso contrário será considerado apenas
                     * o número constante no primeiro registro detalhe de cada
                     * lote. As posições 86 a 93 não são tratadas pelo sistema.
                     * As informações impostas nessa posição voltarão iguais no
                     * arquivo retorno
                     */
                    field("numeroDocumento").length(20).filler(Fillers.WHITE_SPACE_LEFT),
                    //                    field("").length(14).filler(Fillers.WHITE_SPACE_RIGHT),
                    fdataPagamento().length(8),
                    field("moeda").length(3).value("BRL"),
                    fqtdeMoeda().value("000000000000000"),
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
            detalheSegmentoB(fbancoCodigo().length(3).value("001"),
                    flote().value("#"),
                    fcodigoRegistro().length(1).value("3"), fsequencialRegistro().length(5).value("#####"),
                    fsegmento().id(true).value("B"), fbranco().length(3),
                    // Tipo Inscrição Favorecido | CPF = 1, CNPJ = 2
                    favorecidoTipoInscricao().length(1).value("#"),
                    // Endereço do Favorecido - opcional
                    ffavorecidoCPFCNPJ().length(14).filler(Fillers.ZERO_LEFT),
                    field("enderecoRua").length(30).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoNumeroRua").length(5).filler(Fillers.ZERO_LEFT),
                    field("enderecoComplemento").length(15).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoBairro").length(15).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoCidade").length(20).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoCEP").length(5).filler(Fillers.ZERO_LEFT),
                    field("enderecoComplementoCEP").length(3).filler(Fillers.WHITE_SPACE_LEFT),
                    field("enderecoUf").length(2).filler(Fillers.WHITE_SPACE_LEFT),
                    fdata().filler(Fillers.ZERO_LEFT).length(8), // Data do Vencimento
                    fvalor().length(15), // Valor do Documento
                    fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor do Abatimento
                    fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor do Desconto
                    fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor da Mora
                    fzero().length(15).filler(Fillers.ZERO_LEFT), // Valor da Multa
                    fbranco().length(15), // Código/Documento do Favorecido - Número interno sem tratamento para o banco
                    field("avisoAoFavorecido").length(1).value("0"),
                    fbranco().length(14)// Exclusivo FEBRABAN / CNAB

            ),
            rodapeLote(
                    //Controle: Banco, lote e registro
                    //Banco: Código do Banco na Compensação133-NumG001
                    fbancoCodigo().value("001"),
                    flote()
                            .value(1), // o mesmo do cabeçalho do lote
                    fcodigoRegistro().value("5"),
                    //04.5 CNAB Uso Exclusivo FEBRABAN/CNAB 9 17 9 - Alfa Brancos G004
                    fbranco().length(9),
                    //Quantidade de Registros do Lote 18 23 6 - Num *G057
                    fquantidadeRegistros().length(6).value(0),
                    fvalorTotalRegistros().length(18).value(0),
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
                    fbancoCodigo().value("001"), flote().value("9999"), fcodigoRegistro().value("9"),
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

    public static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_RETORNO = flatfile(
            layout(nome("Layout BB CNAB240 Remessa"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("001"),
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
                    fliteralServico().length(8).value("COBRANCA"),
                    fbranco().length(7),
                    fagencia().length(5),
                    fconta().length(9),
                    //     fconvenio().length(6),
                    fzero().length(6),
                    fcedenteNome().length(30),
                    fbancoCodigo().length(3).value("001"),
                    fbancoNome().length(15).value("BANCODOBRASIL"),
                    fdataGeracaoCurta(),
                    fsequencialArquivo().length(7),
                    fbranco().length(22),
                    fconvenio().length(7),
                    fbranco().length(258),
                    fsequencialRegistro().length(6)
            ),
            detalhe(
                    // 20	LINHA 2 - Registro Detalhe 7				
                    // 20	POSIÇÃO	NOME DO CAMPO	CONTEÚDO OBSERVADO	ERRO/ALERTA  Erros de estrutura: Impedem o processamento  Erros: impedem o processamento  Alertas: recomendação de ajuste  (não impede o processamento)	SOLUÇÃO/RECOMENDAÇÃO
                    // 21	1 a 1	01.7 - Tipo de registro 7	[7]		
                    // 22	2 a 3	02.7 - Indicador de 01-CPF / 02-CNPJ	[02]		
                    // 23	4 a 17	03.7 - Número do CNPJ do Beneficiário	[]		
                    // 24	18 a 21	04.7 - Prefixo da agência	[000]		
                    // 25	22 a 22	05.7 - Dv da agência	[0]		
                    // 26	23 a 30	06.7 - Número da conta	[000]		
                    // 27	31 a 31	07.7 - DV da conta	[0]		
                    // 28	32 a 38	08.7 - Número do Convênio	[0000]		
                    // 29	39 a 63	09.7 - Código de Controle da Empresa	[CR00020350000001         ]		
                    // 30	64 a 80	10.7 - Nosso Número	[35953760000000117]		
                    // 31	81 a 82	11.7 - Número da Prestação	[00]		
                    // 32	83 a 84	12.7 - Grupo de Valor	[00]		
                    // 33	85 a 86	13.7 - Tipo de Moeda	[  ]		
                    // 34	87 a 87	14.7 - Complemento de Registro	[ ]		
                    // 35	88 a 88	15.7 - Indicativo de Mensagem ou Sacador Avalista	[ ]		
                    // 36	89 a 91	16.7 - Prefixo do Título	[]		
                    // 37	92 a 94	17.7 - Variação da Carteira	[019]		
                    // 38	95 a 95	18.7 - Conta Caução	[0]		
                    fcodigoRegistro().length(1).value("7"),
                    //     fzero().length(16),
                    ftipoInscricaoCedente().length(2),
                    fcedenteCnpj(),
                    fagencia().length(5),
                    fconta().length(9),
                    fconvenio().length(7),
                    fnumeroDocumento().length(25),
                    fnossoNumero().length(17),
                    field("numeroPrestacao").valLen("00"),
                    field("grupoValor").valLen("00"),
                    ftipoMoeda().length(2).value("  "),
                    field("complementoRegistro").length(1).value(" "),
                    field("tipoMensagenOuScadaor").valLen(" "),
                    field("prefixoTitulo").length(3).value("   "),
                    fvariacao().length(3).filler(Fillers.ZERO_LEFT),
                    field("contaCaucao").valLen("0"),
                    field("numeroBordero").valLen("000000"),
                    ftipoCobranca().length(5).value("     "),
                    fcarteira().length(3).filler(Fillers.ZERO_LEFT),
                    // 42	109 a 110	22.7 - Comando	[01]		
                    // 43	111 a 120	23.7 - Seu Número/Número do título atribuído pelo  Beneficiário	[0024500024]		
                    // 44	121 a 126	24.7 - Data de Vencimento DDMMAA	[300823]		
                    // 45	127 a 139	25.7 - Valor do Titulo	[0000007786372]		
                    // 46	140 a 142	26.7 - Número do Banco	[001]		
                    // 47	143 a 146	27.7 - Prefixo da Agência Cobradora	[0000]		
                    // 48	147 a 147	28.7 - Branco	[]		
                    // 49	148 a 149	29.7 - Espécie do Título	[01]		
                    // 50	150 a 150	30.7 - Aceite do Título	[N]		
                    fespecieTitulo().length(2).value("01"),
                    fnumeroDocumento().length(10).filler(Fillers.ZERO_LEFT),
                    fdataVencimento().length(6).format(new SimpleDateFormat("ddMMyy")),
                    fvalor().length(13).filler(Fillers.ZERO_LEFT),
                    fbancoCodigo().length(3).value("001"),
                    fzero().length(4),
                    fbranco().length(1),
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
                    field("instrucao1").length(2).value("00"),
                    //IMPORTANTE: - 07 - Não protestar. Consultar melhor manual
                    field("instrucao2").length(2).value("07"),
                    field("moraDiaria").length(13).filler(Fillers.ZERO_LEFT).value(0),
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

                    fsacadoNome().length(37).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcomplemento().length(2).value("  "),
                    fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
                    fbairro().length(12).filler(Fillers.WHITE_SPACE_RIGHT),
                    fcep().length(8).filler(Fillers.ZERO_RIGHT),
                    fcidade().length(15).filler(Fillers.WHITE_SPACE_RIGHT),
                    fuf().length(2),
                    field("mensagemAoSacador").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
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

    public static final TagLayout LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA
            .cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_PAGAMENTO_RETORNO = _LAYOUT_BB_CNAB240_PAGAMENTO_RETORNO
            .cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB400_COBRANCA_RETORNO = _LAYOUT_BB_CNAB400_RETORNO
            .cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB400_COBRANCA_REMESSA = _LAYOUT_BB_CNAB400_REMESSA
            .cloneReadonly();

    public static Map<String, TagLayout> layouts = Map.of(
            //            "retorno-cobranca-cnab-240", LAYOUT_BB_CNAB240_COBRANCA_RETORNO,
            "retorno-cobranca-cnab-400", LAYOUT_BB_CNAB400_COBRANCA_RETORNO,
            //            "remessa-cobranca-cnab-240", LAYOUT_BB_CNAB240_COBRANCA_REMESSA,
            "remessa-cobranca-cnab-400", LAYOUT_BB_CNAB400_COBRANCA_REMESSA);

    public static void main(String... args) {
        // System.out.println(_LAYOUT_BB_CNAB240.toString());
        // _LAYOUT_BB_CNAB240.
//        TagLayout cabecalho = _LAYOUT_BB_CNAB400_RETORNO.get(cabecalho());
        TagLayout cabecalho = _LAYOUT_BB_CNAB400_REMESSA.get(detalhe());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB400_RETORNO.get(detalhe());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB400_RETORNO.get(rodape());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalhoLote());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoA());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(detalheSegmentoB());
//        TagLayout cabecalho = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(rodapeLote());

        System.out.println(cabecalho.toStringDetalhado());
    }

    public static TagLayout getLayout(String layout) {
        return layouts.get(layout);
    }
}
