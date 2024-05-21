import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptchaGenerator {

    public static void main(String[] args) throws IOException {
        int width = 90;
        int height = 40;

        // Create a buffered image in which to draw
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // Draw an oval
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, width, height);

        // Font settings
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);

        String captcha = generateCaptchaString();
        g2d.setColor(Color.YELLOW);
        g2d.drawString(captcha, 10, 25);

        // Graphics context no longer needed so dispose it
        g2d.dispose();

        // Write the image as a PNG
        ImageIO.write(bufferedImage, "png", new File("captcha.png"));
    }

    private static String generateCaptchaString() {
        Random rand = new Random();
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return captcha.toString();
    }
}
