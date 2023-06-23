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

import static com.github.braully.boleto.LayoutsSuportados._LAYOUT_FEBRABAN_CNAB240;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;

/**
 *
 * @author strike
 */
public class LayoutsSantander {

    static final TagLayout _LAYOUT_SANTANDER_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {
        // personalizações Santander

        String codigoSantander = "033";
        String campoBancoNome = "bancoNome";
        String campoBancoCodigo = "bancoCodigo";

        // Layout
        TagLayout layout = _LAYOUT_SANTANDER_CNAB240.get(layout());
        layout.get("nome").value("Layout Padrão Santander CNAB240 Pagamento Remessa");
        layout.get("banco").value(codigoSantander);
        layout.get("url").withValue(
                "https://cms.santander.com.br/sites/WPS/documentos/arq-layout-de-arquivos-1/17-10-26_171722_258-37-pagamento+a+fornecedores+layout+cnab+240+-+v10.pdf");
        layout.get("versao").value("80");

        // Cabeçalho
        TagLayout cabecalho = _LAYOUT_SANTANDER_CNAB240.get(cabecalho());
        cabecalho.get(campoBancoNome).value("Banco Santander (Brasil) S.A.");
        cabecalho.get(campoBancoCodigo).value(codigoSantander);

    }

    static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SANTANDER_CNAB240.clone();

    static final TagLayout _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SANTANDER_CNAB240.clone();

    static final TagLayout _LAYOUT_SANTANDER_CNAB240_PAGAMENTO = _LAYOUT_SANTANDER_CNAB240.clone();

    static {
        _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).value('1');
        _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.get(layout())
                .get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);
        TagLayout layout = _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.get(layout());
        layout.get(fservico()).withValue(CNABServico.COBRANCA_REMESSA);

        TagLayout cabecalho = _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.get(cabecalho());
        cabecalho.get(fcodigoArquivo()).value('2');
        layout = _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.get(layout());
        layout.get(fservico()).withValue(CNABServico.COBRANCA_RETORNO);

        _LAYOUT_SANTANDER_CNAB240_PAGAMENTO.get(cabecalho()).get(fcodigoArquivo()).value('1');
        layout = _LAYOUT_SANTANDER_CNAB240_PAGAMENTO.get(layout());
        layout.get(fservico()).withValue(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);

    }
    public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA = _LAYOUT_SANTANDER_CNAB240.cloneReadonly();
    public static final TagLayout LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_SANTANDER_CNAB240
            = _LAYOUT_SANTANDER_CNAB240.cloneReadonly();

}
