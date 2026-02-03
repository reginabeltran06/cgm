package Homework02.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Homework02.converters.Converter;

public class Panel extends JPanel {

    // --- Converter UI ---
    private JComboBox<Converter> convertersBox;
    private JPanel inputsPanel;
    private JTextField[] fields;
    private JLabel result;

    private PolarRose rosePanel;
    private JTextField aField, kField, y0Field;

    public Panel(List<Converter> converters) {

        setLayout(null);
        setBackground(Color.pink);

        convertersBox = new JComboBox<>(converters.toArray(new Converter[0]));
        convertersBox.setBounds(40, 20, 260, 30);
        add(convertersBox);

        inputsPanel = new JPanel(null);
        inputsPanel.setBounds(40, 70, 320, 120);
        inputsPanel.setBackground(Color.pink);
        add(inputsPanel);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(40, 210, 320, 30);
        add(convertBtn);

        result = new JLabel();
        result.setBounds(40, 250, 320, 30);
        add(result);

        convertersBox.addActionListener(e -> refreshInputs());
        convertBtn.addActionListener(e -> compute());

        refreshInputs();

        add(new JLabel("Polar Rose")).setBounds(40, 290, 100, 20);

        add(new JLabel("a")).setBounds(40, 320, 20, 20);
        add(new JLabel("k")).setBounds(120, 320, 20, 20);
        add(new JLabel("y0")).setBounds(200, 320, 30, 20);

        aField = new JTextField("100");
        kField = new JTextField("4");
        y0Field = new JTextField("0");

        aField.setBounds(40, 345, 60, 30);
        kField.setBounds(120, 345, 60, 30);
        y0Field.setBounds(200, 345, 60, 30);

        add(aField);
        add(kField);
        add(y0Field);

        JButton roseBtn = new JButton("Draw Rose");
        roseBtn.setBounds(280, 345, 120, 30);
        add(roseBtn);

        rosePanel = new PolarRose();
        rosePanel.setBounds(420, 20, 360, 360);
        rosePanel.setBackground(Color.WHITE);
        add(rosePanel);

        roseBtn.addActionListener(e -> drawRose());
    }

    private void refreshInputs() {

        inputsPanel.removeAll();

        String[] params = getConverter().getParameterNames();
        fields = new JTextField[params.length];

        for (int i = 0; i < params.length; i++) {
            JLabel label = new JLabel(params[i] + ":");
            label.setBounds(20, i * 35, 120, 30);
            inputsPanel.add(label);

            fields[i] = new JTextField();
            fields[i].setBounds(160, i * 35, 120, 30);
            inputsPanel.add(fields[i]);
        }

        inputsPanel.revalidate();
        inputsPanel.repaint();
        result.setText("");
    }

    private void compute() {
        try {
            double[] v = new double[fields.length];
            for (int i = 0; i < v.length; i++) {
                v[i] = Double.parseDouble(fields[i].getText());
            }

            result.setText("Result: " + getConverter().convert(v));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Numbers only.");
        }
    }
    private void drawRose() {
        try {
            double a = Double.parseDouble(aField.getText());
            int k = Integer.parseInt(kField.getText()); // MUST be integer
            double y0 = Double.parseDouble(y0Field.getText());

            if (k <= 0) {
                JOptionPane.showMessageDialog(this, "k must be a positive integer.");
                return;
            }

            rosePanel.setParameters(a, k, y0);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid polar rose parameters.");
        }
    }

    private Converter getConverter() {
        return (Converter) convertersBox.getSelectedItem();
    }
}
