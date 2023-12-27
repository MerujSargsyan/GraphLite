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
        int xPos = 0;
        int yPos = 0;
        
        for(int i = xPos; i < Main.WINDOW_SIZE_X; i += dX) {
            for(int j = yPos; j < Main.WINDOW_SIZE_Y; j += dY) {
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

    public boolean usePoint(Point p) {
        if(validPoint(p)) {
            grid.put(new Point(roundValue(p.x), roundValue(p.y)), 
                true);
            return true;
        }
        return false;
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