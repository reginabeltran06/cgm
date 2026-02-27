import ui.Panel;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Image Editor");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new Panel());
        setVisible(true);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(Main::new);}
}
