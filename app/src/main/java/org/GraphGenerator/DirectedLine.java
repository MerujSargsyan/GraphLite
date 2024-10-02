package org.GraphGenerator;

public class DirectedLine extends Line {
    public DirectedLine(Vertex a, Vertex b) {
        super(a, b);
    } 

    public float[] getPolygon() {
        float arrowSize = 10.0f;  
        float halfBase = 5.0f;    
    
        float[] points = new float[6];
    
        float x2 = endingPoint.x;
        float y2 = endingPoint.y;
    
        float dx = x2 - startingPoint.x;
        float dy = y2 - startingPoint.y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
    
        dx /= length;
        dy /= length;
    
        float perpX = -dy;
        float perpY = dx;
    
        float offset = 5.0f;  
    
        points[0] = x2 - dx * offset;
        points[1] = y2 - dy * offset;
    
        // left base 
        points[2] = points[0] - dx * arrowSize + perpX * halfBase;
        points[3] = points[1] - dy * arrowSize + perpY * halfBase;
    
        // right base
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
