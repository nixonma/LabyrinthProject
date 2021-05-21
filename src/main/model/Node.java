package main.model;

import java.util.ArrayList;

public class Node {

    public boolean hasBeenVisited;
    public int row, col;
    int treeNum;
    public ArrayList<Node> successors;
    public Node predecessor;
    private static int baseNum;

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

    public void unvisit(){
        this.predecessor = null;
        this.successors = new ArrayList<>();
        this.treeNum = -1;
        hasBeenVisited = false;
    }

    public int getTreeNum(){
        return treeNum;
    }

    public void addToBaseTree() {
        this.treeNum = baseNum;
    }

    public void setBaseNum(int num){
        baseNum = num;
    }

    public int getBaseNum(){
        return baseNum;
    }
}
