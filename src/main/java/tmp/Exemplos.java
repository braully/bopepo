/*
 * Copyright 2023 strike.
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
package tmp;

import com.github.braully.boleto.LayoutsSuportados;
import com.github.braully.boleto.RetornoArquivo;
import com.github.braully.boleto.TituloArquivo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exemplos {

    public static void main(String... args) throws FileNotFoundException, IOException {

//        BufferedReader leitorArquivo = new BufferedReader(new FileReader("RETORNO001.txt"));
//
//        List<String> linhasLidas = new ArrayList<>();
//        String linha = null;
//        while (null != (linha = leitorArquivo.readLine())) {
//            linhasLidas.add(linha);
//        }
//
//        // Layout de arquivo de retorno
//        RetornoArquivo retorno = new RetornoArquivo(LayoutsSuportados.LAYOUT_FEBRABAN_CNAB240);
//        // Parse do arquivo lido no layout LAYOUT_FEBRABAN_CNAB240
//        retorno.parse(linhasLidas);
//
//        System.out.println("Detalhes as Titulos: ");
//
//        List<TituloArquivo> titulos = retorno.detalhesAsTitulos();
//        //Titulos encontrados no arquivo de retorno
//        // E principais dados disponiveis
//        for (TituloArquivo titulo : titulos) {
//            String segmento = titulo.segmento();
//            String numeroDocumento = titulo.numeroDocumento();
//            String nossoNumero = titulo.nossoNumero();
//            String valorPagamento = titulo.valorPagamento();
//            String valorLiquido = titulo.valorLiquido();
//            String dataOcorrencia = titulo.dataOcorrencia();
//            String movimentoCodigo = titulo.movimentoCodigo();
//            String rejeicoes = titulo.rejeicoes();
//            String valorTarifaCustas = titulo.valorTarifaCustas();
//
//            // Print dos dados
//            System.out.println("Campos: {segmento=" + segmento + " numeroDocumento=" + numeroDocumento);
//            System.out.println("\tnossoNumero=" + nossoNumero + " valorPagamento=" + valorPagamento);
//            System.out.println("\tvalorLiquido=" + valorLiquido + " dataOcorrencia=" + dataOcorrencia);
//            System.out.println("\tmovimentoCodigo=" + movimentoCodigo + " rejeicoes=" + rejeicoes);
//            System.out.println("\tvalorTarifaCustas=" + valorTarifaCustas + " rejeicoes=" + valorTarifaCustas + "}");
//        }
    }
}
