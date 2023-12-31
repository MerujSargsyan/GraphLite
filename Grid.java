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
        return !grid.getOrDefault(p, false);
    }

    public Vertex usePoint(Point p) {
        Point newP = new Point(roundValue(p.x), roundValue(p.y));
        if(validPoint(newP)) {
            grid.put(newP, true);

            return new Vertex(newP);
        }
        return null;
        
    }

    public void deletePoint(Vertex v) {
        grid.put(new Point(v.x, v.y), false);
    }

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