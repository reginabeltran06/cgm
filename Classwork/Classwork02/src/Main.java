import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedImage gradientTriangle = new BufferedImage(1200, 1200, BufferedImage.TYPE_INT_RGB);

        // bottom left (red)
        int xA= 0;
        int yA= gradientTriangle.getHeight();

        // bottom right (green)
        int xB= gradientTriangle.getWidth();
        int yB= gradientTriangle.getHeight();

        //top (blue)
        int xC= gradientTriangle.getWidth() /2;
        int yC= 0;

        Color A= new Color(255, 0, 0);
        Color B= new Color(0, 255, 0);
        Color C = new Color(0, 0, 255);

        double divisor = ((yB - yC)*(xA-xC) + (xC-xB)*(yA-yC));

        for (int y=0; y<gradientTriangle.getHeight(); y++) {
            for (int x=0; x< gradientTriangle.getWidth(); x++){
                //barycentric coordinates
                double a1= ((yB-yC)*(x-xC) + (xC -xB)*(y-yC)) /  divisor;
                double a2 = ((yC-yA)*(x-xC) + (xA - xC)*(y-yC)) / divisor;
                double a3 = 1- a1-a2;

                if ( a1>=0 && a2>=0 && a3>=0) {     //checking if the pixel is inside the triangle
                    int r = (int) ((a1 * A.getRed()) + (a2 * B.getRed()) + (a3 * C.getRed()));
                    int g = (int) ((a1 * A.getGreen()) + (a2 * B.getGreen()) + (a3 * C.getGreen()));
                    int b = (int) ((a1 * A.getBlue()) + (a2 * B.getBlue()) + (a3 * C.getBlue()));

                    int rgb = new Color(r, g, b).getRGB();
                    gradientTriangle.setRGB(x, y, rgb);
                }
            }
        }


        File outputImage = new File("gradientTriangle.jpg");
        try {
            ImageIO.write(gradientTriangle, "jpg", outputImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
