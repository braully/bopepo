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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
import picocli.CommandLine;

/**
 *
 * @author Braully Rocha da Silva Reference:
 * https://stackoverflow.com/questions/62799744/two-exclusive-optiongroup-with-apache-commons-cli
 */
public abstract class CLIRemessaPagamentoAbs implements Runnable {

    public String getContaComDv(JSONObject contaconjson) {
        StringBuilder sb = new StringBuilder();
        String conta = contaconjson.getString("conta");
        sb.append(conta);
        String digito = contaconjson.getString("digito");
        if (digito != null) {
            sb.append(digito);
        }
        return sb.toString();
    }

    public String somenteNumeros(Object obj) {
        String valorstr = obj.toString();
        return valorstr.replaceAll("\\D+", "");
    }

    protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Date parseDataAAAA_MM_DD(String strdataVencimento) throws ParseException {
        return df.parse(strdataVencimento);
    }
}
