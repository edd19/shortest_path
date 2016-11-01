package com.company;

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
}
