package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            int fileNameInt = Arrays.hashCode((maze.toByteArray()));
            String fileName = Integer.toString(fileNameInt);
            //System.out.println(tempDirectoryPath+"\\"+fileName);
            boolean check = new File(tempDirectoryPath, fileName).exists();
            if (check)
            {
                FileInputStream fileIn = new FileInputStream(tempDirectoryPath+"\\"+fileName);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                solution = (Solution) objectIn.readObject();
                objectIn.close();
            }
            else{
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ISearchingAlgorithm searcher;
                String searchingAlgo = Configurations.getConf().getSearchingAlgo();

                if(searchingAlgo.equals("DepthFirstSearch"))
                {
                    searcher = new DepthFirstSearch();
                }
                else if(searchingAlgo.equals("BestFirstSearch"))
                {
                   searcher = new BestFirstSearch();
                }
                else
                {
                    searcher = new BreadthFirstSearch();
                }
                solution = searcher.solve(searchableMaze);
                FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath+"\\"+fileName);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(solution);
                objectOut.close();

            }
            toClient.writeObject(solution);
           // toClient.flush();
            fromClient.close();
            toClient.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
