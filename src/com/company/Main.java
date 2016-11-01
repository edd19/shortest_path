package com.company;

public class Main {

    public static void main(String[] args) {
	    String filePath = args[0];
        ShortestPathSolver solver = new ShortestPathSolver(filePath);
        solver.solve();
        String solution = solver.getSolution();
        System.out.println(solution);
    }
}
