package io.github.shashankn.qrterminal.palette;

/**
 * Full block palette, each character will correspond to two bit from QR code,
 * one bit for ith row and jth column and another from (i+1)th row and jth column
 */
public class HalfBlockPalette {

    public static final HalfBlockPalette DEFAULT = new HalfBlockPalette(" ",
            new String(new char[]{0x2584}),
            new String(new char[]{0x2580}),
            new String(new char[]{0x2588}),
            System.lineSeparator());

    private final String blackWhite;

    private final String blackBlack;

    private final String whiteBlack;

    private final String whiteWhite;

    private final String lineSeparator;

    /**
     * @param blackBlack    character corresponding to (i,j)th bit set to 0 and (i+1,j)th bit set to 0
     * @param blackWhite    character corresponding to (i,j)th bit set to 0 and (i+1,j)th bit set to 1
     * @param whiteBlack    character corresponding to (i,j)th bit set to 1 and (i+1,j)th bit set to 0
     * @param whiteWhite    character corresponding to (i,j)th bit set to 1 and (i+1,j)th bit set to 1
     * @param lineSeparator character corresponding to new line
     */
    public HalfBlockPalette(String blackBlack, String blackWhite, String whiteBlack, String whiteWhite, String lineSeparator) {
        this.blackBlack = blackBlack;
        this.blackWhite = blackWhite;
        this.whiteBlack = whiteBlack;
        this.whiteWhite = whiteWhite;
        this.lineSeparator = lineSeparator;
    }

    /**
     * @return character corresponding to (i,j)th bit set to 0 and (i+1,j)th bit set to 1
     */
    public String getBlackWhite() {
        return blackWhite;
    }

    /**
     * @return character corresponding to (i,j)th bit set to 0 and (i+1,j)th bit set to 0
     */
    public String getBlackBlack() {
        return blackBlack;
    }

    /**
     * @return character corresponding to (i,j)th bit set to 1 and (i+1,j)th bit set to 0
     */
    public String getWhiteBlack() {
        return whiteBlack;
    }

    /**
     * @return character corresponding to (i,j)th bit set to 1 and (i+1,j)th bit set to 1
     */
    public String getWhiteWhite() {
        return whiteWhite;
    }

    /**
     * @return character corresponding to new line
     */
    public String getLineSeparator() {
        return lineSeparator;
    }


}
