package main.gui;
import main.model.Node;
import main.model.labyrinth.*;

import java.awt.*;
import javax.swing.JComponent;

public class LabyrinthComponent extends JComponent {

    private final int size;
    private Labyrinth labyrinth;
    public LabyrinthComponent(int windowSize) {
        size = (int) (windowSize/Main.SCALE);
        this.labyrinth = new IterativeBackTrackingLabyrinth(size);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        displayLabyrinth(g2);
    }

    public void displayLabyrinth(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, size * Main.SCALE, size * Main.SCALE);
        g2.setColor(Color.WHITE);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                g2.fillRect((c * Main.SCALE) + 1, (r * Main.SCALE) + 1, Main.SCALE - 2, Main.SCALE - 2);
                Node n = this.labyrinth.getNodeFromBoard(r, c);
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