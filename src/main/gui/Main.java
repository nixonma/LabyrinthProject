package main.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

    public static final int SIZE = 700;
    public static final int SCALE = 20;
    public static final Dimension LABYRINTH_VIEWER_SIZE = new Dimension(SIZE + 15, SIZE + 35);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(LABYRINTH_VIEWER_SIZE);
        frame.setTitle("RecursiveBackTrackingLabyrith");
        frame.add(new LabyrinthComponent(SIZE));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}