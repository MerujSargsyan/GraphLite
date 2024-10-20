package org.GraphGenerator;

import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;

public class FileManager {
    public static String fileName = "output.txt";

    public static void outputToFile(ArrayList<Vertex> vertices, ArrayList<Line> lines) {
        File fd = new File("graphs/" + fileName);

        try(PrintWriter pw = new PrintWriter(fd)) {
            for(Vertex v : vertices) {
                pw.write("\\" + v.toString());
            }
            pw.write("\n");
            for(Line l : lines) {
                pw.write("\\" + l.toString());
            }
        } catch(Exception ex) {
            System.out.println("Error in saving");
        }

        System.out.println("Saved");
    }
}
