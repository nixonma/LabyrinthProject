package main.model;

import java.util.*;

public class Graph {
    public Node[][] nodes;
    public int size;
    HashMap<Integer, ArrayList<Node>> treeNodesMap;
    public Set<Node> nodeSet;

    public Graph(int size) {
        nodes = new Node[size][size];
        this.size = size;
        treeNodesMap = new HashMap<>();
        nodeSet = new HashSet<>();
    }

    public void addNode(Node n) {
        nodeSet.add(n);
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

    public ArrayList<Node> getUnrelatedAdjacent(Node n) {
        ArrayList<Node> options = new ArrayList<>();
        if (n.row < nodes.length - 1 && nodes[n.row + 1][n.col].getTreeNum() != n.getTreeNum()) {
            options.add(nodes[n.row + 1][n.col]);
        }
        if (n.row > 0 && nodes[n.row - 1][n.col].getTreeNum() != n.getTreeNum()) {
            options.add(nodes[n.row - 1][n.col]);
        }
        if (n.col < nodes.length - 1 && nodes[n.row][n.col + 1].getTreeNum() != n.getTreeNum()) {
            options.add(nodes[n.row][n.col + 1]);
        }
        if (n.col > 0 && nodes[n.row][n.col - 1].getTreeNum() != n.getTreeNum()) {
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

    public ArrayList<Node> getAdjacent(Node n) {
        ArrayList<Node> options = new ArrayList<>();
        if (n.row < nodes.length - 1) {
            options.add(nodes[n.row + 1][n.col]);
        }
        if (n.row > 0) {
            options.add(nodes[n.row - 1][n.col]);
        }
        if (n.col < nodes.length - 1) {
            options.add(nodes[n.row][n.col + 1]);
        }
        if (n.col > 0) {
            options.add(nodes[n.row][n.col - 1]);
        }
        return options;
    }

    public boolean hasEdge(Node from, Node to) {
        return from.isConnected(to);
    }

    public List<Node> shortestPath(Node startNode, Node endNode) {
        // pulled this case out because it's O(1)
        if (this.hasEdge(startNode, endNode)) {
            ArrayList<Node> list = new ArrayList<>();
            list.add(startNode);
            list.add(endNode);
            return list;
        }
        boolean hasChanged = true;
        HashMap<Node, Integer> nodeToPath = new HashMap<Node, Integer>();
        // Initialize path values as -1 (no connections)
        for (Node n : this.nodeSet) {
            if (n.equals(startNode) || n.equals(endNode)) {
                nodeToPath.put(n, 0);
            } else
                nodeToPath.put(n, -1);
        }
        Set<Node> nodesToPathCheck = new HashSet<Node>();
        for (Node n : this.nodeSet) {
            nodesToPathCheck.add(n);
        }
        // for each node in the key set ask if it is connected to the end node, if so
        // change its value to 1.
        // if the current node is connected to a node that is connected to the end, map
        // it to 2
        // continue this process until the start node has a value != -1
        // or an entire iteration of the graph has not changed any node path values
        while (hasChanged) {
            hasChanged = false;
            ArrayList<Node> toRemove = new ArrayList<Node>();
            for (Node n : nodesToPathCheck) {
                // check if currentVertex is connected to the goal vertex
                if (hasEdge(n, endNode)) {
                    hasChanged = true;
                    nodeToPath.put(n, 1);
                    // if a node is directly connected to the end value,
                    // then that is the shortest path between the two nodes and thus the path length
                    // between the two will not change
                    toRemove.add(n);
                } else {
                    // find successor with shortest path(if any) to the end node
                    int shortest = 0;
                    for (Node successorNode : n.getSuccessors()) {
                        if (nodeToPath.get(successorNode) > 0
                                && (nodeToPath.get(successorNode) < shortest || shortest == 0)) {
                            shortest = nodeToPath.get(successorNode);
                        }
                    }
                    if (nodeToPath.get(n) == shortest + 1) {
                        // nothing has changed, node already path calculated
                        toRemove.add(n);
                    } else if (shortest != 0) {
                        hasChanged = true;
                        nodeToPath.put(n, shortest + 1);
                    }
                }

            }
            for (Node n : toRemove) {
                nodesToPathCheck.remove(n);
            }
        }
        // create a list of the nodes on the path that starts at startLabel
        // and follows the smallest number on the keyToPath map, and finishes at
        // endLabel
        ArrayList<Node> path = new ArrayList<Node>();
        Node n = startNode;
        path.add(n);
        while (!n.equals(endNode)) {
            // find successor with shortest path to the end node
            int shortest = 0;
            Node shortestKey = null;
            for (Node successorKey : n.getSuccessors()) {
                if (nodeToPath.get(successorKey) > 0 && (nodeToPath.get(successorKey) < shortest || shortest == 0)) {
                    shortest = nodeToPath.get(successorKey);
                    shortestKey = successorKey;
                }
            }
            if (shortestKey == null) {
                // no path found from this node (v)
                return null;
            }
            path.add(shortestKey);
            n = shortestKey;
            if (nodeToPath.get(n) == 1) {
                path.add(endNode);
                break;
            }
        }
        return path;
    }
}

