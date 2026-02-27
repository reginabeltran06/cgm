package ui;

import edits.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Panel extends JPanel {

    private JLabel infoLabel;
    private ImageCanvas canvas;


    public Panel() {

        //setting all the layout/buttons/labels
        setLayout(new BorderLayout());
        setBackground(new Color(255, 230, 250));

        infoLabel= new JLabel("No image loaded");
        infoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        add(infoLabel, BorderLayout.NORTH);

        canvas = new ImageCanvas();
        add(canvas, BorderLayout.CENTER);

        JPanel buttons= new JPanel(new GridBagLayout());
        buttons.setPreferredSize(new Dimension(250,0));
        buttons.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.NONE;

        Dimension buttonSize = new Dimension(160, 45);

        JButton loadImageBtn = createStyledButton("Load image", buttonSize);
        JButton cropImageBtn = createStyledButton("Crop", buttonSize);
        JButton invertColorsBtn = createStyledButton("Invert colors", buttonSize);
        JButton rotateBtn = createStyledButton("Rotate", buttonSize);
        JButton saveImageBtn = createStyledButton("Save as new image", buttonSize);

        buttons.add(loadImageBtn, gbc);
        buttons.add(cropImageBtn, gbc);
        buttons.add(invertColorsBtn, gbc);
        buttons.add(rotateBtn, gbc);
        buttons.add(saveImageBtn, gbc);

        add(buttons, BorderLayout.WEST);
        loadImageBtn.addActionListener(e -> loadImage());
        cropImageBtn.addActionListener(e -> cropImage());
        invertColorsBtn.addActionListener(e -> invertColors());
        rotateBtn.addActionListener(e -> rotate());
        saveImageBtn.addActionListener(e -> saveImage());
    }

    private JButton createStyledButton(String text, Dimension size) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
        return btn;
    }


    private void loadImage() {
        JFileChooser chooser = new JFileChooser();

        try {
            int option = chooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                BufferedImage loadedImage= ImageIO.read(file);

                if(loadedImage != null) {
                    canvas.setImage(loadedImage);
                    updateInfo();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid image file.");
        }
    }

    private void cropImage() {
        if (canvas.getImage() == null) {
            JOptionPane.showMessageDialog(this, "No image loaded.");
            return;
        }

        GetCoordinates coords = GetCoordinates.request(
                this,
                canvas.getImage().getWidth(),
                canvas.getImage().getHeight()
        );

        if (coords == null) return;

        BufferedImage cropped = Crop.cropImage(
                canvas.getImage(),
                coords.x1,
                coords.y1,
                coords.x2,
                coords.y2
        );

        canvas.setImage(cropped);
        updateInfo();

    }

    private void invertColors(){
        if (canvas.getImage()  == null) {
            JOptionPane.showMessageDialog(this, "No image loaded.");
            return;
        }

        GetCoordinates coords = GetCoordinates.request(
                this,
                canvas.getImage().getWidth(),
                canvas.getImage().getHeight()
        );

        if (coords == null) return;

        BufferedImage invertedColors = InvertColors.ColorsInverted(
                canvas.getImage(),
                coords.x1,
                coords.y1,
                coords.x2,
                coords.y2
        );

        canvas.setImage(invertedColors);
        updateInfo();
    }

    private void rotate() {
        if (canvas.getImage() == null) {
            JOptionPane.showMessageDialog(this, "No image loaded.");
            return;
        }

        GetCoordinates coords = GetCoordinates.request(
                this,
                canvas.getImage().getWidth(),
                canvas.getImage().getHeight()
        );

        if (coords == null) return;

        BufferedImage rotatedRegion = Rotate.RotateRegion(
                canvas.getImage(),
                coords.x1,
                coords.y1,
                coords.x2,
                coords.y2
        );

        canvas.setImage(rotatedRegion);
        updateInfo();
    }

    private void saveImage() {
        if (canvas.getImage() == null) {
            JOptionPane.showMessageDialog(this, "No image loaded.");
            return;
        }

        Save.save(canvas.getImage(), this);
    }


    private void updateInfo() {

        BufferedImage img = canvas.getImage();
        if (img != null) {
            infoLabel.setText("Resolution: " + img.getWidth() + " x " + img.getHeight() + " px ");
        }
    }


}
