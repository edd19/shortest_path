package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class DijkstraTest {

    @Test
    public void createDijkstra(){
        int sourceNodeId = 0;
        int destinationNodeId = 10;
        Edge[] edges = DataForTest.getEdgesSet1();
        Dijkstra dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);

        assertEquals("Wrong source node", sourceNodeId, dijkstra.getSourceNodeId());
        assertEquals("Wrong destination node", destinationNodeId, dijkstra.getDestinationNodeId());
        assertArrayEquals("Wrong edges", edges, dijkstra.getEdges());
    }

    @Test
    public void computeDijkstra(){
        int sourceNodeId = 1;
        int destinationNodeId = 4;
        Edge[] edges = DataForTest.getEdgesSet1();
        Dijkstra dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);
        Node destination = dijkstra.compute();

        assertEquals("Wrong weight from source to destination", 4, destination.getProperDistance());

        sourceNodeId = 1;
        destinationNodeId = 10;
        edges = DataForTest.getEdgesSet2();
        dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);
        destination = dijkstra.compute(0);

        assertEquals("Wrong weight from source to destination", 487, destination.getProperDistance());
    }



}
