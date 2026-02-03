package Homework02.ui;

import javax.swing.*;
import java.awt.*;

public class PolarRose extends JPanel {

    private double a = 100;
    private int k = 4;
    private double y0 = 0;

    public void setParameters(double a, int k, double y0) {
        this.a = a;
        this.k = k;
        this.y0 = y0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2;

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, cy, w, cy);
        g.drawLine(cx, 0, cx, h);

        g.setColor(Color.RED);

        double prevX = cx;
        double prevY = cy;

        for (double phi = 0; phi <= 2 * Math.PI * k; phi += 0.01) {

            // r(φ) = a cos(kφ + y0)
            double r = a * Math.cos(k * phi + y0);

            double x = r * Math.cos(phi);
            double y = r * Math.sin(phi);

            double screenX = cx + x;
            double screenY = cy - y;

            g.drawLine(
                    (int) prevX, (int) prevY,
                    (int) screenX, (int) screenY
            );

            prevX = screenX;
            prevY = screenY;
        }
    }
}
