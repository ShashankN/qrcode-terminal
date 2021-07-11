# QRCode terminal
Print QR Codes straight in your terminal

## Usage

### Generate full size ASCII block QR Code

```
QRCode.from("https://www.google.com").generate();
```
<img src="https://user-images.githubusercontent.com/6450630/125182867-5dcd2d80-e22f-11eb-854b-ea802bcf0be3.png" alt="half block qrcode" width="400" height="400">

### Generate smaller 'half blocks' in the terminal

```
QRCode.from("https://www.google.com").generateHalfBlock();
```

<img src="https://user-images.githubusercontent.com/6450630/125182829-ec8d7a80-e22e-11eb-9fe7-4d7ce882145d.png " alt="half block qrcode" width="200" height="200">

### Specify other params

```
QRCode.from("https://www.google.com")
        .withSize(30,30)
        .withMargin(3)
        .withErrorCorrection(ErrorCorrectionLevel.H)
        .generate();
```

### Specify your own palette/characters

```
FullBlockPalette customPalette = new FullBlockPalette("\033[40m  \033[0m", "\033[47m  \033[0m", System.lineSeparator());
QRCode.from("https://www.google.com")
        .withSize(30,30)
        .withMargin(3)
        .withErrorCorrection(ErrorCorrectionLevel.H)
        .generateWithPalette(customPalette);
      
HalfBlockPalette customPalette =  new HalfBlockPalette(" ", "▄", "▀", "█",System.lineSeparator());
QRCode.from("https://www.google.com")
        .withMargin(3)
        .withErrorCorrection(ErrorCorrectionLevel.H)
        .withSize(30,30)
        .generateHalfBlockWithPalette(customPalette);
```
