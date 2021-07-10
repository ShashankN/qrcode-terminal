package io.github.shashankn.qrterminal;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.github.shashankn.qrterminal.palette.FullBlockPalette;
import io.github.shashankn.qrterminal.palette.HalfBlockPalette;

/**
 * Util to generate string representation of QR code, which can be displayed on terminal
 */
public class QRCode extends AbstractQRCode {


    private QRCode(String content) {
        super(content);
    }

    /**
     * Static method to set QRCode content
     * <p/>
     * @param content content which needs to be encoded
     * @return {@link QRCode} instance
     */
    public static QRCode from(String content) {
        return new QRCode(content);
    }


    /**
     * Specify preferred width and height of QRCode
     * <p/>
     * Note: this does not guarantee that bitmatrix would be of given width and height
     * If given content won't fit in this dimension, the actual width and height would be different
     *
     * @param width
     * @param height
     * @return {@link QRCode} instance
     */
    public QRCode withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Specify error correction level. Defaults to {@link ErrorCorrectionLevel#L} (~7% correction)
     * <p/>
     * @param level required error correction level
     * @return {@link QRCode} instance
     */
    public QRCode withErrorCorrection(ErrorCorrectionLevel level) {
        this.errorCorrectionLevel = level;
        return this;
    }

    /**
     * Specify margin, defaults to 2
     * <p/>
     * @param margin
     * @return {@link QRCode} instance
     */
    public QRCode withMargin(int margin) {
        withHint(EncodeHintType.MARGIN, margin);
        return this;
    }

    /**
     * Adds the given hintType and value to hints
     * <p/>
     * @param hintType hint type
     * @param value    hint type value
     * @return {@link QRCode} instance
     */
    public QRCode withHint(EncodeHintType hintType, Object value) {
        this.hints.put(EncodeHintType.MARGIN, value);
        return this;
    }

    /**
     * Returns string representation of QR codes using {@link FullBlockPalette#DEFAULT}
     * <p/>
     * @return string representation of QR code using full blocks
     * @throws WriterException
     */
    public String generate() throws WriterException {
        return generateWithPalette(FullBlockPalette.DEFAULT);
    }

    /**
     * Return string representation of QR code using half blocks, i.e (i,j) and (i+1,j) bits would be combined to single ascii character
     * Hence generated QR code would be compact compared to the one generated using {@link QRCode#generate()}
     * <p/>
     * @return string representation of QR code using half blocks
     * @throws WriterException
     */
    public String generateHalfBlock() throws WriterException {
        return generateHalfBlockWithPalette(HalfBlockPalette.DEFAULT);
    }

    /**
     * Generates full block QR code using specified palette
     * <p/>
     * @param palette palette
     * @return string representation of QR code using full blocks
     * @throws WriterException
     */
    public String generateWithPalette(FullBlockPalette palette) throws WriterException {
        BitMatrix qr = this.generateBitMatrix();
        int height = qr.getHeight(), width = qr.getWidth();
        StringBuilder builder = new StringBuilder(height * width);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                builder.append((qr.get(i, j) ? palette.getWhite() : palette.getBlack()));
            }
            builder.append(palette.getLineSeparator());
        }
        return builder.toString();
    }

    /**
     * Generates half block QR code using specified palette.
     * See {@link QRCode#generateHalfBlock()} for more details
     * <p/>
     * @param palette palette
     * @return string representation of QR code using full blocks
     * @throws WriterException
     */
    public String generateHalfBlockWithPalette(HalfBlockPalette palette) throws WriterException {
        BitMatrix matrix = generateBitMatrix();
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        StringBuilder builder = new StringBuilder((height / 2) * width);
        for (int i = 0; i < height + 1; i = i + 2) {
            for (int j = 0; j < width; j++) {
                boolean top = matrix.get(i, j);
                // if i is index of last odd row, set bottom as false, else set the actual value
                boolean bottom = (i + 1 < height) && matrix.get(i + 1, j);
                if (top) {
                    // top && bottom - whiteWhite block
                    // top && !bottom - whiteBlack block
                    builder.append(bottom ? palette.getWhiteWhite() : palette.getWhiteBlack());
                } else {
                    // !top && bottom - blackWhite block
                    // !top and !bottom - blackBlack block
                    builder.append(bottom ? palette.getBlackWhite() : palette.getBlackBlack());
                }
            }
            builder.append(palette.getLineSeparator());
        }
        return builder.toString();
    }

}
