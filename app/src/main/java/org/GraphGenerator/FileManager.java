package org.GraphGenerator;

import java.io.File;
import java.io.PrintWriter;

import java.util.Scanner;
import java.util.ArrayList;

public class FileManager {
    public static String fileName = "output";
    public static int maxCol = 20;

    public static void outputToFile(ArrayList<Shape> shapes) {
        File fd = new File("graphs/" + fileName + ".txt");

        try(PrintWriter pw = new PrintWriter(fd)) {
            for(Shape s : shapes) {
                pw.write(s.outString() + "\n");
            }            
        } catch(Exception ex) {
            System.out.println("Error in saving");
        }

        System.out.println("Saved");
    }

    public static Vertex stringToVertex(String s) throws NumberFormatException{
        int midIdx = s.indexOf(',');
        String x = s.substring(2, midIdx);
        String y = s.substring(midIdx + 1, s.length());

        return new Vertex(Integer.parseInt(x), Integer.parseInt(y));
    }

    public static Line stringToLine(String s) throws NumberFormatException {
        int midIdx = s.indexOf("),(");
        String sP = "\\v" + s.substring(3, midIdx);
        String eP = "\\v" + s.substring(midIdx + 3, s.length() - 1);
        System.out.println(sP);
        System.out.println(eP);
        Vertex startV = stringToVertex(sP);
        System.out.println(startV);
        Vertex endV = stringToVertex(eP);
        System.out.println(endV);
        return new Line(startV, endV);
    }

    public static ArrayList<Shape> parseInput(String filename) {
        File fd = new File("graphs/" + filename + ".txt");

        ArrayList<Shape> arr = new ArrayList<>();

        try(Scanner sc = new Scanner(fd)) {
            while(sc.hasNextLine()) {
                String parsed = sc.nextLine();
                System.out.println(parsed);

                if(parsed.charAt(1) == 'v') {
                    Vertex v = stringToVertex(parsed);
                    arr.add(v);
                } else if(parsed.charAt(1) == 'e') {
                    Line l = stringToLine(parsed);
                    System.out.println(l);
                    arr.add(l);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch(NumberFormatException nfe) {
            System.out.println("Input file not formatted correctly");
            return null;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
       
        System.out.println(arr);
        return arr;
    }
}
