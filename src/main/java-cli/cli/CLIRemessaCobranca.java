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
import com.github.braully.boleto.LayoutsItau;
import com.github.braully.boleto.RemessaArquivo;
import com.github.braully.boleto.TagLayout;
import com.github.braully.boleto.TituloArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fendereco;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoCpf;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricao;
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
public class CLIRemessaCobranca implements Runnable {

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
            in = "/tmp/1.json";
            banco = "341";
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
            Logger.getLogger(CLIRemessaCobranca.class.getName()).log(Level.SEVERE, null, ex);
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

            TagLayout padrao = LayoutsBB.LAYOUT_BB_CNAB400_COBRANCA_REMESSA;

            if (banco != null && banco.equals("237")) {
                padrao = LayoutsBradesco.LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA;
            } else if (banco != null && banco.equals("341")) {
                padrao = LayoutsItau.LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA;
            }

            RemessaArquivo remessa = new RemessaArquivo(padrao);
            int cont = 1;

            System.out.printf("Agencia: %s", contaconjson.getString("agencia"));

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
                            formatarContaComDv(banco, contaconjson),
                            contaconjson.getString("dac"))
                    .carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"))
                    .sequencialRegistro(cont++);

//            remessa.addNovoCabecalhoLote()
//                    .operacao("R")//Operação de remessa
//                    .servico(remjson.getInt("tipo"))
//                    //Cobrança=1 olhar melhor no manual febraban
//                    .forma(1)//Crédito em Conta Corrente
//                    .cedente(cedentejson.getString("nome_razao_social"),
//                            cedentejson.getString("cpf_cnpj"))
//                    .convenio(conveniojson.getString("codigo_convenio"),
//                            contaconjson.getString("agencia"),
//                            formatarContaComDv(banco, contaconjson),
//                            contaconjson.getString("dac"))
//                    .carteira(conveniojson.getString("carteira"))
//                    .variacao(conveniojson.getString("variacao"));
            int total = 0;
//            if (!remjson.isNull("boletos")) {
            JSONArray boletosjson = remjson.getJSONArray("boletos");

            if (boletosjson != null) {
                for (int i = 0; i < boletosjson.length(); i++) {
                    JSONObject boletojson = boletosjson.getJSONObject(i);
                    JSONObject sacadojson = boletojson.getJSONObject("sacado");
                    TituloArquivo datalhe = remessa.addNovoDetalhe();

                    System.out.printf("Numero documento %s ", boletojson.get("numero_documento"));
                    datalhe.valor(somenteNumeros(boletojson.getString("valor")))
                            //                                .valorDesconto(0).valorAcrescimo(0)//opcionais
                            .dataVencimento(new Date());
                    datalhe.dataGeracao(new Date());
                    datalhe.numeroDocumento(boletojson.get("numero_documento"))
                            .nossoNumero(boletojson.get("nosso_numero"));
                    datalhe.cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"));
                    datalhe.convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            formatarContaComDv(banco, contaconjson),
                            contaconjson.getString("dac"))
                            .carteira(conveniojson.getString("carteira"))
                            .variacao(conveniojson.getString("variacao"));

                    datalhe.sacado(sacadojson.getString("nome_razao_social"),
                            somenteNumeros(sacadojson.getString("cpf_cnpj"))
                    )
                            .cedente(cedentejson.getString("nome_razao_social"),
                                    cedentejson.getString("cpf_cnpj"))
                            .convenio(conveniojson.getString("codigo_convenio"),
                                    contaconjson.getString("agencia"),
                                    formatarContaComDv(banco, contaconjson),
                                    contaconjson.getString("dac"))
                            .sequencialRegistro(cont++)
                            .carteira(conveniojson.getString("carteira"))
                            .variacao(conveniojson.getString("variacao"));

                    if (sacadojson.getString("tipo_pessoa").equals("PJ")) {
                        datalhe.tipoInscricao("02");
                    } else {
                        datalhe.tipoInscricao("01");
                    }

                    try {
                        JSONObject endrecoSacado = sacadojson.getJSONObject("endereco");
                        if (endrecoSacado != null) {
//                        sacadoEndereco(String endereco, String bairro, String cep, String cidade, String uf)
                            datalhe.sacadoEndereco(endrecoSacado.getString("logradouro"),
                                    endrecoSacado.getString("bairro"),
                                    somenteNumeros(endrecoSacado.getString("cep")),
                                    endrecoSacado.getString("municipio"), endrecoSacado.getString("uf"));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    cont++;
//                    total += totalboleto;
                }
            }
//            }

//           
//            remessa.addNovoRodapeLote()
//                    .quantidadeRegistros(2)
//                    .valorTotalRegistros(1)
//                    .cedente(cedentejson.getString("nome_razao_social"),
//                            cedentejson.getString("cpf_cnpj"))
//                    .convenio(conveniojson.getString("codigo_convenio"),
//                            contaconjson.getString("agencia"),
//                            formatarContaComDv(banco, contaconjson),
//                            contaconjson.getString("dac"))
//                    .carteira(conveniojson.getString("carteira"))
//                    .variacao(conveniojson.getString("variacao"));
            remessa.addNovoRodape()
                    .quantidadeRegistros(cont)
                    .valorTotalRegistros(total)
                    .sequencialRegistro(cont)
                    .setVal("codigoRetorno", "1")
                    .cedente(cedentejson.getString("nome_razao_social"),
                            cedentejson.getString("cpf_cnpj"))
                    .convenio(conveniojson.getString("codigo_convenio"),
                            contaconjson.getString("agencia"),
                            formatarContaComDv(banco, contaconjson),
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
    }

    String formatarContaComDv(String banco, JSONObject contaconjson) {
        StringBuilder sb = new StringBuilder();
        String conta = contaconjson.getString("conta");
        sb.append(conta);
        if (banco.equals("001")) {
            String digito = contaconjson.getString("digito");
            if (digito != null) {
                sb.append(digito);
            }
        }
        return sb.toString();
    }

    /* 
    
    
     */
    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLIRemessaCobranca()).execute(args);
        System.exit(exitCode);
    }

    public String somenteNumeros(String valorstr) {
        return valorstr.replaceAll("\\D+", "");
    }
}
