/**
 * 
 */
package org.jrimum.domkee.banco;

import org.jrimum.domkee.pessoa.CPF;

/**
 * @author misael
 *
 */
public class Contribuinte extends IEntidadeDeCobranca {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3267851061149256619L;

	
	/**
	 * 
	 */
	public Contribuinte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param nome
	 * @param cpf
	 */
	public Contribuinte(String nome, CPF cpf) {
		super(nome, cpf);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nome
	 * @param cpf
	 */	
	public Contribuinte(String nome, String cpf) {
		super();
		
		setNome(nome);
		
		CPF cPF = new CPF(cpf);
		setCPF(cPF);
	}

	/**
	 * @param nome
	 */
	public Contribuinte(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @return CPF
	 * @see #getCPF()
	 */
	public CPF getCPF() {
		return (CPF)pessoa.getCPRF();
	}

	/**
	 */
	public void setCPF(CPF cpf) {
		pessoa.setCPRF(cpf);
	}
	
}
