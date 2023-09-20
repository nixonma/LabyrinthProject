package main.gui;

import main.model.labyrinth.IterativeBackTrackingLabyrinth;
import main.model.labyrinth.Labyrinth;

import java.awt.Dimension;
import javax.swing.*;

public class Main {

    public static final int SIZE = 800; //changes size of the window
    public static int SCALE; //multiples of 4 work best
    public static final Dimension LABYRINTH_VIEWER_SIZE = new Dimension(SIZE + 15, SIZE + 38);

    public static void main(String[] args) {
        String difficulty = JOptionPane.showInputDialog(
                "How complex of a maze would you like? \n (1-4 where 1 is hard, 4 is easy)");
        int multiplier = 0;
        while (multiplier == 0) {
            try {
                multiplier = Integer.parseInt(difficulty);
            } catch (NumberFormatException e) {
                difficulty = JOptionPane.showInputDialog(
                        "Please enter a number. How complex of a maze would you like? \n (1-4 where 1 is hard, 4 is easy)");
            }
        }
        SCALE = 4 * multiplier;
        JFrame frame = new JFrame();
        frame.setSize(LABYRINTH_VIEWER_SIZE);
        frame.setTitle("Labyrinth");
        Labyrinth l = new IterativeBackTrackingLabyrinth(SIZE/SCALE);
        LabyrinthComponent lc = new LabyrinthComponent(SIZE, false, l);
        frame.add(lc);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        int response = JOptionPane.showConfirmDialog(null, "Do you want a solution?");
        boolean solution = true;
        if(response == 1) {
            solution = false;
        }
        lc = new LabyrinthComponent(SIZE, solution, l);
        frame.dispose();
        frame = new JFrame();
        frame.setSize(LABYRINTH_VIEWER_SIZE);
        frame.setTitle("Labyrinth");
        frame.add(lc);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /*
    Removed due to bad data access' required to calculate data.
     */
//    private static void generateStats() {
//        double totalTime = 0;
//        long startTime;
//        long endTime;
//        double longest = 0;
//        double shortest = 100;
//
//        String response = JOptionPane.showInputDialog("How many mazes do you want to average? Current scale: " + SCALE);
//        int n = Integer.parseInt(response);
//        Set<Labyrinth> labyrinthSet = new HashSet<>();
//        for(int i = 0; i < n; i++) {
//            startTime = System.currentTimeMillis();
//            Labyrinth labyrinth = new IterativeBackTrackingLabyrinth(SIZE / SCALE);
//            labyrinthSet.add(labyrinth);
//            endTime = System.currentTimeMillis();
//            totalTime += endTime - startTime;
//            if(endTime - startTime > longest){
//                longest = endTime - startTime;
//            }
//            if(endTime - startTime < shortest){
//                shortest = endTime - startTime;
//            }
//        }
//        System.out.println("average time to generate mazes: " + totalTime/n + "ms");
//        System.out.println("Longest: " + longest + "ms");
//        System.out.println("Shortest: " + shortest + "ms");
//
//        totalTime = 0;
//        longest = 0;
//        shortest = 100000;
//        int longestSolution = 0;
//        double complexity = 0;
//        for(Labyrinth l : labyrinthSet) {
//            startTime = System.currentTimeMillis();
//            List<Node> solutionPath = l.getSolution();
//            endTime = System.currentTimeMillis();
//            totalTime += endTime - startTime;
//            if(endTime - startTime > longest){
//                longest = endTime - startTime;
//            }
//            if(endTime - startTime < shortest){
//                shortest = endTime - startTime;
//            }
//            if(solutionPath.size() > longestSolution){
//                longestSolution = solutionPath.size();
//            }
//            complexity+=(double)l.getSolutionDecisions()/solutionPath.size();
//        }
//        System.out.println("average time to solve mazes: " + totalTime/n + "ms");
//        System.out.println("Longest: " + longest + "ms");
//        System.out.println("Shortest: " + shortest + "ms");
//        System.out.println("Average complexity: " + complexity/n);
//    }
}