package edits;

import javax.swing.*;
import java.awt.Component;

public class GetCoordinates {

    public int x1;
    public int y1;
    public int x2;
    public int y2;

    private GetCoordinates(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public static GetCoordinates request(Component parent, int maxWidth, int maxHeight) {

        JTextField xField = new JTextField();
        JTextField yField = new JTextField();

        Object[] message = {
                "X:", xField,
                "Y:", yField
        };

        // -------- FIRST COORDINATE --------
        int option = JOptionPane.showConfirmDialog(
                parent,
                message,
                "Start Coordinate",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option != JOptionPane.OK_OPTION) return null;

        int x1, y1;
        try {
            x1 = Integer.parseInt(xField.getText());
            y1 = Integer.parseInt(yField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid values.");
            return null;
        }

        xField.setText("");
        yField.setText("");

        option = JOptionPane.showConfirmDialog(
                parent,
                message,
                "End Coordinate",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option != JOptionPane.OK_OPTION) return null;

        int x2, y2;
        try {
            x2 = Integer.parseInt(xField.getText());
            y2 = Integer.parseInt(yField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid values.");
            return null;
        }


        x1 = Math.max(0, Math.min(x1, maxWidth));
        y1 = Math.max(0, Math.min(y1, maxHeight));
        x2 = Math.max(0, Math.min(x2, maxWidth));
        y2 = Math.max(0, Math.min(y2, maxHeight));

        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y2 < y1) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        return new GetCoordinates(x1, y1, x2, y2);
    }
}