import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class gradientBg {
    public static void main(String[] args) {
        BufferedImage gradientBg = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_RGB);

        Color top= new Color(255, 255, 50);     //yellow
        Color bottom = new Color(255, 30, 40);  //red
        int width = gradientBg.getWidth();
        int height = gradientBg.getHeight();
        int cx = width/2;
        int cy = height/2;
        int heightRed=520;  //where the gradient red begins
        int rGirlHead=45;
        int rBun= 25;


        //paint the top half yellow
        for (int y=0; y<heightRed; y++) {
            for (int x = 0; x < width; x++) {
                gradientBg.setRGB(x, y, top.getRGB());
            }
        }

        //start painting the bg red
        for (int y=heightRed; y<height; y++) {
            double c = (double) (y-heightRed) / (height- heightRed - 1);
            int r = (int) (top.getRed()+ c * (bottom.getRed()- top.getRed()));
            int g = (int) (top.getGreen()+c * (bottom.getGreen()- top.getGreen()));
            int b = (int) (top.getBlue()+c* (bottom.getBlue()- top.getBlue()));

            int rgb = new Color(r, g, b).getRGB();
            for (int x = 0; x < width; x++) {
                gradientBg.setRGB(x, y, rgb);
            }
        }

        int cxHead= cx-300;
        int cyHead= cy-120;

        for (int i=0; i<3; i++){ //draw the 3 girls

            //draw outline of the head
            for (double a = 0; a < 2 * Math.PI; a += 0.0001) {
                int x = (int) (cxHead + rGirlHead * Math.cos(a));
                int y = (int) (cyHead + rGirlHead * Math.sin(a));
                gradientBg.setRGB(x, y, Color.black.getRGB());

            }
            //color the circle
            for (int x = cxHead - rGirlHead; x <= cxHead + rGirlHead; x++) {
                int dx = x - cxHead;
                int h = (int) Math.sqrt(rGirlHead * rGirlHead - dx * dx);

                int yTop = cyHead- h;
                int yBottom = cyHead + h;

                for (int y = yTop; y <= yBottom; y++) {
                    gradientBg.setRGB(x, y, Color.black.getRGB());
                }
            }
            //make the bun
            Octagon.drawOctagon(
                    gradientBg,
                    cxHead+45, cyHead-35,
                    rBun,
                    Color.black.getRGB()
            );
            //draw the neck
            RectangleDrawer.drawRectangle(
                    gradientBg,
                    cxHead-10, cyHead,
                    20, 80,
                    Color.black.getRGB()
            );
            //draw her body
            Triangle.drawTriangle(
                    gradientBg,
                    cxHead-75, cyHead +80,
                    cxHead + 75, cyHead + 80,
                    cxHead,cy + 120,  // bottom
                    Color.black.getRGB()
            );
            //draw her skirt
            Triangle.drawTriangle(
                    gradientBg,
                    cxHead-60, cyHead +300,
                    cxHead +60, cyHead + 300,
                    cxHead, cy + 60,  // bottom
                    Color.black.getRGB()
            );
            //draw her legs
            RectangleDrawer.drawRectangle(
                    gradientBg,
                    cxHead-38, cyHead + 300,
                    20, 100,
                    Color.black.getRGB()
            );
            RectangleDrawer.drawRectangle(
                    gradientBg,
                    cxHead+20, cyHead + 300,
                    20, 100,
                    Color.black.getRGB()
            );
             cxHead+=300;
        }

        File outputImage = new File("gradientBackground.jpg");
        try {
            ImageIO.write(gradientBg, "jpg", outputImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
