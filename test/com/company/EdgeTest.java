package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Test;

public class EdgeTest {

    @Test
    public void createEdge(){
        int nodeId1 = 1;
        int nodeId2 = 2;
        int weight = 10;
        int resourceConsumption = 5;
        Edge edge = new Edge(nodeId1, nodeId2, weight, resourceConsumption);
        assertEquals("Wrong node id", nodeId1, edge.getNodeId1());
        assertEquals("Wrong node id", nodeId2, edge.getNodeId2());
        assertEquals("Wrong weight", weight, edge.getWeight());
        assertEquals("Wrong resource consumption", resourceConsumption, edge.getResourceConsumption());
    }

    @Test
    public void equalEdge(){
        int nodeId1 = 1;
        int nodeId2 = 2;
        int nodeId3 = 4;
        int weight = 10;
        int resourceConsumption = 5;
        Edge edge1 = new Edge(nodeId1, nodeId2, weight, resourceConsumption);
        Edge edge2 = new Edge(nodeId1, nodeId2, weight, resourceConsumption);

        assertEquals("Should be equals", edge1, edge2);

        Edge edge3 = new Edge(nodeId1, nodeId3, weight, resourceConsumption);
        assertNotSame("Should not be equals", edge1, edge3);
    }
}
