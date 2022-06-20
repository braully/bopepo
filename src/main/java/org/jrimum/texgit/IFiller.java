package org.jrimum.texgit;

import java.io.Serializable;

import org.jrimum.texgit.TextStream;

public interface IFiller extends Serializable{

	/**
	 * Preenche o campo com o caracter especificado e no lado especificado.
	 * 
	 * <p>
	 * Exemplo:
	 * <br>
	 * Se <code>sideToFill == SideToFill.LEFT</code>, o caracter especificado será adicionado à String
	 * no lado esquerdo até que o campo fique com o tamanho que foi definido.
	 * </p>
	 * 
	 * @param toFill
	 * @param length
	 * @return String preenchida
	 * 
	 * @since 0.2
	 */
	String fill(String toFill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(long tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(int tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(short tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(byte tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(char tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(double tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>String.valueOf(toFill)</code>.
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since
	 */
	String fill(float tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>toFill.toString()</code>.
     *
	 * <p>
	 * Caso <code>toFill</code> seja <code>null</code>, o método 
	 * <code>fill(String, int)</code> receberá uma String nula como parâmetro.
	 * </p>
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(Object tofill, int length);

	/**
	 * Executa o método <code>fill(String, int)</code> passando o parâmetro
	 * <code>toFill</code> como <code>toFill.write()</code>.
     *
	 * <p>
	 * Caso <code>toFill</code> seja <code>null</code>, o método 
	 * <code>fill(String, int)</code> receberá uma String nula como parâmetro.
	 * </p>
	 * 
	 * @param tofill
	 * @param length
	 * @return String preenchida
	 * 
	 * @see IFiller#fill(String, int)
	 * 
	 * @since 0.2
	 */
	String fill(TextStream tofill, int length);
}