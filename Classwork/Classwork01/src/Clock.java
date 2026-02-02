import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Clock {
    public static void main(String[] args) {
        BufferedImage clock = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);

        int width = clock.getWidth();
        int height = clock.getHeight();

        //get the center of the image
        int cx = width/2;
        int cy = height/2;

        int r=100;                  //radius of the clock
        int rHours= 80;             //hours markers
        int hoursLength= 50;        //hours hand length
        int minLength= 70;          //minutes hand length
        double angleHour = (2 * Math.PI / 12);      //how much hours hand need to move p/hour

        //set the time of the clock
        int hour= 10;
        int mins= 5;

        // -pi/2 so the angle starts at top and not at the right
        double angleH= (angleHour * hour) - (Math.PI /2 );
        double angleMins= ((2*Math.PI/60) * mins) - (Math.PI / 2);

        //draw the circle
        for (double a = 0; a < 2 * Math.PI; a += 0.0001) {
            int x = (int) (cx + r * Math.cos(a));
            int y = (int) (cy + r * Math.sin(a));
            clock.setRGB(x, y, Color.WHITE.getRGB());
        }

        //draw the markers
        for (double a = 0; a < 2 * Math.PI; a += angleHour) {
            int x = (int) (cx + rHours * Math.cos(a));
            int y = (int) (cy + rHours * Math.sin(a));
            clock.setRGB(x, y, Color.WHITE.getRGB());
        }

        //draw hours hand
        for (int i = 0; i < hoursLength; i++) {
            int x = (int) (cx + i * Math.cos(angleH));
            int y = (int) (cy + i * Math.sin(angleH));
            clock.setRGB(x, y, Color.WHITE.getRGB());
        }

        //draw minutes hand
        for (int i = 0; i < minLength; i++) {
            int x = (int) (cx + i * Math.cos(angleMins));
            int y = (int) (cy + i * Math.sin(angleMins));
            clock.setRGB(x, y, Color.WHITE.getRGB());
        }

            File outputImage = new File("clock.jpg");
        try {
            ImageIO.write(clock, "jpg", outputImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
