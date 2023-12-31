import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

//TO-DO: implement MouseListener
public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private ArrayList<Shape> shapes;
    private ArrayList<Vertex> vertecies;
    private final int X_SIZE = 25;
    private final int Y_SIZE = 25;
    private final int ARC_SIZE = 25;
    private final int CENTER_ADJUSTMENT = 12;
    private Grid grid = new Grid();

    private final int DELETE_VALUE = 8;
    private int vertexCount;
    private int edgeCount;

    public DrawPanel() {
        vertexCount = 0;
        edgeCount = 0;
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
                paintLine((Line)s, g);
            }
        }
    }

    public void addVertex(Vertex v) {
        shapes.add(v);
        vertecies.add(v);
        vertexCount++;
        if(vertecies.size() >= 2) {
            edgeCount++;
            addLine();
        }        
        repaint();
    }

    public void connectVertex(Point v) {
        Vertex newV = new Vertex(grid.roundValue(v.x), grid.roundValue(v.y));
        Line newLine = new Line(newV, vertecies.get(vertecies.size()-1));
        shapes.add(newLine);
        edgeCount++;
        repaint();
    }   

    public void addLine() {
        Line newLine = new Line(vertecies.get(vertecies.size() - 2), 
            vertecies.get(vertecies.size() - 1));
        shapes.add(newLine); 
    }

    public void paintVertex(Vertex v, Graphics g) {
        if(v.equals(vertecies.get(vertecies.size() - 1))) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRoundRect(v.x - CENTER_ADJUSTMENT, v.y - CENTER_ADJUSTMENT, X_SIZE, 
            Y_SIZE, ARC_SIZE, ARC_SIZE);
    }

    public void paintLine(Line l, Graphics g) {
        g.drawLine(l.startingPoint.x, l.startingPoint.y, l.endingPoint.x, 
            l.endingPoint.y);
    }

    private void deleteRecentButton() {
        int i = shapes.size() - 1;
        Shape s = shapes.get(shapes.size() - 1);
        if(s instanceof Vertex) {
            grid.deletePoint((Vertex)s);
            vertecies.remove((Vertex)s);
            vertexCount--;
        }
        if(s instanceof Line) {
            edgeCount--;
        }
        System.out.println("|V| = " + vertexCount + " |E| = " + edgeCount);   
        shapes.remove(s);
        repaint();
    }

    //Overrides all MouseListener methods
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
        if(v == null) {
            connectVertex(p);
        } else {
            addVertex(v);
        }
        System.out.println("|V| = " + vertexCount + " |E| = " + edgeCount);   
    } 

    //Overrides all KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {
        
    }; //does nothing

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == DELETE_VALUE) {
            deleteRecentButton();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}; //does nothing
}

//TODO: catch too many deletes error