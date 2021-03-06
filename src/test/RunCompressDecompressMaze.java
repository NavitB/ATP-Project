package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {

    public static void main(String[] args) throws Exception {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(1000,1000);



        try{
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try
        {
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            //System.out.println(Arrays.toString(maze.toByteArray()));

            in.read(savedMazeBytes);
            in.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s", areMazesEquals));
    }
}
