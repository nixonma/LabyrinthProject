package main.model.labyrinth;

import main.model.Graph;
import main.model.Node;

import java.util.List;

public abstract class Labyrinth extends Graph{
    int size;
    Node start;
    Node end;
    public List<Node> solution;

    public Labyrinth(int s) {
        super(s);
        this.size = s;
        initBoard();
        start = getNodeFromBoard(0,0);
        end = getNodeFromBoard(this.size - 1, this.size - 1);
    }

    private void initBoard() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.addNode(new Node(r, c, -1));
            }
        }
        genMaze();
    }

    public Node getNodeFromBoard(int r, int c) {
        return this.getNode(r, c);
    }

    public List<Node> getSolution(){
        if(solution == null){
            solution = this.shortestPath(start, end);
        }
        return solution;
    }

    protected abstract void genMaze();
}
