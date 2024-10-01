package org.GraphGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

//TO-DO: implement MouseListener
public class DrawP extends JPanel implements MouseListener, KeyListener {
    // Keeps track of objects to draw
    private ArrayList<Shape> shapes;
    private ArrayList<Vertex> vertecies;
    private ArrayList<Line> lines;

    // Keeps track of current and previous point created
    private Vertex current;
    private Vertex previous;

    // Grid constants can be manipulated to change point positions
    private final int X_SIZE = 25;
    private final int Y_SIZE = 25;
    private final int ARC_SIZE = 25;
    private final int CENTER_ADJUSTMENT = 12;
    private Grid grid = new Grid();

    // Standard keybaord inputs
    private final int DELETE_VALUE = 8;
    private final int SPACE_VALUE = 32;

    private int vertexCount;
    private int edgeCount;

    // Textbox to display vertex and edge count
    private JLabel text;
    private final int TEXT_WIDTH = 100;
    private final int TEXT_HEIGHT = 50;
    private final int TEXT_POSITION = 50; //X and Y offset

    private boolean darkMode;

    // Initializes text Jlabel, ArrayLists and variables
    public DrawP() {
        text = new JLabel();
        text.setSize(TEXT_HEIGHT, TEXT_WIDTH);
        text.setLocation(TEXT_POSITION, TEXT_POSITION);
        text.setVisible(true);
        text.setForeground(Color.WHITE);
        add(text);
        current = null;
        previous = null;
        vertexCount = 0;
        edgeCount = 0;
        shapes = new ArrayList<>();
        vertecies = new ArrayList<>();
        lines = new ArrayList<>();
        darkMode = true;
    }

    // Custom way to paint on JPanel
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

    // Adds a vertex to the panel and draws a line to it from the current vertex
    // @param Vertex v to be added
    public void addVertex(Vertex v) {
        shapes.add(v);
        vertecies.add(v);
        vertexCount++;
        updateCurrent(v);   
        addLine();
        repaint();
    }

    // Updates the current and previous verticies
    // @param Vertex v to set as current vertex
    public void updateCurrent(Vertex v) {
        previous = current;
        current = v;
    }

    // Used if a point already exists on the graph so that instead of creating a
    // new one, an edge is connected to it.
    // @param Point p to connect the edge to
    public void connectVertex(Point p) {
        Vertex newV = new Vertex(grid.roundValue(p.x), grid.roundValue(p.y));
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

    // helper method to connect edges to vertecies
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
    }

    // helper method for painting vertex Graphics on JPanel
    // @param Vertex v to paint using JPanel Graphics component
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

    // helper method for painting edge Graphics on JPanel
    // @param Line l to paint using JPanel Graphics component
    public void paintLine(Line l, Graphics g) {
        g.drawLine(l.startingPoint.x, l.startingPoint.y, l.endingPoint.x, 
            l.endingPoint.y);
    }

    // deletes the most recent edge or graph that was painted using the
    // "backspace" key. 
    private void deleteRecent() {
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
        updateText(); 
        shapes.remove(s);
        repaint();
    }

    // updates the text of vertex and edge count
    public void updateText() {
        text.setText("|V| = " + vertexCount + " |E| = " + edgeCount);
    }

    // changes the color mode of the JPanel, activated by "space" key
    public void changeDarkMode() {
        if(darkMode) {
            text.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
        } else {
            text.setForeground(Color.WHITE);
            this.setBackground(Color.BLACK);
        }
        darkMode = !darkMode;
    }
    // Overrides all MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseReleased(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseEntered(MouseEvent e) {}; //nothing happens

    @Override
    public void mouseExited(MouseEvent e) {}; //nothing happens

    // creates a new line if the vertex is available on the grid, otherwise
    // connects it to an existing vertex
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = getMousePosition(false);
        Vertex v = grid.usePoint(p);
        if(v == null) {
            connectVertex(p);
        } else {
            addVertex(v);
        }
        updateText();
    } 

    // Overrides all KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {}; //does nothing

    // Deletes recent Shape if input is "backspace"
    // Changes color mode if input is "space"
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
