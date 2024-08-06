package cli;

import com.github.braully.boleto.CabecalhoArquivo;
import com.github.braully.boleto.LayoutsItau;
import com.github.braully.boleto.RemessaArquivo;
import com.github.braully.boleto.RodapeArquivo;
import com.github.braully.boleto.TagLayout;
import static com.github.braully.boleto.TagLayout.TagCreator.fcampoLivre;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteCnpj;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fdigitoVerificador;
import static com.github.braully.boleto.TagLayout.TagCreator.ffatorVencimento;
import static com.github.braully.boleto.TagLayout.TagCreator.ffavorecidoCodigoBanco;
import static com.github.braully.boleto.TagLayout.TagCreator.fmoeda;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoCpfCnpj;
import static com.github.braully.boleto.TagLayout.TagCreator.fsacadoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricaoCedente;
import static com.github.braully.boleto.TagLayout.TagCreator.ftipoInscricaoSacado;
import static com.github.braully.boleto.TagLayout.TagCreator.fvalorBoleto;
import com.github.braully.boleto.TituloArquivo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import picocli.CommandLine;
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
public class CLIRemessaPagamentoItau extends CLIRemessaPagamentoAbs {

    @CommandLine.Option(names = {"-i", "--file-in"}, description = "Arquivo de entrada")
    public String in;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Arquivo de saida")
    public String out;

    //Futuro
    @CommandLine.Option(names = {"-b", "--banco"}, description = "Banco")
    public String banco;

    @CommandLine.Option(names = {"-l", "--layout"}, description = "Banco")
    public String layout;

    public String[] args;

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

    String empresaNome;
    String empresaCnpj;
    String empresaTipoInscricao;

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

            TagLayout padrao = LayoutsItau.LAYOUT_ITAU_CNAB240_PAGAMENTO_REMESSA;

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
                System.out.println("Remessas pixs");
                System.out.println(transferenciasPix);
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
                System.out.println("Pagamento boletos");
                System.out.println(transferencias);
                if (transferencias != null && transferencias.length() > 0) {
                    gerarLotePagamentoBoletos(transferencias);
                }
            }

//            if (!remjson.isNull("pagamento_boleto")) {
//                JSONArray transferencias = remjson.getJSONArray("pagamento_boleto");
//                if (transferencias != null && transferencias.length() > 0) {
//                    gerarLotePagamentoBoletos(transferencias);
//                }
//            }
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
        CLIRemessaPagamentoItau cliRemessaPagamentoItau = new CLIRemessaPagamentoItau();
        int exitCode = new CommandLine(cliRemessaPagamentoItau).execute(args);
        System.exit(exitCode);
    }

    private CabecalhoArquivo addCabecalhoLote() {
        CabecalhoArquivo cabecalho = remessa.addNovoCabecalhoLote();
        cabecalho.cedente(cedenteRazaoSocial,
                cedenteCnpj);
        cabecalho.convenio(convenio,
                agencia,
                conta,
                dac);
        return cabecalho;
    }

    private void gerarLoteTransferenciasPix(JSONArray transferenciasPix) throws ParseException {
        CabecalhoArquivo cabecalho = addCabecalhoLote();
        cabecalho.operacao("C");

        int cont = 1;
        int total = 0;

        JSONArray transferenciasjson = transferenciasPix;

        for (int i = 0; i < transferenciasjson.length(); i++) {
            JSONObject transjson = transferenciasjson.getJSONObject(i);

            TituloArquivo detalheSegmentoA = remessa.addNovoDetalheSegmentoA();

            JSONObject fornecedorjson = transjson.getJSONObject("fornecedor");
            String fornecedorNome = fornecedorjson.getString("nome_razao_social");
            String fornecedorCPFCNPJ = fornecedorjson.getString("cpf_cnpj");

            total += setValorTotal(transjson, detalheSegmentoA);
            setDataPagamento(transjson, detalheSegmentoA);

            JSONObject contaFornecedorJson = transjson.getJSONObject("fornecedor_conta_bancaria");
            setNumeroDocumento(transjson, detalheSegmentoA);
            detalheSegmentoA
                    // /* Código Câmara Compensação 003 = CC | 018 = TED | 700 = DOC */
                    .formaDeTransferencia("009");// PIX ITAU
            if (contaFornecedorJson.has("banco")
                    && contaFornecedorJson.has("agencia")
                    && contaFornecedorJson.has("conta")) {
                detalheSegmentoA.favorecidoCodigoBanco(contaFornecedorJson.getString("banco"))
                        .favorecidoAgencia(contaFornecedorJson.getString("agencia"))
                        .favorecidoConta(getContaComDv(contaFornecedorJson));
            }
            // .numeroDocumento(transjson.get("numero_documento"))
            // testando sanitize remover acentos e transformar em maiusculo
            detalheSegmentoA.favorecidoNome(fornecedorNome);
            // .dataPagamento(new Date())
            // .dataPagamento(new Date())

            detalheSegmentoA.sequencialRegistro(cont);

            detalheSegmentoA.favorecidoCPFCNPJ(
                    somenteNumeros(fornecedorCPFCNPJ));
            cont++;

            // if (banco.equals("001")) {
            TituloArquivo segmentoB = remessa.addNovoDetalheSegmentoB();
            segmentoB.numeroDocumento(1)
                    // .valor(transjson.get("valor_total"))
                    .sequencialRegistro(cont)
                    // .setValue("data", new Date())
                    .setValue("lote", 1);

            if (fornecedorjson.getString("tipo_pessoa").equals("PJ")) {
                segmentoB.favorecidoTipoInscricao("2");
            } else {
                segmentoB.favorecidoTipoInscricao("1");
            }
            String somenteNumerosCpfCnpj = somenteNumeros(fornecedorjson.get("cpf_cnpj"));
            segmentoB.favorecidoCPFCNPJ(somenteNumerosCpfCnpj);
            // segmentoB.setValue("chavePix", somenteNumerosCpfCnpj);

            // "fornecedor_conta_bancaria": {
            // "id": 2,
            // "pessoa_banco": 7,
            // "pix": "43913162000123",
            // "conta_contabil": 7,
            // "tipo_pix": "03"
            // },
            JSONObject contaFornecedor = transjson.getJSONObject("fornecedor_conta_bancaria");

            segmentoB.setVal("chavePix", contaFornecedor.getString("pix"));
            segmentoB.setVal("tipoChave", contaFornecedor.getString("tipo_pix"));
            cont++;

            // }
        }

        // }
        RodapeArquivo rodapeLote = addNovoRodapeLote();
        rodapeLote.valorTotalRegistros(total);

    }

    private RodapeArquivo addNovoRodapeLote() {
        RodapeArquivo rodape = remessa.addNovoRodapeLote();
        rodape.quantidadeRegistros(remessa.registrosCount() - 1);
        rodape.cedente(cedenteRazaoSocial,
                cedenteCnpj);
        rodape.convenio(convenio,
                agencia,
                conta,
                dac);
        rodape.carteira(carteira).variacao(variacao);
        return rodape;
    }

    private void gerarLoteTransferencias(JSONArray transferencias) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void gerarLotePagamentoBoletos(JSONArray transferencias) throws ParseException {
        CabecalhoArquivo cabecalhoLote = addCabecalhoLote();

        int count = 0;
        long total = 0;
        for (int i = 0; i < transferencias.length(); i++) {
            JSONObject transjson = transferencias.getJSONObject(i);
            JSONObject fornecedorjson = transjson.getJSONObject("fornecedor");
            String fornecedorNome = fornecedorjson.getString("nome_razao_social");
            String fornecedorCPFCNPJ = fornecedorjson.getString("cpf_cnpj");
            String fornecedorCPFCNPJNumeros = somenteNumeros(fornecedorCPFCNPJ);

            TituloArquivo segmentoJ = remessa.addNovoDetalheSegmentoJ();
            if (transjson.has("linha_digitavel")) {
//            "codigo_banco": "237",
//            "codigo_moeda": "9",
//            "campo_livre": "3381260007827139500006330",
//            "dv_codigo_barras": "9",
//            "fator_vencimento": "7552",
//            "valor_titulo": "0000370000"
                segmentoJ.setValue(ffavorecidoCodigoBanco(), transjson.get("codigo_banco"));
                segmentoJ.setValue(fmoeda(), transjson.get("codigo_moeda"));
                segmentoJ.setValue(fcampoLivre(), transjson.get("campo_livre"));
                segmentoJ.setValue(fdigitoVerificador(), transjson.get("dv_codigo_barras"));
                segmentoJ.setValue(ffatorVencimento(), transjson.get("fator_vencimento"));
                segmentoJ.setValue(fvalorBoleto(), transjson.get("valor_titulo"));

            }
            segmentoJ.sacado(fornecedorNome, fornecedorCPFCNPJ);

            total += setValorTotal(transjson, segmentoJ);
            setDataVencimento(transjson, segmentoJ);
            setNumeroDocumento(transjson, segmentoJ);

            TituloArquivo segmentoJ52 = remessa.addNovoDetalheSegmentoJ52();

//            Caso não seja informado o Número de Inscrição do Sacado (posições 021 a 035), será assumido o número
//do CNPJ do pagador informado no registro Header de Lote.
            if (fornecedorjson.getString("tipo_pessoa").equals("PJ")) {
                segmentoJ52.setValue(ftipoInscricaoCedente(), "2");
            } else {
                segmentoJ52.setValue(ftipoInscricaoCedente(), "1");
            }
            segmentoJ52.setValue(fcedenteCnpj(), fornecedorCPFCNPJNumeros);
            segmentoJ52.setValue(fcedenteNome(), fornecedorNome);

            segmentoJ52.setValue(fsacadoCpfCnpj(), cedenteCnpj);
            segmentoJ52.setValue(fsacadoNome(), cedenteRazaoSocial);
//            segmentoJ52.setValue(ftipoInscricaoSacado(), "2");

        }

        RodapeArquivo rodapeLote = addNovoRodapeLote();
    }

    private void gerarLotePagamentoConcessionaria(JSONArray transferencias) {
        CabecalhoArquivo cabecalhoLote = addCabecalhoLote();

        for (int i = 0; i < transferencias.length(); i++) {
            JSONObject transjson = transferencias.getJSONObject(i);
            TituloArquivo segmentoJ = remessa.addNovoDetalheSegmentoQ();
        }

        RodapeArquivo rodapeLote = addNovoRodapeLote();
    }

    private long setValorTotal(JSONObject transjson, TituloArquivo detalheSegmentoA) {
        String valorstr = transjson.getString("valor_total");
        String valor = somenteNumeros(valorstr);
        System.out.println(valorstr);
        detalheSegmentoA.valor(valor);
        return Long.parseLong(valor);
    }

    private void setDataPagamento(JSONObject transjson, TituloArquivo detalheSegmentoA) throws ParseException {
        String strdataVencimento = transjson.getString("data_vencimento");
        System.out.println("data vencimento: " + strdataVencimento);
        Date dataVencimento = parseDataAAAA_MM_DD(strdataVencimento);
        System.out.println("data vencimento parsed: " + dataVencimento);
        detalheSegmentoA.dataPagamento(dataVencimento);

    }

    private void setDataVencimento(JSONObject transjson, TituloArquivo detalheSegmentoA) throws ParseException {
        String strdataVencimento = transjson.getString("data_vencimento");
        System.out.println("data vencimento: " + strdataVencimento);
        Date dataVencimento = parseDataAAAA_MM_DD(strdataVencimento);
        System.out.println("data vencimento parsed: " + dataVencimento);
        detalheSegmentoA.dataVencimento(dataVencimento);

    }

    private void setNumeroDocumento(JSONObject transjson, TituloArquivo detalheSegmentoA) {
        detalheSegmentoA.numeroDocumento(transjson.get("id"));
    }
}
