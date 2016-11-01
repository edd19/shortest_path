package com.company;

/**
 * Created by ndizera on 1/11/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;


public class ShortestPathSolverTest {

    @Test
    public void createShortestPathSolver(){
        String filePath = "./resources/instance1.txt";
        ShortestPathSolver solver = new ShortestPathSolver(filePath);

        int source = 0;
        int destination = 1;
        int capacity = 2;
        Edge[] edges = getEdgesSet1();
        assertEquals("Source node id is incorrect", source, solver.getSourceNodeId());
        assertEquals("Destination node id is incorrect", destination, solver.getDestinationNodeId());
        assertEquals("Capacity is incorrect", capacity, solver.getCapacity());
        assertArrayEquals("Incorret edges", edges, solver.getEdges());

        filePath = "./resources/instance2.txt";
        solver = new ShortestPathSolver(filePath);

        source = 190;
        destination = 255;
        capacity = 242;
        assertEquals("Source node id is incorrect", source, solver.getSourceNodeId());
        assertEquals("Destination node id is incorrect", destination, solver.getDestinationNodeId());
        assertEquals("Capacity is incorrect", capacity, solver.getCapacity());
    }

    @Test
    public void solve(){
        String filePath = "./resources/instance1.txt";
        ShortestPathSolver solver = new ShortestPathSolver(filePath);
        solver.solve();
        String solution = solver.getSolution();
        String expectedSolution = "3\n0 2 1";
        assertEquals("Wrong solution", expectedSolution, solution);
    }

    public Edge[] getEdgesSet1(){
        Edge edge1 = new Edge(0, 1, 1, 5);
        Edge edge2 = new Edge(0, 2, 2, 1);
        Edge edge3 = new Edge(2, 1, 1, 1);

        return new Edge[]{edge1, edge2, edge3};
    }


}
