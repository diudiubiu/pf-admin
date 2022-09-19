package com.example.ecr.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.ParseException;

public class PdfQrcodeHelp {
    public static void main(String[] args) {
        QrConfig config = new QrConfig();
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        QrCodeUtil.generate("ESTID:MRNOI2513599000#TRRN:4372208008106#TOTALAMOUNT:7,41,938", config, FileUtil.file("/Users/chenchencui/Downloads/upload/qrcode/qrcode.jpg"));


        RandomGenerator randomGenerator = new RandomGenerator(8);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(190, 45,4,4);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();

        lineCaptcha.write("/Users/chenchencui/Downloads/upload/circle/circle.png");

    }


}
