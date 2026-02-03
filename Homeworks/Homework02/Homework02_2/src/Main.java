package Homework02_2;

import javax.swing.*;
import Homework02_2.ui.Panel;

public class Main extends JFrame {

    public Main() {

        setTitle("Aspect Ratio Calculator");
        setSize(320, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new Panel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
