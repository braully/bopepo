package org.jrimum.utilix;

import com.itextpdf.text.Rectangle;

public class RectanglePDF extends Rectangle {

    //Padrão de pagina inicial no novo itext
    private int page = 1;

    /**
     * For each of this groups the values are: [page, llx, lly, urx, ury].
     */
    public RectanglePDF(float[] positions) {
        super(positions[1], positions[2], positions[3], positions[4]);
        page = (int) positions[0];
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    public RectanglePDF(float arg0, float arg1, float arg2, float arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public RectanglePDF(float arg0, float arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public RectanglePDF(Rectangle arg0) {
        super(arg0);
    }

    public int getPage() {
        return page;
    }

    /**
     * <p>
     * SOBRE O MÉTODO
     * </p>
     *
     * @return llx - lower left x
     */
    public float getLowerLeftX() {
        return this.llx;
    }

    /**
     * <p>
     * SOBRE O MÉTODO
     * </p>
     *
     * @return lly - lower left y
     */
    public float getLowerLeftY() {
        return lly;
    }

    /**
     * <p>
     * SOBRE O MÉTODO
     * </p>
     *
     * @return urx - upper right x
     *
     */
    public float getUpperRightX() {
        return urx;
    }

    /**
     * <p>
     * SOBRE O MÉTODO
     * </p>
     *
     * @return ury - upper right y
     *
     * @since 1.0
     */
    public float getUpperRightY() {
        return ury;
    }

}
