/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:58:42
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:58:42
 * 
 */
package org.jrimum.domkee.banco;

import static org.jrimum.utilix.Objects.isNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.jrimum.domkee.banco.ParametroBancario;
import org.jrimum.domkee.banco.ParametrosBancariosMap;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.Objects;

/**
 * Representa um título em cobrança, tais como:
 *
 * 
 *
 * <ul>
 * <li>Cheque</li>
 * <li>Duplicata Mercantil</li>
 * <li>Duplicata de Serviço</li>
 * <li>Duplicata Rural</li>
 * <li>Letra de Câmbio</li>
 * <li>Nota de Crédito Comercial</li>
 * <li>Nota de Crédito Industrial</li>
 * <li>Nota de Crédito Rural</li>
 * <li>E outras espécies pagáveis através da rede bancária.</li>
 *
 * @see TipoDeTitulo
 *
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 * Mercantil</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class Titulo {

    /**
     * @see #setNumeroDoDocumento(String)
     */
    private String numeroDoDocumento;

    /**
     * @see #setDataDoDocumento(Date)
     */
    private Date dataDoDocumento;

    /**
     * @see #setDataDoVencimento(Date)
     */
    private Date dataDoVencimento;

    /**
     * @see org.jrimum.domkee.banco.TipoDeMoeda
     */
    private TipoDeMoeda tipoDeMoeda = TipoDeMoeda.REAL;

    /**
     * @see #setValor(BigDecimal)
     */
    private BigDecimal valor;

    /**
     * @see #setDesconto(BigDecimal)
     */
    private BigDecimal desconto;

    /**
     * @see #setMora(BigDecimal)
     */
    private BigDecimal mora;

    /**
     * @see #setDeducao(BigDecimal)
     */
    private BigDecimal deducao;

    /**
     * @see #setAcrecimo(BigDecimal)
     */
    private BigDecimal acrecimo;

    /**
     * @see #setValorCobrado(BigDecimal)
     */
    private BigDecimal valorCobrado;

    /**
     * @see org.jrimum.domkee.banco.TipoDeTitulo
     */
    private TipoDeTitulo tipoDeDocumento;

    /**
     * @see #setNossoNumero(String)
     */
    private String nossoNumero;

    /**
     * @see #setDigitoDoNossoNumero(String)
     */
    private String digitoDoNossoNumero;

    /**
     * @see #setContaBancaria(ContaBancaria)
     */
    private ContaBancaria contaBancaria;

    /**
     * @see #setCedente(Cedente)
     */
    private Cedente cedente;

    /**
     * @see #setSacado(Sacado)
     */
    private Sacado sacado;

    /**
     * @see #setSacadorAvalista(SacadorAvalista)
     */
    private SacadorAvalista sacadorAvalista;

    /**
     * @see #setParametrosBancarios(ParametrosBancariosMap)
     */
    private ParametrosBancariosMap parametrosBancariosMap;

    /**
     * <p>
     * Identificação de Título Aceito / Não Aceito Código adotado pela FEBRABAN
     * para identificar se o título de cobrança foi aceito (reconhecimento da
     * dívida pelo Sacado). Domínio:
     * </p>
     * <ul>
     * <li>'A' = Aceite</li>
     * <li>'N' = Não Aceite</li>
     * </ul>
     *
     * @since 0.2
     *
     * @version 0.2
     */
    public enum Aceite {
        /**
         * 'A' = Título Aceite.
         */
        A,
        /**
         * 'N' = Título Não Aceite.
         */
        N;
    }

    /*
     Construtor padrão
     */
    public Titulo() {

    }
    /**
     * @see
     * #setAceite(org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite)
     *
     * @see Aceite
     */
    private Aceite aceite;

    /**
     * <p>
     * Cria uma instância de título.
     * </p>
     *
     * @param contaBancaria
     * @param sacado
     * @param cedente
     *
     */
    public Titulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente) {
        this.setContaBancaria(contaBancaria);
        this.setSacado(sacado);
        this.setCedente(cedente);
    }

    /**
     * <p>
     * Cria uma instância de título com sacador avalista.
     * </p>
     *
     * @param contaBancaria
     * @param sacado
     * @param cedente
     * @param sacadorAvalista
     *
     */
    public Titulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente, SacadorAvalista sacadorAvalista) {
        this(contaBancaria, sacado, cedente);
        this.setSacadorAvalista(sacadorAvalista);
    }

    /**
     * <p>
     * Cria uma instância de título acrescentado outros dados bancários ao
     * mesmo.
     * </p>
     *
     * @param contaBancaria
     * @param sacado
     * @param cedente
     * @param parametrosBancariosMap
     *
     */
    public Titulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente,
            ParametrosBancariosMap parametrosBancariosMap) {
        this.setContaBancaria(contaBancaria);
        this.setSacado(sacado);
        this.setCedente(cedente);
        this.setParametrosBancarios(parametrosBancariosMap);
    }

    /**
     * <p>
     * Cria uma instância de título com sacador avalista e acrescentado outros
     * dados bancários além do título.
     * </p>
     *
     * @param contaBancaria
     * @param sacado
     * @param cedente
     * @param parametrosBancariosMap
     * @param sacadorAvalista
     *
     */
    public Titulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente,
            ParametrosBancariosMap parametrosBancariosMap, SacadorAvalista sacadorAvalista) {
        this(contaBancaria, sacado, cedente, parametrosBancariosMap);
        this.setSacadorAvalista(sacadorAvalista);
    }

    /**
     * <p>
     * Indica se o título possuem sacador avalista na instância
     * </p>
     *
     * @return true se tem
     */
    public boolean hasSacadorAvalista() {

        boolean has = false;

        if (isNotNull(this.sacadorAvalista)) {
            has = true;
        }

        return has;
    }

    /**
     * @see
     * #setAceite(org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite)
     *
     * @return the aceite
     */
    public Aceite getAceite() {
        return aceite;
    }

    /**
     * <p>
     * Indicação de aceite.
     * </p>
     *
     * @param aceite the aceite to set
     */
    public void setAceite(Aceite aceite) {
        this.aceite = aceite;
    }

    /**
     * @see #setDataDoDocumento(Date)
     *
     * @return the dataDoDocumento
     */
    public Date getDataDoDocumento() {
        return dataDoDocumento;
    }

    /**
     * <p>
     * Data de emissão do documento.
     * </p>
     *
     * @param dataDoDocumento the dataDoDocumento to set
     */
    public void setDataDoDocumento(Date dataDoDocumento) {
        this.dataDoDocumento = dataDoDocumento;
    }

    /**
     * @see #setDataDoVencimento(Date)
     *
     * @return the dataDoVencimento
     */
    public Date getDataDoVencimento() {
        return dataDoVencimento;
    }

    /**
     * <p>
     * Data de vencimento nominal do título de cobrança, ou melhor, data limite
     * para pagamento do título.
     * </p>
     *
     * @param dataDoVencimento the dataDoVencimento to set
     */
    public void setDataDoVencimento(Date dataDoVencimento) {
        this.dataDoVencimento = dataDoVencimento;
    }

    /**
     * @see #setDigitoDoNossoNumero(String)
     *
     * @return the dígitoDoNossoNúmero
     */
    public String getDigitoDoNossoNumero() {
        return digitoDoNossoNumero;
    }

    /**
     * <p>
     * Dígito verificador do nosso número, caso exista.
     * </p>
     *
     * @param digitoDoNossoNumero the dígitoDoNossoNúmero to set
     */
    public void setDigitoDoNossoNumero(String digitoDoNossoNumero) {
        this.digitoDoNossoNumero = digitoDoNossoNumero;
    }

    /**
     * @see org.jrimum.domkee.banco.TipoDeMoeda
     *
     * @return the enumMoeda
     */
    public TipoDeMoeda getTipoDeMoeda() {
        return tipoDeMoeda;
    }

    /**
     * @see org.jrimum.domkee.banco.TipoDeMoeda
     *
     * @param tipoDeMoeda the enumMoeda to set
     */
    public void setTipoDeMoeda(TipoDeMoeda tipoDeMoeda) {
        this.tipoDeMoeda = tipoDeMoeda;
    }

    /**
     * @see #setNossoNumero(String)
     *
     * @return the nossoNúmero
     */
    public String getNossoNumero() {
        return nossoNumero;
    }

    /**
     * <p>
     * Código fornecido pelo Banco correspondente para identificação do título
     * ou identificação do título atribuído pelo esmissor do título de cobrança.
     * </p>
     *
     * @param nossoNumero the nossoNúmero to set
     */
    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    /**
     * @see #setNumeroDoDocumento(String)
     *
     * @return the númeroDoDocumento
     */
    public String getNumeroDoDocumento() {
        return numeroDoDocumento;
    }

    /**
     * <p>
     * Número de controle do cedente para o referido título. Não confundir com o
     * nosso número.
     * </p>
     *
     * @param numeroDoDocumento the númeroDoDocumento to set
     */
    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
    }

    /**
     * @see #setContaBancaria(ContaBancaria)
     *
     * @return the contaBancaria
     */
    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    /**
     * <p>
     * Conta na qual o título se encontra, conta do favorecido (Cedente).
     * </p>
     *
     * @param contaBancaria the contaBancaria to set
     */
    public void setContaBancaria(ContaBancaria contaBancaria) {

        if (isNotNull(contaBancaria)) {
            this.contaBancaria = contaBancaria;
        } else {
            Exceptions.throwIllegalArgumentException("ContaBancaria não pode ser nula!");
        }
    }

    /**
     * @see #setCedente(Cedente)
     *
     * @return the cedente
     */
    public Cedente getCedente() {
        return cedente;
    }

    /**
     * <p>
     * Cliente que entrega os títulos ao Banco para serem cobrados.
     * </p>
     *
     * @param cedente the cedente to set
     */
    public void setCedente(Cedente cedente) {
        if (isNotNull(cedente)) {
            this.cedente = cedente;
        } else {
            Exceptions.throwIllegalArgumentException("Cedente não pode ser nulo!");
        }
    }

    /**
     * @see #setSacado(Sacado)
     *
     * @return the sacado
     */
    public Sacado getSacado() {
        return sacado;
    }

    /**
     * <p>
     * Pessoa física ou jurídica a que se destina a cobrança do compromisso, é o
     * cliente do Cedente.
     * </p>
     *
     * @param sacado the sacado to set
     */
    public void setSacado(Sacado sacado) {
        if (isNotNull(sacado)) {
            this.sacado = sacado;
        } else {
            Exceptions.throwIllegalArgumentException("Sacado não pode ser nulo!");
        }
    }

    /**
     * @see #setSacadorAvalista(SacadorAvalista)
     *
     * @return the sacadorAvalista
     */
    public SacadorAvalista getSacadorAvalista() {
        return sacadorAvalista;
    }

    /**
     * <p>
     * Cedente original do Título.
     * </p>
     *
     * @param sacadorAvalista the sacadorAvalista to set
     */
    public void setSacadorAvalista(SacadorAvalista sacadorAvalista) {
        if (isNotNull(sacadorAvalista)) {
            this.sacadorAvalista = sacadorAvalista;
        } else {
            Exceptions.throwIllegalArgumentException("SacadorAvalista não pode ser nulo!");
        }
    }

    /**
     * @return the tipoDeDocumento
     */
    public TipoDeTitulo getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    /**
     * @param tipoDeDocumento the tipoDeDocumento to set
     */
    public void setTipoDeDocumento(TipoDeTitulo tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    /**
     * @see #setValor(BigDecimal)
     *
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * <p>
     * Atribui um valor (original do Título) expresso em moeda corrente e
     * arredondanda em duas casas decimais para baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        valor = valor.setScale(2, RoundingMode.DOWN);
        this.valor = valor;
    }

    /**
     * @see #setDesconto(BigDecimal)
     *
     * @return the desconto
     */
    public BigDecimal getDesconto() {
        return desconto;
    }

    /**
     * <p>
     * Atribui um valor de descontos/abatimentos ao valor original do título
     * expresso em moeda corrente e arredondanda em duas casas decimais para
     * baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * @param desconto the desconto to set
     */
    public void setDesconto(BigDecimal desconto) {

        if (desconto != null) {
            desconto = desconto.setScale(2, RoundingMode.DOWN);
        }
        this.desconto = desconto;
    }

    /**
     * @see #setMora(BigDecimal)
     *
     * @return the mora
     */
    public BigDecimal getMora() {
        return mora;
    }

    /**
     * <p>
     * Atribui um valor de mora/multa ao título expresso em moeda corrente e
     * arredondanda em duas casas decimais para baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * @param mora
     */
    public void setMora(BigDecimal mora) {
        if (mora != null) {
            mora = mora.setScale(2, RoundingMode.DOWN);
        }
        this.mora = mora;
    }

    /**
     * @see #setDeducao(BigDecimal)
     *
     * @return the deducao
     */
    public BigDecimal getDeducao() {
        return deducao;
    }

    /**
     * <p>
     * Atribui um valor para outras deduções ao título expresso em moeda
     * corrente e arredondanda em duas casas decimais para baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * @param deducao
     */
    public void setDeducao(BigDecimal deducao) {
        if (deducao != null) {
            deducao = deducao.setScale(2, RoundingMode.DOWN);
        }
        this.deducao = deducao;
    }

    /**
     * @see #setAcrecimo(BigDecimal)
     *
     * @return the acrecimo
     */
    public BigDecimal getAcrecimo() {

        return acrecimo;
    }

    /**
     * <p>
     * Atribui um valor para outros acrécimos ao título expresso em moeda
     * corrente e arredondanda em duas casas decimais para baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * @param acrecimo
     */
    public void setAcrecimo(BigDecimal acrecimo) {
        if (acrecimo != null) {
            acrecimo = acrecimo.setScale(2, RoundingMode.DOWN);
        }
        this.acrecimo = acrecimo;
    }

    /**
     * @see #setValorCobrado(BigDecimal)
     *
     * @return the valorCobrado
     */
    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    /**
     * <p>
     * Atribui um "valor cobrardo" ao título expresso em moeda corrente e
     * arredondanda em duas casas decimais para baixo.
     * </p>
     * <p>
     * Exemplo: Para o valor 12,349 ele definirá o valor para 12,34.
     * </p>
     *
     * <p>
     * Note que o valor cobrado não é o valor de face do título e sim um valor
     * resultante de deduções ou acrécimos.
     * </p>
     *
     * @param valorCobrado
     */
    public void setValorCobrado(BigDecimal valorCobrado) {
        if (valorCobrado != null) {
            valorCobrado = valorCobrado.setScale(2, RoundingMode.DOWN);
        }
        this.valorCobrado = valorCobrado;
    }

    /**
     * <p>
     * Indica se existe parâmetros bancários no título.
     * </p>
     *
     * @return parametrosBancarios
     *
     * @see #getParametrosBancarios()
     *
     * @since 0.2
     */
    public boolean hasParametrosBancarios() {
        return ParametrosBancariosMap.hasElement(getParametrosBancarios());
    }

    /**
     * <p>
     * Retorna os parâmetros bancários usados no título.
     * </p>
     *
     * @return parametrosBancarios
     *
     * @see #setParametrosBancarios(ParametrosBancariosMap)
     *
     * @since 0.2
     */
    public ParametrosBancariosMap getParametrosBancarios() {
        return parametrosBancariosMap;
    }

    /**
     * <p>
     * Atribui Parâmetros bancários necessários ao título.
     * </p>
     *
     * <p>
     * Em alguns casos, talvez seja necessário utilizar este campo para fornecer
     * mais informações a um título. Seja para simplesmente gerar um boleto ou
     * quando se desejar implementar um campo livre ou banco ainda não
     * suportado.
     * </p>
     *
     * @see org.jrimum.domkee.banco.ParametrosBancariosMap
     *
     * @param parametrosBancariosMap
     *
     * @since 0.2
     */
    public <P extends ParametroBancario<?>> void setParametrosBancarios(ParametrosBancariosMap parametrosBancariosMap) {
        this.parametrosBancariosMap = parametrosBancariosMap;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
