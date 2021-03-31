package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze M;
    private MazeState startState;
    private MazeState goalState;

    public SearchableMaze(Maze m) {
        M = m;
        startState = new MazeState(M.getStartPosition());
        goalState= new MazeState(M.getGoalPosition());
    }

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState S) {
        int[][] map = M.getMaze();
        int col = ((Position)S.getState()).getColumnIndex();
        int row = ((Position)S.getState()).getRowIndex();
        ArrayList<AState> validStates = new ArrayList<AState>();
        int numOfRows = map.length;
        int numOfCol = map[0].length;
        if (col+1 < numOfCol && map[row][col+1] == 0)
        {
            Position right = new Position(row , col+1);
            MazeState rightS = new MazeState(right);
            validStates.add(rightS);
        }
        if (col -1 >= 0 && map[row][col - 1] == 0)
        {
            Position left = new Position(row , col-1);
            MazeState leftS = new MazeState(left);
            validStates.add(leftS);
        }
        if (row+1 < numOfRows && map[row+1][col] == 0 )
        {
            Position down = new Position(row+1 , col);
            MazeState downS = new MazeState(down);
            validStates.add(downS);
        }
        if (row-1 >=0 && map[row-1][col]==0)
        {
            Position up = new Position(row-1 , col);
            MazeState upS = new MazeState(up);
            validStates.add(upS);
        }
        return validStates;
    }
}
