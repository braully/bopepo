package com.github.braully.boleto;

import org.jrimum.bopepo.parametro.ParametroBancoSicredi;
import org.jrimum.bopepo.parametro.ParametroCaixaEconomicaFederal;
import org.jrimum.bopepo.view.BoletoViewer;
import org.junit.Test;

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

/**
 * @author braully
 */
public class TestBoletoFacade {

    @Test
    public void testBoletoSimples() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Sacado da Silva Sauro").sacadoCpf("7068093868");
        boletoFacade.banco("1").agencia("1").conta("1");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("60746948000112");
        //boletoFacade.covenio("1");
        boletoFacade.carteira("1");
        boletoFacade.numeroDocumento("1")
                .nossoNumero("1234567890")
                .valor(100.23).dataVencimento("01/01/2019");

        boletoFacade.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste.pdf");
    }

    //Teste adaptado do exemplo: 
    //https://github.com/jrimum/bopepo/blob/b0168a10c234b73e6eab681c4d508397208b477b/src/examples/java/org/jrimum/bopepo/exemplo/banco/sicredi/BoletoSicrediExemplo.java
    @Test
    public void testBoletoSicredi() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Sacado da Silva Sauro").sacadoCpf("07068093868");
        boletoFacade.banco("748").agencia("1").conta("1").carteira("1");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("60746948000112");
        //boletoFacade.covenio("1");
        boletoFacade.carteira("1");
        boletoFacade.numeroDocumento("1")
                //Nosso numero do sicredi exige digito verificador
                .nossoNumero("12345678-0")
                .valor(100.23).dataVencimento("01/01/2019");

        boletoFacade.parametroBancario(ParametroBancoSicredi.POSTO_DA_AGENCIA, 2);

//        boletoFacade.getTitulo().
//                setParametrosBancarios(
//                        new ParametrosBancariosMap(ParametroBancoSicredi.POSTO_DA_AGENCIA, 2));
        boletoFacade.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste-sicredi.pdf");
    }

    @Test
    public void testBoletoBancoBradesco() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Teste da silva Sauro")
                .sacadoCpf("07068093868");
        boletoFacade.banco("237").agencia("4534-9").conta("188-999");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("60746948000112");
        boletoFacade.carteira("1");
        boletoFacade.numeroDocumento("1")
                .nossoNumero("12345678901")
                .valor(100.23).dataVencimento("01/01/2019");

        boletoFacade.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste-bradesco.pdf");
    }

    @Test
    public void testBoletoBancoSantander() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Teste do banco santander")
                .sacadoCpf("04895540162");
        boletoFacade.banco("033").agencia("4536").conta("10008-6");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("22.413.806/0001-44");
        boletoFacade.carteira("101").carteiraCobrancaRegistrada(Boolean.TRUE);
        boletoFacade.getTitulo();
        boletoFacade.numeroDocumento("36744")
                .nossoNumero("56612457800-2")
                .valor(100.23).dataVencimento("10/08/2012");
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste-santander.pdf");
    }

    @Test
    public void testBoletoBancoCaixa() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Sacado da Silva Sauro").sacadoCpf("07068093868");
        boletoFacade.banco("104").agencia("1").conta("1");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("22.413.806/0001-44");
        //boletoFacade.covenio("1");
        boletoFacade.carteira("1");
        boletoFacade.numeroDocumento("1")
                .nossoNumero("3234567890")
                .valor(100.23).dataVencimento("01/01/2019");

        boletoFacade.parametroBancario(ParametroCaixaEconomicaFederal.CODIGO_OPERACAO, 2);

        boletoFacade.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste-bancocaixa.pdf");
    }

    @Test
    public void testBoletoBancoItau() {
        BoletoCobranca boletoFacade = new BoletoCobranca();
        boletoFacade.sacado("Sacado da Silva Sauro").sacadoCpf("07068093868");
        boletoFacade.banco("341").agencia("1").conta("1");
        boletoFacade.cedente("Cedente da Silva Sauro").cedenteCnpj("Y9.T2X.T5C/0001-37");
        //boletoFacade.covenio("1");
        boletoFacade.carteira("1");
        boletoFacade.numeroDocumento("1")
                .nossoNumero("323456-7")
                .valor(100.23).dataVencimento("01/01/2019");

        boletoFacade.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boletoFacade);
        create.getPdfAsFile("./target/teste-bancoitau.pdf");
    }
}
