package com.example.helloworldapi.Method;

import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;

public class ConvertImage {
    public static byte[] converter(byte[] image) throws Exception {
        InputStream byteArrayInputStream = new ByteArrayInputStream(image);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        InputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(image));
        String content = URLConnection.guessContentTypeFromStream(bufferedInputStream);
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                int transform = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int rgb = 0xff000000 | (transform << 16) | (transform << 8) | transform;
                bufferedImage.setRGB(j, i, rgb);
            }
        }
        byteArrayInputStream.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (content.equals(MediaType.IMAGE_PNG_VALUE)) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } else if (content.equals(MediaType.IMAGE_JPEG_VALUE)) {
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        } else {
            throw new Exception();
        }
        byteArrayOutputStream.flush();
        byte[] imageResult = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return imageResult;
    }
}
