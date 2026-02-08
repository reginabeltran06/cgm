import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;


public class Main {

    //count back to back pixels that are the same
    static class Counter{
        int pixel;
        int count;

        Counter(int pixel, int count){
            this.pixel = pixel;
            this.count = count;
        }
    }

    public static void main(String[] args) {

        try {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                BufferedImage img = ImageIO.read(file);

                int width = img.getWidth();
                int height = img.getHeight();

                int[] pixels = new int[width * height];
                img.getRGB(0, 0, width, height, pixels, 0, width);

                ArrayList<Counter> compressed = new ArrayList<>();
                int currentPixel = pixels[0];
                int count = 1;

                for (int i = 1; i < pixels.length; i++) {
                    if (pixels[i] == currentPixel) {        //if the pixel is the same as the last one
                        count++;                            //add one to the counter
                    } else {                                                    //if the pixel is different than the last
                        compressed.add(new Counter(currentPixel, count));       //start a mew counter
                        currentPixel = pixels[i];
                        count = 1;
                    }
                }
                compressed.add(new Counter(currentPixel, count));

                int originalSize = pixels.length * 4;           //bytes per pixel
                int compressedSize = compressed.size() * 8;

                //if the compressed image would not result in a smaller file, keep the original
                if (compressedSize >= originalSize) {
                    JOptionPane.showMessageDialog(null,
                            "Compression not efficient. Original image kept.");
                } else {
                    saveCompressed(new File("compressed.rle"), width, height, compressed);
                    JOptionPane.showMessageDialog(null, "Image compressed and saved successfully!");
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid image file.");
        }


        try {
            BufferedImage restored = decompress(new File("compressed.rle"));
            ImageIO.write(restored, "png", new File("restored.png"));

            JOptionPane.showMessageDialog(null, "Image decompressed successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Decompression failed.");
        }
    }



    static void saveCompressed(
            File outputFile,
            int width,
            int height,
            ArrayList<Counter> compressed
    ) throws IOException {

        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile));

        out.writeInt(width);
        out.writeInt(height);
        out.writeInt(compressed.size());

        for (Counter c : compressed) {
            out.writeInt(c.pixel);
            out.writeInt(c.count);
        }

        out.close();
    }


    //decompressing
    static BufferedImage decompress(File inputFile) throws IOException {

        DataInputStream in = new DataInputStream(new FileInputStream(inputFile));

        int width = in.readInt();
        int height = in.readInt();
        int runs = in.readInt();

        int[] pixels = new int[width * height];
        int index = 0;

        for (int i = 0; i < runs; i++) {
            int pixel = in.readInt();
            int count = in.readInt();

            for (int j = 0; j < count; j++) {
                pixels[index++] = pixel;
            }
        }

        in.close();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        img.setRGB(0, 0, width, height, pixels, 0, width);

        return img;
    }


}
