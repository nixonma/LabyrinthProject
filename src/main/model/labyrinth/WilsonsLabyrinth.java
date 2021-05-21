package main.model.labyrinth;

import main.model.Node;

import java.util.ArrayList;

public class WilsonsLabyrinth extends Labyrinth{

    public WilsonsLabyrinth(int s) {
        super(s);
    }

    @Override
    protected void genMaze() {
        Node seed = getRandomNode();
        seed.setBaseNum(1);
        seed.visit(1);
        loopErasedRandomWalk();
    }

    private void loopErasedRandomWalk() {
        Node current = this.getRandomStartNode();
        System.out.println("Starting walk at: " + current.row + " " + current.col);
        while(!current.hasBeenVisited) {
            current.visit(2);
            ArrayList<Node> options = this.board.getUnrelatedAdjacent(current);
            if (options.size() == 0) {
                Node reset = null;
                for (Node n : board.getAdjacent(current)) {
                    if (!n.predecessor.equals(current)) {
                        reset = n;
                        current.successors.add(n);
                        break;
                    }
                }
                resetLoop(current, reset); //there should only be one successor
                current = reset;
                current.hasBeenVisited = false;
                if(current.predecessor == null){
                    //the loop erase was bad
                    throw new RuntimeException("no");
                }
            } else {
                int opIndex = (int) (Math.random() * options.size());
                Node next = options.get(opIndex);
                board.addDirectionedEdge(current, next);
                if(next.hasBeenVisited && next.getTreeNum() == next.getBaseNum()){
                    this.addTreeToBase(current);
                    loopErasedRandomWalk();
                    return;
                }
                current = next;
            }
        }
    }

    private void addTreeToBase(Node n) {
        Node current = n;
        while(current != null){
            current.addToBaseTree();
            current = current.predecessor;
        }
    }

    private void resetLoop(Node loopEnd, Node loopStart) {
        System.out.println("resetting loop at: " + loopEnd.row + " " + loopEnd.col);
        Node current = loopStart.successors.get(0);
        while(!current.equals(loopEnd)) {
            Node next = current.successors.get(0);
            current.unvisit();
            current = next;
        }
        loopEnd.unvisit();
    }

    private Node getRandomStartNode(){
            Node current = getRandomNode();
        while(current.hasBeenVisited){
            current = getRandomNode();
        }
        return current;
    }

    private Node getRandomNode(){
        int x = (int) (Math.random() * this.size);
        int y = (int) (Math.random() * this.size);
        return this.board.getNode(x,y);
    }
}
