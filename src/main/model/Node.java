package main.model;

import java.util.ArrayList;

public class Node {

    private boolean hasBeenVisited;
    int row, col;
    int treeNum;
    ArrayList<Node> successors;
    public Node predecessor;

    public Node(int r, int c, int num) {
        this.row = r;
        this.col = c;
        this.treeNum = num;
        hasBeenVisited = false;
        successors = new ArrayList<>();
        predecessor = null;
    }

    public void setTreeNum(int n) {
        this.treeNum = n;
    }

    public int getTreeNum() {
        return treeNum;
    }

    public boolean getVisited() {
        return hasBeenVisited;
    }

    public void visit(int treeNum) {
        this.treeNum = treeNum;
        hasBeenVisited = true;
    }

    public int getR() {
        return row;
    }

    public int getC() {
        return col;
    }

//    public Node getPredecessor(){
//        return predecessor;
//    }
//
//    public ArrayList<Node> getSuccessors(){
//        return successors;
//    }
}
