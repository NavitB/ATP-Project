package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    @Test
    void insertToQueue() {


    }
    @Test
    void solve(){
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
        Solution s = new Solution(solutionPath);
        assertEquals(s.getSolutionPath(),searchableMaze);
    }
}