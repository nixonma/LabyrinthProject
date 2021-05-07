package main.model.labyrinth;

import main.gui.Main;
import main.model.Graph;
import main.model.Node;

import java.awt.*;

public abstract class Labyrinth {
    int size;
    Graph board;
    Node start;

    public Labyrinth(int s) {
        this.size = s;
        board = new Graph(size);
        initBoard();
    }

    private void initBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board.addNode(new Node(r, c, -1));
            }
        }
        genMaze();
    }

    protected abstract void genMaze();

    public void display(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, size * Main.SCALE, size * Main.SCALE);
        g2.setColor(Color.WHITE);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                g2.fillRect((c * Main.SCALE) + 1, (r * Main.SCALE) + 1, Main.SCALE - 2, Main.SCALE - 2);
            }
        }
        //g2.setColor(Color.GREEN);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Node n = this.board.getNode(r, c);
                if (n.predecessor != null) {
                    int startY = (int) (Main.SCALE * ((double) (n.predecessor.getR() + n.getR()) / 2.0));
                    int startX = (int) (Main.SCALE * ((double) (n.predecessor.getC() + n.getC()) / 2.0));
                    if (startX == n.getC()) {
                        g2.fillRect(startX + 1, startY + 1, Main.SCALE - 2, Main.SCALE - 2);
                    } else {
                        g2.fillRect(startX + 1, startY + 1, Main.SCALE - 2, Main.SCALE - 2);
                    }
                }
            }
        }

        g2.setColor(Color.blue);
        g2.fillRect((0 * Main.SCALE) + 1, (0 * Main.SCALE) + 1, Main.SCALE - 2, Main.SCALE - 2);
        g2.fillRect((size * Main.SCALE) - Main.SCALE + 1, (size * Main.SCALE) - Main.SCALE + 1, Main.SCALE - 2,
                Main.SCALE - 2);
    }
}
