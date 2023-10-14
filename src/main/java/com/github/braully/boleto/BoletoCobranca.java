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

import org.jrimum.ConfiguracaoJRimum;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.tuple.Pair;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.LinhaDigitavel;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.Cedente;
import org.jrimum.domkee.banco.ContaBancaria;
import org.jrimum.domkee.banco.NumeroDaConta;
import org.jrimum.domkee.banco.ParametroBancario;
import org.jrimum.domkee.banco.ParametrosBancariosMap;
import org.jrimum.domkee.banco.Sacado;
import org.jrimum.domkee.banco.TipoDeCobranca;
import org.jrimum.domkee.banco.Titulo;
import org.jrimum.domkee.pessoa.CNPJ;
import org.jrimum.domkee.pessoa.CPF;
import org.jrimum.texgit.Filler;
import org.jrimum.utilix.DateUtil;

/**
 *
 * @author braully
 */
public class BoletoCobranca extends Boleto {

    public static final String SEPARADOR_DIGITO_VERIFICADOR = "-";

    public BoletoCobranca() {
        super();
    }

    @Override
    public LinhaDigitavel getLinhaDigitavel() {
        if (this.linhaDigitavel == null) {
            log.debug("Boleto não gerado");
            gerarLinhaDigitavel();
        }
        return super.getLinhaDigitavel();
    }

    /* Metodos da API Publica */
    public BoletoCobranca banco(String numeroBanco) {
        String numeroBancoCompleto = this.completaCodigoBanco(numeroBanco);
        this.getContaBancaria().setBanco(BancosSuportados.instancia(numeroBancoCompleto));
        return this;
    }

    /**
     * Número da agência, caso tenha digito verificador, favor informar no
     * formato "numero-dv".
     *
     * @param strNumero
     * @return o proprio boleto, para facilidades de utilização.
     */
    public BoletoCobranca agencia(String strNumero) {
        Pair<Integer, String> numeroDV = quebraNumeroDV(strNumero);

        Integer numero = numeroDV.getLeft();
        String dv = numeroDV.getRight();

        if (dv != null) {
            this.getContaBancaria().setAgencia(new Agencia(numero, dv));
        } else {
            this.getContaBancaria().setAgencia(new Agencia(numero));
        }

        return this;
    }

    /**
     * Número da conta, caso tenha digito verificador, favor informar no formato
     * "numero-dv".
     *
     * @param strconta
     * @return o proprio boleto, para facilidades de utilização.
     */
    public BoletoCobranca conta(String strconta) {
        Pair<Integer, String> numeroDV = quebraNumeroDV(strconta);
        Integer numero = numeroDV.getLeft();
        String dv = numeroDV.getRight();

        if (dv != null) {
            this.getContaBancaria().setNumeroDaConta(new NumeroDaConta(numero, dv));
        } else {
            this.getContaBancaria().setNumeroDaConta(new NumeroDaConta(numero));
        }
        return this;
    }

    public BoletoCobranca sacado(String nomeSacado) {
        this.getSacado().setNome(nomeSacado);
        return this;
    }

    public BoletoCobranca sacadoCpf(String cpf) {
        String completaCpf = this.completaCpf(cpf);
        this.getSacado().setCPRF(new CPF(completaCpf));
        return this;
    }

    public BoletoCobranca sacadoCnpj(String cnpj) {
        String completaCnpj = this.completaCnpj(cnpj);
        this.getSacado().setCPRF(new CNPJ(completaCnpj));
        return this;
    }

//    TODO: Implementar
//    public Boleto sacadoEndereco(String endereco) {
//        this.getSacado().addEndereco(endereco);
//        return this;
//    }
//
//    public BoletoCobranca cedenteEndereco(String endereco) {
//        this.getCedente().addEndereco(endereco);
//        return this;
//    }
    public BoletoCobranca cedente(String nomeSacado) {
        this.getCedente().setNome(nomeSacado);
        return this;
    }

    public BoletoCobranca cedenteCnpj(String cnpj) {
        cnpj = completaCnpj(cnpj);
        this.getCedente().setCPRF(new CNPJ(cnpj));
        return this;
    }

    public BoletoCobranca nossoNumero(String nossoNumero) {
        Pair<String, String> numeroDV = quebraStringDV(nossoNumero);

        String numero = numeroDV.getLeft();
        String dv = numeroDV.getRight();

        this.getTitulo().setNossoNumero(numero);

        if (dv != null) {
            this.getTitulo().setDigitoDoNossoNumero(dv);
        }
        return this;
    }

    public BoletoCobranca valor(String vr) {
        return this.valor(Long.parseLong(vr.replaceAll("\\D+", "")), 2);
    }

    public BoletoCobranca valor(BigDecimal big) {
        this.getTitulo().setValor(big);
        return this;
    }

    public BoletoCobranca valor(Double valor) {
        this.getTitulo().setValor(BigDecimal.valueOf(valor));
        return this;
    }

    public BoletoCobranca valor(long val, int scale) {
        this.getTitulo().setValor(BigDecimal.valueOf(val, scale));
        return this;
    }

    public BoletoCobranca valor(long val) {
        this.getTitulo().setValor(BigDecimal.valueOf(val));
        return this;
    }

    public BoletoCobranca dataVencimento(String strDate) {
        this.getTitulo().setDataDoVencimento(DateUtil.parse(strDate, "dd/MM/yyyy"));
        return this;
    }

    public BoletoCobranca dataVencimento(Date datavencimento) {
        this.getTitulo().setDataDoVencimento(datavencimento);
        return this;
    }

    public BoletoCobranca numeroDocumento(Number num) {
        this.getTitulo().setNumeroDoDocumento("" + num);
        return this;
    }

    public BoletoCobranca numeroDocumento(String string) {
        this.getTitulo().setNumeroDoDocumento(string);
        return this;
    }

    public BoletoCobranca carteira(String string) {
        this.getCarteira().setCodigo(parseInt(string));
        return this;
    }

    // Alias
    public BoletoCobranca cobrancaRegistrada(Boolean registrado) {
        return carteiraCobrancaRegistrada(registrado);
    }

    public BoletoCobranca carteiraCobrancaRegistrada(Boolean registrado) {
        if (registrado) {
            this.getCarteira().setTipoCobranca(TipoDeCobranca.COM_REGISTRO);
        } else {
            this.getCarteira().setTipoCobranca(TipoDeCobranca.SEM_REGISTRO);
        }
        return this;
    }

    /* Metodos internos */
    private String completaCodigoBanco(String banco) {
        String fill = Filler.ZERO_LEFT.fill(banco, 3);
        return fill;
    }

    private String completaCnpj(String cnpj) {
        String fill = Filler.ZERO_LEFT.fill(cnpj, 14);
        return fill;
    }

    private String completaCpf(String cnpj) {
        String fill = Filler.ZERO_LEFT.fill(cnpj, 11);
        return fill;
    }

    private Cedente getCedente() {
        Cedente sacado = this.getTitulo().getCedente();
        if (sacado == null) {
            sacado = new Cedente("");
            this.getTitulo().setCedente(sacado);
        }
        return sacado;
    }

    private Sacado getSacado() {
        Sacado sacado = this.getTitulo().getSacado();
        if (sacado == null) {
            sacado = new Sacado("");
            this.getTitulo().setSacado(sacado);
        }
        return sacado;
    }

    private Carteira getCarteira() {
        Carteira carteira = this.getContaBancaria().getCarteira();
        if (carteira == null) {
            carteira = new Carteira();
            this.getContaBancaria().setCarteira(carteira);
            // Tipo de cobrança padrão é registrado
            carteira.setTipoCobranca(TipoDeCobranca.COM_REGISTRO);
        }
        return carteira;
    }

    public Titulo getTitulo() {
        Titulo titulo1 = super.getTitulo();
        if (titulo1 == null) {
            titulo1 = new Titulo();
            this.setTitulo(titulo1);
        }
        return titulo1;
    }

    private ContaBancaria getContaBancaria() {
        if (this.getTitulo().getContaBancaria() == null) {
            this.getTitulo().setContaBancaria(new ContaBancaria());
        }
        return this.getTitulo().getContaBancaria();
    }

    static Integer parseInt(String string) {
        if (string == null) {
            throw new IllegalArgumentException("null string");
        }
        if (!ConfiguracaoJRimum.ignorarZeroEsquerdaConversao && !string.isEmpty() && string.charAt(0) == '0') {
            throw new IllegalArgumentException("Octal converter: " + string);
        }
        return Integer.parseInt(string);
    }

    private Pair<String, String> quebraStringDV(String strNumero) {
        String numero = null;
        String dv = null;
        if (strNumero == null) {
            throw new IllegalArgumentException("Numero esta null");
        }
        // TODO: Melhorar isso, ir pelo formato de agencia de cada banco.
        if (strNumero.contains(SEPARADOR_DIGITO_VERIFICADOR)) {
            String[] split = strNumero.split(SEPARADOR_DIGITO_VERIFICADOR);
            numero = split[0];
            dv = split[1];
        } else {
            numero = strNumero;
        }
        return Pair.of(numero, dv);
    }

    private Pair<Integer, String> quebraNumeroDV(String strNumero) {
        Integer numero = null;
        String dv = null;
        if (strNumero == null) {
            throw new IllegalArgumentException("Numero esta null");
        }
        // TODO: Melhorar isso, ir pelo formato de agencia de cada banco.
        if (strNumero.contains(SEPARADOR_DIGITO_VERIFICADOR)) {
            String[] split = strNumero.split(SEPARADOR_DIGITO_VERIFICADOR);
            numero = parseInt(split[0]);
            dv = split[1];
        } else {
            numero = parseInt(strNumero);
        }
        return Pair.of(numero, dv);
    }

    public BoletoCobranca gerarLinhaDigitavel() {
        this.processFromTitulo(titulo);
        return this;
    }

    public BoletoCobranca parametroBancario(ParametroBancario parametroBancario, Object val) {
        ParametrosBancariosMap parametrosBancarios = this.getParametrosBancarios();
        Number valor = null;
        if (val != null) {
            if (val instanceof Number) {
                valor = (Number) val;
            } else {
                parseInt(val.toString());
            }
        }
        parametrosBancarios.adicione(parametroBancario, valor);
        return this;
    }

    public ParametrosBancariosMap getParametrosBancarios() {
        Titulo titulo1 = this.getTitulo();
        ParametrosBancariosMap parametrosBancarios = titulo1.getParametrosBancarios();
        if (parametrosBancarios == null) {
            parametrosBancarios = new ParametrosBancariosMap();
            titulo1.setParametrosBancarios(parametrosBancarios);
        }
        return parametrosBancarios;
    }
}
