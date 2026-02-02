import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Octagon {

    public static void drawOctagon(BufferedImage img,
                                   int cx, int cy,
                                   int r,
                                   int rgb) {

        int sides = 8;
        int[] px = new int[sides];
        int[] py = new int[sides];
        //find vertices
        for (int i = 0; i < sides; i++) {
            double angle = i * 2 * Math.PI / sides;
            px[i] = (int) (cx + r * Math.cos(angle));
            py[i] = (int) (cy + r * Math.sin(angle));
        }

        int yMin = py[0], yMax = py[0];
        for (int i = 1; i < sides; i++) {
            yMin = Math.min(yMin, py[i]);
            yMax = Math.max(yMax, py[i]);
        }

        for (int y = yMin; y <= yMax; y++) {
            ArrayList<Integer> intersections = new ArrayList<>();

            for (int i = 0; i < sides; i++) {
                int j = (i + 1) % sides;

                int x1 = px[i], y1 = py[i];
                int x2 = px[j], y2 = py[j];

                if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {
                    double t = (double) (y - y1) / (y2 - y1);
                    int x = (int) (x1 + t * (x2 - x1));
                    intersections.add(x);
                }
            }

            Collections.sort(intersections);

            //fill the shape
            for (int i = 0; i < intersections.size(); i += 2) {
                int xStart = intersections.get(i);
                int xEnd = intersections.get(i + 1);

                for (int x = xStart; x <= xEnd; x++) {
                    if (x >= 0 && x < img.getWidth()
                            && y >= 0 && y < img.getHeight()) {
                        img.setRGB(x, y, rgb);
                    }
                }
            }
        }
    }
    }
