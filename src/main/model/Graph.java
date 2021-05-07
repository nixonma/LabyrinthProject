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
        nodes[n.getR()][n.getC()] = n;
        if (n.getTreeNum() < 0) {
            if (treeNodesMap.get(n.getTreeNum()) != null) {
                treeNodesMap.get(n.getTreeNum()).add(n);
            } else {
                ArrayList<Node> nodes = new ArrayList<>();
                nodes.add(n);
                treeNodesMap.put(n.getTreeNum(), nodes);
            }
        }
    }

    public void addDirectionedEdge(Node from, Node to) {
        from.successors.add(to);
        to.predecessor = from;
    }

    public ArrayList<Node> getUnvisitedAdjacent(Node n) {
        ArrayList<Node> options = new ArrayList<>();
        if (n.getR() < nodes.length - 1) {
            if (!nodes[n.getR() + 1][n.getC()].getVisited()) {
                options.add(nodes[n.getR() + 1][n.getC()]);
            }
        }
        if (n.getR() > 0) {
            if (!nodes[n.getR() - 1][n.getC()].getVisited()) {
                options.add(nodes[n.getR() - 1][n.getC()]);
            }
        }
        if (n.getC() < nodes.length - 1) {
            if (!nodes[n.getR()][n.getC() + 1].getVisited()) {
                options.add(nodes[n.getR()][n.getC() + 1]);
            }
        }
        if (n.getC() > 0) {
            if (!nodes[n.getR()][n.getC() - 1].getVisited()) {
                options.add(nodes[n.getR()][n.getC() - 1]);
            }
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

