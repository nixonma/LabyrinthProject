package main.model.labyrinth;

import main.model.Graph;
import main.model.Node;

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

    public Node getNodeFromBoard(int r, int c) {
        return this.board.getNode(r, c);
    }

    protected abstract void genMaze();
}
