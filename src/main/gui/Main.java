package main.gui;

import main.model.Node;
import main.model.labyrinth.IterativeBackTrackingLabyrinth;
import main.model.labyrinth.Labyrinth;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;

public class Main {

    public static final int SIZE = 800; //changes size of the window
    public static final int SCALE = 16; //multiples of 4 work best
    public static final Dimension LABYRINTH_VIEWER_SIZE = new Dimension(SIZE + 15, SIZE + 38);

    public static void main(String[] args) {
        //generateStats();
        //0 is yes, 1 is no
        int response = JOptionPane.showConfirmDialog(null, "Do you want a solution?");
        JFrame frame = new JFrame();
        frame.setSize(LABYRINTH_VIEWER_SIZE);
        frame.setTitle("Labyrinth");
        boolean solution = true;
        if(response == 1) {
            solution = false;
        }
        LabyrinthComponent lc = new LabyrinthComponent(SIZE, solution);
        frame.add(lc);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void generateStats() {
        double totalTime = 0;
        long startTime;
        long endTime;
        double longest = 0;
        double shortest = 100;

        String response = JOptionPane.showInputDialog("How many mazes do you want to average? Current scale: " + SCALE);
        int n = Integer.parseInt(response);
        Set<Labyrinth> labyrinthSet = new HashSet<>();
        for(int i = 0; i < n; i++) {
            startTime = System.currentTimeMillis();
            Labyrinth labyrinth = new IterativeBackTrackingLabyrinth(SIZE / SCALE);
            labyrinthSet.add(labyrinth);
            endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
            if(endTime - startTime > longest){
                longest = endTime - startTime;
            }
            if(endTime - startTime < shortest){
                shortest = endTime - startTime;
            }
        }
        System.out.println("average time to generate mazes: " + totalTime/n + "ms");
        System.out.println("Longest: " + longest + "ms");
        System.out.println("Shortest: " + shortest + "ms");

        totalTime = 0;
        longest = 0;
        shortest = 100000;
        for(Labyrinth l : labyrinthSet) {
            startTime = System.currentTimeMillis();
            List<Node> solutionPath = l.getSolution();
            endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
            if(endTime - startTime > longest){
                longest = endTime - startTime;
            }
            if(endTime - startTime < shortest){
                shortest = endTime - startTime;
            }
        }
        System.out.println("average time to solve mazes: " + totalTime/n + "ms");
        System.out.println("Longest: " + longest + "ms");
        System.out.println("Shortest: " + shortest + "ms");
    }
}