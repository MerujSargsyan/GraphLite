import java.util.HashMap;
import java.awt.Point;

public class Grid {
    private final int dX = 50;
    private final int dY = 50;
    private final int MID = 25; //used to round to nearest factor of 25

    private HashMap<Point, Boolean> grid;

    public Grid() {
        grid = new HashMap<>();
        createGrid();
    }

    private void createGrid() {
        for(int i = 0; i < Main.WINDOW_SIZE_X; i += dX) {
            for(int j = 0; j < Main.WINDOW_SIZE_Y; j += dY) {
                grid.put(new Point(i, j), false);
            }
        } 
    }
 
    private boolean validPoint(Point p) {
        //rounds to nearest factor of 25
        Point normPoint = new Point(roundValue(p.x), roundValue(p.y));

        //no point nearby -> validPoint
        return grid.get(normPoint) == false; 
    }

    public Vertex usePoint(Point p) {
        if(validPoint(p)) {
            Point newP = new Point(roundValue(p.x), roundValue(p.y));
            grid.put(p, true);
            return new Vertex(newP);
        }
        return null;
        
    }

    private int roundValue(int num) {
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