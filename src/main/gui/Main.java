package main.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

    public static final int SIZE = 800; //changes size of the window
    public static int SCALE;
    public static final Dimension LABYRINTH_VIEWER_SIZE = new Dimension(SIZE + 15, SIZE + 38);
    private static StartUpSelections select;

    public static void main(String[] args) {
        select = new StartUpSelections();
    }

    public static void run(){
        String lType = select.getLabyrinthType();
        SCALE = select.getScale();
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
