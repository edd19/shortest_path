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

    @Test
    public void computeDijkstra(){
        int sourceNodeId = 1;
        int destinationNodeId = 4;
        Edge[] edges = getEdgesSet1();
        Dijkstra dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);
        Node destination = dijkstra.compute();

        assertEquals("Wrong weight from source to destination", 4, destination.getDistanceToSource());

        sourceNodeId = 1;
        destinationNodeId = 10;
        edges = getEdgesSet2();
        dijkstra = new Dijkstra(sourceNodeId, destinationNodeId, edges);
        destination = dijkstra.compute();

        assertEquals("Wrong weight from source to destination", 487, destination.getDistanceToSource());
    }

    public Edge[] getEdgesSet1(){
        Edge edge1 = new Edge(1, 2, 2, 1);
        Edge edge2 = new Edge(1, 3, 3, 1);
        Edge edge3 = new Edge(2, 4, 2, 1);
        Edge edge4 = new Edge(3, 4, 2, 1);

        return new Edge[]{edge1, edge2, edge3, edge4};
    }

    public Edge[] getEdgesSet2(){
        Edge edge1 = new Edge(1, 2, 85, 1);
        Edge edge2 = new Edge(1, 3, 217, 1);
        Edge edge3 = new Edge(1, 5, 173, 1);
        Edge edge4 = new Edge(2, 6, 80, 1);
        Edge edge5 = new Edge(3, 7, 186, 1);
        Edge edge6 = new Edge(3, 8, 103, 1);
        Edge edge7 = new Edge(4, 8, 183, 1);
        Edge edge8 = new Edge(5, 10, 502, 1);
        Edge edge9 = new Edge(6, 9, 250, 1);
        Edge edge10 = new Edge(8, 10, 167, 1);
        Edge edge11 = new Edge(9, 10, 84, 1);

        return new Edge[]{edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11};
    }

}
