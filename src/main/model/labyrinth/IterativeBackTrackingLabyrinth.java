package main.model.labyrinth;

import main.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeBackTrackingLabyrinth extends Labyrinth {
    /*
     * An implementation of an recursive backtracking algorithm
     * using an explicit stack to make the approach iterative
     * to lift some of the size restrictions
     */

    public IterativeBackTrackingLabyrinth(int s) {
        super(s);
        //gen maze will be called before any code below this point
    }

    @Override
    public List<Node> getSolution(){
        if(this.solution == null) {
            Stack<Node> path = new Stack<>();
            Node curr = this.end;
            do {
                path.push(curr);
                curr = curr.predecessor;
            } while (curr.predecessor != null);
            solution = new ArrayList<>();
            while (!path.isEmpty()) {
                solution.add(path.pop());
            }
        }
        return solution;
    }

    @Override
    protected void genMaze() {
        Stack<Node> stack;
        stack = new Stack<Node>();
        stack.push(this.getNode(0, 0));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            current.visit(1);
            ArrayList<Node> options = this.getUnvisitedAdjacent(current);
            if (options.size() == 0 || current.equals(this.end)) {
                Node prev = current.predecessor;
                if(prev == null){
                    return;
                }
                stack.push(prev);
            } else {
                int opIndex = (int) (Math.random() * options.size());
                this.addDirectionedEdge(current, options.get(opIndex));
                stack.push(options.get(opIndex));
            }
        }
    }
}
