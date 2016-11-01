package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EdgeTest {

    @Test
    public void createEdge(){
        int nodeId1 = 1;
        int nodeId2 = 2;
        int distance = 10;
        Edge edge = new Edge(nodeId1, nodeId2, distance);
        assertEquals("Wrong node id", nodeId1, edge.getNodeId1());
        assertEquals("Wrong node id", nodeId2, edge.getNodeId2());
        assertEquals("Wrong distance", distance, edge.getDistance());
    }
}
