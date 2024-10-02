package org.GraphGenerator;

public class DirectedLine extends Line {
    public DirectedLine(Vertex a, Vertex b) {
        super(a, b);
    } 

    public float[] getPolygon() {
        float arrowSize = 10.0f;  // Size of the arrowhead (distance from tip to base)
        float halfBase = 5.0f;    // Half the width of the arrowhead's base
    
        float[] points = new float[6];
    
        // Assume the ending point of the line is (x2, y2)
        float x2 = endingPoint.x;
        float y2 = endingPoint.y;
    
        // Calculate the direction vector of the line (from start to end)
        float dx = x2 - startingPoint.x;
        float dy = y2 - startingPoint.y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
    
        // Normalize the direction vector
        dx /= length;
        dy /= length;
    
        // Find the perpendicular direction for the base of the arrowhead
        float perpX = -dy;
        float perpY = dx;
    
        // Offset the arrow tip slightly behind the actual end point
        float offset = 5.0f;  // Offset distance behind the endpoint
    
        // Tip of the arrow (offset from the line's endpoint)
        points[0] = x2 - dx * offset;
        points[1] = y2 - dy * offset;
    
        // Left corner of the triangle (base of the arrowhead)
        points[2] = points[0] - dx * arrowSize + perpX * halfBase;
        points[3] = points[1] - dy * arrowSize + perpY * halfBase;
    
        // Right corner of the triangle (base of the arrowhead)
        points[4] = points[0] - dx * arrowSize - perpX * halfBase;
        points[5] = points[1] - dy * arrowSize - perpY * halfBase;
    
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
