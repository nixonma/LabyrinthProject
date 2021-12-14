package main.gui;
import main.model.Node;
import main.model.labyrinth.*;

import javax.swing.JComponent;
import java.awt.*;
import java.util.List;

public class LabyrinthComponent extends JComponent {

    private final int size;
    private Labyrinth labyrinth;
    private boolean showSolution;
    public LabyrinthComponent(int windowSize, boolean solution) {
        showSolution = solution;
        this.size = (int) (windowSize/Main.SCALE);
        this.labyrinth = new IterativeBackTrackingLabyrinth(this.size);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        displayLabyrinth(g2);
        if(showSolution) {
            displaySolution(g2);
        }
    }

    private void displaySolution(Graphics2D g2) {
        int decisions = 0;
        List<Node> solutionPath = this.labyrinth.getSolution();
        g2.setColor(Color.RED);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Node n = this.labyrinth.getNodeFromBoard(r, c);
                if(n.getSuccessors().size() > 1) {
                    decisions ++;
                }
                if(solutionPath.contains(n)) {
                    g2.fillRect((c * Main.SCALE) + 1, (r * Main.SCALE) + 1, Main.SCALE - 2, Main.SCALE - 2);
                    if (n.predecessor != null) {
                        int startY = (int) (Main.SCALE * ((double) (n.predecessor.row + n.row) / 2.0));
                        int startX = (int) (Main.SCALE * ((double) (n.predecessor.col + n.col) / 2.0));
                        g2.fillRect(startX + 1, startY + 1, Main.SCALE - 2, Main.SCALE - 2);
                    }
                }
            }
        }
        System.out.println("Decisions: "+ decisions);
        System.out.println("Path Length: "+ solutionPath.size());
    }

    public void displayLabyrinth(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, size * Main.SCALE, size * Main.SCALE);
        g2.setColor(Color.WHITE);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Node n = this.labyrinth.getNodeFromBoard(r, c);
                g2.fillRect((c * Main.SCALE) + 1, (r * Main.SCALE) + 1, Main.SCALE - 2, Main.SCALE - 2);
                if (n.predecessor != null) {
                    int startY = (int) (Main.SCALE * ((double) (n.predecessor.row + n.row) / 2.0));
                    int startX = (int) (Main.SCALE * ((double) (n.predecessor.col + n.col) / 2.0));
                    g2.fillRect(startX + 1, startY + 1, Main.SCALE - 2, Main.SCALE - 2);
                }
            }
        }
        g2.setColor(Color.BLUE);
        g2.fillRect( 1, 1, Main.SCALE - 2, Main.SCALE - 2);
        g2.setColor(Color.RED);
        g2.fillRect((size * Main.SCALE) - Main.SCALE + 1, (size * Main.SCALE) - Main.SCALE + 1, Main.SCALE - 2,
                Main.SCALE - 2);
    }
}