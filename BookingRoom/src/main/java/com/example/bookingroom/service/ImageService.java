package com.example.bookingroom.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ByteArrayResource getImage(String imageName, String dir);
    String upload(MultipartFile multipartFile, String dir) throws IOException;
    Resource download(String imageName, String dir) throws IOException;
}
