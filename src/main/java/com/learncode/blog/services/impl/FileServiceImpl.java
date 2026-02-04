package com.learncode.blog.services.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.learncode.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // Get the original filename
        String originalFilename = file.getOriginalFilename();

        // Generate a unique filename to avoid clashes
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFilename;

        // Full path where the image will be stored
        String fullPath = path + File.separator + uniqueFileName;

        // Create directory if it doesn't exist
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Copy file to the target location
        Files.copy(file.getInputStream(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    @Override
   
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;

        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
