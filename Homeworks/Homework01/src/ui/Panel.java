package Homework01.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Homework01.ui.shapes.Shape;

public class Panel extends JPanel {

    private JComboBox<Shape> shapesBox;
    private JPanel inputsPanel;
    private JTextField[] fields;
    private JLabel result;

    public Panel(List<Shape> shapes) {

        setLayout(null);
        setBackground(Color.pink);

        shapesBox = new JComboBox<>(shapes.toArray(new Shape[0]));
        shapesBox.setBounds(120, 20, 160, 30);
        add(shapesBox);

        inputsPanel = new JPanel(null);
        inputsPanel.setBounds(40, 70, 320, 120);
        inputsPanel.setBackground(Color.pink);
        add(inputsPanel);

        JButton areaBtn = new JButton("Area");
        JButton periBtn = new JButton("Perimeter");
        areaBtn.setBounds(40, 210, 140, 30);
        periBtn.setBounds(200, 210, 160, 30);
        add(areaBtn);
        add(periBtn);

        result = new JLabel();
        result.setBounds(40, 260, 320, 30);
        add(result);

        shapesBox.addActionListener(e -> refreshInputs());
        areaBtn.addActionListener(e -> compute(true));
        periBtn.addActionListener(e -> compute(false));

        refreshInputs();
    }

    private void refreshInputs() {

        inputsPanel.removeAll();

        String[] params = getShape().getParameterNames();
        fields = new JTextField[params.length];

        for (int i = 0; i < params.length; i++) {
            inputsPanel.add(new JLabel(params[i] + ":"))
                    .setBounds(20, i * 35, 100, 30);

            fields[i] = new JTextField();
            fields[i].setBounds(140, i * 35, 120, 30);
            inputsPanel.add(fields[i]);
        }

        inputsPanel.revalidate();
        inputsPanel.repaint();
        result.setText("");
    }

    private void compute(boolean area) {
        try {
            double[] v = new double[fields.length];
            for (int i = 0; i < v.length; i++)
                v[i] = Double.parseDouble(fields[i].getText());

            result.setText("Result: " +
                    (area ? getShape().calculateArea(v)
                            : getShape().calculatePerimeter(v)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Numbers only.");
        }
    }

    private Shape getShape() {
        return (Shape) shapesBox.getSelectedItem();
    }
}
