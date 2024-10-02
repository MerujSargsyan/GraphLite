package org.GraphGenerator;

public class DirectedLine extends Line {
    public DirectedLine(Vertex a, Vertex b) {
        super(a, b);
    } 

    public float[] getPolygon() {
        // Triangle
        float[] points = new float[6];

        int x1 = startingPoint.x, y1 = startingPoint.y;
        int x2 = endingPoint.x, y2 = endingPoint.y;

        points[0] = x2;
        points[1] = y2;

        float m = (y2 - y1) / (x2 - x1);
        float b = y2 - m*x2;
        float invM = -1 / m;

        float estimX = x2 - 0.25f;
        float estimY = m*estimX + b;

        float invB = estimY - invM*estimX;
        // perpendicular line: estimY = invM * estimX + invB

        //finding points for arrow
        float leftX = estimX - 0.25f;
        float rightX = estimX + 0.25f;

        float leftY = invM * leftX + invB;
        float rightY = invM * rightX + invB;

        points[2] = leftX;
        points[3] = leftY;

        points[4] = rightX;
        points[5] = rightY;

        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DirectedLine)) {
            return false;
        }
        DirectedLine l = (DirectedLine) o;
        boolean startingEq = this.startingPoint.equals(l.startingPoint);
        boolean endingEq = this.endingPoint.equals(l.endingPoint);

        return startingEq && endingEq;
    }
}
