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

import java.io.BufferedReader;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jrimum.bopepo.view.BoletoViewer;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public class CLIRetorno implements Runnable {

    @Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    String in;

    @Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    String out;

    @Override
    public void run() {
        BufferedReader reader;
        try {
            if (in == null) {

            }
            if (out == null) {
                out = in + ".json";
            }
            reader = new BufferedReader(new FileReader(in));
            List<String> linhas = new ArrayList<>();
            String linha = null;
            RetornoArquivo retorno = new RetornoArquivo(LayoutsBB.LAYOUT_BB_CNAB240_COBRANCA_RETORNO);
//            RetornoArquivo retorno = new RetornoArquivo(LayoutsFebraban.LAYOUT_FEBRABAN_CNAB240_COBRANCA_RETORNO);

            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
            retorno.parse(linhas);

            FileWriter writer = new FileWriter(out);

//            int cont = 1;
            for (RegistroArquivo reg : retorno.registros) {
//                System.out.print("linha-" + cont + ": ");
//                System.out.println(reg);
                System.out.println(reg.toJson());
                writer.write(reg.toJson());
                writer.write("\n");

//                cont++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLIRemessa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CLIRemessa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLIRetorno()).execute(args);
        System.exit(exitCode);
    }
}
