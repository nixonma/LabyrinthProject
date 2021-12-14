package main.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {

    public boolean hasBeenVisited;
    public int row, col;
    int treeNum;
    public ArrayList<Node> successors;
    private Set<Node> successorSet;
    public Node predecessor;
    private static int baseNum;

    public Node(int r, int c, int treeNum) {
        this.row = r;
        this.col = c;
        this.treeNum = treeNum;
        hasBeenVisited = false;
        successors = new ArrayList<>();
        predecessor = null;
        successorSet = new HashSet<Node>();
    }

    public boolean isConnected(Node n){
        if(predecessor == null){
            return successors.contains(n);
        }
        return (predecessor.equals(n) || successors.contains(n));
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

    public Set<Node> getSuccessors() {
        successorSet.addAll(successors);
        return successorSet;
    }
}
