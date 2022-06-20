/**
 *
 */
package org.jrimum.domkee.banco;

import java.io.Serializable;
import org.jrimum.domkee.pessoa.AbstractCPRF;
import org.jrimum.domkee.pessoa.Pessoa;

/**
 * @author misael
 *
 */
public abstract class IEntidadeDeCobranca implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8794286412913271840L;

    /**
     * Utilizado como composição
     */
    protected Pessoa pessoa;

    protected IEntidadeDeCobranca() {
        pessoa = new Pessoa();
    }

    protected IEntidadeDeCobranca(String nome) {
        pessoa = new Pessoa(nome);
    }

    protected IEntidadeDeCobranca(String nome, AbstractCPRF cadastroDePessoa) {
        pessoa = new Pessoa(nome, cadastroDePessoa);
    }

    /**
     * @return @see
     * br.com.nordestefomento.jrimum.domkee.financeiro.banco.Pessoa#getNome()
     */
    public String getNome() {
        return pessoa.getNome();
    }

    /**
     * @param nome
     * @see
     * org.jrimum.domkee.pessoa.Pessoa#setNome(java.lang.String)
     */
    public void setNome(String nome) {
        pessoa.setNome(nome);
    }

}
