package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Edge {
    private int nodeId1;
    private int nodeId2;
    private int weight;
    private int resourceConsumption;

    public Edge(int nodeId1, int nodeId2, int weight, int resourceConsumption) {
        this.nodeId1 = nodeId1;
        this.nodeId2 = nodeId2;
        this.weight = weight;
        this.resourceConsumption = resourceConsumption;
    }

    public int getNodeId1() {
        return nodeId1;
    }

    public int getNodeId2() {
        return nodeId2;
    }

    public int getWeight() {
        return weight;
    }

    public int getResourceConsumption() {
        return resourceConsumption;
    }

    @Override
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (o.getClass() != Edge.class)
            return false;

        Edge other = (Edge) o;
        if (this.weight != other.weight)
            return false;
        if (this.resourceConsumption != other.resourceConsumption)
            return false;

        if(this.nodeId1 == other.nodeId1){
            if (this.nodeId2 != other.nodeId2)
                return false;
        }
        else if(this.nodeId1 == other.nodeId2){
            if(this.nodeId2 != other.nodeId1)
                return false;
        }
        return true;
    }
}
