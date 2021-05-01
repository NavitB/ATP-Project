package test;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class RunMazeGenerator {
    public static void main(String[] args) throws Exception {
//        testMazeGenerator(new EmptyMazeGenerator());
//        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());

    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) throws Exception {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100,100)));
        Maze maze = mazeGenerator.generate(300,300);
        maze.print();
        Position startPosition = maze.getStartPosition();
        System.out.println(String.format("Start Position: %s", startPosition));
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
        System.out.println("the byte array is: " + Arrays.toString(maze.toByteArray()));
        //OutputStream output = new Out
        //SimpleCompressorOutputStream Stream = new SimpleCompressorOutputStream();
    }

}
