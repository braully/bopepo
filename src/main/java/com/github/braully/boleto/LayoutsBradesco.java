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
import static com.github.braully.boleto.TagLayout.TagCreator.*;
import java.text.SimpleDateFormat;
import org.jrimum.texgit.Fillers;

/**
 *
 * @author strike
 */
public class LayoutsBradesco {

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_REMESSA = flatfile(
            layout(nome("Layout Padrão Bradesco CNAB400 Remessa"),
                    cnab(CNAB_400),
                    servico(CNABServico.COBRANCA_REMESSA),
                    banco("237"),
                    tag("url").withValue("https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
                    versao("15")
            ),
            cabecalho(
                    fcodigoRegistro().length(1).value("0"),
                    fcodigoArquivo().length(1).value("1"),
                    fliteralRemessa().length(7).value("REMESSA"),
                    fservico().length(2).val("01"),
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
                    //                    fnossoNumero().length(11).filler(Fillers.ZERO_LEFT),
                    //                    field("digitoNossoNumero").length(1),
                    fnossoNumero().length(12).filler(Fillers.ZERO_LEFT),
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
//                    field("sacadoCep").length(8),
                    fcep().length(8),
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
                    servico(CNABServico.COBRANCA_RETORNO),
                    banco("237"),
                    tag("url").value("https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf"),
                    versao("15")
            ),
            cabecalho(
                    fcodigoRegistro().value("0"),
                    fcodigoArquivo(),
                    fliteralRetorno().length(7),
                    fservico().length(2).val("01"),
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

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA
            = _LAYOUT_BRADESCO_CNAB400_REMESSA.clone();

    static final TagLayout _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO
            = _LAYOUT_BRADESCO_CNAB400_RETORNO.clone();

    static {
        _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).withValue('1');
        _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);

        _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).withValue('2');
        _LAYOUT_BRADESCO_CNAB400_RETORNO.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);

    }

    private static final TagLayout _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA = LayoutsFebraban._LAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA
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

//        //REMOVENDO - A forma de pagamento atual (filho indice 22) E incluindo com valor 01
//        cabecalhoLote.filhos.remove(22);
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

    public static final TagLayout LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA
            = _LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA
            = _LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO = _LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO
            .cloneReadonly();

}
