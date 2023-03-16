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

import static com.github.braully.boleto.TagLayout.TagCreator.banco;
import static com.github.braully.boleto.TagLayout.TagCreator.cabecalho;
import static com.github.braully.boleto.TagLayout.TagCreator.cnab;
import static com.github.braully.boleto.TagLayout.TagCreator.descricao;
import static com.github.braully.boleto.TagLayout.TagCreator.fagencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fbranco;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoRegistro;
import static com.github.braully.boleto.TagLayout.TagCreator.fcodigoRetorno;
import static com.github.braully.boleto.TagLayout.TagCreator.fconta;
import static com.github.braully.boleto.TagLayout.TagCreator.fdataGeracao;
import static com.github.braully.boleto.TagLayout.TagCreator.flatfile;
import static com.github.braully.boleto.TagLayout.TagCreator.fquantidadeRegistros;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorTotalRegistros;
import static com.github.braully.boleto.TagLayout.TagCreator.fzero;
import static com.github.braully.boleto.TagLayout.TagCreator.layout;
import static com.github.braully.boleto.TagLayout.TagCreator.nome;
import static com.github.braully.boleto.TagLayout.TagCreator.rodape;
import static com.github.braully.boleto.TagLayout.TagCreator.servico;
import static com.github.braully.boleto.TagLayout.TagCreator.titulo;
import static com.github.braully.boleto.TagLayout.TagCreator.versao;

import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author braully
 */
public class TestRemessaFacade {

    @Ignore
    @Test
    public void testRemessaCobancaGenericaFebraban240V5SegmentoPQ() {
        RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
        remessa.addNovoCabecalho()
                .sequencialArquivo(1)
                .dataGeracao(new Date()).setVal("horaGeracao", new Date())
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoCabecalhoLote()
                .operacao("R")//Operação de remessa
                .servico(1)//Cobrança
                .forma(1)//Crédito em Conta Corrente
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1");

        remessa.addNovoDetalheSegmentoP()
                .valor(1)
                .valorDesconto(0).valorAcrescimo(0)//opcionais
                .dataGeracao(new Date())
                .dataVencimento(new Date())
                .numeroDocumento(1)
                .nossoNumero(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .sequencialRegistro(1);

        remessa.addNovoDetalheSegmentoQ()
                .sacado("Fulano de Tal", "0")
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .sequencialRegistro(2);

        remessa.addNovoRodapeLote()
                .quantidadeRegistros(2)
                .valorTotalRegistros(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoRodape()
                .quantidadeRegistros(1)
                .valorTotalRegistros(1)
                .setVal("codigoRetorno", "1")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        String remessaStr = remessa.render();
        System.err.println(remessaStr);
    }

    @Test
    public void testRemessaCobancaBBFebraban240V5SegmentoPQ() {
        RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
        remessa.addNovoCabecalho()
                .sequencialArquivo(1)
                .dataGeracao(new Date()).setVal("horaGeracao", new Date())
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .carteira("00");

        remessa.addNovoCabecalhoLote()
                .operacao("R")//Operação de remessa
                .servico(1)//Cobrança
                .forma(1)//Crédito em Conta Corrente
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .carteira("00");;

        remessa.addNovoDetalheSegmentoP()
                .valor(1)
                .valorDesconto(0).valorAcrescimo(0)//opcionais
                .dataGeracao(new Date())
                .dataVencimento(new Date())
                .numeroDocumento(1)
                .nossoNumero(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .sequencialRegistro(1)
                .carteira("00");

        remessa.addNovoDetalheSegmentoQ()
                .sacado("Fulano de Tal", "0")
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .sequencialRegistro(2)
                .carteira("00");;

        remessa.addNovoRodapeLote()
                .quantidadeRegistros(2)
                .valorTotalRegistros(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .carteira("00");;

        remessa.addNovoRodape()
                .quantidadeRegistros(1)
                .valorTotalRegistros(1)
                .setVal("codigoRetorno", "1")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1")
                .carteira("00");

        String remessaStr = remessa.render();
        System.err.println(remessaStr);
    }

    @Ignore
    @Test
    public void testRemessaCobancaGenericaFebraban240V5() {
        RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
        remessa.addNovoCabecalho()
                .sequencialArquivo(1)
                .dataGeracao(new Date()).setVal("horaGeracao", new Date())
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoCabecalhoLote()
                .operacao("R")//Operação de remessa
                .servico(1)//Cobrança
                .forma(1)//Crédito em Conta Corrente
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1");

        remessa.addNovoDetalheSegmentoJ()
                .sacado("Fulano de Tal", "0")
                .codigoBarras("0")
                .valor(1)
                .valorDesconto(0).valorAcrescimo(0)//opcionais
                .dataVencimento(new Date())
                .numeroDocumento(1)
                .nossoNumero(1)
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1")
                .sequencialRegistro(1);

        remessa.addNovoDetalheSegmentoJ52()
                .sacado("Fulano de Tal", "0")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1")
                .sequencialRegistro(2);

        remessa.addNovoRodapeLote()
                .quantidadeRegistros(2)
                .valorTotalRegistros(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoRodape()
                .quantidadeRegistros(1)
                .valorTotalRegistros(1)
                .setVal("codigoRetorno", "1")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        String remessaStr = remessa.render();
        System.err.println(remessaStr);
//        assertEquals(remessaStr, "");
    }

    @Ignore
    @Test
    public void testRemessaCobancaGenericaSicredi240V5() {
        RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.LAYOUT_FEBRABAN_CNAB240_COBRANCA_REMESSA);
        remessa.addNovoCabecalho()
                .sequencialArquivo(1)
                .dataGeracao(new Date()).setVal("horaGeracao", new Date())
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoCabecalhoLote()
                .operacao("R")//Operação de remessa
                .servico(1)//Cobrança
                .forma(1)//Crédito em Conta Corrente
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1")
                .convenio("1", "1", "1", "1");

        remessa.addNovoDetalheSegmentoJ()
                .sacado("Fulano de Tal", "0")
                .codigoBarras("0")
                .valor(1)
                .valorDesconto(0).valorAcrescimo(0)//opcionais
                .dataVencimento(new Date())
                .numeroDocumento(1)
                .nossoNumero(1)
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1")
                .sequencialRegistro(1);

        remessa.addNovoDetalheSegmentoJ52()
                .sacado("Fulano de Tal", "0")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1")
                .sequencialRegistro(2);

        remessa.addNovoRodapeLote()
                .quantidadeRegistros(2)
                .valorTotalRegistros(1)
                .banco("0", "Banco")
                .cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        remessa.addNovoRodape()
                .quantidadeRegistros(1)
                .valorTotalRegistros(1)
                .setVal("codigoRetorno", "1")
                .banco("0", "Banco").cedente("ACME S.A LTDA.", "1").convenio("1", "1", "1", "1");

        String remessaStr = remessa.render();
        System.err.println(remessaStr);
//        assertEquals(remessaStr, "");
    }

    @Ignore
    @Test
    public void testRemessaVazia() {
        RemessaFacade remessa = new RemessaFacade(layoutGenericoTest());
        remessa.addNovoCabecalho().agencia("1")
                .conta("1").numeroConvenio("1")
                .cedente("ACME S.A LTDA.").cedenteCnpj("1")
                .dataGeracao(new Date()).setValue("codigoRetorno", "1");

        remessa.addNovoDetalhe().valor("1").vencimento("1")
                .numeroDocumento("1").nossoNumero("1")
                .dataEmissao("1").carteira("1")
                .sacado("Fulano de Tal").sacadoCpf("1")
                .sacadoEndereco("Rua 1, Numero 1, Cidade Z")
                .instrucao("Senhor caixa não receber nunca");

        remessa.addNovoRodape()
                .quantidadeRegistros(1)
                .valorTotalRegistros(1)
                .setValue("codigoRetorno", "1");

        String remessaStr = remessa.render();
        System.err.println(remessaStr);
//        assertEquals(remessaStr, "");
    }



	@Test
	public void testGetLayoutCNAB240PagamentoRemessa() {

		System.out.println(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("033"));
		System.out.println(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("001"));
		System.out.println(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("237"));
		System.out.println(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("341"));

	}

	@Test
	public void testRemessaPagamentoSantander240() {
		RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("033"));

		Assert.assertEquals(true, remessa.isPermiteQtdeMoeda());


		String razaoSocial = "ACME S.A LTDA.";
		String cnpj = "111.222.33.0001/44";

		String numeroConvenio = "1234567890-1234567890";
		//testando preenchimento automatico do digito veriricador como 0
		String agenciaComDigito = "0123";
		String contaComDigito = "0000123-4";
		String DAC = " ";
		int sequencialRegistro = 1;

		remessa.addNovoCabecalho()
		.dataGeracao(new Date())
		.horaGeracao(new Date())
		.sequencialArquivo(22)
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC);

		remessa.addNovoCabecalhoLote()
				.forma(1)// 1 = Crédito em Conta Corrente mesmo banco 3 = doc/ted outro banco
				.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
				.cedente(razaoSocial, cnpj)
				.endereco("Rua XYZ","123","","São Paulo","12345-123", "SP");


		BigDecimal valorPagamento = new BigDecimal(5.82).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);

		remessa.addNovoDetalheSegmentoA()
		.numeroDocumento("1")
		.formaDeTransferencia("000")
		.favorecidoCodigoBanco("033")
		.favorecidoAgencia("1234-5")
		.favorecidoConta("1234-5")
		 //testando sanitize remover acentos e transformar em maiusculo
		.favorecidoNome("José da Silva")
		.dataPagamento(new Date())
		.valor(valorPagamento)
		.sequencialRegistro(sequencialRegistro);

		remessa.addNovoDetalheSegmentoB()
		.numeroDocumento(1)
		.favorecidoTipoInscricao("1")
		 //testando sanitize apenasNumeros
		.favorecidoCPFCNPJ("111.222.33/4-----55")
		.valor(valorPagamento.toString())
		.sequencialRegistro(sequencialRegistro)
		.setValue("data",new Date())
		.setValue("lote",1);


		RodapeArquivo rodapeLote = remessa.addNovoRodapeLote();

		rodapeLote
		.quantidadeRegistros(24)
		.valorTotalRegistros(valorPagamento.toString())
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.setValue("lote",1);

		if (remessa.isPermiteQtdeMoeda()) {
			rodapeLote.setValue("qtdeMoeda", valorPagamento.multiply(new BigDecimal(100000)).setScale(0).toString());
		}


		remessa.addNovoRodape()
		.quantidadeRegistros(14)
		.quantidadeLotes(1);

		String remessaStr = remessa.render();
		System.out.println(remessaStr);
	}

	@Test
	public void testRemessaPagamentoBradesco240() {
		RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("237"));

		Assert.assertEquals(true, remessa.isPermiteQtdeMoeda());


		String razaoSocial = "EMPRESA TESTE XYZ";
		String cnpj = "11.222.333/0001-44";

		String numeroConvenio = "555555";
		
                //testando preenchimento automatico do digito veriricador como 0
		String agenciaComDigito = "01110";
		String contaComDigito = "0011111-1";
		String DAC = " ";
		int sequencialRegistro = 1;

                String numeroDocumento = "";


		remessa.addNovoCabecalho()
		.dataGeracao(new Date())
		.horaGeracao(new Date())
		.sequencialArquivo(22)
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC);

		remessa.addNovoCabecalhoLote()
				.forma(1)// 1 = Crédito em Conta Corrente mesmo banco 3 = doc/ted outro banco
				.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
				.cedente(razaoSocial, cnpj)
				.endereco("AV TESTE","111","","São Paulo","01104010", "SP");


		BigDecimal valorPagamento = new BigDecimal(5.82).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);

                if (remessa.isExigeNumeroDocumento()) {
                        numeroDocumento = "1";
		}

		remessa.addNovoDetalheSegmentoA()
		.numeroDocumento(numeroDocumento)
		.formaDeTransferencia("000")
		.favorecidoCodigoBanco("033")
		.favorecidoAgencia("1234-5")
		.favorecidoConta("1234-5")
		 //testando sanitize remover acentos e transformar em maiusculo
		.favorecidoNome("José da Silva")
		.dataPagamento(new Date())
		.valor(valorPagamento)
		.sequencialRegistro(sequencialRegistro);

		remessa.addNovoDetalheSegmentoB()
		.numeroDocumento(1)
		.favorecidoTipoInscricao("1")
		 //testando sanitize apenasNumeros
		.favorecidoCPFCNPJ("111.222.33/4-----55")
		.valor(valorPagamento.toString())
		.sequencialRegistro(sequencialRegistro)
		.setValue("data",new Date())
		.setValue("lote",1);


		RodapeArquivo rodapeLote = remessa.addNovoRodapeLote();

		rodapeLote
		.quantidadeRegistros(24)
		.valorTotalRegistros(valorPagamento.toString())
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.setValue("lote",1);

		if (remessa.isPermiteQtdeMoeda()) {
			rodapeLote.setValue("qtdeMoeda", valorPagamento.multiply(new BigDecimal(100000)).setScale(0).toString());
		}


		remessa.addNovoRodape()
		.quantidadeRegistros(14)
		.quantidadeLotes(1);

		String remessaStr = remessa.render();
		System.out.println(remessaStr);
	}


	@Test
	public void testRemessaPagamentoBB240() {
		RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("001"));

		Assert.assertEquals(false, remessa.isPermiteQtdeMoeda());

		String razaoSocial = "ACME S.A LTDA.";
		String cnpj = "111.222.33.0001/44";

		String numeroConvenio = "12345678";
		String agenciaComDigito = "0123-X";
		String contaComDigito = "0000123-X";
		String DAC = " ";
		int sequencialRegistro = 1;
                String numeroDocumento = "";

		remessa.addNovoCabecalho()
		.dataGeracao(new Date())
		.horaGeracao(new Date())
		.sequencialArquivo(22)
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC);

		remessa.addNovoCabecalhoLote()
		.forma(1)// 1 = Crédito em Conta Corrente mesmo banco 3 = doc/ted outro banco
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.cedente(razaoSocial, cnpj)
		.endereco("Rua XYZ","123","","São Paulo","12345-123", "SP");

		BigDecimal valorPagamento = new BigDecimal(5.82).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);

                if (remessa.isExigeNumeroDocumento()) {
                        numeroDocumento = "1";
		}

		remessa.addNovoDetalheSegmentoA()
		.numeroDocumento("1")
		.formaDeTransferencia("000")
		.favorecidoCodigoBanco("033")
		.favorecidoAgencia("1234-5")
		.favorecidoConta("1234-5")
                .numeroDocumento(numeroDocumento)
		 //testando sanitize remover acentos e transformar em maiusculo
		.favorecidoNome("José da Silva")
		.dataPagamento(new Date())
		.valor(valorPagamento)
		.sequencialRegistro(sequencialRegistro);

		sequencialRegistro++;

		remessa.addNovoDetalheSegmentoB()
		.numeroDocumento(1)
		.favorecidoTipoInscricao("1")
		 //testando sanitize apenasNumeros
		.favorecidoCPFCNPJ("111.222.33/4-----55")
		.valor(valorPagamento.toString())
		.sequencialRegistro(sequencialRegistro)
		.setValue("data",new Date())
		.setValue("lote",1);

		RodapeArquivo rodapeLote = remessa.addNovoRodapeLote();


		rodapeLote
		.quantidadeRegistros(24)
		.valorTotalRegistros(valorPagamento.toString())
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.setValue("lote",1);

		if (remessa.isPermiteQtdeMoeda()) {
			rodapeLote.setValue("qtdeMoeda", valorPagamento.multiply(new BigDecimal(100000)).setScale(0).toString());
		}

		remessa.addNovoRodape()
		.quantidadeRegistros(18)
		.quantidadeLotes(1);

		String remessaStr = remessa.render();
		System.out.println(remessaStr);
	}

	@Test
	public void testRemessaPagamentoItau240() {
		RemessaFacade remessa = new RemessaFacade(LayoutsSuportados.getLayoutCNAB240PagamentoRemessa("341"));

		Assert.assertEquals(true, remessa.isPermiteQtdeMoeda());


		String razaoSocial = "ACME S.A LTDA.";
		String cnpj = "111.222.33.0001/44";

		String numeroConvenio = "12345678";
		String agenciaComDigito = "0123-1";
		String contaComDigito = "0000123-1";
		String DAC = " ";
		int sequencialRegistro = 1;
                String numeroDocumento = "";

		remessa.addNovoCabecalho()
		.dataGeracao(new Date())
		.horaGeracao(new Date())
		.sequencialArquivo(22)
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC);

		remessa.addNovoCabecalhoLote()
		.forma(1)// 1 = Crédito em Conta Corrente mesmo banco 3 = doc/ted outro banco
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.cedente(razaoSocial, cnpj)
		.endereco("Rua XYZ","123","","São Paulo","12345-123", "SP");


		BigDecimal valorPagamento = new BigDecimal(5.82).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);

                if (remessa.isExigeNumeroDocumento()) {
                        numeroDocumento = "123456";
		}

		remessa.addNovoDetalheSegmentoA()
		.numeroDocumento(numeroDocumento)
		.formaDeTransferencia("000")
		.favorecidoCodigoBanco("341")
		.favorecidoAgencia("1234-5")
		.favorecidoConta("1234-5")
		 //testando sanitize remover acentos e transformar em maiusculo
		.favorecidoNome("José da Silva")
		.dataPagamento(new Date())
		.valor(valorPagamento)
		.sequencialRegistro(sequencialRegistro);

		sequencialRegistro++;

		remessa.addNovoDetalheSegmentoB()
		.numeroDocumento(1)
		.favorecidoTipoInscricao("1")
		 //testando sanitize apenasNumeros
		.favorecidoCPFCNPJ("111.222.33/4-----55")
		.valor(valorPagamento.toString())
		.sequencialRegistro(sequencialRegistro)
		.setValue("data",new Date())
		.setValue("lote",1);

		RodapeArquivo rodapeLote = remessa.addNovoRodapeLote();


		rodapeLote
		.quantidadeRegistros(24)
		.valorTotalRegistros(valorPagamento.toString())
		.cedente(razaoSocial, cnpj)
		.convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
		.setValue("lote",1);

		if (remessa.isPermiteQtdeMoeda()) {
			rodapeLote.setValue("qtdeMoeda", valorPagamento.multiply(new BigDecimal(100000)).setScale(0).toString());
		}

		remessa.addNovoRodape()
		.quantidadeRegistros(18)
		.quantidadeLotes(1);

		String remessaStr = remessa.render();
		System.out.println(remessaStr);
	}




    public TagLayout layoutGenericoTest() {
        TagLayout flatfileLayout = flatfile(
                /*
                <layout>
                <name>Arquivo-Febraban_CNAB400</name>
                <version>Version 00</version>
                <description>
                Layout padrão do Febraban
                </description>
                </layout>
                 */
                layout(
                        nome("Arquivo-Febraban_CNAB400"),
                        descricao("Layout padrão do Febraban"),
                        versao("01"),
                        banco(BancosSuportados.BANCO_DO_BRASIL.create()),
                        cnab(CNAB.CNAB_400),
                        servico(CNABServico.COBRANCA_REMESSA)
                ),
                /*
                        <GroupOfRecords>
                        <Record name="cabecalho" description="Protocolo de comunicação">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1" value="0" />
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="AGENCIA" length="4" type="INTEGER" padding="ZERO_LEFT" />
                        <Field name="DATA_ARQUIVO" length="6" type="DATE" format="DATE_DDMMYY" />
                 */
                cabecalho(
                        fcodigoRegistro().value(0),
                        fcodigoRetorno(),
                        fagencia().length(4),
                        fconta().length(7),
                        fdataGeracao()
                ),
                titulo(
                        fcodigoRegistro().value(7)
                ),
                /*
                        <Record name="TRAILLER">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1"  value="9"/>
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="Filler" length="2" />
                        <Field name="CODIGO_BANCO" length="3" />
                        <Field name="Filler" length="10" />
                        <Field name="QUANTIDADE_TITULOS" length="8" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="VALOR_TOTAL_TITULOS" length="15" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="Filler" length="8" />
                        </GroupOfFields>
                        </Record>
                 */
                rodape(
                        fcodigoRegistro().value(9),
                        fcodigoRetorno(),
                        fzero().length(2),
                        fbranco().length(3),
                        fzero().length(10),
                        fquantidadeRegistros().length(8),
                        fvalorTotalRegistros().length(8),
                        fzero().length(8)
                )
        );
        return flatfileLayout;
    }

    public TagLayout layoutBancoBradescoTest() {
        TagLayout flatfileLayout = flatfile(
                /*
                <layout>
                <name>Arquivo-Febraban_CNAB400</name>
                <version>Version 00</version>
                <description>
                Layout padrão do Febraban
                </description>
                </layout>
                 */
                layout(
                        nome("Arquivo-Febraban_CNAB400"),
                        descricao("Layout padrão do Febraban"),
                        versao("01"),
                        banco(BancosSuportados.BANCO_BRADESCO.create()),
                        cnab(CNAB.CNAB_400),
                        servico(CNABServico.COBRANCA_REMESSA)
                ),
                /*
                        <GroupOfRecords>
                        <Record name="cabecalho" description="Protocolo de comunicação">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1" value="0" />
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="AGENCIA" length="4" type="INTEGER" padding="ZERO_LEFT" />
                        <Field name="DATA_ARQUIVO" length="6" type="DATE" format="DATE_DDMMYY" />
                 */
                cabecalho(
                        fcodigoRegistro().value(0),
                        fcodigoRetorno(),
                        fagencia().length(4),
                        fconta().length(7),
                        fdataGeracao()
                ),
                titulo(
                        fcodigoRegistro().value(7)
                ),
                /*
                        <Record name="TRAILLER">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1"  value="9"/>
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="Filler" length="2" />
                        <Field name="CODIGO_BANCO" length="3" />
                        <Field name="Filler" length="10" />
                        <Field name="QUANTIDADE_TITULOS" length="8" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="VALOR_TOTAL_TITULOS" length="15" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="Filler" length="8" />
                        </GroupOfFields>
                        </Record>
                 */
                rodape(
                        fcodigoRegistro().value(9),
                        fcodigoRetorno(),
                        fzero().length(2),
                        fbranco().length(3),
                        fzero().length(10),
                        fquantidadeRegistros().length(8),
                        fvalorTotalRegistros().length(8),
                        fzero().length(8)
                )
        );
        return flatfileLayout;
    }

    public TagLayout layoutBancoSantander() {
        TagLayout flatfileLayout = flatfile(
                /*
                <layout>
                <name>Arquivo-Febraban_CNAB400</name>
                <version>Version 00</version>
                <description>
                Layout padrão do Febraban
                </description>
                </layout>
                 */
                layout(
                        nome("Arquivo-Febraban_CNAB400"),
                        descricao("Layout padrão do Febraban"),
                        versao("01"),
                        banco(BancosSuportados.BANCO_SANTANDER.create()),
                        cnab(CNAB.CNAB_400),
                        servico(CNABServico.COBRANCA_REMESSA)
                ),
                /*
                        <GroupOfRecords>
                        <Record name="cabecalho" description="Protocolo de comunicação">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1" value="0" />
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="AGENCIA" length="4" type="INTEGER" padding="ZERO_LEFT" />
                        <Field name="DATA_ARQUIVO" length="6" type="DATE" format="DATE_DDMMYY" />
                 */
                cabecalho(
                        fcodigoRegistro().value(0),
                        fcodigoRetorno(),
                        fagencia().length(4),
                        fconta().length(7),
                        fdataGeracao()
                ),
                titulo(
                        fcodigoRegistro().value(7)
                ),
                /*
                        <Record name="TRAILLER">
                        <GroupOfFields>
                        <IdType name="CODIGO_REGISTRO" length="1" position="1"  value="9"/>
                        <Field name="CODIGO_RETORNO" length="1" />
                        <Field name="Filler" length="2" />
                        <Field name="CODIGO_BANCO" length="3" />
                        <Field name="Filler" length="10" />
                        <Field name="QUANTIDADE_TITULOS" length="8" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="VALOR_TOTAL_TITULOS" length="15" type="BIGDECIMAL" format="DECIMAL_DD" />
                        <Field name="Filler" length="8" />
                        </GroupOfFields>
                        </Record>
                 */
                rodape(
                        fcodigoRegistro().value(9),
                        fcodigoRetorno(),
                        fzero().length(2),
                        fbranco().length(3),
                        fzero().length(10),
                        fquantidadeRegistros().length(8),
                        fvalorTotalRegistros().length(8),
                        fzero().length(8)
                )
        );
        return flatfileLayout;
    }
}
