import java.awt.*;
import javax.swing.*;

public class Main {
    final public static int WINDOW_SIZE_X = 750;
    final public static int WINDOW_SIZE_Y = 500;


    public static void main(String args[]) {
        JFrame window = new JFrame("Graph Generator");
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

        JPanel drawPanel = new DrawPanel();
        drawPanel.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

        drawPanel.setBackground(Color.BLACK);
        window.add(drawPanel);
        window.setVisible(true);
    }
}