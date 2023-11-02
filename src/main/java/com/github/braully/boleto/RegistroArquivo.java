/*
 * Copyright 2019 Projeto JRimum.
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

import static com.github.braully.boleto.TagLayout.TagCreator.fagencia;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoCodigo;
import static com.github.braully.boleto.TagLayout.TagCreator.fbancoNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteCnpj;
import static com.github.braully.boleto.TagLayout.TagCreator.fcedenteNome;
import static com.github.braully.boleto.TagLayout.TagCreator.fconta;
import static com.github.braully.boleto.TagLayout.TagCreator.fconvenio;
import static com.github.braully.boleto.TagLayout.TagCreator.fmovimentoCodigo;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialArquivo;
import static com.github.braully.boleto.TagLayout.TagCreator.fsequencialRegistro;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import org.jrimum.texgit.FixedField;
import org.jrimum.texgit.IFiller;
import org.jrimum.texgit.Record;
import org.jrimum.utilix.Objects;

/**
 *
 * @author braully
 */
public class RegistroArquivo extends Record {

    protected TagLayout layoutRegistro;
    protected List<FixedField> extraIds;

    public RegistroArquivo() {
    }

    public RegistroArquivo(TagLayout layoutRegistro) {
        this.setName(layoutRegistro.nome);
        this.layoutRegistro = layoutRegistro;
        layoutRegistro.filhos.stream().forEach(l -> add(l));
    }

    public void addExtraId(FixedField fixedField) {
        if (extraIds == null) {
            extraIds = new ArrayList<>();
        }
        extraIds.add(fixedField);
    }

    public String render() {
//            StringBuilder sb = new StringBuilder();
//            return sb.toString();
        return this.write();
    }

    /* Campos comuns na maioria dos registros na maioria dos layouts */
    public RegistroArquivo sequencialRegistro(Integer seq) {
        setValue(fsequencialRegistro().nome, seq);
        return this;
    }

    public Integer sequencialRegistro() {
        return this.getValueAsInteger();
    }

    public RegistroArquivo banco(String codigo, String nome) {
        setValue(fbancoCodigo().nome, codigo).setValue(fbancoNome().nome, nome.toUpperCase());
        return this;
    }

    public Integer sequencialArquivo() {
        return getValueAsInteger(fsequencialArquivo().nome);
    }

    public String bancoCodigo() {
        return getValue(fbancoCodigo().nome);
    }

    public RegistroArquivo cedente(String nome, String cnpj) {
        setValue(fcedenteNome().nome, nome).setValue(fcedenteCnpj().nome, cnpj);
        return this;
    }

    public String cedenteCnpj() {
        return getValue(fcedenteCnpj().nome);
    }

    public RegistroArquivo convenio(String convenio, String agencia, String conta, String dac) {

        this.convenio(convenio)
                .agencia(agencia)
                .conta(conta)
                .dac(dac);
        return this;
    }

    public RegistroArquivo convenio(String convenio, String carteira, String agencia, String conta, String dac) {
        this.convenio(convenio).carteira(carteira).agencia(agencia).conta(conta).dac(dac);
        return this;
    }

    public RegistroArquivo endereco(String endereco) {
        return setValue(endereco);
    }

    public RegistroArquivo endereco(String logradouro, String numero, String complemento, String nomeCidade, String cep, String uf) {
        this.setValue(logradouro)
                .setValue("numero", numero)
                .setValue("complemento", complemento == null ? "" : complemento)
                .setValue("cidade", nomeCidade)
                .setValue("cep", cep)
                .setValue("uf", uf);
        return this;
    }

    public RegistroArquivo convenio(String convenio) {
        return setValue(convenio);
    }

    public RegistroArquivo carteira(String carteira) {
        return setValue(carteira);
    }

    public RegistroArquivo variacao(String variacao) {
        return setValue(variacao);
    }

    public RegistroArquivo modalidade(String modalidade) {
        return setValue(modalidade);
    }

    public String convenio() {
        return getValue(fconvenio().nome);
    }

    //não pode ser marcado como String pois Strings são immmutable, o que faz com que se perca mudanças de sanitize
    public RegistroArquivo agencia(Object agencia) {

        if (agencia != null && ((String) agencia).length() == 4) {
            //caso o usuario não tenha informado o digito verificador, assumir que é 0
            agencia = agencia + "-0";
        }

        return setValue(agencia);
    }

    public String agencia() {
        return getValue(fagencia().nome);
    }

    public RegistroArquivo conta(String conta) {
        return setValue(conta);
    }

    public String conta() {
        return getValue(fconta().nome);
    }

    public RegistroArquivo dac(String dac) {
        return setValue(dac);
    }

    public RegistroArquivo setVal(String nomeAtributo, Object valor) {
        this.setValue(nomeAtributo, valor);
        return this;
    }

    protected String getValue() {
        //TODO: Melhorar isso; perda de performance
        String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();
        /* Propriedade a ser setada é o nome do metodo que chamou */
        Object value = this.getValue(nomeMetodoAnterior);
        String ret = null;
        if (value != null) {
            ret = value.toString();
        }
        return ret;
    }

    protected String removeLeftZeros(String number) {
        if (number == null) {
            return null;
        }
        return number.replaceFirst("^0+(?!$)", "");
    }

    protected String trimNumberValue(String str) {
        if (str != null) {
            str = str.replaceAll("\\D", "");
            str = removeLeftZeros(str);
        }
        return str;
    }

    public Integer getValueAsInteger() {
        //TODO: Melhorar isso; perda de performance
        String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getValueAsInteger(nomeMetodoAnterior);
    }

    public Integer getValueAsInteger(String nomefield) {
        /* Propriedade a ser setada é o nome do metodo que chamou */
        Object value = this.getValue(nomefield);
        Integer ret = null;
        if (value != null) {
            if (value instanceof Integer) {
                ret = (Integer) value;
            } else {
                ret = Integer.parseInt(trimNumberValue(value.toString()));
            }
        }
        return ret;
    }

    public Number getValueAsNumber() {
        //TODO: Melhorar isso; perda de performance
        String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();
        /* Propriedade a ser setada é o nome do metodo que chamou */
        Object value = this.getValue(nomeMetodoAnterior);
        Number ret = null;
        if (value != null) {
            if (value instanceof Number) {
                ret = (Number) value;
            } else {
                ret = Long.parseLong(trimNumberValue(value.toString()));
            }
        }
        return ret;
    }

    protected RegistroArquivo setValue(Object valor) {
        //TODO: Melhorar isso; perda de performance
        String nomeMetodoAnterior = Thread.currentThread().getStackTrace()[2].getMethodName();

        /* Propriedade a ser setada é o nome do metodo que chamou */
        this.setValue(nomeMetodoAnterior, valor);
        return this;
    }

    private void add(TagLayout l) {
        FixedField fixedField = new FixedField();
        if (isValid(l.nome)) {
            fixedField.setName(l.nome);
        }
        String value = l.getAtr("value");
        if (isValid(value)) {
            fixedField.setValue(value);
        }
        Integer len = l.getInt("length");
        if (Objects.isNotNull(len)) {
            fixedField.setFixedLength(len);
        } else if ("field".equals(l.nome)) {
            throw new IllegalArgumentException("Field " + l + " sem comprimento (lenght) ");
        }
        Format format = (Format) l.getObj("format");
        if (Objects.isNotNull(format)) {
            fixedField.setFormatter(format);
        }
        IFiller filler = (IFiller) l.getObj("filler");
        if (Objects.isNotNull(filler)) {
            fixedField.setFiller(filler);
            fixedField.setBlankAccepted(true);
            fixedField.setValue("");
        }
        filler = (IFiller) l.getObj("padding");
        if (Objects.isNotNull(filler)) {
            fixedField.setFiller(filler);
        }
        if (l.isAttr("truncate")) {
            fixedField.setTruncate(true);
        }

        if (l.isAttr("apenasDigitos")) {
            fixedField.setApenasDigitos(true);
        }

        if (l.isAttr("id")) {
            if (this.getIdType() == null) {
                setIdType(fixedField);
            } else {
                addExtraId(fixedField);
            }
        }
        super.add(fixedField);
        super.incLength(len);
        super.incSize();
    }

    @Override
    public String toString() {
        return toStringValorFields();
    }

    public String toStringValorFields() {
        StringBuilder sb = new StringBuilder("Registro{value=");
        if (layoutRegistro != null) {
            sb.append(layoutRegistro.nome);
        }
        sb.append(",\n fields=[");
        if (this.fields != null) {
            sb.append("\n");
            for (FixedField ff : this.fields) {
                Object value = ff.getValue();
                String name = ff.getName();
                if (name != null && !name.trim().isEmpty()
                        && value != null && !value.toString().trim().isEmpty()) {
                    sb.append("\t");
                    sb.append(name);
                    sb.append("=");
                    sb.append(value);
                    sb.append("\n");
                }
            }

        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public String toStringLayoutFields() {
        StringBuilder sb = new StringBuilder("Registro{value=");
        if (layoutRegistro != null) {
            sb.append(layoutRegistro.nome);
        }

        sb.append(",\nlen=").append(this.getFixedLength());
        sb.append(",\n fields=[");
        if (this.fields != null) {
            sb.append("\n");
            for (FixedField ff : this.fields) {
                String name = ff.getName();
                if (name != null && !name.trim().isEmpty()) {
                    sb.append("\t");
                    sb.append(name);
                    sb.append(" [len: ");
                    sb.append(ff.getFixedLength());
                    sb.append("]\n");
                }
            }

        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public String getDescricaoLayout() {
        StringBuilder sb = new StringBuilder("Registro{layout=");
        if (layoutRegistro != null) {
            sb.append(layoutRegistro.nome);
        }
        sb.append(",\n fields=[");
        if (this.fields != null) {
            sb.append("\n");
            for (FixedField ff : this.fields) {
                sb.append("\t");
                sb.append(ff.getName());
                sb.append("=");
                sb.append(ff.getLength());
                sb.append("\n");
            }
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public RegistroArquivo clone() {
        RegistroArquivo clone = new RegistroArquivo(this.layoutRegistro);
        return clone;
    }

    private boolean isValid(String nome) {
        return nome != null;
    }

    public boolean checkIds(String linha) {
        FixedField<String> id = null;
        FixedField<String> idType = this.getIdType();
        try {
            id = this.getId(linha);
        } catch (Exception e) {

        }
        boolean ret = idType != null && idType.equalsValue(id);
        if (ret) {
            if (extraIds != null) {
                for (FixedField ff : extraIds) {
                    FixedField<String> lval = this.get(ff, linha);
                    ret = ret && ff.equalsValue(lval);
                    //Break para melhorar a performance
                }
            }
        }
        return ret;
    }

    public String movimentoCodigo() {
        return getValue(fmovimentoCodigo().nome);
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder("{\"registro\": ");
        if (layoutRegistro != null) {
            sb.append("\"");
            sb.append(layoutRegistro.nome);
            sb.append("\"");
            sb.append(", ");
        }
//        sb.append(",\n fields=[");
        if (this.fields != null) {
            sb.append("\n");
            for (FixedField ff : this.fields) {
                Object value = ff.getValue();
                String name = ff.getName();
                if (name != null && !name.trim().isEmpty()
                        && value != null && !value.toString().trim().isEmpty()) {
                    sb.append("\t");

                    sb.append("\"");
                    sb.append(name);
                    sb.append("\"");

                    sb.append(": ");
                    sb.append("\"");
                    sb.append(value);
                    sb.append("\"");
                    sb.append(",");
                }
            }

        }
        sb.append("}");
        return sb.toString();
    }
}
