package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ndizera on 1/11/2016.
 */
public class ShortestPathSolver {
    private int sourceNodeId;
    private int destinationNodeId;
    private int capacity;
    private int numberNodes;
    private int numberEdges;
    private Edge[] edges;

    public ShortestPathSolver(String filepath){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            parseHeader(reader.readLine());
            skipNodesLocations(reader);
            extractEdges(reader);
        }catch (IOException e){
            System.err.println("Error with reading the file: " + filepath);
        }
    }

    private void parseHeader(String header){
        String[] parameters = header.split(" ");
        this.numberNodes = Integer.parseInt(parameters[0]);
        this.numberEdges = Integer.parseInt(parameters[1]);
        this.sourceNodeId = Integer.parseInt(parameters[2]);
        this.destinationNodeId = Integer.parseInt(parameters[3]);
        this.capacity = Integer.parseInt(parameters[4]);
    }

    private void skipNodesLocations(BufferedReader reader) throws IOException{
        for(int i = 0; i < numberNodes; i++){
            reader.readLine();
        }
    }

    private void extractEdges(BufferedReader reader) throws IOException{
        edges = new Edge[numberEdges];
       for(int i = 0; i < numberEdges; i++){
            String line = reader.readLine();
            Edge edge = parseEdge(line);
            this.edges[i] = edge;
        }
    }

    private Edge parseEdge(String line){
        String[] edgeInfo = line.split(" ");
        int nodeId1 = Integer.parseInt(edgeInfo[0]);
        int nodeId2 = Integer.parseInt(edgeInfo[1]);
        int weight = Integer.parseInt(edgeInfo[2]);
        int resourceConsumption = Integer.parseInt(edgeInfo[3]);
        Edge edge = new Edge(nodeId1, nodeId2, weight, resourceConsumption);
        return edge;
    }

    public int getSourceNodeId() {
        return sourceNodeId;
    }

    public int getDestinationNodeId() {
        return destinationNodeId;
    }

    public int getCapacity() {
        return capacity;
    }

    public Edge[] getEdges() {
        return edges;
    }
}
