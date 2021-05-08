package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    static private AtomicInteger count = new AtomicInteger(0);
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            int rows = maze.getMaze().length;
            int cols = maze.getMaze()[0].length;
            String folderName = rows + "_" + cols;
            byte[] mazeInByte = maze.toByteArray();
            String fileNameString = Arrays.toString(mazeInByte);
            int fileNameIntString = fileNameString.hashCode();
            String fileNameToPath = Integer.toString(fileNameIntString);
            boolean checkDir = new File(tempDirectoryPath,folderName).exists();
            //check if size folder is exist
            if(checkDir)
            {
                boolean checkFile = new File(tempDirectoryPath + "\\" + folderName + "\\PathsToSol\\"+ fileNameToPath).exists();
                if (checkFile)
                {
                    //reading solution from existing file
                    FileInputStream fileIn = new FileInputStream(tempDirectoryPath + folderName+ "\\PathsToSol\\" + fileNameToPath);
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    String pathToSol = (String)objectIn.readObject();
                    FileInputStream fileInSol = new FileInputStream(pathToSol);
                    ObjectInputStream objectInSol = new ObjectInputStream(fileInSol);
                    solution = (Solution)objectInSol.readObject();
                    objectIn.close();
                    objectInSol.close();
                }
                else {
                    solution = getSolutionPath(maze, tempDirectoryPath, folderName, fileNameToPath);
                }
            }
            else
            {
                //creating folder in name of size
                boolean ifCreated = new File(tempDirectoryPath+"\\"+folderName).mkdir();
                if (ifCreated)
                {
                    boolean ifCreatedSolutions = new File(tempDirectoryPath+"\\"+folderName +"\\"+ "Solutions").mkdir();
                    boolean ifCreatedPaths = new File(tempDirectoryPath+"\\"+folderName +"\\"+ "PathsToSol").mkdir();
                    if(ifCreatedSolutions && ifCreatedPaths)
                    {
                        solution = getSolutionPath(maze, tempDirectoryPath, folderName, fileNameToPath);
                    }
                    else
                    {
                        throw new IOException();
                    }
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

    private Solution getSolutionPath(Maze maze, String tempDirectoryPath, String folderName, String fileNameToPath) throws Exception {
        Solution solution;
        String solPathFile = tempDirectoryPath+folderName +"\\"+ "PathsToSol"+ "\\" + fileNameToPath;
        int solCounter = count.getAndIncrement();
        String solPath = tempDirectoryPath+ folderName +"\\"+ "Solutions" + "\\" +fileNameToPath+ "Sol_" + solCounter;
        FileOutputStream fileOut = new FileOutputStream(solPathFile);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(solPath);
        solution = createSolution( maze, tempDirectoryPath,folderName + "\\Solutions", fileNameToPath+"Sol_" + solCounter);
        return solution;
    }


    private Solution createSolution(Maze maze, String tempDirectoryPath,String folderName ,String fileName) throws Exception {
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
        Solution solution = searcher.solve(searchableMaze);
        String newDir = tempDirectoryPath+ "\\" + folderName + "\\" + fileName;
        FileOutputStream fileOut = new FileOutputStream(newDir);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(solution);
        objectOut.close();
        return solution;
    }

}
