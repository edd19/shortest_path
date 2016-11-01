package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Dijkstra {
    private int sourceNodeId;
    private int destinationNodeId;
    private Edge[] edges;

    public Dijkstra(int sourceNodeId, int destinationNodeId, Edge[] edges) {
        this.sourceNodeId = sourceNodeId;
        this.destinationNodeId = destinationNodeId;
        this.edges = edges;
    }

    public int getSourceNodeId() {
        return sourceNodeId;
    }

    public int getDestinationNodeId() {
        return destinationNodeId;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Node compute() {
        Solver solver = new Solver(sourceNodeId, destinationNodeId, edges, 0);
        solver.compute();
        return solver.destination;
    }

    public Node compute(int lamdba) {
        Solver solver = new Solver(sourceNodeId, destinationNodeId, edges, lamdba);
        solver.compute();
        return solver.destination;
    }

    private class Solver {
        private HashMap<Integer, Node> visited_nodes;
        private Edge[] edges;
        private int sourceNodeId;
        private int destinationNodeId;
        private Node destination;
        private Node source;
        private int lambda;

        public Solver(int sourceNodeId, int destinationNodeId, Edge[] edges, int lamdba) {
            this.edges = edges;
            this.sourceNodeId = sourceNodeId;
            this.destinationNodeId = destinationNodeId;
            this.destination = null;
            this.visited_nodes = new HashMap<>();
            this.lambda = lamdba;
        }

        public void compute(){
            initialization();

            boolean to_continue = true;

            while (to_continue){
                Node min = findClosestNode();
                if (min == null){
                    to_continue = false;
                }
                else if (min.getId() == destinationNodeId){
                    to_continue = false;
                    destination = min;
                }
                else{
                    visited_nodes.put(min.getId(), min);
                }
            }

        }

        public void initialization(){
            Node source = new Node(sourceNodeId);
            source.setDistanceToSource(0);
            visited_nodes.put(sourceNodeId, source);
        }

        public Node findClosestNode(){
            int minWeight = Integer.MAX_VALUE;
            int[] nodeVisited = new int[]{-1,-1};

            for(Edge edge : edges){
                int[] nodeId = direction(edge);
                if (nodeId[0] != -1){
                    int weight = getWeight(nodeId[0], edge);
                    if (weight < minWeight){
                        minWeight = weight;
                        nodeVisited = nodeId;
                    }
                }
            }

            if (nodeVisited[0] == -1)
                return null;

            Node n = new Node(nodeVisited[1]);
            n.setPredecessor(visited_nodes.get(nodeVisited[0]));
            n.setDistanceToSource(minWeight);

            return n;
        }

        public int[] direction(Edge edge){
            int nodeId1 = edge.getNodeId1();
            int nodeId2 = edge.getNodeId2();
            if(hasBeenVisited(nodeId1) && hasNotBeenVisited(nodeId2))
                return new int[]{nodeId1, nodeId2};
            else if (hasNotBeenVisited(nodeId1) && hasBeenVisited(nodeId2))
                return new int[]{nodeId2, nodeId1};
            else return new int[]{-1, -1};

        }

        public int getWeight(int nodeId, Edge edge){
            Node node = visited_nodes.get(nodeId);
            int weight = (node.getDistanceToSource() + edge.getWeight()) + (this.lambda * edge.getResourceConsumption());
            return weight;
        }

        public boolean hasBeenVisited(int nodeId){
            return visited_nodes.containsKey(nodeId);
        }

        public boolean hasNotBeenVisited(int nodeId){
            return !hasBeenVisited(nodeId);
        }
    }
}
