package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void testPath() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        int[][] map = {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
        Position start = new Position(0,0);
        Position end = new Position(2,4);
        Maze maze = new Maze(start,end,map);
        ISearchable searchableMaze = new SearchableMaze(maze);
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,1);
        Position p3 = new Position(0,2);
        Position p4 = new Position(0,3);
        Position p5 = new Position(1,4);
        Position p6 = new Position(2,4);
        AState s1 = new MazeState(p1);
        AState s2 = new MazeState(p2);
        AState s3 = new MazeState(p3);
        AState s4 = new MazeState(p4);
        AState s5 = new MazeState(p5);
        AState s6 = new MazeState(p6);
        ArrayList<AState> solutionPath = new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5,s6));
        Solution goodPath = new Solution(solutionPath);
        Solution sol = bestFirstSearch.solve(searchableMaze);
        assertEquals(goodPath.getSolutionPath(),sol.getSolutionPath());
    }

    @Test
    void TimeCheckMazeSearch() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        long startT = System.currentTimeMillis();
        bestFirstSearch.solve(searchableMaze);
        long endT = System.currentTimeMillis();
        long time = endT-startT;
        assertTrue(time <= 60000);
    }
    @Test
    void TimeCheck3DMazeSearch() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100, 100,100);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        long startT = System.currentTimeMillis();
        bestFirstSearch.solve(searchableMaze);
        long endT = System.currentTimeMillis();
        long time = endT-startT;
        assertTrue(time <= 60000);
    }

    @Test
    void checkDiffBFSBest() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        int[][] map = {{ 1, 0, 1, 0 ,0 ,0, 0, 0 ,0 ,0, 1, 0 ,0 ,0, 1 ,0 ,0, 0, 0 ,0 ,0, 0, 0 ,0 ,0, 0, 0, 0 ,1, },
                { 1, 0 ,1, 0, 1, 1 ,1, 0 ,1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, },
                { 1, 0, 1, 0, 1, 0, 1 ,0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, },
                { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, },
                { 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1 ,1 ,1 ,1 ,1 },
                { 1 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 0, 0 ,1 ,0 ,0 ,0 ,1, 0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 }
                ,{ 1, 0, 1, 0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, },
                { 1, 0, 1, 0, 1, 0, 1, 0 ,1 ,0, 0, 0, 1, 0, 0, 0, 1, 0, 1 ,0 ,1 ,0, 0, 0, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1, 1 ,0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,1 },
                { 1 ,0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 0, 0, 1 },
                { 1, 0, 1 ,1 ,1 ,1 ,1, 1, 1, 0, 1 ,0 ,1 ,0 ,1 ,1, 1 ,1 ,1, 1, 1 ,1 ,1, 0,1, 1, 1, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,0, 1, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 1, 0, 0, 0, 1, 0 ,1 ,0 ,1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1, 1, 0, 1, 0, 1, 0 ,1 ,0 ,1 },
                { 1, 0, 0, 0, 1, 0 ,0 ,0 ,0 ,0 ,1 ,0, 0, 0, 0, 0, 1, 0, 1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0, 1 },
                { 1 ,1 ,1 ,0, 1, 0 ,1, 1 ,1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1, 0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 },
                { 1, 0 ,0 ,0 ,1 ,0, 1, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 1 ,0 ,0 ,0 ,1 ,0 ,0 },
                { 1, 0, 1 ,1 ,1 ,0 ,1 ,1 ,1, 1, 1, 1 ,1 ,1 ,1 ,0, 1, 0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 },
                { 1, 0, 1 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1 },
                { 1, 0 ,1 ,0, 1 ,0 ,1, 0, 1, 0 ,1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1, 0, 1, 0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 },
                { 1 ,0 ,1 ,0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1 },
                { 1 ,0 ,1, 1, 1 ,0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 },
                { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0 ,0 ,0 ,1, 0, 1, 0 ,1 ,0 ,0 ,0 ,1 ,0 ,1 ,0, 0 ,0 ,1 },
                { 1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,1, 1, 0, 1, 0 ,1 ,0, 1 ,0 ,1 ,0, 1, 0, 1 ,1 ,1 ,0 ,1 },
                { 1 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 1 ,0 ,0 ,0 ,0 ,0, 0, 0, 1 }};
        Position start = new Position(0,13);
        Position end = new Position(18,28);
        Maze maze = new Maze(start,end,map);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solBFS = bfs.solve(searchableMaze);
        Solution solBest = bestFirstSearch.solve(searchableMaze);
        assertNotEquals(solBest.getSolutionPath(), solBFS.getSolutionPath());
    }

    @Test
    void checkIfBestCheaper() throws Exception {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        for(int i=0; i<10; i++)
        {
            IMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(500, 500);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            Solution solBFS = bfs.solve(searchableMaze);
            Solution solBest = bestFirstSearch.solve(searchableMaze);
            AState goalBFS = solBFS.getSolutionPath().get(solBFS.getSolutionPath().size()-1);
            AState goalBest = solBest.getSolutionPath().get(solBest.getSolutionPath().size()-1);
            assertTrue(goalBFS.getCost() >= goalBest.getCost());
        }
    }
}