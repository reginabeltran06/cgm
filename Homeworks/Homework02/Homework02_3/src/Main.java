package Homework02_3;

import javax.swing.*;
import java.util.List;
import Homework02_3.ui.Panel;
import Homework02_3.converters.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Polar to Cartesian Converter");
        setSize(820, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Converter> converters = List.of(
                new PolarToCartesian(),
                new CartesianToPolar()
        );

        add(new Panel(converters));
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
