package main.model.labyrinth;

import main.model.Node;

import java.util.ArrayList;

public class RecursiveBackTrackingLabyrinth extends Labyrinth {

    // An implementation of a recursive backtracking algorithm
    // this algorithm has a size limit due to the recursive
    // nature of the generation function

    public RecursiveBackTrackingLabyrinth(int s) {
        super(s);
    }

    // row is y, col is x
    @Override
    protected void genMaze() {
        this.start = this.getNode(0, 0);
        drunkWalk(start, 1);
    }

    private void drunkWalk(Node n, int treeNum) {
        n.visit(treeNum);
        ArrayList<Node> options = this.getUnvisitedAdjacent(n);
        int opIndex = (int) (Math.random() * options.size());
        if (options.size() == 0) {
            Node prevNode = recursiveBackTrack(n);
            if (prevNode == null) {
                return;
            }
            ArrayList<Node> prevOptions = this.getUnvisitedAdjacent(prevNode);
            int prevOpIndex = (int) (Math.random() * prevOptions.size());
            this.addDirectionedEdge(prevNode, prevOptions.get(prevOpIndex));
            drunkWalk(prevNode, treeNum);
        } else {
            this.addDirectionedEdge(n, options.get(opIndex));
            drunkWalk(options.get(opIndex), treeNum);
        }
        return;
    }

    private Node recursiveBackTrack(Node n) {
        // find the node that came before this and check if it has any unvisited
        // adjacent
        // get all adjacent nodes to n, and see if they have a directioned edge pointing
        // to n
        Node predecessor = this.findPredecessor(n);
        ArrayList<Node> options = this.getUnvisitedAdjacent(predecessor);
        if (options.size() == 0) {
            if (predecessor.equals(this.start)) {
                // we have recursed to the beginning and there are no more options
                return null;
            }
            return recursiveBackTrack(predecessor);
        }
        return predecessor;
    }
}