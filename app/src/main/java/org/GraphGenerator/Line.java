package org.GraphGenerator;

public class Line extends Shape {
    public Vertex startingPoint, endingPoint;

    public Line(Vertex a, Vertex b) {
        super(a.x, a.y);
        startingPoint = a;
        endingPoint = b;
    }

    // @return true if both startingPoint and endingPoint Vectors are the same
    //         or if the edge is backwards
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Line)) {
            return false;
        }
        Line l = (Line)o;
        boolean startingEq = this.startingPoint.equals(l.startingPoint);
        boolean endingEq = this.endingPoint.equals(l.endingPoint);
        
        //Lines pointing opposite are still the same edge
        if(this.startingPoint.equals(l.endingPoint) && 
            this.endingPoint.equals(l.startingPoint)) {
            return true;
        }
        return startingEq && endingEq;
    }
    
    @Override
    public String toString() {
        return "<" + startingPoint.x + ", " + startingPoint.y + "> -> <" +
            endingPoint.x + ", " + endingPoint.y + ">";
    }

    public String outString() {
        return "\\e(" + startingPoint.x + "," + startingPoint.y + "),(" +
            endingPoint.x + "," + endingPoint.y + ")";
    }
}