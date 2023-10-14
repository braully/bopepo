/**
 * 
 */
package org.jrimum.domkee.banco;

/**
 * @author misael
 *
 */
public enum TipoProduto {

	ARRECADACAO(8, "Arredação");
		
	private Integer codigo;
	private String nome;
	
	/**
	 * @param codigo
	 * @param nome
	 */
	private TipoProduto(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}	
	
	public static TipoProduto findByCodigo(Integer codigo) {
		TipoProduto tipoProdutoRetorno = null;
		
		TipoProduto[] tipoProdutoArray = TipoProduto.values();
		for (int i = 0; i < tipoProdutoArray.length; i++) {
			if (tipoProdutoArray[i].codigo.equals(codigo)) {
				tipoProdutoRetorno = tipoProdutoArray[i];
				break;
			}
				
		}
		
		return tipoProdutoRetorno;
	}	
	
}
