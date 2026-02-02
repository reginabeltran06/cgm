import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class sunnyDay {
    public static void main(String[] args) {
        BufferedImage sunnyDay = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_RGB);

        int width = sunnyDay.getWidth();
        int height = sunnyDay.getHeight();
        double amplitude= 45;
        double frequency= .033;
        int grassHeight= 650;
        int xSunCenter= 230;
        int ySunCenter= 180;
        int r= 100;
        int rayLeng1 = 160;
        int rayLeng2= 180;
        double angleRay1= Math.PI /4;

        //make the bg white
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sunnyDay.setRGB(x, y, Color.white.getRGB());
            }
        }

        //draw the grass line
        for (int x = 0; x < width; x++) {
            int y= (int)(grassHeight + amplitude * Math.sin(frequency*x));
            sunnyDay.setRGB(x,y, Color.green.getRGB());
            //color all the grass
            for (int grass = y; grass< height; grass++){
                sunnyDay.setRGB(x, grass, Color.green.getRGB());
            }
        }

        //short angled sun rays
        for (int i=0; i< rayLeng1; i++){
            int x = (int) (xSunCenter + i * Math.cos(angleRay1));
            int y = (int) (ySunCenter + i * Math.sin(angleRay1));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter - i * Math.cos(angleRay1));
            y = (int) (ySunCenter - i * Math.sin(angleRay1));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter - i * Math.cos(-angleRay1));
            y = (int) (ySunCenter - i * Math.sin(-angleRay1));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter + i * Math.cos(-angleRay1));
            y = (int) (ySunCenter + i * Math.sin(-angleRay1));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
        }

        //straight sun rays
        for (int i=0; i< rayLeng2; i++){
            int x = (int) (xSunCenter + i * Math.cos(0));
            int y = (int) (ySunCenter + i * Math.sin(0));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter + i * Math.cos(Math.PI));
            y = (int) (ySunCenter + i * Math.sin(Math.PI));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter + i * Math.cos((Math.PI)/2));
            y = (int) (ySunCenter + i * Math.sin((Math.PI)/2));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
            x = (int) (xSunCenter + i * Math.cos(-(Math.PI)/2));
            y = (int) (ySunCenter + i * Math.sin(-(Math.PI)/2));
            sunnyDay.setRGB(x, y, Color.red.getRGB());
        }

        //sun
        for (double a = 0; a < 2 * Math.PI; a += 0.0001) {
            int x = (int) (xSunCenter + r * Math.cos(a));
            int y = (int) (ySunCenter + r * Math.sin(a));
            sunnyDay.setRGB(x, y, Color.YELLOW.getRGB());
        }
        for (int x = xSunCenter - r; x <= xSunCenter + r; x++) {
            int dx = x - xSunCenter;
            int h = (int) Math.sqrt(r * r - dx * dx);

            int yTop = ySunCenter - h;
            int yBot = ySunCenter + h;
            for (int y = yTop; y <= yBot; y++) {
                sunnyDay.setRGB(x, y, Color.YELLOW.getRGB());
            }
        }

        File outputImage = new File("sunnyDay.jpg");
        try {
            ImageIO.write(sunnyDay, "jpg", outputImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}