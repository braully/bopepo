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
 * Created at: 30/03/2008 - 19:07:43
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
 * Criado em: 30/03/2008 - 19:07:43
 * 
 */
package org.jrimum.domkee.pessoa;

import org.jrimum.utilix.Objects;

/**
 * <p>
 * Entidade que encapsula dados do endereço de uma pessoa, como também
 * facilidades na manipulação dos seus atributos.
 * </p>
 *
 * @author   <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author   <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 * Mercantil</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a>
 *
 * @since 0.2
 * @version 0.2
 */
public class Endereco {

    private String enderecoFormatado;

    private String enderecoFormatadoComplemento;

    public String getEnderecoFormatado() {
        return enderecoFormatado;
    }

    public void setEnderecoFormatado(String enderecoFormatado) {
        this.enderecoFormatado = enderecoFormatado;
    }

    public String getEnderecoFormatadoComplemento() {
        return enderecoFormatadoComplemento;
    }

    public void setEnderecoFormatadoComplemento(String enderecoFormatadoComplemento) {
        this.enderecoFormatadoComplemento = enderecoFormatadoComplemento;
    }

    /**
     * Nome da rua, avenida, etc.
     */
    private String logradouro;

    /**
     * Número que identifica o lugar no logradouro (ex: número da casa).
     */
    private String numero;

    /**
     * Informação adicional para identificar o estabelecimento na rua.
     */
    private String complemento;

    /**
     * Cada uma das divisões de uma cidade ou povoação.
     */
    private String bairro;

    /**
     * Cidade, município, etc.
     */
    private String localidade;

    /**
     * @see CEP
     */
    private CEP cep;

    /**
     * @see UnidadeFederativa
     */
    private UnidadeFederativa uf;

    private String pais;

    public Endereco() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the localidade
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * @param localidade the localidade to set
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public UnidadeFederativa getUF() {
        return uf;
    }

    public void setUF(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CEP getCEP() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public void setCep(String cep) {
        setCep(new CEP(cep));
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
