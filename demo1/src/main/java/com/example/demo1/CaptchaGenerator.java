package com.example.demo1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CaptchaGenerator {

    public static void captcha(String Cap, int r, int g, int b) throws IOException {
        int width = 90;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(new Color(50,40,35));
        g2d.fillRect(0, 0, width, height);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        g2d.setColor(new Color(r, g, b));
        g2d.drawString(Cap, 7, 25);
        g2d.dispose();
        ImageIO.write(bufferedImage, "png", new File("captcha.png"));
    }

    public static void code(String text) throws IOException{
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(text);
        }
    }

    public static String generateCaptchaString() {
        Random rand = new Random();
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) captcha.append(characters.charAt(rand.nextInt(characters.length())));
        return captcha.toString();
    }

    public static String CodeGenerator() {
        Random rand = new Random();
        int length = 6;
        String characters = "0123456789";
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) code.append(characters.charAt(rand.nextInt(characters.length())));
        return code.toString();
    }
}

