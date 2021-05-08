package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] rowsCols = (int[])fromClient.readObject();
            int rows = rowsCols[0];
            int cols = rowsCols[1];
            IMazeGenerator mazeGenerator;
            String generatorAlgo = Configurations.getConf().getGeneratingAlgo();
            if(generatorAlgo.equals("MyMazeGenerator"))
            {
                mazeGenerator = new MyMazeGenerator();
            }
            else if(generatorAlgo.equals("SimpleMazeGenerator"))
            {
                mazeGenerator = new SimpleMazeGenerator();
            }
            else
            {
                mazeGenerator = new EmptyMazeGenerator();
            }
            Maze maze = mazeGenerator.generate(rows,cols);
            byte[] mazeInBytes = maze.toByteArray();
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            MyCompressorOutputStream out = new MyCompressorOutputStream(byteArrayOut);
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
