package main.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    Node[][] nodes;
    HashMap<Integer, ArrayList<Node>> treeNodesMap;

    public Graph(int size) {
        nodes = new Node[size][size];
        treeNodesMap = new HashMap<>();
    }

    public void addNode(Node n) {
        nodes[n.row][n.col] = n;
        if (n.treeNum < 0) {
            if (treeNodesMap.get(n.treeNum) != null) {
                treeNodesMap.get(n.treeNum).add(n);
            } else {
                ArrayList<Node> nodes = new ArrayList<>();
                nodes.add(n);
                treeNodesMap.put(n.treeNum, nodes);
            }
        }
    }

    public void addDirectionedEdge(Node from, Node to) {
        from.successors.add(to);
        to.predecessor = from;
    }

    public ArrayList<Node> getUnvisitedAdjacent(Node n) {
        ArrayList<Node> options = new ArrayList<>();
        if (n.row < nodes.length - 1 && !nodes[n.row + 1][n.col].hasBeenVisited) {
            options.add(nodes[n.row + 1][n.col]);
        }
        if (n.row > 0 && !nodes[n.row - 1][n.col].hasBeenVisited) {
            options.add(nodes[n.row - 1][n.col]);
        }
        if (n.col < nodes.length - 1 && !nodes[n.row][n.col + 1].hasBeenVisited) {
            options.add(nodes[n.row][n.col + 1]);
        }
        if (n.col > 0 && !nodes[n.row][n.col - 1].hasBeenVisited) {
            options.add(nodes[n.row][n.col - 1]);
        }
        return options;
    }

    public Node getNode(int r, int c) {
        return nodes[r][c];
    }

    public Node findPredecessor(Node n) {
        return n.predecessor;
    }
}

