package com.example.bookingroom.service.Imp;

import com.example.bookingroom.exception.AppException;
import com.example.bookingroom.exception.ErrorCode;
import com.example.bookingroom.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ImageServiceImp implements ImageService {
    @Value("${path.url}")
    private String IMAGE_DIRECTORY;

    @Override
    public ByteArrayResource getImage(String imageName, String dir) {
        try {
            Path imagePath = Paths.get(IMAGE_DIRECTORY + dir, imageName);
            byte[] buffer = Files.readAllBytes(imagePath);
            return new ByteArrayResource(buffer);
        }catch (IOException e){
            throw new AppException(ErrorCode.IMAGE_NOT_EXISTED);
        }
    }

    @Override
    public String upload(MultipartFile multipartFile, String dir) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new AppException(ErrorCode.IMAGE_NOT_PROVIDED);
        }

        // Create the upload directory if it doesn't exist
        Path uploadPath = Paths.get(IMAGE_DIRECTORY + dir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the image file with its original filename
        String imageName = multipartFile.getOriginalFilename();
        Path imagePath = uploadPath.resolve(imageName);
        Files.write(imagePath, multipartFile.getBytes());

        return "/" + dir + "/" + imageName;
    }

    @Override
    public Resource download(String imageName, String dir) throws IOException {
        Path imagePath = Paths.get(IMAGE_DIRECTORY + dir, imageName);

        // Load the file as a resource
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() && Files.isReadable(imagePath)) {
            log.info(resource.toString());
            return resource;
        } else {
            throw new AppException(ErrorCode.IMAGE_NOT_EXISTED);
        }
    }

}
