package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */
public class Node {
    private int id;
    private Node predecessor;

    public Node(int id){
        this.id = id;
        this.predecessor = null;
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
}
