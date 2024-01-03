public class Line extends Shape {
    public Vertex startingPoint, endingPoint;

    public Line(Vertex a, Vertex b) {
        super(a.x, a.y);
        startingPoint = a;
        endingPoint = b;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Line)) {
            return false;
        }
        Line l = (Line)o;
        boolean startingEq = this.startingPoint.equals(l.startingPoint);
        boolean endingEq = this.endingPoint.equals(l.endingPoint);
        return startingEq && endingEq;
    }
    
    @Override
    public String toString() {
        return "<" + startingPoint.x + ", " + startingPoint.y + "> -> <" +
            endingPoint.x + ", " + endingPoint.y + ">";
    }
}