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

import com.github.braully.boleto.BoletoCobranca;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public class CLIMulti implements Runnable {

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
        System.out.println("Gerando boleto");

        if (in == null) {
            in = "/home/strike/Tmp/boletos_20240222215742.json";
        }
        if (out == null) {
            out = in + ".pdf";
        }

        if (layout == null) {
            layout = "/home/strike/BoletoTemplateEnbpar.pdf";
        }

        JSONArray boletosjson = null;

        List<Boleto> boletos = new ArrayList<>();

        try {
            FileInputStream fileReader = new FileInputStream(in);
            JSONTokener tokener = new JSONTokener(fileReader);
            boletosjson = new JSONArray(tokener);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLIRemessaCobranca.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(boletosjson);

        for (int i = 0; i < boletosjson.length(); i++) {
            JSONObject boletojson = boletosjson.getJSONObject(i);
            String sacado = boletojson.getString("nome");
            String sacadoCnpj = boletojson.getString("cpf_cnpj");

            String valor = boletojson.getString("valor");
            String numero = boletojson.getString("numero_documento");
            String nossonumero = boletojson.getString("nosso_numero");
            String agencia = boletojson.getString("agencia");
            String conta = boletojson.getString("conta");
            JSONObject contajson = boletojson.getJSONObject("conta_bancaria");

            String cedente = boletojson.getString("cedente_nome");
            String cedenteCnpj = boletojson.getString("cedente_cpf_cnpj");
            String carteira = boletojson.getString("carteira");

            String datavencimento = boletojson.getString("data_vencimento");
            String strbanco = contajson.getString("banco");

            BoletoCobranca boleto = new BoletoCobranca();
            boleto.banco(strbanco)
                    .agencia(agencia)
                    .conta(conta)
                    .carteira(carteira);

            //Cedente
            boleto.cedente(cedente);
            boleto.cedenteCnpj(cedenteCnpj);
//        boleto.cedenteEndereco(cedenteEndereco);

            //Sacado
            boleto.sacado(sacado);
            if (sacadoCnpj != null) {
                boleto.sacadoCnpj(sacadoCnpj);
            }
//        boleto.sacadoEndereco(sacadoEndereco);

            //Boleto
            boleto.dataVencimento(datavencimento);
            boleto.nossoNumero(nossonumero);
            boleto.numeroDocumento(numero);
            boleto.valor(valor);
            boleto.gerarLinhaDigitavel();

            boletos.add(boleto);

        }

//        BoletoViewer create = BoletoViewer.create(boleto);
//        create.getPdfAsFile(out);
//        
        if (layout != null && !layout.isEmpty()) {
            BoletoViewer.groupInOnePdfWithTemplate(boletos, out, layout);
        } else {
            BoletoViewer.groupInOnePDF(boletos, out);
        }
        System.out.println("Boleto gerado: " + out);
    }

    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLIMulti()).execute(args);
        System.exit(exitCode);
    }
}
