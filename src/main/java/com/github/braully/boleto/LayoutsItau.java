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
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalhoLote;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoA;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoB;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;
import static com.github.braully.boleto.TagLayout.TagCreator.rodape;
import static com.github.braully.boleto.TagLayout.TagCreator.rodapeLote;

/**
 *
 * @author strike
 */
public class LayoutsItau {

    public static final TagLayout _LAYOUT_ITAU_CNAB240 = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240();

    public static final TagLayout LAYOUT_ITAU_CNAB240
            = _LAYOUT_ITAU_CNAB240.cloneReadonly();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240.clone();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    static final TagLayout _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240.clone();

    public static final TagLayout LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    static {
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).value(CNABServico.COBRANCA_RETORNO);
        _LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');

        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(layout())
                .get(fservico()).value(CNABServico.COBRANCA_REMESSA);
        _LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');
    }

    private static final TagLayout _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_PAGAMENTO_REMESSA();

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

    public static final TagLayout LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA.cloneReadonly();

}
