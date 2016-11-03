package com.company;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ndizera on 2/11/2016.
 */
public class BranchBound {
    private int sourceNodeId;
    private int destinationNodeId;
    private Edge[] edges;
    private int capacity;
    private int upperBound;
    private Node solution;
    private HashMap<Integer, Node> nodes;

    public BranchBound(int sourceNodeId, int destinationNodeId, int capacity,  Edge[] edges) {
        this.sourceNodeId = sourceNodeId;
        this.destinationNodeId = destinationNodeId;
        this.edges = edges;
        //Arrays.sort(edges);
        this.capacity = capacity;
        this.upperBound = Integer.MAX_VALUE;
        Dijkstra dijkstra = new Dijkstra(destinationNodeId, sourceNodeId, edges);
        this.nodes = dijkstra.computeComplete();
    }

    public void compute(){
        //System.out.println("compute begins");
        Node source = initialization();
        compute(source);
    }

    private void compute(Node actual){
        for (Edge edge: edges){
            Node next = nextNode(actual, edge);
            if (next != null){
                if(next.getId() == this.destinationNodeId){
                    updateSolution(next);
                    //System.out.println("" + next.getProperDistance() + "    " + next.getCapacityUsed());
                }
                else if (passUpperBound(next))
                    compute(next);
            }
        }
    }

    private void updateSolution(Node node){
        if (passUpperBound(node)){
            solution = node;
            this.upperBound = node.getProperDistance();
        }
    }

    public boolean passUpperBound(Node node){
        if(node.getProperDistance() >= this.upperBound)
            return false;
        if(node.getCapacityUsed() > this.capacity)
            return false;
        if(node.getProperDistance() + nodes.get(node.getId()).getProperDistance() >= this.upperBound)
            return false;
        return true;
    }

    public static Node nextNode(Node actual, Edge edgeToVisit){
        if (!isInEdge(actual, edgeToVisit))
            return null;

        int nodeToVisitId = edgeToVisit.getNodeId1() == actual.getId() ? edgeToVisit.getNodeId2() : edgeToVisit.getNodeId1();

        if (alreadyPassedByNode(actual, nodeToVisitId))
            return null;
        return createNodeViaEdge(actual, edgeToVisit, nodeToVisitId);
    }

    public static Node createNodeViaEdge(Node actual, Edge edgeToVisit, int nodeToVisitId){
        Node nodeToVisit = new Node(nodeToVisitId);
        nodeToVisit.setPredecessor(actual);
        nodeToVisit.setDistanceToSource(actual.getDistanceToSource() + edgeToVisit.getWeight());
        nodeToVisit.setProperDistance(actual.getProperDistance() + edgeToVisit.getWeight());
        nodeToVisit.setCapacityUsed(actual.getCapacityUsed() + edgeToVisit.getResourceConsumption());

        return nodeToVisit;
    }


    private static boolean isInEdge(Node node, Edge edge){
        return (node.getId() == edge.getNodeId1() || node.getId() == edge.getNodeId2());
    }

    public static boolean alreadyPassedByNode(Node actual, int nodeToVisitId){
        Node predecessor = actual;
        while(predecessor != null){
            if (predecessor.getId() == nodeToVisitId)
                return true;
            predecessor = predecessor.getPredecessor();
        }
        return false;
    }

    public Node initialization(){
        Node source = new Node(sourceNodeId);
        source.setProperDistance(0);
        source.setCapacityUsed(0);
        source.setDistanceToSource(0);
        return source;
    }

    public Node getSolution(){
        return solution;
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

    public int getCapacity() {
        return capacity;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}
