package edits;

import java.awt.image.BufferedImage;

public class Crop {

    public static BufferedImage cropImage(
            BufferedImage image,
            int x1, int y1,
            int x2, int y2
    ) {

        int width = x2 - x1;
        int height = y2 - y1;

        if (width <= 0 || height <= 0) return null;
        if (x1 < 0 || y1 < 0) return null;
        if (x2 > image.getWidth() || y2 > image.getHeight()) return null;

        return image.getSubimage(x1, y1, width, height);

    }
}