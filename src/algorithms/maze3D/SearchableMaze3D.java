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

    public SearchableMaze3D(Maze3D m) {
        M = m;
        startState = new Maze3DState(M.getStartPosition());
        goalState= new Maze3DState(M.getGoalPosition());
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
        int[][][] map = M.getMap();
        int col = ((Position3D)S.getState()).getColumnIndex();
        int row = ((Position3D)S.getState()).getRowIndex();
        int depth = ((Position3D)S.getState()).getDepthIndex();
        ArrayList<AState> validStates = new ArrayList<>();
        int numOfDepth = map.length;
        int numOfRows = map[0].length;
        int numOfCol = map[0][0].length;
        this.getSide(S,depth,row ,col, numOfDepth ,numOfRows ,numOfCol ,map, validStates);
        this.getDimension(S,depth,row ,col, numOfDepth ,numOfRows ,numOfCol ,map, validStates);
        return validStates;
    }

    private void getSide(AState S,int depth,int row, int col,int numOfDepth, int numOfRows, int numOfCol, int[][][] map, ArrayList<AState> validStates)
    {
        if (col+1 < numOfCol && map[depth][row][col+1] == 0)
        {
            Position3D right = new Position3D(depth,row, col+1);
            Maze3DState rightS = new Maze3DState(right);
            rightS.setCost(10 + S.getCost());
            validStates.add(rightS);
        }
        if (col -1 >= 0 && map[depth][row][col - 1] == 0)
        {
            Position3D left = new Position3D(depth,row , col-1);
            Maze3DState leftS = new Maze3DState(left);
            leftS.setCost(10 + S.getCost());
            validStates.add(leftS);
        }
        if (row+1 < numOfRows && map[depth][row+1][col] == 0)
        {
            Position3D down = new Position3D(depth,row+1 , col);
            Maze3DState downS = new Maze3DState(down);
            downS.setCost(10 + S.getCost());
            validStates.add(downS);
        }
        if (row-1 >=0 && map[depth][row-1][col]==0)
        {
            Position3D up = new Position3D(depth,row-1 , col);
            Maze3DState upS = new Maze3DState(up);
            upS.setCost(10 + S.getCost());
            validStates.add(upS);
        }
    }

    private void getDimension(AState S,int depth,int row, int col,int numOfDepth, int numOfRows, int numOfCol, int[][][] map, ArrayList<AState> validStates)
    {
        if(depth+1 < numOfDepth && map[depth+1][row][col] == 0)
        {
            Position3D up = new Position3D(depth+1,row , col);
            Maze3DState upS = new Maze3DState(up);
            upS.setCost(10 + S.getCost());
            validStates.add(upS);
        }
        if(depth-1 >=0 && map[depth-1][row][col] == 0)
        {
            Position3D down = new Position3D(depth-1,row , col);
            Maze3DState downS = new Maze3DState(down);
            downS.setCost(10 + S.getCost());
            validStates.add(downS);
        }
    }
}
