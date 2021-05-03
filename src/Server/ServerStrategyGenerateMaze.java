package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            SimpleCompressorOutputStream toClient = new SimpleCompressorOutputStream(outToClient);
            int[] rowsCols = (int[])fromClient.readObject();
            int rows = rowsCols[0];
            int cols = rowsCols[1];
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(rows,cols);
            byte[] mazeInBytes = maze.toByteArray();
            toClient.write(mazeInBytes);
            toClient.flush();
            fromClient.close();
            toClient.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
