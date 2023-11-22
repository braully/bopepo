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

import static com.github.braully.boleto.CNAB.CNAB_400;
import com.github.braully.boleto.CNABServico;
import com.github.braully.boleto.TagLayout;
import static com.github.braully.boleto.TagLayout.TagCreator.banco;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalhoLote;
import static com.github.braully.boleto.TagLayout.TagCreator.cnab;
import static com.github.braully.boleto.TagLayout.TagCreator.detalhe;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoP;
import static com.github.braully.boleto.TagLayout.TagCreator.faceite;
import static com.github.braully.boleto.TagLayout.TagCreator.fagencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fbairro;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoCodigo;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fbranco;
import static com.github.braully.boleto.TagLayout.TagCreator.fcarteira;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteCnpj;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fcep;
import static com.github.braully.boleto.TagLayout.TagCreator.fcidade;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoOcorrencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoRegistro;
import static com.github.braully.boleto.TagLayout.TagCreator.fcomplemento;
import static com.github.braully.boleto.TagLayout.TagCreator.fconta;
import static com.github.braully.boleto.TagLayout.TagCreator.fconvenio;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataDesconto;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataGeracao;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataGeracaoCurta;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataVencimento;
import static com.github.braully.boleto.TagLayout.TagCreator.fendereco;
import static com.github.braully.boleto.TagLayout.TagCreator.fespecieTitulo;
import static com.github.braully.boleto.TagLayout.TagCreator.field;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.flatfile;
import static com.github.braully.boleto.TagLayout.TagCreator.fliteralRemessa;
import static com.github.braully.boleto.TagLayout.TagCreator.fliteralRetorno;
import static com.github.braully.boleto.TagLayout.TagCreator.fliteralServico;
import static com.github.braully.boleto.TagLayout.TagCreator.fnossoNumero;
import static com.github.braully.boleto.TagLayout.TagCreator.fnumeroDocumento;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoCpf;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialRegistro;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoCobranca;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoCobrancaComando72;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricao;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricaoCedente;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoMoeda;
import static com.github.braully.boleto.TagLayout.TagCreator.fuf;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalor;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorAbatimento;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorDesconto;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorIOF;
import static com.github.braully.boleto.TagLayout.TagCreator.fvariacao;
import static com.github.braully.boleto.TagLayout.TagCreator.fzero;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;
import static com.github.braully.boleto.TagLayout.TagCreator.nome;
import static com.github.braully.boleto.TagLayout.TagCreator.rodape;
import static com.github.braully.boleto.TagLayout.TagCreator.servico;
import static com.github.braully.boleto.TagLayout.TagCreator.tag;
import static com.github.braully.boleto.TagLayout.TagCreator.versao;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author strike
 */
public class LayoutsBB {

    /**
     * Particularidades BB CNAB 240 2019
     * https://www.bb.com.br/docs/pub/emp/empl/dwn/CNAB240SegPQRSTY.pdf
     */
    static final TagLayout _LAYOUT_BB_CNAB240 = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_V05();

    static {

        List<TagLayout> alld = _LAYOUT_BB_CNAB240.getAllDescendents(fbancoCodigo());

        // TODO: Migrar para uma constante
        for (TagLayout chd : alld) {
            chd.val("001");
        }

        alld = _LAYOUT_BB_CNAB240.getAllDescendents(fbancoNome());

        // TODO: Migrar para uma constante
        for (TagLayout chd : alld) {
            chd.value("BANCO DO BRASIL S.A.");
        }

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
                 * cedente
                 * 07.0 BB3 Número da carteira de cobrança BB 46 47 2 Numérico Informar o número
                 * da carteira de cobrança
                 * 07.0 BB4 Número da variação da carteira de 48 50 3 Numérico Informar o número
                 * da variação da carteira de cobrança cobrança BB
                 * 07.0 BB5 Campo reservado BB 51 52 2 Alfanumérico Informar brancos.
         */
        cabecalho.insertAfter(fconvenioCabecalho,
                field("cobrancaCedente").valLen("0014"),
                field("carteira").length(2),
                field("variacao").length(3).filler(Fillers.ZERO_LEFT),
                fbranco().length(2));

        TagLayout cabecalhoLote = _LAYOUT_BB_CNAB240.get(cabecalhoLote());
        TagLayout fconvenioCabecalhoLote = cabecalhoLote.get(fconvenio()).length(9);

        /*
                 * 11.1 BB1 Nùmero do convênio de cobrança BB 34 42 9 Numérico Informar o número
                 * do convênio de cobrança, alinhado à direita com zeros à esquerda.
                 * 11.1 BB2 Cobrança Cedente BB 43 46 4 Numérico Informar 0014 para cobrança
                 * cedente
                 * 11.1 BB3 Número da carteira de cobrança BB 47 48 2 Numérico Informar o número
                 * da carteira de cobrança
                 * 11.1 BB4 Número da variação da carteira de 49 51 3 Numérico Informar o número
                 * da variação da carteira de cobrança
         */
        cabecalhoLote.insertAfter(fconvenioCabecalhoLote,
                field("cobrancaCedente").valLen("0014"),
                field("carteira").length(2),
                field("variacao").length(3).filler(Fillers.ZERO_LEFT),
                /*
                                 * informar brancos; ou para tratamento de arquivo teste:
                                 * cliente, antes de realizar os procedimentos abaixo,entre em contato
                                 * com sua agência, pois a situação de seu intercâmbio eletrônico de
                                 * dados deverá ser alterado de ATIVO para TESTE.
                                 * 11.1 BB5 Campo que identifica remessa de testes 52 53 2 Alfanumérico
                                 * Importante que nesse caso não deverá ser enviado arquivos para a produção,
                                 * pois sua condição foi alterada para TESTE.
                                 * Obs.: Caso a empresa queira efetuar TESTE pelo sistema, com
                                 * geração
                                 * de arquivo retorno TESTE pelo Gerenciador Financeiro, basta
                                 * substituir os espaços em branco (posições 52 e 53) por "TS".
                                 * Caso não queira realizar os testes, informe brancos
                 */
                fbranco().length(2));

        TagLayout detalheSegmentoP = _LAYOUT_BB_CNAB240.get(detalheSegmentoP());
        // Importante:todos os "nosso número" devem ser alinhados à esquerda com brancos
        // à direita.
        TagLayout fnossoNumeroDetalhe = detalheSegmentoP.get(fnossoNumero());
        fnossoNumeroDetalhe.filler(Fillers.WHITE_SPACE_RIGHT);

    }

    // Especializar
    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_REMESSA = _LAYOUT_BB_CNAB240.clone();

    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_RETORNO = _LAYOUT_BB_CNAB240.clone();

    static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BB_CNAB240.clone();

    static {
        // Alterar o cabeçalho e o tipo de serviço no layout
        _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).withValue('1');
        TagLayout layout = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(layout());
        layout.get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);

        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).withValue('2');
        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);

        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).withValue('1');
        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
    }
    public static final TagLayout LAYOUT_BB_CNAB240 = _LAYOUT_BB_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_REMESSA = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA
            .cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_RETORNO = _LAYOUT_BB_CNAB240_COBRANCA_RETORNO
            .cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA
            .cloneReadonly();

    static final TagLayout _LAYOUT_BB_CNAB400_RETORNO = flatfile(
            layout(nome("Layout BB CNAB400 Retorno"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("001"),
                    tag("url"),
                    //     .withValue("https://..."),
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
                    fbranco().length(3),
                    /* Indicativo de Mensagem ou Sacador/Avalista:
a) “Brancos”: Pode ser informada nas posições 352 a 391
qualquer mensagem para ser impressa no bloqueto
b) “A”: Informar o nome e CPF/CNPJ do sacador nas posições
352 a 391. */
                    field("tipoMensagenOuScadaor").valLen("A"),
                    /*
                    Prefixo do Título:
a) carteiras 31 e 51: informar SD
b) carteira 12: informar AIU
c) demais carteiras: informar AI
                     */
                    field("prefixoTitulo").length(3).value("AI"),
                    fzero().length(1),
                    fcarteira().length(3).filler(Fillers.ZERO_LEFT),
                    fagencia().length(5).filler(Fillers.ZERO_LEFT),
                    fconta().length(8).filler(Fillers.ZERO_LEFT),
                    field("numeroControleParticipante").length(25).filler(Fillers.ZERO_LEFT).value(0),
                    fbancoCodigo().length(3).value("237"),
                    field("campoMulta").length(1).value(0),
                    field("percentualMulta").length(4).filler(Fillers.ZERO_LEFT).value(0),
                    fnossoNumero().length(11).filler(Fillers.ZERO_LEFT),
                    field("digitoNossoNumero").length(1),
                    field("descontoBonificacaoDia").length(10).filler(Fillers.ZERO_LEFT).value(0),
                    field("codicaoEmissaoPapeladaCobranca").length(1).value(2),
                    field("identificacaoBoletoDebito").length(1).value("N"),
                    fbranco().length(10),
                    field("rateioCredito").length(1).value(" "),
                    field("avisoDebitoAutomatico").length(1).value(2),
                    field("quantidadePagamentos").length(2).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
                    fcodigoOcorrencia().length(2).value("01"),
                    fnumeroDocumento().length(10).filler(Fillers.ZERO_LEFT),
                    fdataVencimento().length(6).format(new SimpleDateFormat("ddMMyy")),
                    fvalor().length(13).filler(Fillers.ZERO_LEFT),
                    fzero().length(3),
                    fzero().length(5),
                    fespecieTitulo().length(2).value("01"),
                    field("identificacao").length(1).value("N"),
                    fdataGeracao().length(6).format(new SimpleDateFormat("ddMMyy")),
                    field("instrucao1").length(2).filler(Fillers.ZERO_LEFT).value(0),
                    field("instrucao2").length(2).filler(Fillers.ZERO_LEFT).value(0),
                    field("moraDiaria").length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fdataDesconto().length(6).format(new SimpleDateFormat("ddMMyy")).value(0),
                    fvalorDesconto().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorIOF().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    fvalorAbatimento().length(13).filler(Fillers.ZERO_LEFT).value(0),
                    ftipoInscricao().length(2).value("01"),
                    fsacadoCpf().length(14).filler(Fillers.ZERO_LEFT),
                    fsacadoNome().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
                    fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
                    field("mensagem1").length(12).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
                    field("sacadoCep").length(8),
                    field("mensagem2").length(60).filler(Fillers.WHITE_SPACE_RIGHT).value(" "),
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            ),
            rodape(
                    fcodigoRegistro().length(1).value("9"),
                    fcodigoArquivo().length(1).value("2"),
                    field("constante").valLen("01"),
                    fbancoCodigo().val("001"),
                    fbranco()
                            .length(10),
                    field("quantidadeCobrancaSimples").length(8),
                    field("valorTotalCobrancaSimples").length(13),
                    field("numeroAvisoCobrancaSimples").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaVinculada").length(8),
                    field("valorTotalCobrancaVinculada").length(13),
                    field("numeroAvisoCobrancaVinculada").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaCaucionada").length(8),
                    field("valorTotalCobrancaCaucionada").length(13),
                    field("numeroAvisoCobrancaCaucionada").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaDescontada").length(8),
                    field("valorTotalCobrancaDescontada").length(13),
                    field("numeroAvisoCobrancaDescontada").length(8),
                    fbranco().length(10),
                    field("quantidadeCobrancaVendor").length(8),
                    field("valorTotalCobrancaVendor").length(13),
                    field("numeroAvisoCobrancaVendor").length(8),
                    fbranco().length(10),
                    //     ...
                    fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
            )
    );

    static final TagLayout _LAYOUT_BB_CNAB400_REMESSA = flatfile(
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

    public static void main(String... args) {
        // System.out.println(_LAYOUT_BB_CNAB240.toString());
        // _LAYOUT_BB_CNAB240.
        TagLayout cabecalho = _LAYOUT_BB_CNAB240.get(cabecalhoLote());
        System.out.println(cabecalho.toStringDetalhado());
    }
}
