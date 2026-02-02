import java.awt.image.BufferedImage;

public class RectangleDrawer {
    public static void drawRectangle(BufferedImage img,
                                     int x, int y,
                                     int width, int height,
                                     int rgb) {
        int xEnd = x + width;
        int yEnd = y + height;

        for (int j = y; j < yEnd; j++) {
            if (j < 0 || j >= img.getHeight()) continue;

            for (int i = x; i < xEnd; i++) {
                if (i < 0 || i >= img.getWidth()) continue;

                img.setRGB(i, j, rgb);
            }

        }
    }
}