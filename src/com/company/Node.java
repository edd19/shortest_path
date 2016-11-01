package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Node {
    private int id;
    private Node predecessor;
    private int distanceToSource;

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

    public int getDistanceToSource() {
        return distanceToSource;
    }

    public void setDistanceToSource(int distanceToSource) {
        this.distanceToSource = distanceToSource;
    }

    public String toString(){
        return "Id: " + id + " - Distance: " + distanceToSource;
    }
}
