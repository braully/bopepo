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

import com.github.braully.boleto.CabecalhoArquivo;
import com.github.braully.boleto.LayoutsBB;
import com.github.braully.boleto.LayoutsBradesco;
import com.github.braully.boleto.LayoutsItau;
import com.github.braully.boleto.RemessaArquivo;
import com.github.braully.boleto.TagLayout;
import com.github.braully.boleto.TituloArquivo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.FileWriter;
import java.text.ParseException;
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
public class CLIRemessaPagamentoItau extends CLIRemessaPagamento implements Runnable {

    @Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    String in;

    @Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    String out;

    // Futuro
    @Option(names = {"-b", "--banco"}, description = "Banco")
    String banco;

    @Option(names = {"-l", "--layout"}, description = "Banco")
    String layout;

    int loteAtual = 1;
    int registroAtual = 1;
    int totalRegistros = 0;

    String cedenteRazaoSocial;
    String cedenteCnpj;
    String convenio;
    String agencia;
    String dac;
    String contaComDv;
    String carteira;
    String variacao;
    String conta;

    RemessaArquivo remessa = null;

    @Override
    public void run() {

        if (in == null) {
            in = "/home/strike/Workspace/norli/tmp.json";
        }
        if (banco == null) {
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
            Logger.getLogger(CLIRemessaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(remjson);

        try {
            Date dataGeracao = new Date();
            JSONObject cedentejson = remjson.getJSONObject("cedente");
            JSONObject conveniojson = remjson.getJSONObject("convenio");
            JSONObject contaconjson = conveniojson.getJSONObject("conta_bancaria");

            cedenteRazaoSocial = cedentejson.getString("nome_razao_social");
            cedenteCnpj = cedentejson.getString("cpf_cnpj");
            convenio = conveniojson.getString("codigo_convenio");
            agencia = contaconjson.getString("agencia");
            dac = contaconjson.getString("dac");
            contaComDv = getContaComDv(contaconjson);
            carteira = conveniojson.getString("carteira");
            variacao = conveniojson.getString("variacao");
            conta = contaconjson.getString("conta");

            TagLayout padrao = LayoutsBB.LAYOUT_BB_CNAB240_PAGAMENTO_REMESSA;

            if (banco != null && banco.equals("237")) {
                padrao = LayoutsBradesco.LAYOUT_BRADESCO_CNAB400_COBRANCA_REMESSA;
            }

            if (banco != null && banco.equals("341")) {
                padrao = LayoutsItau.LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA;
            }

            remessa = new RemessaArquivo(padrao);

            // REMESSSA DE BOLETO
            CabecalhoArquivo cabecalhoArquivo = (CabecalhoArquivo) remessa.addNovoCabecalho()
                    .sequencialArquivo(remjson.getInt("sequencial"))
                    .numeroRemessa(remjson.getInt("numero"))
                    .dataGeracao(dataGeracao).cedente(cedentejson.getString("nome_razao_social"),
                    cedentejson.getString("cpf_cnpj"))
                    .setVal("horaGeracao", dataGeracao); // .banco("0", "Banco")

            cabecalhoArquivo.carteira(conveniojson.getString("carteira"))
                    .variacao(conveniojson.getString("variacao"));
            cabecalhoArquivo.convenio(conveniojson.getString("codigo_convenio"),
                    contaconjson.getString("agencia"),
                    getContaComDv(contaconjson),
                    contaconjson.getString("dac"));

            cabecalhoArquivo.carteira(carteira)
                    .variacao(variacao);
            cabecalhoArquivo.convenio(convenio,
                    agencia,
                    conta,
                    dac);

            if (!remjson.isNull("transferencias_pix")) {
                JSONArray transferenciasPix = remjson.getJSONArray("transferencias_pix");
                if (transferenciasPix != null && transferenciasPix.length() > 0) {
                    gerarLoteTransferenciasPix(transferenciasPix);
                }
            }

            if (!remjson.isNull("transferencias")) {
                JSONArray transferencias = remjson.getJSONArray("transferencias");
                if (transferencias != null && transferencias.length() > 0) {
                    gerarLoteTransferencias(transferencias);
                }
            }

            if (!remjson.isNull("pagamento_boleto")) {
                JSONArray transferencias = remjson.getJSONArray("pagamento_boleto");
                if (transferencias != null && transferencias.length() > 0) {
                    gerarLotePagamentoBoletos(transferencias);
                }
            }

            if (!remjson.isNull("pagamento_boleto")) {
                JSONArray transferencias = remjson.getJSONArray("pagamento_boleto");
                if (transferencias != null && transferencias.length() > 0) {
                    gerarLotePagamentoBoletos(transferencias);
                }
            }

            if (!remjson.isNull("pagamento_concessionaria")) {
                JSONArray transferencias = remjson.getJSONArray("pagamento_concessionaria");
                if (transferencias != null && transferencias.length() > 0) {
                    gerarLotePagamentoConcessionaria(transferencias);
                }
            }

            remessa.addNovoRodape()
                    .quantidadeRegistros(remessa.registrosCount())
                    .valorTotalRegistros(totalRegistros)
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

    private void gerarLoteTransferenciasPix(JSONArray transferenciasPix) throws ParseException {
        CabecalhoArquivo cabecalho = remessa.addNovoCabecalhoLote();
        //                    .operacao("R")//Operação de remessa
        cabecalho.operacao("C")//Operação de remessa
                //                    .servico(remjson.getInt("tipo"))
                //Cobrança=1 olhar melhor no manual febraban
                .cedente(cedenteRazaoSocial,
                        cedenteCnpj)
                .convenio(convenio,
                        agencia,
                        contaComDv,
                        dac)
                .carteira(carteira)
                .variacao(variacao);

        if (banco.equals("001")) {
            cabecalho.forma(1);//Crédito em Conta Corrente
        }

        if (banco != null && banco.equals("341")) {
            cabecalho.carteira(carteira)
                    .variacao(variacao);
            cabecalho.convenio(convenio,
                    agencia,
                    conta,
                    dac);
        }

        int cont = 1;
        int total = 0;

        JSONArray transferenciasjson = transferenciasPix;

        for (int i = 0; i < transferenciasjson.length(); i++) {
            JSONObject transjson = transferenciasjson.getJSONObject(i);
            JSONObject fornecedorjson = transjson.getJSONObject("fornecedor");

            String valorstr = transjson.getString("valor_total");
            String valor = somenteNumeros(valorstr);
            total += Long.parseLong(valor);
            System.out.println(valorstr);

            TituloArquivo detalheSegmentoA = remessa.addNovoDetalheSegmentoA();
            JSONObject jsonObject = fornecedorjson.getJSONObject("conta");
            detalheSegmentoA.numeroDocumento(transjson.get("id"));
            detalheSegmentoA
                    //                          /* Código Câmara Compensação 003 = CC | 018 = TED | 700 = DOC */
                    //                            .formaDeTransferencia("018") //TED BB
                    .formaDeTransferencia("009")//PIX ITAU
                    .favorecidoCodigoBanco(jsonObject.getString("banco"))
                    .favorecidoAgencia(jsonObject.getString("agencia"))
                    .favorecidoConta(getContaComDv(jsonObject))
                    //                            .numeroDocumento(transjson.get("numero_documento"))
                    //testando sanitize remover acentos e transformar em maiusculo
                    .favorecidoNome(fornecedorjson.get("nome_razao_social"))
                    //                            .dataPagamento(new Date())
                    //                            .dataPagamento(new Date())
                    .valor(valor)
                    .sequencialRegistro(cont);

            String strdataVencimento = transjson.getString("data_vencimento");
            System.out.println("data vencimento: " + strdataVencimento);
            Date dataVencimento = parseDataAAAA_MM_DD(strdataVencimento);
            System.out.println("data vencimento parsed: " + dataVencimento);

            detalheSegmentoA.dataPagamento(dataVencimento);

            detalheSegmentoA.favorecidoCPFCNPJ(
                    somenteNumeros(fornecedorjson.get("cpf_cnpj"))
            );
            cont++;

//                    if (banco.equals("001")) {
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
            String somenteNumerosCpfCnpj = somenteNumeros(fornecedorjson.get("cpf_cnpj"));
            segmentoB.favorecidoCPFCNPJ(somenteNumerosCpfCnpj);
//                    segmentoB.setValue("chavePix", somenteNumerosCpfCnpj);

//            "fornecedor_conta_bancaria": {
//                "id": 2,
//                "pessoa_banco": 7,
//                "pix": "43913162000123",
//                "conta_contabil": 7,
//                "tipo_pix": "03"
//            },
            segmentoB.setVal("chavePix", "70ca7164-930c-492d-99e3-58bc81e6a8ab");
            segmentoB.setVal("tipoChave", "04");
            cont++;

//                    }
        }

//            }
        remessa.addNovoRodapeLote()
                .quantidadeRegistros(remessa.registrosCount() - 1)
                .valorTotalRegistros(total)
                .cedente(cedenteRazaoSocial,
                        cedenteCnpj)
                .convenio(convenio,
                        agencia,
                        conta,
                        dac)
                .carteira(carteira)
                .variacao(variacao);
    }

    private void gerarLoteTransferencias(JSONArray transferencias) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void gerarLotePagamentoBoletos(JSONArray transferencias) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void gerarLotePagamentoConcessionaria(JSONArray transferencias) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
