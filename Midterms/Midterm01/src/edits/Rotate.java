package edits;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotate {

    public static Integer requestAngle(Component parent) {

        String[] options = {"90", "180", "270"};

        String input = (String) JOptionPane.showInputDialog(
                parent,
                "Select rotation angle:",
                "Rotate",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (input == null) return null; // user cancelled

        return Integer.parseInt(input);
    }

    public static BufferedImage RotateRegion(
            BufferedImage image,
            int x1, int y1,
            int x2, int y2
    ) {
        Integer angle = requestAngle(null);
        if (angle == null) return image;

        int width = x2 - x1;
        int height = y2 - y1;

        double centerX = x1 + width / 2.0;
        double centerY = y1 + height / 2.0;

        BufferedImage rotated;

        if (angle == 180) {
            rotated = new BufferedImage(width, height, image.getType());
        } else {
            rotated = new BufferedImage(height, width, image.getType());
        }

        rotatePixels(image, rotated, x1, y1, width, height, angle);

        int newX1 = (int) Math.round(centerX - rotated.getWidth() / 2.0);
        int newY1 = (int) Math.round(centerY - rotated.getHeight() / 2.0);

        for (int y = y1; y < y2; y++){
            for (int x = x1; x < x2; x++){
                image.setRGB(x, y, 0x000000);
            }
        }

        for (int y = 0; y < rotated.getHeight(); y++) {
            for (int x = 0; x < rotated.getWidth(); x++) {

                int rgb = rotated.getRGB(x, y);

                int targetX = newX1 + x;
                int targetY = newY1 + y;

                if (targetX >= 0 && targetX < image.getWidth() &&
                        targetY >= 0 && targetY < image.getHeight()) {

                    image.setRGB(targetX, targetY, rgb);
                }
            }
        }
        return image;
    }

    private static void rotatePixels(
            BufferedImage original,
            BufferedImage rotated,
            int startX,
            int startY,
            int width,
            int height,
            int angle
    ) {



        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int rgb = original.getRGB(startX + x, startY + y);

                switch (angle) {

                    case 90:
                        rotated.setRGB(height - 1 - y, x, rgb);
                        break;

                    case 180:
                        rotated.setRGB(width - 1 - x, height - 1 - y, rgb);
                        break;

                    case 270:
                        rotated.setRGB(y, width - 1 - x, rgb);
                        break;
                }
            }
        }
    }
}
