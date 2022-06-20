# Bopepo Cobrança

Este projeto é um fork feito por Braully Rocha da Silva,
do projeto JRimum Bopepo: http://www.jrimum.org
com os seguintes objetivos:


## Objetivos desse fork

- Unificado das bibliotecas do projeto JRimum (concluído)
- Atualizar dependências: Java 11 e itextpdf5 (concluído)
- Refatorar pacotes reduzindo a quantidade em unidades maiores e coesas (concluído)
- Biblioteca pra gerar boletos, arquivos de remessa e retorno  (concluído)
- Façade para facilitar o uso da biblioteca (concluído)
- Disponiblizar uma versão estável no repositorio maven central (futuro)

#### Suporte a boletos com registro dos principais bancos

- Banco do Brasil
- Bradesco
- Caixa
- Itau
- Santander
- Sicredi

### Suporte para remessa de boletos CNAB 240

- Febraban Cnab 240 - versão 5.0 (https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240)
- Banco do Brasil Cnab  240 - 2019 (https://www.bb.com.br/docs/pub/emp/empl/dwn/CNAB240SegPQRSTY.pdf)
- Bradesco Cnab 400 - versão 15 por @EdsonIsaac (https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf)
- Caixa Cnag 240 - por @EdsonIsaac http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf
- Itaú - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)
- Santander - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)
- Sicredi - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)

###  Suporte para retorno de boletos CNAB 240

- Febraban Cnab 240 - versão 5.0 (https://portal.febraban.org.br/pagina/3053/33/pt-br/layout-240)
- Banco do Brasil Cnab  240 - 2019 (https://www.bb.com.br/docs/pub/emp/empl/dwn/CNAB240SegPQRSTY.pdf)
- Bradesco Cnab 400 - versão 15 by @EdsonIsaac (https://banco.bradesco/assets/pessoajuridica/pdf/4008-524-0121-layout-cobranca-versao-portugues.pdf)
- Caixa Cnag 240 - by @EdsonIsaac http://www.caixa.gov.br/Downloads/cobranca-caixa/Manual_de_Leiaute_de_Arquivo_Eletronico_CNAB_240.pdf
- Itau - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)
- Santander - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)
- Sicredi - Febraban Cnab 240 (necessário fazer personalizações especificas do banco)

### Fusão das branches 

- 'master'
- 'helio'
- 'litio' 

### Fusão dos projetos:

- 'bopepo' 
- 'domkee' 
- 'utilix' 
- 'vallia' 
- 'texgit'
- 'texgit-febraban'

### Refatoração dos pacotes e classes
Concentrar todas as classes nos pacotes

- com.github.braully.boleto
- org.jrimum
- org.jrimum.bopepo
- org.jrimum.bopepo.campolivre
- org.jrimum.bopepo.parametro
- org.jrimum.bopepo.pdf
- org.jrimum.bopepo.view
- org.jrimum.bopepo.banco
- org.jrimum.domkee.pessoa
- org.jrimum.domkee.banco
- org.jrimum.utilix
- org.jrimum.valia

## Instalação e compilação

$ git clone https://github.com/braully/bopepo.git

$ cd bopepo

$ ./sh/compile.sh 

## Artefato do Maven Central

        <dependency>
            <groupId>io.github.braully</groupId>
            <artifactId>bpp-cobranca</artifactId>
            <version>1.0.0</version>
        </dependency>

## Exemplos


### Criar um boleto simples:
```
import com.github.braully.boleto.BoletoCobranca;
import org.jrimum.bopepo.view.BoletoViewer;

public class ExemploBoletoSimples {

    public static void main(String... args) {
        BoletoCobranca boleto = new BoletoCobranca();
        boleto.sacado("Sacado da Silva Sauro").sacadoCpf("1");
        boleto.banco("1").agencia("1").conta("1");
        boleto.cedente("Cedente da Silva Sauro").cedenteCnpj("1");
        boleto.carteira("1");
        boleto.numeroDocumento("1")
                .nossoNumero("1234567890")
                .valor(100.23).dataVencimento("01/01/2019");

        boleto.gerarLinhaDigitavel();
        BoletoViewer create = BoletoViewer.create(boleto);
        create.getPdfAsFile("./target/teste.pdf");
    }
}
```


### Criar um arquivo de remessa de boleto CNAB 240:
```
import com.github.braully.boleto.LayoutsSuportados;
import com.github.braully.boleto.RemessaArquivo;
import java.util.Date;

public class ExemploRemessaSimles {

    public static void main(String... args) {
        RemessaArquivo remessa = new RemessaArquivo(LayoutsSuportados.LAYOUT_BB_CNAB240_COBRANCA_REMESSA);
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
        System.out.println(remessaStr);
    }
}
```

Saída:

```
00000000         200000000000001000000001001400000  00000100000000000011ACME S.A LTDA.                BANCO                                   10505202111574500000110300000                                                                     
00000011R01  060 2000000000000001000000001001400000  00000100000000000011ACME S.A LTDA.                                                                                                0000000000000000                                         
0000001300001P 01000001000000000000111                   111220000000000000010505202100000000000000100000002N050520213000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000300000009           
0000001300002Q 011000000000000000FULANO DE TAL                                                                                  00000000                 0000000000000000                                        00000000000000000000000        
00000015         000002000000000000000001000000000000000000000000                                                                                                                                                                               
00099999         000001000001000000                                                                                                           
```

### Ler um arquivo de retorno de boleto CNAB 240:
```

    RetornoArquivo retorno = new RetornoArquivo(LayoutsSuportados.LAYOUT_FEBRABAN_CNAB240);

    String[] arrLinhas = BB_EXEMPLO_RETORNO.split("\n");

    List<String> linhas = Arrays.asList(arrLinhas);
    retorno.parse(linhas);

    System.out.println("Detalhes as Titulos: ");

    List<TituloArquivo> titulos = retorno.detalhesAsTitulos();
    for (TituloArquivo titulo : titulos) {
        String segmento = titulo.segmento();
        String numeroDocumento = titulo.numeroDocumento();
        String nossoNumero = titulo.nossoNumero();
        String valorPagamento = titulo.valorPagamento();
        String valorLiquido = titulo.valorLiquido();
        String dataOcorrencia = titulo.dataOcorrencia();
        String movimentoCodigo = titulo.movimentoCodigo();
        String rejeicoes = titulo.rejeicoes();
        String valorTarifaCustas = titulo.valorTarifaCustas();

        System.out.println("Campos: {segmento=" + segmento + " numeroDocumento=" + numeroDocumento);
        System.out.println("\tnossoNumero=" + nossoNumero + " valorPagamento=" + valorPagamento);
        System.out.println("\tvalorLiquido=" + valorLiquido + " dataOcorrencia=" + dataOcorrencia);
        System.out.println("\tmovimentoCodigo=" + movimentoCodigo + " rejeicoes=" + rejeicoes);
        System.out.println("\tvalorTarifaCustas=" + valorTarifaCustas + " rejeicoes=" + valorTarifaCustas + "}");
    }
```


### Criar um novo layout de remessa ou de retorno

```
import com.github.braully.boleto.TagLayout;
import static com.github.braully.boleto.TagLayout.TagCreator.*;
import java.text.SimpleDateFormat;

public class ExemploLayoutSimples {

    /*
     * Ver exemplo mais detalhado em:
     * com.github.braully.boleto.LayoutsSuportados._LAYOUT_FEBRABAN_CNAB240
     */
    public static void main(String... args) {
        TagLayout arquivo = tag("arquivo");
        arquivo.with(
                tag("cabecalho").with(
                        //a linha de cabeçalho será ignorada
                        tag("branco").length(240)
                ),
                tag("detalhe").with(
                        tag("codigoBanco").length(3),
                        //Val é usado para setar um campo literal fixo: espaçoes em branco, codigos, literais e etc
                        tag("branco").val("  "),
                        tag("codigoMoeda").val("09"),
                        //As tags com id são importantes pra determinar o tipo da linha no layout de retorno
                        tag("codigoRegistro").length(1).id(true),
                        tag("segmento").id(true).value("D"),
                        //Alguns campos podemo precisar de formatação ou parser personalizado, exemplo data
                        tag("dataVencimento").length(8).format(new SimpleDateFormat("ddMMyyyy"))
                ),
                tag("rodape").with(
                        //a linha de rodape será ignorada
                        tag("branco").length(240)
                )
        );

        //
        System.out.println(arquivo);
    }
}
```
