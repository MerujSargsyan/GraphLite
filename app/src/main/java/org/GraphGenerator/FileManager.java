package org.GraphGenerator;

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;

public class FileManager {
    public static String fileName = "output.txt";
    public static int maxCol = 20;

    public static void outputToFile(ArrayList<Vertex> vertices, ArrayList<Line> lines) {
        File fd = new File("graphs/" + fileName);
        int colCount = 1;

        try(PrintWriter pw = new PrintWriter(fd)) {
            for(Vertex v : vertices) {
                if(colCount >= maxCol) {
                    pw.write("\n");
                    colCount = 0;
                }
                pw.write("\\" + v.x + "," + v.y);
                colCount++;
            }
            pw.write("\n");
            for(Line l : lines) {
                pw.write("\\(" + l.startingPoint.x + "," + l.startingPoint.y + "),(" 
                    + l.endingPoint.x + "," + l.endingPoint.y + ")\n");
                colCount++;
            }
        } catch(Exception ex) {
            System.out.println("Error in saving");
        }

        System.out.println("Saved");
    }
}
