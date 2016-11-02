package com.company;

/**
 * Created by ndizera on 2/11/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class BranchBoundTest {

    @Test
    public void createBranchBound(){
        Edge[] edges = DataForTest.getEdgesSet1();
        int sourceNodeId = 1;
        int destinationNodeId = 4;
        int capacity = 10;
        BranchBound branchBound = new BranchBound(sourceNodeId, destinationNodeId, capacity, edges);

        assertEquals("Wrong source id", sourceNodeId, branchBound.getSourceNodeId());
        assertEquals("Wrong destination id", destinationNodeId, branchBound.getDestinationNodeId());
        assertEquals("Wrong capacoty", capacity, branchBound.getCapacity());
        assertArrayEquals("Wrong edges", edges, branchBound.getEdges());

        int upperBound = 100;
        branchBound.setUpperBound(upperBound);
        assertEquals("Wrong upper bound", upperBound, branchBound.getUpperBound());
    }

    @Test
    public void compute(){
        Edge[] edges = DataForTest.getEdgesSet1();
        int sourceNodeId = 1;
        int destinationNodeId = 4;
        int capacity = 10;
        BranchBound branchBound = new BranchBound(sourceNodeId, destinationNodeId, capacity, edges);

        branchBound.compute();
        Node destination = branchBound.getSolution();
        assertEquals("Wrong weight from source to destination", 4, destination.getProperDistance());

        sourceNodeId = 1;
        destinationNodeId = 10;
        edges = DataForTest.getEdgesSet2();
        branchBound = new BranchBound(sourceNodeId, destinationNodeId, capacity, edges);

        branchBound.compute();
        destination = branchBound.getSolution();

        assertEquals("Wrong weight from source to destination", 487, destination.getProperDistance());

    }

    @Test
    public void alreadyPassedByNode(){
        Node first = new Node(1);
        Node second = new Node(2);
        second.setPredecessor(first);
        Node third = new Node(3);
        third.setPredecessor(second);

        assertTrue("Is already visited", BranchBound.alreadyPassedByNode(third, 1));
        assertFalse("Not visited", BranchBound.alreadyPassedByNode(third, 4));
    }

    @Test
    public void nextNode(){
        Node first = new Node(1);
        Node second = new Node(2);
        second.setPredecessor(first);
        Node third = new Node(3);
        third.setPredecessor(second);

        Edge edge = new Edge(3, 4,2, 5);
        Node next = BranchBound.nextNode(third, edge);

        assertEquals("Should return node 4", 4, next.getId());

        edge = new Edge(3, 2,2, 5);
        next = BranchBound.nextNode(third, edge);
        assertNull("Should return null", next);

        edge = new Edge(4, 5,2, 5);
        next = BranchBound.nextNode(third, edge);
        assertNull("Should return null", next);

    }

}
