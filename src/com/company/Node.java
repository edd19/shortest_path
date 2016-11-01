package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Node {
    private int id;
    private Node predecessor;
    private double distanceToSource;
    private int capacityUsed;
    private int properDistance;

    public Node(int id){
        this.id = id;
        this.predecessor = null;
        this.distanceToSource = Integer.MAX_VALUE;
    }

    public int getId(){
        return this.id;
    }

    public void setPredecessor(Node predecessor){
        this.predecessor = predecessor;
    }

    public Node getPredecessor(){
        return this.predecessor;
    }

    public double getDistanceToSource() {
        return distanceToSource;
    }

    public void setDistanceToSource(double distanceToSource) {
        this.distanceToSource = distanceToSource;
    }

    public String toString(){
        return "Id: " + id + " - Distance: " + distanceToSource;
    }

    public int getCapacityUsed() {
        return capacityUsed;
    }

    public void setCapacityUsed(int capacityUsed) {
        this.capacityUsed = capacityUsed;
    }

    public int getProperDistance() {
        return properDistance;
    }

    public void setProperDistance(int properDistance) {
        this.properDistance = properDistance;
    }
}
