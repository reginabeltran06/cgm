package Homework01.ui;

import javax.swing.*;
import java.util.List;

import Homework01.ui.shapes.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Area & Perimeter Calculator");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Shape> shapes = List.of(
                new Square(),
                new Rectangle(),
                new Circle(),
                new Triangle(),
                new Semicircle(),
                new Pentagram(),
                new Pentagon()

        );

        add(new Panel(shapes));
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
