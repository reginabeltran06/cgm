import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Triangle {
    public static void drawTriangle(BufferedImage img,
                                    int x1, int y1,
                                    int x2, int y2,
                                    int x3, int y3,
                                    int rgb) {

        int[] px = {x1, x2, x3};
        int[] py = {y1, y2, y3};

        int yMin = py[0];
        int yMax = py[0];
        for (int i = 1; i < 3; i++) {
            yMin = Math.min(yMin, py[i]);
            yMax = Math.max(yMax, py[i]);
        }

        yMin = Math.max(yMin, 0);
        yMax = Math.min(yMax, img.getHeight() - 1);
        for (int y = yMin; y <= yMax; y++) {
            ArrayList<Integer> intersections = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                int j = (i + 1) % 3;

                int xa = px[i], ya = py[i];
                int xb = px[j], yb = py[j];

                if ((ya <= y && y < yb) || (yb <= y && y < ya)) {
                    double t = (double) (y - ya) / (yb - ya);
                    int x = (int) (xa + t * (xb - xa));
                    intersections.add(x);
                }
            }
            Collections.sort(intersections);

            if (intersections.size() >= 2) {
                int xStart = intersections.get(0);
                int xEnd = intersections.get(1);

                for (int x = xStart; x <= xEnd; x++) {
                    if (x >= 0 && x < img.getWidth()) {
                        img.setRGB(x, y, rgb);
                    }
                }
            }
        }
    }
}
