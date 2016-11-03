package com.company;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

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
        Arrays.sort(edges);
        this.capacity = capacity;
        this.upperBound = Integer.MAX_VALUE;
        Dijkstra dijkstra = new Dijkstra(destinationNodeId, sourceNodeId, edges);
        this.nodes = dijkstra.computeComplete();
    }

    public void compute(){
        Node source = initialization();
        long start = System.currentTimeMillis();
        compute(source, start);
    }

    private void compute(Node actual, long start){
        for (Edge edge: edges){
            Node next = nextNode(actual, edge);
            if (next != null){
                if(next.getId() == this.destinationNodeId){
                    updateSolution(next);
                }
                else if (passUpperBound(next))
                    compute(next, start);
            }
            long end = System.currentTimeMillis();
            if (end - start > 250000){
                break;
            }
        }
    }

    public void computeHazard(){
        Node source = initialization();
        long start = System.currentTimeMillis();
        HashMap<Integer, Integer> nodesToDiscard = hazard();
        computeHazard(source, start, nodesToDiscard);
    }

    public void computeHazard(Node actual, long start, HashMap<Integer, Integer> nodesToDiscard){
        for (Edge edge: edges){
            Node next = nextNode(actual, edge);
            if (next != null && !nodesToDiscard.containsKey(next.getId())){
                if(next.getId() == this.destinationNodeId){
                    updateSolution(next);
                }
                else if (passUpperBound(next))
                    computeHazard(next, start, nodesToDiscard);
            }
            long end = System.currentTimeMillis();
            if (end - start > 250000){
                break;
            }

        }
    }

    private void updateSolution(Node node){
        if (passUpperBound(node)){
            solution = node;
            this.upperBound = node.getProperDistance();
        }
    }

    private HashMap<Integer, Integer> hazard(){
        int n = 250;
        HashMap<Integer, Integer> nodesToDiscard = new HashMap<Integer, Integer>(this.nodes.size()-n);
        Random random = new Random();
        for(int i = 0; i > this.nodes.size()-n; i++){
            int randomNodeId = random.nextInt(this.nodes.size());
            nodesToDiscard.put(randomNodeId, randomNodeId);
        }
        if (nodesToDiscard.containsKey(sourceNodeId))
            nodesToDiscard.remove(nodesToDiscard);
        if (nodesToDiscard.containsKey(destinationNodeId))
            nodesToDiscard.remove(destinationNodeId);

        return nodesToDiscard;
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
