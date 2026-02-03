package Homework02_2.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Panel extends JPanel {

    private JTextField widthField;
    private JTextField heightField;
    private JLabel resultLabel;

    public Panel() {

        //setting all the layout/buttons/labels
        setLayout(null);
        setBackground(Color.pink);

        add(new JLabel("Width:")).setBounds(50, 30, 80, 25);
        add(new JLabel("Height:")).setBounds(50, 65, 80, 25);

        widthField = new JTextField();
        heightField = new JTextField();

        widthField.setBounds(120, 30, 120, 25);
        heightField.setBounds(120, 65, 120, 25);

        add(widthField);
        add(heightField);

        JButton calcBtn = new JButton("Calculate");
        calcBtn.setBounds(80, 110, 150, 30);
        add(calcBtn);

        JButton fileBtn = new JButton("Load Image");
        fileBtn.setBounds(80, 150, 150, 30);
        add(fileBtn);

        resultLabel = new JLabel("Aspect Ratio:");
        resultLabel.setBounds(50, 200, 300, 30);
        add(resultLabel);

        calcBtn.addActionListener(e -> calculateAspectRatio());
        fileBtn.addActionListener(e -> loadImage());
    }

    private void calculateAspectRatio() {
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());

            showAspectRatio(width, height);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers.");
        }
    }

    private void loadImage() {
        try {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                BufferedImage img = ImageIO.read(file);

                int width = img.getWidth();
                int height = img.getHeight();

                widthField.setText(String.valueOf(width));
                heightField.setText(String.valueOf(height));

                showAspectRatio(width, height);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid image file.");
        }
    }

    private void showAspectRatio(int w, int h) {
        //divides the width and height by the gcd to obtain the smallest aspect radio
        int gcd = gcd(w, h);
        int rw = w / gcd;
        int rh = h / gcd;

        resultLabel.setText(
                "Aspect Ratio: " + rw + ":" + rh +
                        " (" + w + "×" + h + ")"
        );
    }


    //finds the greatest common denominator
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
