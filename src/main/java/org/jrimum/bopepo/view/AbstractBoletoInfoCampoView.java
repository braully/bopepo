/*
 * Copyright 2014 JRimum Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created at: 19/01/2014 - 22:11:18
 *
 * ================================================================================
 *
 * Direitos autorais 2014 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode
 * usar esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob esta
 * LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER TIPO, sejam
 * expressas ou tácitas. Veja a LICENÇA para a redação específica a reger permissões
 * e limitações sob esta LICENÇA.
 *
 * Criado em: 19/01/2014 - 22:11:18
 *
 */
package org.jrimum.bopepo.view;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jrimum.bopepo.view.BoletoInfoCampoAgenciaCodigoCedente.getTextoAgenciaCodigoCedente;
import static org.jrimum.bopepo.view.BoletoInfoCampoCodigoBanco.getTextoCodigoDoBanco;
import static org.jrimum.bopepo.view.BoletoInfoCampoEndereco.getTextoEnderecoLinha1;
import static org.jrimum.bopepo.view.BoletoInfoCampoEndereco.getTextoEnderecoLinha2;
import static org.jrimum.bopepo.view.BoletoInfoCampoLogoBanco.getImagemBanco;
import static org.jrimum.bopepo.view.BoletoInfoCampoNossoNumero.getTextoNossoNumero;
import static org.jrimum.bopepo.view.BoletoInfoCampoPessoa.getTextoNomeCprfDaPessoa;
import static org.jrimum.utilix.DateFormat.DDMMYYYY_B;
import static org.jrimum.utilix.DecimalFormat.MONEY_DD_BR;
import static org.jrimum.utilix.Objects.isNotNull;
import static org.jrimum.utilix.Objects.whenNull;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.LinhaDigitavel;
import org.jrimum.bopepo.pdf.CodigoDeBarras;
import org.jrimum.domkee.banco.TipoDeTitulo;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;

/**
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 *
 */
public abstract class AbstractBoletoInfoCampoView implements BoletoInfoCampoView {

    private final ResourceBundle resourceBundle;

    private final Boleto boleto;

    /**
     * Modo de instanciação não permitido.
     *
     * @throws IllegalStateException Caso haja alguma tentativa de utilização
     * deste construtor.
     */
    @SuppressWarnings("unused")
    private AbstractBoletoInfoCampoView() {
        Exceptions.throwIllegalStateException("Instanciação não permitida!");
        resourceBundle = null;
        boleto = null;
    }

    public AbstractBoletoInfoCampoView(ResourceBundle resourceBundle, Boleto boleto) {
        Objects.checkNotNull(resourceBundle);
        Objects.checkNotNull(boleto);
        this.resourceBundle = resourceBundle;
        this.boleto = boleto;
    }

    public String getTextoRsInstrucaoAoSacado() {
        return getValue(boleto.getInstrucaoAoSacado());
    }

    public String getTextoRsCpfCnpj() {
        try {
            return boleto.getTitulo().getCedente().getCPRF().getCodigoFormatado();
        } catch (Exception e) {
            log.debug("Fail on cprf sacado, default vaule 'Empty' in use");
            return EMPTY;
        }
    }

    public String getTextoRsSacado() {
        return getTextoFcSacadoL1();
    }

    public String getTextoFcSacadoL1() {
        return getTextoNomeCprfDaPessoa(boleto.getTitulo().getSacado());
    }

    public String getTextoFcSacadoL2() {
        return getTextoEnderecoLinha1(boleto.getTitulo().getSacado());
    }

    public String getTextoFcSacadoL3() {
        return getTextoEnderecoLinha2(boleto.getTitulo().getSacado());
    }

    public String getTextoFcSacadorAvalistaL1() {
        return getTextoNomeCprfDaPessoa(boleto.getTitulo().getSacadorAvalista());
    }

    public String getTextoFcSacadorAvalistaL2() {
        return getTextoEnderecoLinha1(boleto.getTitulo().getSacadorAvalista());
    }

    public String getTextoFcSacadorAvalistaL3() {
        return getTextoEnderecoLinha2(boleto.getTitulo().getSacadorAvalista());
    }

    public String getTextoFcDataProcessamento() {
        return getValue(boleto.getDataDeProcessamento());
    }

    public String getTextoFcAceite() {
        return getValue(boleto.getTitulo().getAceite());
    }

    public String getTextoFcEspecieDocumento() {
        String textoFcEspecieDocumento = EMPTY;
        TipoDeTitulo tipoTitulo = boleto.getTitulo().getTipoDeDocumento();
        if (tipoTitulo != null) {
            textoFcEspecieDocumento = tipoTitulo.getSigla();
        }
        return textoFcEspecieDocumento;
    }

    public String getTextoFcDataDocumento() {
        return getValue(boleto.getTitulo().getDataDoDocumento());
    }

    public String getTextoFcLocalPagamento() {
        return getValue(boleto.getLocalPagamento());
    }

    public String getTextoFcCarteira() {
        return whenNull(boleto.getTitulo().getContaBancaria().getCarteira(), EMPTY, boleto.getTitulo().getContaBancaria().getCarteira().getCodigo().toString());
    }

    public String getTextoFcInstrucaoAoCaixa1() {
        return getValue(boleto.getInstrucao1());
    }

    public String getTextoFcInstrucaoAoCaixa2() {
        return getValue(boleto.getInstrucao2());
    }

    public String getTextoFcInstrucaoAoCaixa3() {
        return getValue(boleto.getInstrucao3());
    }

    public String getTextoFcInstrucaoAoCaixa4() {
        return getValue(boleto.getInstrucao4());
    }

    public String getTextoFcInstrucaoAoCaixa5() {
        return getValue(boleto.getInstrucao5());
    }

    public String getTextoFcInstrucaoAoCaixa6() {
        return getValue(boleto.getInstrucao6());
    }

    public String getTextoFcInstrucaoAoCaixa7() {
        return getValue(boleto.getInstrucao7());
    }

    public String getTextoFcInstrucaoAoCaixa8() {
        return getValue(boleto.getInstrucao8());
    }

    public String getTextoRsMoraMulta() {
        return getTextoFcMoraMulta();
    }

    public String getTextoFcMoraMulta() {
        return getValue(boleto.getTitulo().getMora());
    }

    public String getTextoRsOutroAcrescimo() {
        return getTextoFcOutroAcrescimo();
    }

    public String getTextoFcOutroAcrescimo() {
        return getValue(boleto.getTitulo().getAcrecimo());
    }

    public String getTextoRsOutraDeducao() {
        return getTextoFcOutraDeducao();
    }

    public String getTextoFcOutraDeducao() {
        return getValue(boleto.getTitulo().getDeducao());
    }

    public String getTextoRsDescontoAbatimento() {
        return getTextoFcDescontoAbatimento();
    }

    public String getTextoFcDescontoAbatimento() {
        return getValue(boleto.getTitulo().getDesconto());
    }

    public String getTextoRsValorDocumento() {
        return getTextoFcValorDocumento();
    }

    public String getTextoFcValorDocumento() {
        return getValue(boleto.getTitulo().getValor());
    }

    public String getTextoRsValorCobrado() {
        return getTextoFcValorCobrado();
    }

    public String getTextoFcValorCobrado() {
        return getValue(boleto.getTitulo().getValorCobrado());
    }

    public String getTextoRsDataVencimento() {
        return getTextoFcDataVencimento();
    }

    public String getTextoFcDataVencimento() {
        return getValue(boleto.getTitulo().getDataDoVencimento());
    }

    public String getTextoRsNumeroDocumento() {
        return getTextoFcNumeroDocumento();
    }

    public String getTextoFcNumeroDocumento() {
        return getValue(boleto.getTitulo().getNumeroDoDocumento());
    }

    public String getTextoRsCedente() {
        return getTextoFcCedente();
    }

    public String getTextoFcCedente() {
        return getValue(boleto.getTitulo().getCedente().getNome());
    }

    public String getTextoRsEspecie() {
        return getTextoFcEspecie();
    }

    public String getTextoFcEspecie() {
        return getValue(boleto.getTitulo().getTipoDeMoeda());
    }

    public String getTextoRsCodigoBanco() {
        return getTextoFcCodigoBanco();
    }

    public String getTextoFcCodigoBanco() {
        return getTextoCodigoDoBanco(boleto.getTitulo().getContaBancaria());
    }

    public String getTextoRsAgenciaCodigoCedente() {
        return getTextoFcAgenciaCodigoCedente();
    }

    public String getTextoFcAgenciaCodigoCedente() {
        return getTextoAgenciaCodigoCedente(boleto.getTitulo().getContaBancaria());
    }

    public String getTextoRsNossoNumero() {
        return getTextoFcNossoNumero();
    }

    public String getTextoFcNossoNumero() {
        return getTextoNossoNumero(boleto.getTitulo());
    }

    public Image getImagemRsLogoBanco() {
        return getImagemFcLogoBanco();
    }

    public Image getImagemFcLogoBanco() {
        return getImagemBanco(resourceBundle, boleto.getTitulo().getContaBancaria());
    }

    public String getTextoRsLogoBanco() {
        return getTextoFcLogoBanco();
    }

    public String getTextoFcLogoBanco() {
        return boleto.getTitulo().getContaBancaria().getBanco().getNome();
    }

    public String getTextoRsLinhaDigitavel() {
        return getTextoFcLinhaDigitavel();
    }

    public String getTextoFcLinhaDigitavel() {
        LinhaDigitavel linhaDigitavel = boleto.getLinhaDigitavel();
        return linhaDigitavel.write();
    }

    public Image getImagemFcCodigoBarra() {
        return CodigoDeBarras.valueOf(boleto.getCodigoDeBarras().write()).toImage();
    }

    protected final Boleto getBoleto() {
        return this.boleto;
    }

    private String getValue(String value) {
        if (isNotBlank(value)) {
            return value;
        }
        return EMPTY;
    }

    private String getValue(Date value) {
        if (isNotNull(value)) {
            return DDMMYYYY_B.format(value);
        }
        return EMPTY;
    }

    private String getValue(BigDecimal value) {
        if (isNotNull(value)) {
            return MONEY_DD_BR.format(value);
        }
        return EMPTY;
    }

    private <T extends Enum<?>> String getValue(T value) {
        if (isNotNull(value)) {
            return value.name();
        }
        return EMPTY;
    }

    public String getEnderecoBeneficiario() {
        StringBuilder enderecoBeneficiario = new StringBuilder("");

        if (getBoleto().getTitulo().getCedente().getNextEndereco() == null) {
            return enderecoBeneficiario.toString();
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getLogradouro() != null) {
            enderecoBeneficiario.append(getBoleto().getTitulo().getCedente().getNextEndereco().getLogradouro());
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getNumero() != null) {
            enderecoBeneficiario.append(", ").append(getBoleto().getTitulo().getCedente().getNextEndereco().getNumero());
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getBairro() != null) {

            enderecoBeneficiario.append(", ");

            if (getBoleto().getTitulo().getCedente().getNextEndereco().getBairro().length() > 30) {
                enderecoBeneficiario.append(getBoleto().getTitulo().getCedente().getNextEndereco().getBairro().subSequence(0, 30));
            } else {
                enderecoBeneficiario.append(getBoleto().getTitulo().getCedente().getNextEndereco().getBairro());
            }
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getLocalidade() != null) {
            enderecoBeneficiario.append(", ");

            if (getBoleto().getTitulo().getCedente().getNextEndereco().getLocalidade().length() >= 30) {
                enderecoBeneficiario.append(getBoleto().getTitulo().getCedente().getNextEndereco().getLocalidade().substring(0, 30));
            } else {
                enderecoBeneficiario.append(getBoleto().getTitulo().getCedente().getNextEndereco().getLocalidade());
            }
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getUF() != null) {
            enderecoBeneficiario.append(" - ").append(getBoleto().getTitulo().getCedente().getNextEndereco().getUF().getSigla());
        }

        if (getBoleto().getTitulo().getCedente().getNextEndereco().getCEP() != null) {
            enderecoBeneficiario.append(" - ").append(getBoleto().getTitulo().getCedente().getNextEndereco().getCEP().getCep());
        }
        return enderecoBeneficiario.toString();
    }
}
