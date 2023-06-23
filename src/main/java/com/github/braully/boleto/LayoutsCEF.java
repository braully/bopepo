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

import com.github.braully.boleto.TagLayout;
import static com.github.braully.boleto.CNAB.CNAB_240;
import static com.github.braully.boleto.TagLayout.TagCreator.*;
import java.text.SimpleDateFormat;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author strike
 */
public class LayoutsCEF {

    /**
     * Layout padrão da caixa economica federal
     */
    static final TagLayout _LAYOUT_CAIXA_CNAB240_REMESSA = flatfile(
            layout(nome("Layout Padrão Caixa Econômica Federal CNAB240 Remessa"),
                    cnab(CNAB_240),
                    banco("104"),
                    servico(CNABServico.COBRANCA_REMESSA),
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
                    servico(CNABServico.COBRANCA_RETORNO),
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

    /*
    Criando o layout de remessa da caixa econmica dedera
     */
    static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_CAIXA_CNAB240_REMESSA.clone();


    /*
        Setando as diferenças do layout padrão para o layout de retorno;
        Setando as diferenças do layout padrão para o layout de remesssa;

     */
    static final TagLayout _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_CAIXA_CNAB240_RETORNO.clone();

    static {
        _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).withValue('1');
        _LAYOUT_CAIXA_CNAB240_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);

        _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).withValue('2');
        _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);
    }

    /**
     * Externalizando layouts criados
     */
    public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO.cloneReadonly();

}
