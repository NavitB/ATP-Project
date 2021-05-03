package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.DepthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
            Solution solution = depthFirstSearch.solve(searchableMaze);
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
