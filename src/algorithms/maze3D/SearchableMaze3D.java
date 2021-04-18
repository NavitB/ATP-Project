package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D  implements ISearchable {
    private Maze3D M;
    private Maze3DState startState;
    private Maze3DState goalState;

    /**
     * @param m naze 3D
     */
    public SearchableMaze3D(Maze3D m) {
        M = m;
        startState = new Maze3DState(M.getStartPosition());
        goalState= new Maze3DState(M.getGoalPosition());
    }

    /**
     * @return AState the represents the start state
     */
    @Override
    public AState getStartState() {
        return startState;
    }

    /**
     * @return AState the represents the goal state
     */
    @Override
    public AState getGoalState() {
        return goalState;
    }

    /**
     * @param S AState the represent the current position
     * @return array list of AState the symbol the all possible states
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState S) {
        int[][][] map = M.getMap();
        int col = ((Maze3DState)S).getState().getColumnIndex();
        int row = ((Maze3DState)S).getState().getRowIndex();
        int depth = ((Maze3DState)S).getState().getDepthIndex();
        ArrayList<AState> validStates = new ArrayList<>();
        int numOfDepth = map.length;
        int numOfRows = map[0].length;
        int numOfCol = map[0][0].length;
        this.getSide(depth,row ,col ,numOfRows ,numOfCol ,map, validStates);
        this.getDimension(depth,row ,col, numOfDepth,map, validStates);
        return validStates;
    }

    /**
     * @param depth depth index
     * @param row row index
     * @param col columns index
     * @param numOfRows num of rows
     * @param numOfCol num of columns
     * @param map 3D array of integers that represent the maze
     * @param validStates array list of AState that represent the valid states
     */
    private void getSide(int depth,int row, int col, int numOfRows, int numOfCol, int[][][] map, ArrayList<AState> validStates)
    {
        if (col+1 < numOfCol && map[depth][row][col+1] == 0)
        {
            Position3D right = new Position3D(depth,row, col+1);
            Maze3DState rightS = new Maze3DState(right);
            rightS.setCost(10);
            validStates.add(rightS);
        }
        if (col -1 >= 0 && map[depth][row][col - 1] == 0)
        {
            Position3D left = new Position3D(depth,row , col-1);
            Maze3DState leftS = new Maze3DState(left);
            leftS.setCost(10);
            validStates.add(leftS);
        }
        if (row+1 < numOfRows && map[depth][row+1][col] == 0)
        {
            Position3D down = new Position3D(depth,row+1 , col);
            Maze3DState downS = new Maze3DState(down);
            downS.setCost(10);
            validStates.add(downS);
        }
        if (row-1 >=0 && map[depth][row-1][col]==0)
        {
            Position3D up = new Position3D(depth,row-1 , col);
            Maze3DState upS = new Maze3DState(up);
            upS.setCost(10);
            validStates.add(upS);
        }
    }

    /**
     * @param depth index of depth
     * @param row index of row
     * @param col index of column
     * @param numOfDepth num of depth
     * @param map 3D array of integers that represnt the maze
     * @param validStates array list of AState that represent the valid states
     */
    private void getDimension(int depth,int row, int col,int numOfDepth, int[][][] map, ArrayList<AState> validStates)
    {
        if(depth+1 < numOfDepth && map[depth+1][row][col] == 0)
        {
            Position3D up = new Position3D(depth+1,row , col);
            Maze3DState upS = new Maze3DState(up);
            upS.setCost(10);
            validStates.add(upS);
        }
        if(depth-1 >=0 && map[depth-1][row][col] == 0)
        {
            Position3D down = new Position3D(depth-1,row , col);
            Maze3DState downS = new Maze3DState(down);
            downS.setCost(10);
            validStates.add(downS);
        }
    }
}
