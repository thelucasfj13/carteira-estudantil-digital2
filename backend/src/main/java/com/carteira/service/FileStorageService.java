package com.carteira.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    public String storeFile(MultipartFile file, String subDir) throws IOException {
        Path uploadDir = Paths.get(uploadPath, subDir);
        Files.createDirectories(uploadDir);

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        
        String newFilename = UUID.randomUUID().toString() + extension;
        Path filePath = uploadDir.resolve(newFilename);
        
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return "/uploads/" + subDir + "/" + newFilename;
    }

    public void deleteFile(String fileUrl) {
        if (fileUrl != null && fileUrl.startsWith("/uploads/")) {
            try {
                Path filePath = Paths.get(uploadPath, fileUrl.substring("/uploads/".length()));
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // Log error but don't throw
            }
        }
    }
}