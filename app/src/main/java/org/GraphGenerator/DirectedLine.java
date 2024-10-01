package org.GraphGenerator;

import java.awt.Polygon;

public class DirectedLine extends Line {
    public DirectedLine(Vertex a, Vertex b) {
        super(a, b);
    } 

    public Polygon getPolygon() {
        // Triangle
        int[] xs = new int[3];
        int[] ys = new int[3];

        int x1 = startingPoint.x, y1 = startingPoint.y;
        int x2 = endingPoint.x, y2 = endingPoint.y;

        xs[0] = x2;
        ys[0] = y2;

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

        xs[1] = (int)leftX;
        ys[1] = (int)leftY;

        xs[2] = (int)rightX;
        ys[2] = (int)rightY;

        return new Polygon(xs, ys, 3);
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
