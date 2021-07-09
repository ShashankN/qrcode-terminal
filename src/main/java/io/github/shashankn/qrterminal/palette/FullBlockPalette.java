package io.github.shashankn.qrterminal.palette;

/**
 * Full block palette, each ascii character will correspond to one bit from QR code bit matrix
 */
public class FullBlockPalette {

    public static final FullBlockPalette DEFAULT = new FullBlockPalette("\033[40m  \033[0m", "\033[47m  \033[0m", System.lineSeparator());

    private final String black;

    private final String white;

    private final String lineSeparator;

    /**
     * @param white         character corresponding to set bit (1) in bit matrix
     * @param black         character corresponding to unset bit (0) in bit matrix
     * @param lineSeparator new line character
     */
    public FullBlockPalette(String white, String black, String lineSeparator) {
        this.white = white;
        this.black = black;
        this.lineSeparator = lineSeparator;
    }

    /**
     * @return character corresponding to unset bit (0) in bit matrix
     */
    public String getBlack() {
        return black;
    }

    /**
     * @return character corresponding to set bit (1) in bit matrix
     */
    public String getWhite() {
        return white;
    }

    /**
     * @return new line character
     */
    public String getLineSeparator() {
        return lineSeparator;
    }
}
