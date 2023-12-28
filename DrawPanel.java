import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

//TO-DO: implement MouseListener
public class DrawPanel extends JPanel implements MouseListener{
    private ArrayList<Shape> shapes;
    private ArrayList<Vertex> vertecies; 
    final int X_SIZE = 25;
    final int Y_SIZE = 25;
    final int ARC_SIZE = 25;
    final int CENTER_ADJUSTMENT = 12;
    private Grid grid = new Grid();

    public DrawPanel() {
        shapes = new ArrayList<>();
        vertecies = new ArrayList<>();
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
        vertecies.add(v);
        repaint();
    }

    public void paintVertex(Vertex v, Graphics g) {
        g.fillRoundRect(v.x - CENTER_ADJUSTMENT, v.y - CENTER_ADJUSTMENT, X_SIZE, 
            Y_SIZE, ARC_SIZE, ARC_SIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseReleased(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseEntered(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseExited(MouseEvent e) {}; //nothing happens

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getMousePosition(false);
        Vertex v = grid.usePoint(p);
        if(v != null) {
            addVertex(v);
            repaint();
        }
    } 

}