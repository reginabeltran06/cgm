package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCanvas extends JPanel {

    private BufferedImage image;
    private static final int maxWidth = 800;
    private static final int maxHeight = 800;

    public ImageCanvas() {
        setPreferredSize(new Dimension(maxWidth, maxHeight));
        setOpaque(false);
    }
    public void setImage(BufferedImage img) {
        this.image = img;
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) return;

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        double widthRatio = (double) maxWidth / imgWidth;
        double heightRatio = (double) maxHeight / imgHeight;
        double scale = Math.min(widthRatio, heightRatio);

//        if (scale > 1.0) {
//            scale = 1.0;
//        }

        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);

        int x = (getWidth() - newWidth) / 2;
        int y = (getHeight() - newHeight) / 2;

        g.drawImage(image, x, y, newWidth, newHeight, this);
    }
}