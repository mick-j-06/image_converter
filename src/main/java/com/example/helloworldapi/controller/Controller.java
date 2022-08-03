package com.example.helloworldapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.helloworldapi.Method.ConvertImage.converter;

@RestController
public class Controller {
    @GetMapping("/")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/{name}")
    public String helloName(@PathVariable String name) {
        return "hello " + name;
    }

    @PostMapping("/image")
    public byte[] convert(@RequestParam("image") MultipartFile image) throws Exception {
        System.out.println(image.getOriginalFilename());
        byte[] img = image.getBytes();
        return converter(img);
    }
}
