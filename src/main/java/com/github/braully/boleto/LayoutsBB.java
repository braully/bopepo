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

import com.github.braully.boleto.CNABServico;
import com.github.braully.boleto.TagLayout;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalhoLote;
import static com.github.braully.boleto.TagLayout.TagCreator.detalheSegmentoP;
import static com.github.braully.boleto.TagLayout.TagCreator.fbranco;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fservico;
import static com.github.braully.boleto.TagLayout.TagCreator.fconvenio;
import static com.github.braully.boleto.TagLayout.TagCreator.field;
import static com.github.braully.boleto.TagLayout.TagCreator.fnossoNumero;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;
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
    static final TagLayout _LAYOUT_BB_CNAB240 = LayoutsFebraban.getLAYOUT_FEBRABAN_CNAB240();

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

    }

    // Especializar 
    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_BB_CNAB240.clone();
    static final TagLayout _LAYOUT_BB_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_BB_CNAB240.clone();

    static final TagLayout _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA
            = _LAYOUT_BB_CNAB240.clone();

    static {
//        Alterar o cabeçalho e o tipo de serviço no layout
        _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');
        _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.get(layout()).get(fservico()).value(CNABServico.COBRANCA_REMESSA);

        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(cabecalho())
                .get(fcodigoArquivo()).value('2');
        _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.get(layout())
                .get(fservico()).value(CNABServico.COBRANCA_RETORNO);

        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(cabecalho()).get(fcodigoArquivo()).value('1');
        _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.get(layout())
                .get(fservico()).value(CNABServico.PAGAMENTO_FORNECEDOR_REMESSA);
    }
    public static final TagLayout LAYOUT_BB_CNAB240 = _LAYOUT_BB_CNAB240.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_REMESSA
            = _LAYOUT_BB_CNAB240_COBRANCA_REMESSA.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_COBRANCA_RETORNO
            = _LAYOUT_BB_CNAB240_COBRANCA_RETORNO.cloneReadonly();

    public static final TagLayout LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA
            = _LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA.cloneReadonly();

}
