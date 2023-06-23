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

/**
 *
 * @author strike
 */
public class LayoutsItau {

    private static final TagLayout _LAYOUT_ITAU_CNAB240 = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240();
    private static final TagLayout _LAYOUT_ITAU_PAGAMENTO = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA();

    static {
        // personalizações Itau

        String codigoBanco = "341";
        String campoBancoNome = "bancoNome";
        String campoBancoCodigo = "bancoCodigo";

        // Layout
        TagLayout layout = _LAYOUT_ITAU_CNAB240.get(layout());
        layout.get("nome").value("Layout Padrão Itau CNAB240");
        layout.get("banco").value(codigoBanco);

        // Cabeçalho
        TagLayout cabecalho = _LAYOUT_ITAU_CNAB240.get(cabecalho());
        cabecalho.get(campoBancoNome).value("BANCO ITAU SA");
        cabecalho.get(campoBancoCodigo).value(codigoBanco);

        cabecalho.get("versaoLayoutArquivo").value("080");

        // cabecalhoLote
        TagLayout cabecalhoLote = _LAYOUT_ITAU_CNAB240.get(cabecalhoLote());
        cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
        cabecalhoLote.get("versaoLayoutLote").value("040");

        // RodapeLote
        TagLayout rodapeLote = _LAYOUT_ITAU_CNAB240.get(rodapeLote());
        rodapeLote.get(campoBancoCodigo).value(codigoBanco);

        // RodapeArquivo
        TagLayout rodapeArquivo = _LAYOUT_ITAU_CNAB240.get(rodape());
        rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);

        layout = _LAYOUT_ITAU_PAGAMENTO.get(layout());
        layout.get("banco").value(codigoBanco);
        layout.get("versao").value("80");
        layout.get("url").value("https://download.itau.com.br/bankline/SISPAG_CNAB.pdf");

        // cabecalhoLote
        cabecalhoLote = _LAYOUT_ITAU_PAGAMENTO.get(cabecalhoLote());
        cabecalhoLote.get(campoBancoCodigo).value(codigoBanco);
        cabecalhoLote.get("versaoLayoutLote").value("040");

        // RodapeLote
        rodapeLote = _LAYOUT_ITAU_PAGAMENTO.get(rodapeLote());
        rodapeLote.get(campoBancoCodigo).value(codigoBanco);

        // RodapeArquivo
        rodapeArquivo = _LAYOUT_ITAU_PAGAMENTO.get(rodape());
        rodapeArquivo.get(campoBancoCodigo).value(codigoBanco);

        // SegmentoA
        TagLayout segmentoA = _LAYOUT_ITAU_PAGAMENTO.get(detalheSegmentoA());
        segmentoA.get(campoBancoCodigo).value(codigoBanco);

        // SegmentoB
        TagLayout segmentoB = _LAYOUT_ITAU_PAGAMENTO.get(detalheSegmentoB());
        segmentoB.get(campoBancoCodigo).value(codigoBanco);
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

}
