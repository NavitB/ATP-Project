package test;

import algorithms.mazeGenerators.*;

public class RunMazeGenerator {
    public static void main(String[] args) throws Exception {
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());

    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) throws Exception {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100,100)));
        Maze maze = mazeGenerator.generate(100,100);
        maze.print();
        Position startPosition = maze.getStartPosition();
        System.out.println(String.format("Start Position: %s", startPosition));
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
