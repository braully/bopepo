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
 * Created at: 30/03/2008 - 18:58:13
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
 * Criado em: 30/03/2008 - 18:58:13
 * 
 */
package org.jrimum.domkee.pessoa;

import static org.jrimum.utilix.Collections.hasElement;
import static org.jrimum.utilix.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;

import org.jrimum.domkee.pessoa.NumeroDeTelefone;
import org.jrimum.domkee.pessoa.Endereco;
import org.jrimum.domkee.pessoa.AbstractCPRF;
import org.jrimum.domkee.pessoa.CPRF;
import org.jrimum.domkee.banco.ContaBancaria;
import org.jrimum.utilix.Objects;

/**
 *
 * <p>
 * Representa uma pessoa no negócio de boletos bancários. Ela pode assumir três
 * papéis diferentes:
 * <ul>
 * <li>Cedente</li>
 * <li>Sacador</li>
 * <li>Sacador Avalista</li>
 * </ul>
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="mailto:samuelvalerio@gmail.com">Samuel Valério</a>
 *
 * @since 0.2
 *
 * @version 0.2
 */
public class Pessoa implements org.jrimum.domkee.pessoa.IPessoa {

    private String nome;

    /**
     * @see CPRF
     */
    private CPRF cprf;

    /**
     * @see NumeroDeTelefone
     */
    private Collection<NumeroDeTelefone> telefones;

    /**
     * @see Endereco
     */
    private Collection<Endereco> enderecos;

    /**
     * @see ContaBancaria
     */
    private Collection<ContaBancaria> contasBancarias;

    public Pessoa() {
    }

    public Pessoa(String nome) {

        this.nome = nome;
    }

    public Pessoa(String nome, String cadastroDePessoa) {

        this.nome = nome;
        this.cprf = AbstractCPRF.create(cadastroDePessoa);
    }

    public Pessoa(String nome, CPRF cadastroDePessoa) {

        this.nome = nome;
        this.cprf = cadastroDePessoa;
    }

    /**
     * @see ContaBancaria
     */
    public void addContaBancaria(ContaBancaria contaBancaria) {

        if (isNull(contasBancarias)) {

            contasBancarias = new ArrayList<ContaBancaria>();
        }

        contasBancarias.add(contaBancaria);
    }

    /**
     * Verifica se esta pessoa tem alguma conta bancária.
     *
     * @see ContaBancaria
     */
    public boolean hasContaBancaria() {

        return hasElement(getContasBancarias());
    }

    /**
     * @see Endereco
     */
    public void addEndereco(Endereco endereco) {

        if (isNull(enderecos)) {

            enderecos = new ArrayList<Endereco>();
        }

        enderecos.add(endereco);
    }

    /**
     * @see NumeroDeTelefone
     */
    public void addTelefone(NumeroDeTelefone telefone) {

        if (isNull(telefones)) {

            telefones = new ArrayList<NumeroDeTelefone>();
        }

        telefones.add(telefone);
    }

    /**
     * @see CPRF
     */
    @Override
    public CPRF getCPRF() {
        return cprf;
    }

    /**
     * Retorna o resultado de uma chamada a {@code iterator.next()} de
     * {@linkplain #getContasBancarias()}, caso exista alguma conta, ou null,
     * caso não exista {@linkplain #contasBancarias}.
     *
     * @return Chamada a {@code iterator.next()}, caso exista algum endereço ou
     * null.
     */
    public ContaBancaria getNextContaBancaria() {

        if (hasElement(getContasBancarias())) {

            return getContasBancarias().iterator().next();
        }

        return null;
    }

    /**
     * @see ContaBancaria
     * @see Collection
     */
    public Collection<ContaBancaria> getContasBancarias() {

        return contasBancarias;
    }

    /**
     * Retorna o resultado de uma chamada a {@code iterator.next()} de
     * {@linkplain #getEnderecos()}, caso exista algum endereço, ou null, caso
     * não exista {@linkplain #enderecos}.
     *
     * @return Chamada a {@code iterator.next()}, caso exista algum endereço ou
     * null.
     */
    public Endereco getNextEndereco() {

        if (hasElement(getEnderecos())) {

            return getEnderecos().iterator().next();
        }

        return null;
    }

    /**
     * @see Endereco
     * @see Collection
     */
    public Collection<Endereco> getEnderecos() {

        return enderecos;
    }

    public String getNome() {

        return nome;
    }

    /**
     * Retorna o resultado de uma chamada a {@code iterator.next()} de
     * {@linkplain #getTelefones()}, caso exista algum telefone, ou null, caso
     * não exista {@linkplain #telefones}.
     *
     * @return Chamada a {@code iterator.next()}, caso exista algum endereço ou
     * null.
     */
    public NumeroDeTelefone getNextTelefone() {

        if (hasElement(getTelefones())) {

            return getTelefones().iterator().next();
        }

        return null;
    }

    /**
     * @see NumeroDeTelefone
     * @see Collection
     */
    public Collection<NumeroDeTelefone> getTelefones() {

        return telefones;
    }

    /**
     * @see CPRF
     */
    public void setCPRF(CPRF cprf) {
        this.cprf = cprf;
    }

    /**
     * @see ContaBancaria
     * @see Collection
     */
    public void setContasBancarias(Collection<ContaBancaria> contasBancarias) {

        this.contasBancarias = contasBancarias;
    }

    /**
     * @see Endereco
     * @see Collection
     */
    public void setEnderecos(Collection<Endereco> enderecos) {

        this.enderecos = enderecos;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    /**
     * @see NumeroDeTelefone
     * @see Collection
     */
    public void setTelefones(Collection<NumeroDeTelefone> telefones) {

        this.telefones = telefones;
    }

    /**
     * Verifica se esta pessoa é uma instância de <code>PessoaFisica</code>.
     *
     * @see org.jrimum.domkee.comum.pessoa.IPessoa#isFisica()
     */
    public boolean isFisica() {

        return (this instanceof PessoaFisica);
    }

    /**
     * Verifica se esta pessoa é uma instância de <code>PessoaJuridica</code>.
     *
     * @see org.jrimum.domkee.comum.pessoa.IPessoa#isJuridica()
     */
    public boolean isJuridica() {

        return (this instanceof PessoaJuridica);
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
