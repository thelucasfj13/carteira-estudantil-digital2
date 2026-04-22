package com.carteira.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class QRCodeService {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${app.base-url}")
    private String baseUrl;

    public String generateQRCode(String codigoCarteira) throws WriterException, IOException {
        String validationUrl = baseUrl + "/validar/" + codigoCarteira;
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(validationUrl, BarcodeFormat.QR_CODE, 300, 300);
        
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        
        Path qrCodeDir = Paths.get(uploadPath, "qrcodes");
        Files.createDirectories(qrCodeDir);
        
        String fileName = "qr_" + codigoCarteira + ".png";
        Path filePath = qrCodeDir.resolve(fileName);
        
        ImageIO.write(image, "PNG", filePath.toFile());
        
        return "/uploads/qrcodes/" + fileName;
    }

    public String generateQRCodeBase64(String codigoCarteira) throws WriterException, IOException {
        String validationUrl = baseUrl + "/validar/" + codigoCarteira;
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(validationUrl, BarcodeFormat.QR_CODE, 300, 300);
        
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}