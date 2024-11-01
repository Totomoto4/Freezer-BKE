package com.example.freezer.service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class QRservice {

    private static final String BASE_URL = "https://freezerappdeploy.vercel.app/informacion/";

    public String generarCodigoQR(Long productoId) throws Exception {
        String url = BASE_URL + productoId;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        byte[] qrBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(qrBytes);
    }

    public static void main(String[] args) throws Exception {
        QRservice qRservice = new QRservice();

        String codigoPrueba = qRservice.generarCodigoQR(1L);
        System.out.println(codigoPrueba);
    }
    
}
