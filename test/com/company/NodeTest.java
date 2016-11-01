package com.company;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NodeTest{

    @Test
    public void createNode(){
        Node node = new Node(1);
        assertEquals("Should return an id of 1", 1, node.getId());
    }

    @Test
    public void addPredecessor(){
        Node node = new Node(2);
        Node predecessor = new Node(1);
        node.setPredecessor(predecessor);
        assertEquals("Error when setting predecessor", predecessor, node.getPredecessor());
    }

    @Test
    public void addDistanceToSource(){
        Node node = new Node(2);
        int distance = 5;
        node.setDistanceToSource(distance);
        assertEquals("Error when setting distance to source", distance, node.getDistanceToSource());
    }

}