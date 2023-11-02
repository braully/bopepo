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

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import org.jrimum.bopepo.view.BoletoViewer;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public class CLI implements Runnable {

    @Option(names = {"-b", "--banco"}, description = "Banco", required = true)
    String banco;

    @Option(names = {"-a", "--agencia"}, description = "Agencia-Dv", required = true)
    String agencia;

    @Option(names = {"-c", "--conta"}, description = "Conta-Dv", required = true)
    String conta;

    @Option(names = {"-r", "--carteira"}, description = "Carteira", required = true)
    String carteira;

    @Option(names = {"-e", "--cedente"}, description = "Cedente", required = true)
    String cedente;
    @Option(names = {"-i", "--cedente-cnpj"}, description = "Cedente CNPJ", required = false)
    String cedenteCnpj;

    @Option(names = {"-x", "--cedente-endereco"}, description = "Cedente Endereço", required = false)
    String cedenteEndereco;

    @Option(names = {"-s", "--sacado"}, description = "Sacado", required = true)
    String sacado;

    @Option(names = {"-j", "--sacado-cnpj"}, description = "Sacado CNPJ", required = false)
    String sacadoCnpj;

    @Option(names = {"-p", "--sacado-cpf"}, description = "Sacado CPF", required = false)
    String sacadoCpf;

    @Option(names = {"-d", "--sacado-endereco"}, description = "Sacado Endereço", required = false)
    String sacadoEndereco;

    @Option(names = {"-n", "--nosso-numero"}, description = "Nosso Numero", required = true)
    String nossoNumero;

    @Option(names = {"-u", "--numero-documento"}, description = "Numero Documento", required = true)
    String numeroDocumento;

    @Option(names = {"-v", "--valor"}, description = "Valor", required = true)
    String valor;

    @Option(names = {"-m", "--data-vencimento"}, description = "Data Vencimento", required = true)
    String dataVencimento;

    @Option(names = {"-o", "--out"}, description = "Output file", required = true)
    String out;

    @Override
    public void run() {
        System.out.println("Gerando boleto");

        BoletoCobranca boleto = new BoletoCobranca();
        boleto.banco(banco).agencia(agencia).conta(conta).carteira(carteira);

        //Cedente
        boleto.cedente(cedente);
        boleto.cedenteCnpj(cedenteCnpj);
//        boleto.cedenteEndereco(cedenteEndereco);

        //Sacado
        boleto.sacado(sacado);
        if (sacadoCnpj != null) {
            boleto.sacadoCnpj(sacadoCnpj);
        } else if (sacadoCpf != null) {
            boleto.sacadoCpf(sacadoCpf);
        }
//        boleto.sacadoEndereco(sacadoEndereco);

        //Boleto
        boleto.dataVencimento(dataVencimento);
        boleto.nossoNumero(nossoNumero);
        boleto.numeroDocumento(numeroDocumento);
        boleto.valor(valor);
        boleto.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boleto);
        create.getPdfAsFile(out);
        System.out.println("Boleto gerado: " + out);
    }

    public static void main(String[] args) {
        // By implementing Runnable or Callable, parsing, error handling and handling
        // user
        // requests for usage help or version help can be done with one line of code.
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }
}
