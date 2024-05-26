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
package cli;

import com.github.braully.boleto.LayoutsBB;
import com.github.braully.boleto.LayoutsBradesco;
import com.github.braully.boleto.RemessaArquivo;
import com.github.braully.boleto.TagLayout;
import com.github.braully.boleto.TituloArquivo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.FileWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public class CLIRemessaPagamento implements Runnable {

    @Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    String in;

    @Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    String out;

    //Futuro
    @Option(names = {"-b", "--banco"}, description = "Banco")
    String banco;

    @Option(names = {"-l", "--layout"}, description = "Banco")
    String layout;

    @Override
    public void run() {

        if (in == null) {
            in = "/tmp/11.json";
        }
        if (out == null) {
            out = in + ".txt";
        }
        JSONObject remjson = null;
        try {
            FileInputStream fileReader = new FileInputStream(in);
            JSONTokener tokener = new JSONTokener(fileReader);
            remjson = new JSONObject(tokener);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLIRemessaPagamento.class.getName()).log(Level.SEVERE, null, ex);
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

            TagLayout padrao = LayoutsBB.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA;

            if (banco != null && banco.equals("237")) {
                padrao = LayoutsBradesco.LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA;
            }

            RemessaArquivo remessa = new RemessaArquivo(padrao);

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
                            getContaComDv(contaconjson),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            remessa.addNovoCabecalhoLote()
                    //                    .operacao("R")//Operação de remessa
                    .operacao("C")//Operação de remessa
                    //                    .servico(remjson.getInt("tipo"))
                    //Cobrança=1 olhar melhor no manual febraban
                    .forma(1)//Crédito em Conta Corrente
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            getContaComDv(contaconjson),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            int cont = 1;
            int total = 0;

//            if (!remjson.isNull("transferencias")) {
            JSONArray transferenciasjson = remjson.getJSONArray("transferencias");
            if (transferenciasjson != null) {
                for (int i = 0; i < transferenciasjson.length(); i++) {
                    JSONObject transjson = transferenciasjson.getJSONObject(i);
                    JSONObject fornecedorjson = transjson.getJSONObject("fornecedor");

                    String valorstr = transjson.getString("valor_total");
                    String valor = somenteNumeros(valorstr);
                    total += Long.parseLong(valor);
                    System.out.println(valorstr);

                    TituloArquivo detalheSegmentoA = remessa.addNovoDetalheSegmentoA();
                    JSONObject jsonObject = fornecedorjson.getJSONObject("conta");
//                    detalheSegmentoA.numeroDocumento(transjson.getString("id"))
                    detalheSegmentoA.numeroDocumento("0")
                            //                          /* Código Câmara Compensação 003 = CC | 018 = TED | 700 = DOC */
                            .formaDeTransferencia("018")
                            .favorecidoCodigoBanco(jsonObject.getString("banco"))
                            .favorecidoAgencia(jsonObject.getString("agencia"))
                            .favorecidoConta(getContaComDv(jsonObject))
                            //                            .numeroDocumento(transjson.get("numero_documento"))
                            //testando sanitize remover acentos e transformar em maiusculo
                            .favorecidoNome(fornecedorjson.get("nome_razao_social"))
                            .dataPagamento(new Date())
                            .valor(valor)
                            .sequencialRegistro(cont);

                    cont++;

                    TituloArquivo segmentoB = remessa.addNovoDetalheSegmentoB();
                    segmentoB.numeroDocumento(1)
                            //                            .valor(transjson.get("valor_total"))
                            .sequencialRegistro(cont)
                            //                            .setValue("data", new Date())
                            .setValue("lote", 1);
                    if (fornecedorjson.getString("tipo_pessoa").equals("PJ")) {
                        segmentoB.favorecidoTipoInscricao("2");
                    } else {
                        segmentoB.favorecidoTipoInscricao("1");

                    }
                    segmentoB.favorecidoCPFCNPJ(fornecedorjson.get("cpf_cnpj"));

                    cont++;
                }
            }
//            }

            remessa.addNovoRodapeLote()
                    .quantidadeRegistros(remessa.registrosCount() - 1)
                    .valorTotalRegistros(total)
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            getContaComDv(contaconjson),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));

            remessa.addNovoRodape()
                    .quantidadeRegistros(remessa.registrosCount())
                    .valorTotalRegistros(total)
                    .setVal("codigoRetorno", "1")
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            getContaComDv(contaconjson),
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
        int exitCode = new CommandLine(new CLIRemessaPagamento()).execute(args);
        System.exit(exitCode);
    }

    String getContaComDv(JSONObject contaconjson) {
        StringBuilder sb = new StringBuilder();
        String conta = contaconjson.getString("conta");
        sb.append(conta);
        String digito = contaconjson.getString("digito");
        if (digito != null) {
            sb.append(digito);
        }
        return sb.toString();
    }

    public String somenteNumeros(String valorstr) {
        return valorstr.replaceAll("\\D+", "");
    }
}
