import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

//TO-DO: implement MouseListener
public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private ArrayList<Shape> shapes;
    private ArrayList<Vertex> vertecies;
    private ArrayList<Line> lines;

    private Vertex current;
    private Vertex previous;

    private final int X_SIZE = 25;
    private final int Y_SIZE = 25;
    private final int ARC_SIZE = 25;
    private final int CENTER_ADJUSTMENT = 12;
    private Grid grid = new Grid();

    private final int DELETE_VALUE = 8;
    private final int SPACE_VALUE = 32;
    private int vertexCount;
    private int edgeCount;
    private JLabel label;

    private boolean darkMode;

    public DrawPanel() {
        label = new JLabel();
        label.setSize(100, 50);
        label.setLocation(50, 50);
        label.setVisible(true);
        label.setForeground(Color.WHITE);
        add(label);
        current = null;
        previous = null;
        vertexCount = 0;
        edgeCount = 0;
        shapes = new ArrayList<>();
        vertecies = new ArrayList<>();
        lines = new ArrayList<>();
        darkMode = true;
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
        updateCurrent(v);   
        addLine();
        repaint();
    }

    public void updateCurrent(Vertex v) {
        previous = current;
        current = v;
    }

    public void connectVertex(Point v) {
        Vertex newV = new Vertex(grid.roundValue(v.x), grid.roundValue(v.y));
        if(newV.equals(current)) {
            updateCurrent(newV);
            return;
        }
        Line newLine = new Line(newV, current);
        if(!lines.contains(newLine)) {
            shapes.add(newLine);
            lines.add(newLine);
            edgeCount++;
        }
        updateCurrent(newV);
        repaint();
    }   

    public void addLine() {
        if(current.equals(previous) || current == null || previous == null) {
            return;
        }
        Line newLine = new Line(previous, current);
        if(!lines.contains(newLine)) {
            shapes.add(newLine);
            lines.add(newLine); 
            edgeCount++;
        }
        System.out.println(newLine);
    }

    public void paintVertex(Vertex v, Graphics g) {
        if(v.equals(current)) {
            if(darkMode) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
        } else {
            if(darkMode) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
        }
        g.fillRoundRect(v.x - CENTER_ADJUSTMENT, v.y - CENTER_ADJUSTMENT, X_SIZE, 
            Y_SIZE, ARC_SIZE, ARC_SIZE);
    }

    public void paintLine(Line l, Graphics g) {
        g.drawLine(l.startingPoint.x, l.startingPoint.y, l.endingPoint.x, 
            l.endingPoint.y);
    }

    private void deleteRecent() {
        int i = shapes.size() - 1;
        Shape s = shapes.get(shapes.size() - 1);
        if(s instanceof Vertex) {
            grid.deletePoint((Vertex)s);
            vertecies.remove((Vertex)s);
            vertexCount--;
        }
        if(s instanceof Line) {
            lines.remove(s);
            edgeCount--;
        }
        if(vertecies.size() >= 1) {
            current = vertecies.get(vertecies.size()-1);
            previous = null;
        } else if(vertecies.size() >= 2) {
            current = vertecies.get(vertecies.size()-1);
            previous = vertecies.get(vertecies.size()-2);
        } else {
            current = null;
            previous = null;
        }
        updateLabel(); 
        shapes.remove(s);
        repaint();
    }

    public void updateLabel() {
        label.setText("|V| = " + vertexCount + " |E| = " + edgeCount);
    }

    public void changeDarkMode() {
        if(darkMode) {
            label.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
        } else {
            label.setForeground(Color.WHITE);
            this.setBackground(Color.BLACK);
        }
        darkMode = !darkMode;
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
        updateLabel();
    } 

    //Overrides all KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {
        
    }; //does nothing

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == DELETE_VALUE && shapes.size() > 0) {
            deleteRecent();
        }
        if(e.getKeyCode() == SPACE_VALUE) {
            changeDarkMode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}; //does nothing
}

//TODO: fix duplicate edge error ??
//TODO: add a color changing mode