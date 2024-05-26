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
import static com.github.braully.boleto.TagLayout.TagCreator.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jrimum.bopepo.BancosSuportados;

/**
 *
 * @author Braully Rocha da Silva
 */
//TODO: Revisar layout de terceiros que vieram via pull request
public class LayoutsSuportados {

    static TagLayout _LAYOUT_FEBRABAN_CNAB240 = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_V05();

    static final TagLayout LAYOUT_FEBRABAN_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.cloneReadonly();

    static final TagLayout _LAYOUT_SICREDI_CNAB240 = _LAYOUT_FEBRABAN_CNAB240.clone();

    static {
        //Layout
        TagLayout layout = _LAYOUT_SICREDI_CNAB240.get(layout());
        layout.get("nome").value("Layout Padrão SICREDI CNAB240");
        layout.get("banco").value("748");
        layout.get("url").value("https://www.sicredi.com.br/html/para-sua-empresa/recebimentos/cobranca/");
    }

    public static final TagLayout LAYOUT_SICREDI_CNAB240 = _LAYOUT_SICREDI_CNAB240.cloneReadonly();

    static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SICREDI_CNAB240.clone();

    static {

        _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.get(cabecalho())
                .get(fcodigoArquivo()).value('1');

    }

    public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA
            = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA().cloneReadonly();

    public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    static final TagLayout _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SICREDI_CNAB240.clone();

    static {

        _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');

    }

    public static final TagLayout LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO
            = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO().cloneReadonly();

    public static final TagLayout LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    static {
        _LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');

// Retorno
        _LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO.get(cabecalho()).get(fcodigoArquivo()).value('2');
    }

    static final List<TagLayout> layoutsSuportados;

    static {
        List<TagLayout> layoutsSuportadosTmp = new ArrayList<>();
        /* */
        //TODO: Adicionar teste e layouts homologados
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240);
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO);
//        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240);
//        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_REMESSA);
//        layoutsSuportadosTmp.add(LAYOUT_SICREDI_CNAB240_COBRANCA_RETORNO);
//        layoutsSuportadosTmp.add(LayoutsBB.LAYOUT_BB_CNAB240);
//        layoutsSuportadosTmp.add(LayoutsBB.LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
//        layoutsSuportadosTmp.add(LayoutsBB.LAYOUT_BB_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LayoutsBB.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA);
        layoutsSuportadosTmp.add(LayoutsSantander.LAYOUT_SANTANDER_CNAB240);
        layoutsSuportadosTmp.add(LayoutsSantander.LAYOUT_SANTANDER_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LayoutsSantander.LAYOUT_SANTANDER_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LayoutsSantander.LAYOUT_SANTANDER_CNAB240_PAGAMENTO_REMESSA);
        layoutsSuportadosTmp.add(LayoutsItau.LAYOUT_ITAU_CNAB240);
        layoutsSuportadosTmp.add(LayoutsItau.LAYOUT_ITAU_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LayoutsItau.LAYOUT_ITAU_CNAB240_COBRANCA_RETORNO);
        layoutsSuportadosTmp.add(LayoutsItau.LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA);
        layoutsSuportadosTmp.add(LayoutsCEF.LAYOUT_CAIXA_CNAB240_COBRANCA_REMESSA);
        layoutsSuportadosTmp.add(LayoutsCEF.LAYOUT_CAIXA_CNAB240_COBRANCA_RETORNO);
        //FIXME: Falha ao carregar bb
//        layoutsSuportadosTmp.add(LayoutsBradesco.LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA);
//        layoutsSuportadosTmp.add(LayoutsBradesco.LAYOUT_BRADESCO_CNAB400_COBRANCA_RETORNO);
//        layoutsSuportadosTmp.add(LayoutsBradesco.LAYOUT_BRADESCO_CNAB240_PAGAMENTO_REMESSA);

        /* */
        layoutsSuportados = Collections.unmodifiableList(layoutsSuportadosTmp);
    }

    public static List<TagLayout> getLayoutsCNAB240PagamentoRemessa() {
        return getLayouts(CNAB_240, CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
    }

    public static TagLayout getLayoutArquivoBancarioCNAB240RemessaCobranca(BancosSuportados banco) {
        return getLayoutArquivoBancarioCNAB240Servico(banco.getCodigoDeCompensacao(), CNABServico.COBRANCA_REMESSA);
    }

    public static TagLayout getLayoutArquivoBancarioCNAB240RetornoCobranca(BancosSuportados banco) {
        return getLayoutArquivoBancarioCNAB240Servico(banco.getCodigoDeCompensacao(), CNABServico.COBRANCA_RETORNO);
    }

    public static TagLayout getLayoutArquivoBancarioCNAB240Servico(String strbanco, CNABServico servico) {
        TagLayout t = null;
        List<TagLayout> layouts = getLayouts(CNAB_240, servico);
        for (TagLayout l : layouts) {
            TagLayout descritor = l.get(layout());
            if (eq(descritor.getValue("banco"), strbanco)) {
                t = l;
                break;
            }
        }
        return t;
    }

    public static List<TagLayout> getLayouts(CNAB cnab, CNABServico servico) {
        List<TagLayout> layouts = new ArrayList<>();
        for (TagLayout layout : layoutsSuportados) {
            TagLayout descritor = layout.get("layout");
            if (descritor != null
                    && eq(descritor.getValue("servico"), servico)
                    && eq(descritor.getValue("cnab"), cnab)) {
                layouts.add(layout);
            }
        }
        return layouts;
    }

    public static TagLayout getLayoutCNAB240PagamentoRemessa(String banco) {
        TagLayout layoutBanco = null;
        List<TagLayout> layoutsCNAB240PagamentoRemessa = getLayoutsCNAB240PagamentoRemessa();
        for (TagLayout layout : layoutsCNAB240PagamentoRemessa) {
            TagLayout descritor = layout.get("layout");
            if (descritor != null
                    && eq(descritor.getValue("banco"), banco)) {
                layoutBanco = layout;
                break;
            }
        }
        return layoutBanco;
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

    public static TagLayout getLayoutArquivoBancario(CNABServico servico, CNAB cnab, String codBanco) {
        return getLayoutArquivoBancario(servico, CNAB_240, codBanco,
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
