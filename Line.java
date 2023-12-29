public class Line extends Shape {
    public Vertex startingPoint, endingPoint;

    public Line(Vertex a, Vertex b) {
        super(a.x, a.y);
        startingPoint = a;
        endingPoint = b;
    }
}