import java.util.HashMap;
import java.awt.Point;

public class Grid {
    private final int dX = 25;
    private final int dY = 25;
    private final int MID25 = 12; //used to round to nearest factor of 25

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

        return grid.get(normPoint) == false; //no point nearby -> validPoint
    }

    public boolean usePoint(Point p) {
        if(validPoint(p)) {
            grid.put(p, true);
            return true;
        }
        return false;
    }

    private int roundValue(int num) {
        int difference = num % dX;
        int output = num - difference;

        if(difference < MID25) {
            return output;
        } else {
            //scales to the upper value of num ex: 640 -> 650, not 625
            return output + dX; 
        }
    }
}