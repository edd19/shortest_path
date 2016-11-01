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
        Edge[] edges = getEdgesSet1();
        Dijkstra dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);

        assertEquals("Wrong source node", sourceNodeId, dijkstra.getSourceNodeId());
        assertEquals("Wrong destination node", destinationNodeId, dijkstra.getDestinationNodeId());
        assertArrayEquals("Wrong edges", edges, dijkstra.getEdges());
    }

    public Edge[] getEdgesSet1(){
        Edge edge1 = new Edge(1, 2, 2, 1);
        Edge edge2 = new Edge(1, 3, 3, 1);
        Edge edge3 = new Edge(2, 4, 2, 1);
        Edge edge4 = new Edge(3, 4, 2, 1);

        return new Edge[]{edge1, edge2, edge3, edge4};
    }

}
