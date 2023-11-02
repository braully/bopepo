/*
 * Copyright 2023 Braully Rocha da Silva.
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

import static com.github.braully.boleto.LayoutsBB._LAYOUT_BB_CNAB240;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.logging.log4j.util.JsonReader;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public class CLIRemessa implements Runnable {

    @Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    String in;

    @Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    String out;

    @Override
    public void run() {

        if (in == null) {
        }
        JSONObject remjson = null;
        try {
            FileInputStream fileReader = new FileInputStream(in);
            JSONTokener tokener = new JSONTokener(fileReader);
            remjson = new JSONObject(tokener);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLIRemessa.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(remjson);

//        if (true) {
//            return;
//        }
        try {
//            RemessaArquivo remessa = new RemessaArquivo(LayoutsBB._LAYOUT_BB_CNAB240);
            Date dataGeracao = new Date();
            JSONObject cedentejson = remjson.getJSONObject("cedente");
            JSONObject conveniojson = remjson.getJSONObject("convenio");
            JSONObject contaconjson = conveniojson.getJSONObject("conta_bancaria");

            RemessaArquivo remessa = new RemessaArquivo(LayoutsBB.LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
            // REMESSSA DE BOLETO
            remessa.addNovoCabecalho()
                    .sequencialArquivo(remjson.getInt("sequencial"))
                    .numeroRemessa(remjson.getInt("numero"))
                    .dataGeracao(dataGeracao).setVal("horaGeracao", dataGeracao)
                    //                    .banco("0", "Banco")
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            contaconjson.getString("conta"),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            remessa.addNovoCabecalhoLote()
                    .operacao("R")//Operação de remessa
                    .servico(remjson.getInt("tipo"))
                    //Cobrança=1 olhar melhor no manual febraban
                    .forma(1)//Crédito em Conta Corrente
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            contaconjson.getString("conta"),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            int cont = 1;
            int total = 0;
            if (!remjson.isNull("boletos")) {
                JSONArray boletosjson = remjson.getJSONArray("boletos");

                if (false && boletosjson != null) {
                    for (int i = 0; i < boletosjson.length(); i++) {
                        JSONObject boletojson = boletosjson.getJSONObject(i);
                        JSONObject sacadojson = boletojson.getJSONObject("sacado");
                        remessa.addNovoDetalheSegmentoP()
                                .valor(boletojson.get("valor"))
                                //                                .valorDesconto(0).valorAcrescimo(0)//opcionais
                                .dataGeracao(new Date())
                                .dataVencimento(new Date())
                                .numeroDocumento(boletojson.get("numero_documento"))
                                .nossoNumero(boletojson.get("nosso_numero"))
                                .cedente(cedentejson.getString("nome_razao_social"),
                                        cedentejson.getString("cpf_cnpj"))
                                .convenio(conveniojson.getString("codigo_convenio"),
                                        contaconjson.getString("agencia"),
                                        contaconjson.getString("conta"),
                                        contaconjson.getString("dac"))
                                .sequencialRegistro(cont++)
                                .carteira(conveniojson.getString("carteira"));

                        remessa.addNovoDetalheSegmentoQ()
                                .sacado(sacadojson.getString("nome_razao_social"),
                                        sacadojson.getString("cpf_cnpj"))
                                .cedente(cedentejson.getString("nome_razao_social"),
                                        cedentejson.getString("cpf_cnpj"))
                                .convenio(conveniojson.getString("codigo_convenio"),
                                        contaconjson.getString("agencia"),
                                        contaconjson.getString("conta"),
                                        contaconjson.getString("dac"))
                                .sequencialRegistro(cont++)
                                .carteira(conveniojson.getString("carteira"));

                        cont++;
//                    total += totalboleto;
                    }
                }
            }

            if (!remjson.isNull("transferencias")) {
                JSONArray transferenciasjson = remjson.getJSONArray("transferencias");
                if (false && transferenciasjson != null) {
                    for (int i = 0; i < transferenciasjson.length(); i++) {
                        JSONObject transjson = transferenciasjson.getJSONObject(i);
                        JSONObject fornecedorjson = transjson.getJSONObject("fornecedor");

                        TituloArquivo detalheSegmentoA = remessa.addNovoDetalheSegmentoA();
                        JSONObject jsonObject = fornecedorjson.getJSONObject("conta");
                        detalheSegmentoA.numeroDocumento(jsonObject.getString("id"))
                                .formaDeTransferencia("000")
                                .favorecidoCodigoBanco(jsonObject.getString("banco"))
                                .favorecidoAgencia(jsonObject.getString("agencia"))
                                .favorecidoConta(jsonObject.getString("conta"))
                                .numeroDocumento(transjson.get("numero_documento"))
                                //testando sanitize remover acentos e transformar em maiusculo
                                .favorecidoNome(fornecedorjson.get("nome_razao_social"))
                                .dataPagamento(new Date())
                                .valor(transjson.get("valor_total"))
                                .sequencialRegistro(cont);

                        cont++;

                        remessa.addNovoDetalheSegmentoB()
                                .numeroDocumento(1)
                                .favorecidoTipoInscricao("1")
                                //testando sanitize apenasNumeros
                                .favorecidoCPFCNPJ(fornecedorjson.get("cpf_cnpj"))
                                .valor(transjson.get("valor_total"))
                                .sequencialRegistro(cont)
                                .setValue("data", new Date())
                                .setValue("lote", 1);
                    }
                }
            }

            remessa.addNovoRodapeLote()
                    .quantidadeRegistros(2)
                    .valorTotalRegistros(1)
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            contaconjson.getString("conta"),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            remessa.addNovoRodape()
                    .quantidadeRegistros(cont)
                    .valorTotalRegistros(total)
                    .setVal("codigoRetorno", "1")
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            contaconjson.getString("conta"),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            String remessaStr = remessa.render();

            System.out.println(remessaStr);

            if (out != null) {
                FileWriter writer = new FileWriter(out);
                writer.write(remessaStr);
                writer.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(CLIRetorno.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        
        //REMESSSA DE TRANSFERENCIA
            String razaoSocial = "ACME S.A LTDA.";
            String cnpj = "111.222.33.0001/44";

            String numeroConvenio = "12345678";
            String agenciaComDigito = "0123-X";
            String contaComDigito = "0000123-X";
            String DAC = " ";
            int sequencialRegistro = 1;
            String numeroDocumento = "";

            remessa.addNovoRodape()
                    .quantidadeRegistros(1)
                    .valorTotalRegistros(1)
                    .setVal("codigoRetorno", "1")
                    .banco("0", "Banco").cedente("ACME S.A LTDA.", "1")
                    .convenio("1", "1", "1", "1")
                    .carteira("00");

            remessa.addNovoCabecalho()
                    .dataGeracao(new Date())
                    .horaGeracao(new Date())
                    .sequencialArquivo(22)
                    .cedente(razaoSocial, cnpj)
                    .carteira("17")
                    .convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC);

            CabecalhoArquivo cabecalhoLote = remessa.addNovoCabecalhoLote();

            cabecalhoLote.forma(1)// 1 = Crédito em Conta Corrente mesmo banco 3 = doc/ted outro banco
                    .convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
                    .cedente(razaoSocial, cnpj)
                    .carteira("17")
                    .endereco("Rua XYZ", "123", "", "São Paulo", "12345-123", "SP");
            cabecalhoLote.operacao('R');
            cabecalhoLote.servico(1);

            BigDecimal valorPagamento = new BigDecimal(5.82).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);

            if (remessa.isExigeNumeroDocumento()) {
                numeroDocumento = "1";
            }

            TituloArquivo detalheSegmentoA = remessa.addNovoDetalheSegmentoA();
            detalheSegmentoA.numeroDocumento("1")
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
                    .setValue("data", new Date())
                    .setValue("lote", 1);

            RodapeArquivo rodapeLote = remessa.addNovoRodapeLote();

            rodapeLote
                    .quantidadeRegistros(24)
                    .valorTotalRegistros(valorPagamento.toString())
                    .cedente(razaoSocial, cnpj)
                    .convenio(numeroConvenio, agenciaComDigito, contaComDigito, DAC)
                    .setValue("lote", 1);

            if (remessa.isPermiteQtdeMoeda()) {
                rodapeLote.setValue("qtdeMoeda", valorPagamento.multiply(new BigDecimal(100000)).setScale(0).toString());
            }

            remessa.addNovoRodape()
                    .quantidadeRegistros(18)
                    .quantidadeLotes(1);
         */
    }

    /* 
    
    
     */
    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLIRemessa()).execute(args);
        System.exit(exitCode);
    }
}
