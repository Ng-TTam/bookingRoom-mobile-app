package com.example.bookingroom.controller;

import com.example.bookingroom.dto.response.ApiResponse;
import com.example.bookingroom.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${path.url}")
    private String IMAGE_DIRECTORY;
    @Autowired
    ImageService imageService;


    @GetMapping("/{dir}/{imageName}")
    @ResponseBody
    public ApiResponse<ResponseEntity<ByteArrayResource>> getImage(@PathVariable String dir, @PathVariable String imageName) {

        if (imageName != null && !imageName.trim().isEmpty()) {
            return ApiResponse.<ResponseEntity<ByteArrayResource>>builder()
                    .result(ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType("image/png"))
                            .body(imageService.getImage(imageName, dir)))
                    .build();
        } else
            return ApiResponse.<ResponseEntity<ByteArrayResource>>builder()
                    .result(ResponseEntity.status(500).build())
                    .build();
    }

    @GetMapping("/download/{dir}/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String dir, @PathVariable String imageName) {
        try {
            // Get the resource (image file) from the service
            Resource resource = imageService.download(imageName, dir);

            // Set the content type and content disposition for download
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Or use a method to determine type based on the file extension
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
