package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        //maze.print();
//        int[][] map = {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
//        Position start = new Position(0,0);
//        Position end = new Position(2,4);
//        Maze maze = new Maze(start,end,map);
//        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze , new BestFirstSearch());
    }
        private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher)
        {
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
