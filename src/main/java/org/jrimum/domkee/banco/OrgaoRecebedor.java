/**
 *
 */
package org.jrimum.domkee.banco;

import java.awt.Image;
import org.jrimum.domkee.pessoa.CNPJ;

/**
 * <p>
 * É a representação da entidade Órgão/Empresa que irá receber o pagamento
 * efetuado pelo Contribuinte.
 * </p>
 *
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 *
 * @since 0.3
 *
 * @version 0.3
 */
public class OrgaoRecebedor extends IEntidadeDeCobranca {

    /**
     *
     */
    private static final long serialVersionUID = 8761667661640466629L;

    private TipoSeguimento tipoSeguimento;
    private CodigoDeIdentificacaoFebraban codigoDeIdentificacaoFebraban;
    private Image imgLogo;

    /**
     * @param nome
     * @param tipoSeguimento
     */
    public OrgaoRecebedor(String nome, TipoSeguimento tipoSeguimento) {
        super(nome);
        setTipoSeguimento(tipoSeguimento);
    }

    /**
     * @param nome
     * @param tipoSeguimento
     * @param codigoDeIdentificacaoFebraban
     * @param imgLogo
     */
    public OrgaoRecebedor(String nome, TipoSeguimento tipoSeguimento, CNPJ cnpj, CodigoDeIdentificacaoFebraban codigoDeIdentificacaoFebraban, Image imgLogo) {
        super(nome, cnpj);
        setTipoSeguimento(tipoSeguimento);
        setCodigoDeIdentificacaoFebraban(codigoDeIdentificacaoFebraban);
        setImgLogo(imgLogo);
    }

    /**
     * @param nome
     * @param tipoSeguimento
     * @param codigoDeIdentificacaoFebraban
     * @param imgLogo
     */
    public OrgaoRecebedor(String nome, TipoSeguimento tipoSeguimento, String cnpj, CodigoDeIdentificacaoFebraban codigoDeIdentificacaoFebraban, Image imgLogo) {
        super(nome, new CNPJ(cnpj));
        setTipoSeguimento(tipoSeguimento);
        setCodigoDeIdentificacaoFebraban(codigoDeIdentificacaoFebraban);
        setImgLogo(imgLogo);
    }

    /**
     * @return the tipoSeguimento
     */
    public TipoSeguimento getTipoSeguimento() {
        return tipoSeguimento;
    }

    /**
     * @param tipoSeguimento the tipoSeguimento to set
     */
    public void setTipoSeguimento(TipoSeguimento tipoSeguimento) {
        this.tipoSeguimento = tipoSeguimento;
    }

    /**
     * @return CNPJ
     * @see #getCNPJ()
     */
    public CNPJ getCNPJ() {
        return (CNPJ) pessoa.getCPRF();
    }

    /**
     * @param abstractCNPJ
     */
    public void setCNPJ(CNPJ cnpj) {
        pessoa.setCPRF(cnpj);
    }

    /**
     * @param abstractCNPJ
     * @see # setCNPJ(CNPJ cnpj)
     */
    public void setCNPJ(String cnpj) {
        setCNPJ(new CNPJ(cnpj));
    }

    /**
     * @return the imgLogo
     */
    public Image getImgLogo() {
        return imgLogo;
    }

    /**
     * @param imgLogo the imgLogo to set
     */
    public void setImgLogo(Image imgLogo) {
        this.imgLogo = imgLogo;
    }

    public CodigoDeIdentificacaoFebraban getCodigoDeIdentificacaoFebraban() {
        return codigoDeIdentificacaoFebraban;
    }

    public void setCodigoDeIdentificacaoFebraban(
            CodigoDeIdentificacaoFebraban codigoDeIdentificacaoFebraban) {
        this.codigoDeIdentificacaoFebraban = codigoDeIdentificacaoFebraban;
    }

}
