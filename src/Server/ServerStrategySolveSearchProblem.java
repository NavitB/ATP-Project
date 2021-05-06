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
            System.out.println(tempDirectoryPath);
            int rows = maze.getMaze().length;
            int cols = maze.getMaze()[0].length;
            String folderName = rows + "*" + cols;
            int fileNameInt = Arrays.hashCode((maze.toByteArray()));
            String fileName = Integer.toString(fileNameInt);
            boolean checkDir = new File(tempDirectoryPath,folderName).exists();
            if(checkDir)
            {
                boolean checkFile = new File(tempDirectoryPath + "\\" + folderName, fileName).exists();
                if (checkFile)
                {
                    FileInputStream fileIn = new FileInputStream(tempDirectoryPath + "\\" + folderName+ "\\" + fileName);
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    solution = (Solution) objectIn.readObject();
                    objectIn.close();
                }
                else{
                    solution = createSolution( maze,tempDirectoryPath +"\\"+folderName, fileName);
                    String newFolder = tempDirectoryPath+ '\\' + folderName + '\\' + fileName;
                    FileOutputStream fileOut = new FileOutputStream(newFolder);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(solution);
                    objectOut.close();
                }
            }
            else
            {
                File newDir = new File(tempDirectoryPath ,folderName);
                boolean ifCreated = newDir.mkdir();
                if (ifCreated)
                {
                    solution = createSolution( maze, newDir.getPath(), fileName);
                    FileOutputStream fileOut = new FileOutputStream(newDir.getPath() + "\\" + fileName);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(solution);
                    objectOut.close();
                }
                else
                {
                    throw new IOException();
                }

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


    private Solution createSolution(Maze maze, String tempDirectoryPath, String fileName) throws Exception {
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
        return searcher.solve(searchableMaze);
    }
}
