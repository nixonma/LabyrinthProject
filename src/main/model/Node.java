package main.model;

import java.util.ArrayList;

public class Node {

    public boolean hasBeenVisited;
    public int row, col;
    int treeNum;
    public ArrayList<Node> successors;
    public Node predecessor;

    public Node(int r, int c, int treeNum) {
        this.row = r;
        this.col = c;
        this.treeNum = treeNum;
        hasBeenVisited = false;
        successors = new ArrayList<>();
        predecessor = null;
    }

    public void visit(int treeNum) {
        this.treeNum = treeNum;
        hasBeenVisited = true;
    }
}
