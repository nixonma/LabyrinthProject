package main.model.labyrinth;

import main.model.Node;

import java.util.ArrayList;
import java.util.Stack;

public class IterativeBackTrackingLabyrinth extends Labyrinth {
    /*
     * An implementation of an recursive backtracking algorithm
     * using an explicit stack to make the approach iterative
     * in the hopes that it will lift some of the size
     * restrictions
     */

    Stack<Node> stack;

    public IterativeBackTrackingLabyrinth(int s) {
        super(s);
        //gen maze will be called before any code below this point
    }

    @Override
    protected void genMaze() {
        stack = new Stack<Node>();
        stack.push(board.getNode(0, 0));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            current.visit(1);
            ArrayList<Node> options = board.getUnvisitedAdjacent(current);
            int opIndex = (int) (Math.random() * options.size());
            if (options.size() == 0) {
                Node prev = current.predecessor;
                if(prev == null){
                    return;
                }
                stack.push(prev);
            } else {
                board.addDirectionedEdge(current, options.get(opIndex));
                stack.push(options.get(opIndex));
            }
        }
    }
}
