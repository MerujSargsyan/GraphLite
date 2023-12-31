import java.awt.Point;

public class Vertex extends Shape {
    public Vertex(int x, int y) {
        super(x, y);
    }

    public Vertex(Point p) {
        super(p.x, p.y);
    }
    
    public boolean equals(Vertex v) {
        return (this.x == v.x && this.y == v.y);
    }
}