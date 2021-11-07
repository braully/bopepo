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

import static com.github.braully.boleto.CNAB.*;
import static com.github.braully.boleto.LayoutFebraban._LAYOUT_FEBRABAN_CNAB240;
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

  
    
    static final TagLayout _LAYOUT_CAIXA_CNAB240_REMESSA = flatfile(
        layout(nome("Layout Padrão Caixa Econômica Federal CNAB240 Remessa"),
            cnab(CNAB_240),
            banco("104"),
            tag("url").value("http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf"),
            versao("17")
        ),
        cabecalho(
            fbancoCodigo().length(3).value("104"),
            flote().value(0),
            fcodigoRegistro().length(1).value("0"),
            fbranco().length(9),
            ftipoInscricao().length(1).value("2"),
            fcedenteCnpj().length(14).filler(Fillers.ZERO_LEFT),
            fzero().length(20),
            fagencia().length(6),
            fconvenio().length(7).filler(Fillers.ZERO_LEFT),
            fzero().length(7),
            fcedenteNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
            fbancoNome().length(30).filler(Fillers.WHITE_SPACE_RIGHT),
            fbranco().length(10),
            fcodigoArquivo().length(1),
            fdataGeracao().length(8),
            field("horaGeracao").length(6).format(new SimpleDateFormat("hhmmss")),
            fsequencialArquivo().length(6),
            field("versaoLayoutArquivo").length(3).value("107"),
            field("densidadeArquivo").length(5).filler(Fillers.ZERO_LEFT).value("0"),
            fbranco().length(20),
            field("situacaoArquivo").length(20).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fbranco().length(4),
            fbranco().length(25)
        ),
        cabecalhoLote(
            fbancoCodigo().length(3).value("104"),
            flote().value(1),
            fcodigoRegistro().length(1).value("1"),
            foperacao().length(1),
            fservico().length(2),
            fzero().length(2),
            field("versaoLayoutLote").length(3).value("067"),
            fbranco().length(1),
            ftipoInscricao().length(1).value("2"),
            fcedenteCnpj().length(15).filler(Fillers.ZERO_LEFT),
            fconvenio().length(7).filler(Fillers.ZERO_LEFT),
            fzero().length(13),
            fagencia().length(6),
            field("codigoBeneficiario").length(6).filler(Fillers.ZERO_LEFT).value(0),
            fzero().length(7),
            fzero().length(1),
            fcedenteNome().length(30),
            field("mensagem1").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            field("mensagem2").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fsequencialArquivo().length(8).filler(Fillers.ZERO_LEFT).value("0"),
            fdataGeracao().length(8),
            fzero().length(8),
            fbranco().length(33)
        ),
        detalheSegmentoP(
            fbancoCodigo().length(3).value("104"),
            flote().value(1),
            fcodigoRegistro().length(1).value("3"),
            fsequencialRegistro().length(5),
            fsegmento().id(true).value("P"),
            fbranco().length(1),
            fmovimentoCodigo().length(2).value("01"),
            fagencia().length(6).filler(Fillers.ZERO_LEFT),
            fconvenio().length(7).filler(Fillers.ZERO_LEFT),
            fzero().length(7),
            fzero().length(2),
            fzero().length(1),
            field("modalidadeCarteira").length(2),
            fnossoNumero().length(15),
            fcodigoCarteira().length(1).value("1"),
            field("formaCadastroTitulo").length(1).value("1"),
            ftipoDocumento().length(1).value("2"),
            field("formaEmissaoTitulo").length(1).value("2"),
            field("formaEntregaTitulo").length(1).value("0"),
            fnumeroDocumento().length(11),
            fbranco().length(4),
            fdataVencimento().length(8),
            fvalor().length(15),
            field("agenciaCobradora").length(6).filler(Fillers.ZERO_LEFT).value("0"),
            fespecieTitulo().length(2).value("02"),
            faceite().length(1).value("N"),
            fdataGeracao().length(8),
            fcodigoAcrescimo().length(1).value("3"),
            fdataAcrescimo().length(8).filler(Fillers.ZERO_LEFT).value("0"),
            fvalorAcrescimo().length(15).filler(Fillers.ZERO_LEFT).value("0"),
            fcodigoDesconto().length(1).value("0"),
            fdataDesconto().length(8).filler(Fillers.ZERO_LEFT).value("0"),
            fvalorDesconto().length(15).filler(Fillers.ZERO_LEFT).value("0"),
            fvalorIOF().length(15).filler(Fillers.ZERO_LEFT).value("0"),
            fvalorAbatimento().length(15).filler(Fillers.ZERO_LEFT).value("0"),
            fnumeroDocumento().nome("numeroDocumentoEmpresa").length(25).filler(Fillers.ZERO_LEFT).value("0"),
            fcodigoProtesto().length(1).value("3"),
            field("numeroDiasProtesto").length(2).value("00"),
            fcodigoBaixa().length(1).value("1"),
            field("numeroDiasBaixa").length(3).value("000"),
            fcodigoMoeda().length(2).value("09"),
            fzero().length(10),
            fbranco().length(1)
        ),
        detalheSegmentoQ(
            fbancoCodigo().length(3).value("104"),
            flote().value(1),
            fcodigoRegistro().length(1).value("3"),
            fsequencialRegistro().length(5),
            fsegmento().id(true).value("Q"),
            fbranco().length(1),
            fmovimentoCodigo().length(2).value("01"),
            ftipoInscricao().length(1).value("1"),
            fsacadoCpf().length(15).filler(Fillers.ZERO_LEFT),
            fsacadoNome().length(40).filler(Fillers.WHITE_SPACE_RIGHT),
            fendereco().length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fbairro().length(15).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fcep().length(8).value("0"),
            fcidade().truncate(true).length(15).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fuf().length(2).value(""),
            field("tipoInscricaoAvalista").length(1).value("0"),
            field("numeroInscricaoAvalista").length(15).filler(Fillers.ZERO_LEFT).value("0"),
            field("nomeAvalista").length(40).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fbancoCodigo().nome("codigoBancoCorrespondente").length(3).filler(Fillers.ZERO_LEFT).value("0"),
            field("nossoNumeroBancoCorrespondente").length(20).filler(Fillers.WHITE_SPACE_RIGHT).value(""),
            fbranco().length(8)
        ),
        rodapeLote(
            fbancoCodigo().length(3).value("104"),
            flote().value(1),
            fcodigoRegistro().length(1).value("5"),
            fbranco().length(9),
            fquantidadeRegistros().length(6).filler(Fillers.ZERO_LEFT).value("0"),
            field("quantidadeTitulosCobranca").length(6).filler(Fillers.ZERO_LEFT).value("0"),
            fvalorTotalRegistros().length(17).filler(Fillers.ZERO_LEFT).value("0"),
            field("quantidadeTitulosCobrancaCaucionada").length(6).filler(Fillers.ZERO_LEFT).value("0"),
            field("valorTotalTitulosCarteiraCaucionada").length(17).filler(Fillers.ZERO_LEFT).value("0"),
            field("quantidadeTitulosCobrancaDescontada").length(6).filler(Fillers.ZERO_LEFT).value("0"),
            field("quantidadeTitulosCarteiraDescontada").length(17).filler(Fillers.ZERO_LEFT).value("0"),
            fbranco().length(31),
            fbranco().length(117)
        ),
        rodape(
            fbancoCodigo().length(3).value("104"),
            flote().length(4).value("9999"),
            fcodigoRegistro().length(1).value("9"),
            fbranco().length(9),
            field("quantidadeLotesArquivo").length(6).value("000001"),
            fquantidadeRegistros().length(6).filler(Fillers.ZERO_LEFT).value("0"),
            fbranco().length(6),
            fbranco().length(205)
        )
    );
    
    static final TagLayout _LAYOUT_CAIXA_CNAB240_RETORNO = flatfile(
        layout(nome("Layout Padrão Caixa Econômica Federal CNAB240 Retorno"),
            cnab(CNAB_240),
            banco("104"),
            tag("url").value("http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf"),
            versao("17")
        ),
        cabecalho(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().length(1).value("0"),
            fbranco().length(9),
            ftipoInscricao().length(1),
            fcedenteCnpj().length(14),
            fzero().length(20),
            fagencia().length(6),
            fconvenio().length(7),
            fzero().length(7),
            fcedenteNome().length(30),
            fbancoNome().length(30),
            fbranco().length(10),
            fcodigoArquivo().length(1),
            fdataGeracao().length(8),
            field("horaGeracao").length(6),
            fsequencialArquivo().length(6),
            field("versaoLayoutArquivo").length(3),
            field("densidadeArquivo").length(5),
            fbranco().length(20),
            field("situacaoArquivo").length(20),
            fbranco().length(4),
            fbranco().length(10),
            fzero().length(3),
            fbranco().length(12)
        ),
        cabecalhoLote(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().length(1).value("1"),
            foperacao().length(1),
            fservico().length(2),
            fzero().length(2),
            field("versaoLayoutLote").length(3),
            fbranco().length(1),
            ftipoInscricao().length(1),
            fcedenteCnpj().length(15),
            fconvenio().length(7),
            fzero().length(13),
            fagencia().length(6),
            fzero().length(6),
            fzero().length(7),
            fzero().length(1),
            fcedenteNome().length(30),
            field("mensagem1").length(40),
            field("mensagem2").length(40),
            fsequencialArquivo().length(8),
            fdataGeracao().length(8),
            field("dataCredito").length(8),
            fzero().length(2),
            fbranco().length(26),
            fzero().length(2),
            fbranco().length(3)
        ),
        detalheSegmentoT(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().value("3"),
            fsequencialRegistro(),
            fsegmento().id(true).value("T"),
            fbranco().length(1),
            fmovimentoCodigo(),
            fzero().length(6),
            fconvenio().length(7),
            fzero().length(2),
            field("numeroBancoPagadores").length(3),
            fzero().length(1),
            fbranco().length(2),
            field("modalidadeCarteiraSINCO").length(1),
            field("modalidadeCarteiraSIGCB").length(2),
            fnossoNumero().length(15),
            field("digitoVerificadorNossoNumero").length(1),
            fcodigoCarteira(),
            fnumeroDocumento().length(11),
            fbranco().length(4),
            fdataVencimento(),
            fvalor().length(15),
            field("bancoCobrador").length(3),
            field("agenciaCobradora").length(5),
            field("digitoAgenciaCobradora").length(1),
            field("identificacaoTituloEmpresa").length(25),
            fcodigoMoeda(),
            ftipoInscricao().value(1),
            fsacadoCpf().length(15),
            fsacadoNome().length(40),
            fbranco().length(10),
            fvalorTarifaCustas().length(15),
            field("motivoOcorrencia").length(10),
            fbranco().length(17)
        ),
        detalheSegmentoU(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().value("3"),
            fsequencialRegistro(),
            fsegmento().id(true).value("U"),
            fbranco().length(1),
            fmovimentoCodigo(),
            fvalorAcrescimo().length(15),
            fvalorDesconto().length(15),
            fvalorAbatimento().length(15),
            fvalorIOF().length(15),
            fvalorPagamento().length(15),
            fvalorLiquido().length(15),
            fvalorOutrasDespesas(),
            fvalorOutrasReceitas(),
            fdataOcorrencia(),
            fdataCredito(),
            fzero().length(4),
            field("dataDebitoTarifa").length(8),
            field("codigoPagador").length(15),
            fzero().length(30),
            field("codigoBancoCompensacao").length(3),
            field("nossoNumeroBancoCorrespondente").length(20),
            fbranco().length(7)
        ),
        rodapeLote(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().value("5"),
            fbranco().length(9),
            fquantidadeRegistros().length(6),
            field("quantidadeTitulosCobrancaSimples").length(6),
            field("valorTotalTitulosCarteiraCobrancaSimples").length(17),
            field("quantidadeTitulosCobrancaCaucionadas").length(6),
            field("valorTotalTitulosCarteiraCaucionadas").length(17),
            field("quantidadeTitulosCobrancaDescontada").length(6),
            field("quantidadeTitulosCarteiraDescontada").length(17),
            fzero().length(31),
            fbranco().length(117)
        ),
        rodape(
            fbancoCodigo(),
            flote(),
            fcodigoRegistro().length(1).value("9"),
            fbranco().length(9),
            field("quantidadeLotesArquivo").length(6),
            fquantidadeRegistros().length(6),
            fbranco().length(211)
        )
    );

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_REMESSA = flatfile(
        layout(nome("Layout Padrão Bradesco CNAB400 Remessa"),
            cnab(CNAB_400),
            banco("237"),
            tag("url").value("https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
            versao("15")
        ),
        cabecalho(
            fcodigoRegistro().length(1).value("0"),
            fcodigoArquivo().length(1).value("1"),
            fliteralRemessa().length(7).value("REMESSA"),
            fservico().length(2),
            fliteralServico().length(15).value("COBRANCA"),
            fconvenio().length(20),
            fcedenteNome().length(30),
            fbancoCodigo().length(3).value("237"),
            fbancoNome().length(15).value("BRADESCO"),
            fdataGeracao().length(6).format(new SimpleDateFormat("ddMMyy")),
            fbranco().length(8),
            field("identificacaoSistema").length(2).value("MX"),
            fsequencialArquivo().length(7).filler(Fillers.ZERO_LEFT),
            fbranco().length(277),
            fsequencialRegistro().length(6)
        ),
        detalhe(
            fcodigoRegistro().length(1).value("1"),
            field("agenciaDebito").length(5).filler(Fillers.ZERO_LEFT).value(0),
            field("digitoAgenciaDebito").length(1).value(0),
            field("razaoContaCorrente").length(5).filler(Fillers.ZERO_LEFT).value(0),
            field("contaCorrente").length(7).filler(Fillers.ZERO_LEFT).value(0),
            field("digitoContaCorrente").length(1).value(0),
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
            fbranco().length(393),
            fsequencialRegistro().length(6).filler(Fillers.ZERO_LEFT)
        )
    );
    
    static final TagLayout _LAYOUT_BRADESCO_CNAB400_RETORNO = flatfile(
        layout(nome("Layout Padrão Bradesco CNAB400 Retorno"),
            cnab(CNAB_400),
            banco("237"),
            tag("url").value("https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
            versao("15")
        ),
        cabecalho(
            fcodigoRegistro().value("0"),
            fcodigoArquivo(),
            fliteralRetorno().length(7),
            fservico().length(2),
            fliteralServico().length(15),
            fconvenio().length(20),
            fcedenteNome().length(30),
            fbancoCodigo(),
            fbancoNome().length(15),
            fdataGeracao().length(6),
            field("densidadeArquivo").length(8),
            field("numeroAvisoBancario").length(5),
            fbranco().length(266),
            fdataCredito().length(6),
            fbranco().length(9),
            fsequencialRegistro().length(6)
        ),
        detalhe(
            fcodigoRegistro().value("1"),
            ftipoInscricaoCedente().length(2),
            fcedenteCnpj().length(14),
            fzero().length(3),
            fzero().length(1),
            fcarteira().length(3),
            fagencia().length(5),
            fconta().length(8),
            field("numeroControleParticipante").length(25),
            fzero().length(8),
            fnossoNumero().length(11),
            field("digitoNossoNumero").length(1),
            fzero().length(22),
            field("rateioCredito").length(1),
            field("quantidadePagamentos").length(2),
            fcodigoCarteira().length(1),
            fmovimentoCodigo().length(2),
            fdataOcorrencia().length(6),
            fnumeroDocumento().length(10),
            field("identificacaoTituloBanco").length(20),
            fdataVencimento().length(6),
            fvalor().length(13),
            field("bancoCobrador").length(3),
            field("agenciaCobradora").length(5),
            fespecieTitulo().length(2),
            fvalorTarifaCustas().length(13),
            fvalorOutrasDespesas().length(13),
            field("jurosOperacaoAtraso").length(13),
            fvalorIOF().length(13),
            fvalorAbatimento().length(13),
            fvalorDesconto().length(13),
            fvalorPagamento().length(13),
            fvalorAcrescimo().length(13),
            fvalorOutrasReceitas().length(13),
            fbranco().length(2),
            field("codigoOcorrenciaProtesto").length(1),
            fdataCredito().length(6),
            field("origemPagamento").length(3),
            fbranco().length(10),
            field("codigoBanco").length(4),
            field("motivoRejeicao").length(10),
            fbranco().length(40),
            field("numeroCartorio").length(2),
            field("numeroProtocolo").length(10),
            fbranco().length(14),
            fsequencialRegistro().length(6)
        ),
        rodape(
            fcodigoRegistro().value("9"),
            fcodigoArquivo(),
            field("identificacaoTipoRegistro").length(2),
            fbancoCodigo(),
            fbranco().length(10),
            field("quantidadeTitulosCobranca").length(8),
            field("valorTotalCobranca").length(14),
            field("numeroAvisoBancario").length(8),
            fbranco().length(10),
            field("quantidadeRegistrosEntradaConfirmada").length(5),
            field("valorRegistrosEntradaConfirmada").length(12),
            field("valorRegistrosLiquidacao").length(12),
            field("quantidadeRegistrosLiquidacao").length(5),
            field("valorRegistrosLiquidacao").length(12),
            field("quantidadeRegistrosBaixados").length(5),
            field("valorRegistrosBaixados").length(12),
            field("quantidadeRegistrosAbatimentoCancelado").length(5),
            field("valorRegistrosAbatimentoCancelado").length(12),
            field("quantidadeRegistrosVencimentoAlterado").length(5),
            field("valorRegistrosVencimentoAlterado").length(12),
            field("quantidadeRegistrosAbatimentoConcedido").length(5),
            field("valorRegistrosAbatimentoConcedido").length(12),
            field("quantidadeRegistrosConfirmacaoProtesto").length(5),
            field("valorRegistrosConfirmacaoProtesto").length(12),
            fbranco().length(174),
            field("valorTotalRateios").length(15),
            field("quantidadeRateios").length(8),
            fbranco().length(9),
            fsequencialRegistro().length(6)
        )
    );

    static final TagLayout _LAYOUT_SICREDI_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {
        //Layout
        TagLayout layout = _LAYOUT_SICREDI_CNAB240.get(layout());
        layout.get("nome").value("Layout Padrão SICREDI CNAB240");
        layout.get("banco").value("748");
        layout.get("url").value("https://www.sicredi.com.br/html/para-sua-empresa/recebimentos/cobranca/");
    }

    /**
     * Particularidades BB CNAB 240 2019
     * https://www.bb.com.br/docs/pub/emp/empl/dwn/CNAB240SegPQRSTY.pdf
     */
    static final TagLayout _LAYOUT_BB_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {

        //Layout
        TagLayout layout = _LAYOUT_BB_CNAB240.get(layout());
        TagLayout tnome = layout.get("nome");
        tnome.withValue("Layout Padrão Banco do Brasil CNAB240");
        layout.get("banco").withValue("001");
        layout.get("url").withValue("https://www.bb.com.br/docs/pub/emp/empl/dwn/CbrVer04BB.pdf");

        //Cabeçalho
        TagLayout cabecalho = _LAYOUT_BB_CNAB240.get(cabecalho());
        //07.0 BB1 Nùmero do convênio de cobrança, 9 digitos, 
        //Numérico Informar o número do convênio de cobrança, alinhado à direita com zeros à esquerda. 
        TagLayout fconvenioCabecalho = cabecalho.get(fconvenio()).length(9);
        /* 07.0 BB2 Cobrança Cedente BB 42 45 4 Numérico Informar 0014 para cobrança cedente
           07.0 BB3 Número da carteira de cobrança BB 46 47 2 Numérico Informar o número da carteira de cobrança
           07.0 BB4 Número da variação da carteira de 48 50 3 Numérico Informar o número da variação da carteira de cobrança cobrança BB
           07.0 BB5 Campo reservado BB 51 52 2 Alfanumérico Informar brancos.
         */
        cabecalho.insertAfter(fconvenioCabecalho,
                field("cobrancaCedente").valLen("0014"),
                field("carteira").length(2),
                field("variacao").length(3).filler(Fillers.ZERO_LEFT),
                fbranco().length(2));

        TagLayout cabecalhoLote = _LAYOUT_BB_CNAB240.get(cabecalhoLote());
        TagLayout fconvenioCabecalhoLote = cabecalhoLote.get(fconvenio()).length(9);

        /*
        11.1 BB1 Nùmero do convênio de cobrança BB 34 42 9 Numérico Informar o número do convênio de cobrança, alinhado à direita com zeros à esquerda.
        11.1 BB2 Cobrança Cedente BB 43 46 4 Numérico Informar 0014 para cobrança cedente
        11.1 BB3 Número da carteira de cobrança BB 47 48 2 Numérico Informar o número da carteira de cobrança
        11.1 BB4 Número da variação da carteira de 49 51 3 Numérico Informar o número da variação da carteira de cobrança
         */
        cabecalhoLote.insertAfter(fconvenioCabecalhoLote,
                field("cobrancaCedente").valLen("0014"),
                field("carteira").length(2),
                field("variacao").length(3).filler(Fillers.ZERO_LEFT),
                /*informar brancos; ou para tratamento de arquivo teste:
                    cliente, antes de realizar os procedimentos abaixo,entre em contato
                    com sua agência, pois a situação de seu intercâmbio eletrônico de
                    dados deverá ser alterado de ATIVO para TESTE.
                    11.1 BB5 Campo que identifica remessa de testes 52 53 2 Alfanumérico Importante que nesse caso não deverá ser enviado arquivos para a produção, pois sua condição foi alterada para TESTE.
                    Obs.: Caso a empresa queira efetuar TESTE pelo sistema, com
                    geração
                    de arquivo retorno TESTE pelo Gerenciador Financeiro, basta
                    substituir os espaços em branco (posições 52 e 53) por "TS".
                    Caso não queira realizar os testes, informe brancos*/
                fbranco().length(2));

        TagLayout detalheSegmentoP = _LAYOUT_BB_CNAB240.get(detalheSegmentoP());
        //Importante:todos os "nosso número" devem ser alinhados à esquerda com brancos à direita. 
        TagLayout fnossoNumeroDetalhe = detalheSegmentoP.get(fnossoNumero());
        fnossoNumeroDetalhe.filler(Fillers.WHITE_SPACE_RIGHT);
//        fnossoNumeroDetalhe.length(11);
//        detalheSegmentoP.insertBefore(fnossoNumeroDetalhe, fconvenio().length(9));
        //System.out.println(detalheSegmentoP);
        //System.out.println(fnossoNumeroDetalhe);

    }

    static final TagLayout _LAYOUT_SANTANDER_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {
        //TODO: Fazer personalizações Santander
    }

    static final TagLayout _LAYOUT_ITAU_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {
        //TODO: Fazer personalizações Itau
    }

    public static final TagLayout LAYOUT_FEBRABAN_CNAB240
            = _LAYOUT_FEBRABAN_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_SICREDI_CNAB240
            = _LAYOUT_SICREDI_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240
            = _LAYOUT_BB_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_SANTANDER_CNAB240
            = _LAYOUT_SANTANDER_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240
            = _LAYOUT_ITAU_CNAB240.cloneReadonly();

    static final TagLayout _LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_FEBRABAN_CNAB240.clone();

    static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SICREDI_CNAB240.clone();

    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_BB_CNAB240.clone();

    static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SANTANDER_CNAB240.clone();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240.clone();

    static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_CAIXA_CNAB240_REMESSA.clone();

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA
            = _LAYOUT_BRADESCO_CNAB400_REMESSA.clone();

    static {
        _LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).value('1');
        
        _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).value('1');
        
        _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(cabecalho())
        	.get(fcodigoArquivo()).value('1');
        
    	_LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).value('1');
    	
        _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.get(cabecalho())
        	.get(fcodigoArquivo()).value('1');
        
        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho())
        	.get(fcodigoArquivo()).value('1');
        
        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho())
        	.get(fcodigoArquivo()).value('1');
        
        _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.get(cabecalho())
        	.get(fcodigoArquivo()).value('1');
    }

    public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA
            = _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.cloneReadonly();

    static final TagLayout _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_FEBRABAN_CNAB240.clone();

    static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SICREDI_CNAB240.clone();

    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_BB_CNAB240.clone();

    static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SANTANDER_CNAB240.clone();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240.clone();

    static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_CAIXA_CNAB240_RETORNO.clone();

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO
            = _LAYOUT_BRADESCO_CNAB400_RETORNO.clone();

    static {
        _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');

        _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');

        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');
        
        _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');
        
        _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');

        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');
        
        _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');
    }

    public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.cloneReadonly();

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

    static {
            _LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

            _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');
    }

    public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO = _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO
                    .cloneReadonly();

    static final List<TagLayout> layoutsSuportados;

    static {
        List<TagLayout> layoutsSuportadosTmp = new ArrayList<>();
        /* */
        //TODO: Adicionar teste e layouts homologados
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_BB_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO);

        /* */
        layoutsSuportados = Collections.unmodifiableList(layoutsSuportadosTmp);
    }

    public static TagLayout getLayoutArquivoBancarioRemessaCobranca(String codBanco, String numConvenio,
            String agencia, String conta, String carteira, Boolean registrado) {
        return getLayoutArquivoBancario(CNABServico.COBRANCA_REMESSA, CNAB_240,
                codBanco, numConvenio, agencia, conta, carteira, registrado);
    }

    public static TagLayout getLayoutArquivoBancario(String codBanco) {
        return getLayoutArquivoBancario(null, CNAB_240, codBanco,
                null, null, null, null, null);
    }

    public static TagLayout getLayoutArquivoBancarioRemessaCobranca(String codBanco) {
        return getLayoutArquivoBancario(CNABServico.COBRANCA_REMESSA, CNAB_240, codBanco,
                null, null, null, null, null);
    }

    //TODO: Implementar um metodo de busca por layouts, a partir de atributos relevantes
    public static TagLayout getLayoutArquivoBancario(CNABServico servico, CNAB cnab, String codBanco,
            String convenio, String agencia, String conta, String carteira, Boolean registrado) {
        TagLayout ret = null;
        for (TagLayout layout : layoutsSuportados) {
            TagLayout descritor = layout.get("layout");
            if (descritor != null
                    && eq(descritor.getValue("banco"), codBanco)
                    && eq(descritor.getValue("cnab"), cnab)
                    && (servico == null || eq(descritor.getValue("servico"), servico))
                    && (convenio == null || eq(descritor.getValue("convenio"), convenio))
                    && (carteira == null || eq(descritor.getValue("carteira"), carteira))) {
                ret = layout;
                break;
            }
        }
        return ret;
    }

    public static boolean eq(Object value1, Object value2) {
        return value1 == value2
                || value1 != null && value1.equals(value2)
                || value2 != null && value2.equals(value1);
    }
}