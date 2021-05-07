package main.gui;
import main.model.labyrinth.Labyrinth;
import main.model.labyrinth.RecursiveBackTrackingLabyrith;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class LabyrinthComponent extends JComponent {

    private int size;

    public LabyrinthComponent(int windowSize) {
        size = (int)(windowSize/Main.SCALE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        Labyrinth l = new RecursiveBackTrackingLabyrith(size);
        l.display(g2);
    }

}