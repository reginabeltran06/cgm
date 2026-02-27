package edits;

import javax.swing.*;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Save {

    public static void save(BufferedImage image, Component parent) {

        if (image == null) {
            JOptionPane.showMessageDialog(parent, "No image to save.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Image");

        int option = fileChooser.showSaveDialog(parent);

        if (option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        String name = file.getName();
        String extension = "png";

        int dot = name.lastIndexOf(".");
        if (dot > 0) {
            extension = name.substring(dot + 1).toLowerCase();
        } else {
            file = new File(file.getAbsolutePath() + ".png");
        }

        try {
            ImageIO.write(image, extension, file);
            JOptionPane.showMessageDialog(parent, "Image saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, "Error saving image.");
        }
    }
}