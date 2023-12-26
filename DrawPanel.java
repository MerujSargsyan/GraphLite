import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

//TO-DO: implement MouseListener
public class DrawPanel extends JPanel {
    private ArrayList<Shape> shapes;
    final int X_SIZE = 25;
    final int Y_SIZE = 25;
    final int ARC_SIZE = 25;

    public DrawPanel() {
        shapes = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        for(Shape s : shapes) {
            if(s instanceof Vertex) {
                //Draw vertex
                paintVertex((Vertex)s, g);
            } else if(s instanceof Line) {
                //Draw line
            }
        }
    }

    public void addVertex(Vertex v) {
        shapes.add(v);
        repaint();
    }

    public void paintVertex(Vertex v, Graphics g) {
        g.fillRoundRect(v.x, v.y, X_SIZE, Y_SIZE, ARC_SIZE, ARC_SIZE);
    }
}