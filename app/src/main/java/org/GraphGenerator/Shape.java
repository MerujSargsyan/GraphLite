package org.GraphGenerator;

import javax.swing.JFrame;

public abstract class Shape extends JFrame {
    int x, y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract String outString();
}