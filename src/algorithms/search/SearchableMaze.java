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
            rightS.setCost(10 + S.getCost());
            validStates.add(rightS);
        }
        if (col -1 >= 0 && map[row][col - 1] == 0)
        {
            Position left = new Position(row , col-1);
            MazeState leftS = new MazeState(left);
            leftS.setCost(10 + S.getCost());
            validStates.add(leftS);
        }
        if (row+1 < numOfRows && map[row+1][col] == 0)
        {
            Position down = new Position(row+1 , col);
            MazeState downS = new MazeState(down);
            downS.setCost(10 + S.getCost());
            validStates.add(downS);
        }
        if (row-1 >=0 && map[row-1][col]==0)
        {
            Position up = new Position(row-1 , col);
            MazeState upS = new MazeState(up);
            upS.setCost(10 + S.getCost());
            validStates.add(upS);
        }
        if (row-1 >=0 && col+1<numOfCol && map[row-1][col+1]==0 && ((map[row-1][col]==0)||(map[row][col+1]==0)))
        {
            Position diagRightUP = new Position(row-1 , col+1);
            MazeState diagRightUPS = new MazeState(diagRightUP);
            diagRightUPS.setCost(15 + S.getCost());
            validStates.add(diagRightUPS);
        }
        if (row+1 < numOfRows && col+1<numOfCol && map[row+1][col+1]==0 && ((map[row][col+1]==0)||(map[row+1][col]==0)))
        {
            Position diagRightDown = new Position(row+1 , col+1);
            MazeState diagRightDownS = new MazeState(diagRightDown);
            diagRightDownS.setCost(15 + S.getCost());
            validStates.add(diagRightDownS);
        }
        if (row-1>=0 && col-1>=0 && map[row-1][col-1]==0 && ((map[row-1][col]==0)||(map[row][col-1]==0)))
        {
            Position diagLeftUp = new Position(row-1 , col-1);
            MazeState diagLeftUpS = new MazeState(diagLeftUp);
            diagLeftUpS.setCost(15 + S.getCost());
            validStates.add(diagLeftUpS);
        }
        if (row+1< numOfRows && col-1>=0 && map[row+1][col-1]==0 && ((map[row+1][col]==0)||(map[row][col-1]==0)))
        {
            Position diagLeftDown = new Position(row+1 , col-1);
            MazeState diagLeftDownS = new MazeState(diagLeftDown);
            diagLeftDownS.setCost(15 + S.getCost());
            validStates.add(diagLeftDownS);
        }
        return validStates;
    }
}
