import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Vertex extends Shape {
    // number of vertecies connected to this vertex by one edge
    public int neighborhood;

    public Vertex(int x, int y) {
        super(x, y);
        this.neighborhood = 0;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent m) {
                System.out.println("output");
            }
        });
    }

    public Vertex(Point p) {
        super(p.x, p.y);
    }
    
    // @return true if x and y values are the same
    public boolean equals(Vertex v) {
        if(v == null) {
            return false;
        }
        return (this.x == v.x && this.y == v.y);
    }
}