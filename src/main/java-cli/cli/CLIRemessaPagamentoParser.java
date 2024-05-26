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
import com.github.braully.boleto.LayoutsItau;
import com.github.braully.boleto.RegistroArquivo;
import com.github.braully.boleto.RemessaArquivo;
import com.github.braully.boleto.RetornoArquivo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class CLIRemessaPagamentoParser implements Runnable {

    @Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    String in;

    @Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    String out;

    @Option(names = {"-jt", "--json2txt"}, description = "Conversao json2txt")
    Boolean json2txt;

    @Override
    public void run() {
        if (json2txt != null && json2txt) {
            parseJson2Txt();
        } else {
//            parseJson2Txt();
            parseTxt2Json();
        }
    }

    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLIRemessaPagamentoParser()).execute(args);
        System.exit(exitCode);
    }

    private void parseJson2Txt() {
        BufferedReader reader;
        if (in == null) {
            in = "/home/strike/M3.json";
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

        try {
            RemessaArquivo remessa = new RemessaArquivo(LayoutsBB.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA);
            JSONArray transferenciasjson = remjson.getJSONArray("registros");
            if (transferenciasjson != null) {
                for (int i = 0; i < transferenciasjson.length(); i++) {
                    JSONObject transjson = transferenciasjson.getJSONObject(i);
                    System.out.println(transjson);
                    RegistroArquivo novoRegistro = remessa.addNovoRegistro(transjson.getString("tipo"));
                    for (String k : transjson.keySet()) {
                        novoRegistro.setValue(k, transjson.get(k));
                    }
                }
            }

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

    private void parseTxt2Json() {
        BufferedReader reader;
        try {
            if (in == null) {
                in = "/home/strike/Downloads/REM1 (6).txt";
            }
            if (out == null) {
                out = in + ".json";
            }
            reader = new BufferedReader(new FileReader(in));
            List<String> linhas = new ArrayList<>();
            String linha = null;
            RetornoArquivo retorno = new RetornoArquivo(LayoutsItau.LAYOUT_ITAU_CNAB400_COBRANCA_REMESSA);
//            RetornoArquivo retorno = new RetornoArquivo(LayoutsBB.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA);
//            RetornoArquivo retorno = new RetornoArquivo(LayoutsFebraban.LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO);

            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
            retorno.parse(linhas);

            FileWriter writer = new FileWriter(out);
            writer.append("{\"layout\": \"LAYOUT_BB_CNAB240_COBRANCA_RETORNO\", ");
            writer.append("\"registros\": [");

            int cont = 0;
            for (RegistroArquivo reg : retorno.getRegistros()) {
//                System.out.print("linha-" + cont + ": ");
//                System.out.println(reg);
                System.out.println(reg.toJson());
                if (cont++ > 0) {
                    writer.write(",");
                }
                writer.write(reg.toJson());
                writer.write("\n");
//                cont++;
            }
            writer.append("]}");
            writer.write("\n");
            writer.flush();
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLIRemessaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CLIRemessaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
