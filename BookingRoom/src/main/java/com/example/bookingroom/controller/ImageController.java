package com.example.bookingroom.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private static final String IMAGE_DIRECTORY =
            "C:\\Users\\Boot10\\IdeaProjects\\BookingRoom\\src\\main\\resources\\static\\hotels";

    @RequestMapping(value = "/image/hotels/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String imageName) {

        if(!imageName.equals("") || imageName != null){
            try{
                Path imagePath = Paths.get(IMAGE_DIRECTORY, imageName);
                byte[] buffer = Files.readAllBytes(imagePath);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }
    private static final String IMAGE_DISCOUNT_DIRECTORY =
            "C:\\Users\\Boot10\\IdeaProjects\\BookingRoom\\src\\main\\resources\\static\\discounts";

    @RequestMapping(value = "/image/discounts/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageDiscount(@PathVariable String imageName) {

        if(!imageName.equals("") || imageName != null){
            try{
                Path imagePath = Paths.get(IMAGE_DISCOUNT_DIRECTORY, imageName);
                byte[] buffer = Files.readAllBytes(imagePath);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }

    private static final String IMAGE_ROOM_DIRECTORY =
            "C:\\Users\\Boot10\\IdeaProjects\\BookingRoom\\src\\main\\resources\\static\\rooms";

    @RequestMapping(value = "/image/rooms/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageRoom(@PathVariable String imageName) {

        if(!imageName.equals("") || imageName != null){
            try{
                Path imagePath = Paths.get(IMAGE_ROOM_DIRECTORY, imageName);
                byte[] buffer = Files.readAllBytes(imagePath);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }
}
