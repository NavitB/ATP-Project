package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            //SimpleCompressorOutputStream toClient = new SimpleCompressorOutputStream(outToClient);
            int[] rowsCols = (int[])fromClient.readObject();
            int rows = rowsCols[0];
            int cols = rowsCols[1];
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(rows,cols);
            byte[] mazeInBytes = maze.toByteArray();
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            SimpleCompressorOutputStream out = new SimpleCompressorOutputStream(byteArrayOut);
            out.write(mazeInBytes);
            out.flush();
            toClient.writeObject(byteArrayOut.toByteArray());
            fromClient.close();
            toClient.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
