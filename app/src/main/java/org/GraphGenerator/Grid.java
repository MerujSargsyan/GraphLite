package org.GraphGenerator;

import java.util.HashMap;
import java.awt.Point;

public class Grid {
    // constants that set up the grid (change to change size of grid)
    // Recommended to have dX = dY to avoid bugs
    private final int dX = 50;
    private final int dY = 50;
    private final int MID = dX/2; //used to round to nearest factor of dX/2

    private HashMap<Point, Boolean> grid;

    public Grid() {
        grid = new HashMap<>();
        createGrid();
    }

    // creates a grid which starts at (0,0) on the JPanel and increments by dX
    // on x-axis and dY on y-axis
    private void createGrid() {
        for(int i = 0; i < Main.WINDOW_SIZE_X; i += dX) {
            for(int j = 0; j < Main.WINDOW_SIZE_Y; j += dY) {
                grid.put(new Point(i, j), false);
            }
        } 
    }
    
    /** 
     * @return true if the point is available (the value in HashMap is true)
     */
    private boolean validPoint(Point p) {
        //rounds to nearest factor of 25
        return !grid.getOrDefault(p, false);
    }

    /**
     * @param Point p to convert to Vertex
     * @return Vertex which is made from the rounded x and y of the param Point
     */
    public Vertex usePoint(Point p) {
        Point newP = new Point(roundValue(p.x), roundValue(p.y));
        if(validPoint(newP)) {
            grid.put(newP, true);

            return new Vertex(newP);
        }
        return null;
    }

    public Vertex usePoint(int x, int y) {
        Point newP = new Point(roundValue(x), roundValue(y));
        if(validPoint(newP)) {
            grid.put(newP, true);

            return new Vertex(newP);
        }

        return null;
    }

    /**
     * @param Vertex v that will be removed from the grid
     */
    public void deletePoint(Vertex v) {
        grid.put(new Point(v.x, v.y), false);
    }

    /** 
     * @param int num to rund to the nearest dX value
     * @return num roudned to the nearst MID value
     */ 
    public int roundValue(int num) {
        int difference = num % dX;
        int output = num - difference;

        if(difference < MID) {
            return output;
        } else {
            //scales to the upper value of num ex: 640 -> 650, not 625
            return output + dX; 
        }
    }
}
