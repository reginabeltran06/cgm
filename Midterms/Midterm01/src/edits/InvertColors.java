package edits;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InvertColors {

    public static BufferedImage ColorsInverted(
            BufferedImage image,
            int x1, int y1,
            int x2, int y2
    ){

        int width  = image.getWidth();
        int height = image.getHeight();

        for (int y=y1; y<y2; y++) {
            for (int x=x1; x<x2; x++){
                Color color = new Color(image.getRGB(x,y));
                int r = 255 - color.getRed();
                int g = 255 - color.getGreen();
                int b = 255 - color.getBlue();
                Color inverted = new Color(r, g, b);
                image.setRGB(x, y, inverted.getRGB());

            }
        }
        return image;
    }


}
