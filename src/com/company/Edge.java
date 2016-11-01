package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Edge {
    private int nodeId1;
    private int nodeId2;
    private int distance;

    public Edge(int nodeId1, int nodeId2, int distance) {
        this.nodeId1 = nodeId1;
        this.nodeId2 = nodeId2;
        this.distance = distance;
    }

    public int getNodeId1() {
        return nodeId1;
    }

    public int getNodeId2() {
        return nodeId2;
    }

    public int getDistance() {
        return distance;
    }
}
