package main.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

    public static final int SIZE = 800; //changes size of the window
    public static final int SCALE = 16; //multiples of 4 work best
    public static final Dimension LABYRINTH_VIEWER_SIZE = new Dimension(SIZE + 15, SIZE + 38);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(LABYRINTH_VIEWER_SIZE);
        frame.setTitle("Labyrinth");
        LabyrinthComponent lc = new LabyrinthComponent(SIZE);
        frame.add(lc);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}