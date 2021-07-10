package io.github.shashankn.qrterminal;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains default values for available configs
 */
public abstract class AbstractQRCode {

    protected final Map<EncodeHintType, Object> hints = new HashMap<>();

    /**
     *  width, defaults to 25
     */
    protected int width = 25;

    /**
     *  height, defaults to 25
     */
    protected int height = 25;

    /**
     *  margin, defaults to 2
     */
    protected int margin = 2;

    /**
     *  errorCorrectionLevel, defaults to {@link ErrorCorrectionLevel#L}
     */
    protected ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;

    /**
     *  content which needs to be encoded to QR code
     */
    protected String content;

    protected AbstractQRCode(String text) {
        this.content = text;
        //set default margin
        this.hints.put(EncodeHintType.MARGIN, margin);
    }

    protected BitMatrix generateBitMatrix() throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(this.content, BarcodeFormat.QR_CODE, this.width, this.height, hints);
    }
}
