package org.GraphGenerator;

public class DirectedLine extends Line {
    public DirectedLine(Vertex a, Vertex b) {
        super(a, b);
    } 

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DirectedLine)) {
            return false;
        }
        DirectedLine l = (DirectedLine) o;
        boolean startingEq = this.startingPoint.equals(l.startingPoint);
        boolean endingEq = this.endingPoint.equals(l.endingPoint);

        return stringEq && endingEq;
    }
}
