package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) throws Exception {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(50, 20);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        maze.print();
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze , new BestFirstSearch());
    }
        private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception {
            Solution solution = searcher.solve(domain);
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getClass().getSimpleName(), searcher.getNumberOfVisitedNodes()));
            System.out.println("Solution path:");
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0 ; i<solutionPath.size();i++)
            {
                System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
            }

        }

}
