package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze M;
    private MazeState startState;
    private MazeState goalState;

    /**
     * Searchable Maze constructor
     * @param m the maze
     */
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

    /**
     * @param S a state in the searchable maze
     * @return a list with all the successors (neighbors) of this state
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState S) {
        int[][] map = M.getMaze();
        int col = ((MazeState)S).getState().getColumnIndex();
        int row = ((MazeState)S).getState().getRowIndex();
        ArrayList<AState> validStates = new ArrayList<>();
        int numOfRows = map.length;
        int numOfCol = map[0].length;
        this.getSide(row, col, numOfRows , numOfCol,map,validStates); //add all side successors
        this.getDiag(row, col, numOfRows , numOfCol,map,validStates); //add all diagonal successors
        return validStates;
    }


    /**
     * function to get all diagonal successors of a specific state
     * @param row the row of the state
     * @param col the column of the state
     * @param numOfRows maze's num of rows
     * @param numOfCol maze's num of columns
     * @param map the map of the maze
     * @param validStates a list to add all the successors
     */
    private void getDiag(int row, int col, int numOfRows, int numOfCol, int[][] map, ArrayList<AState> validStates) {

        if (row - 1 >= 0 && col + 1 < numOfCol && map[row - 1][col + 1] == 0 && ((map[row - 1][col] == 0) || (map[row][col + 1] == 0))) {
            Position diagRightUP = new Position(row - 1, col + 1);
            MazeState diagRightUPS = new MazeState(diagRightUP);
            diagRightUPS.setCost(15);
            validStates.add(diagRightUPS);
        }
        if (row + 1 < numOfRows && col + 1 < numOfCol && map[row + 1][col + 1] == 0 && ((map[row][col + 1] == 0) || (map[row + 1][col] == 0))) {
            Position diagRightDown = new Position(row + 1, col + 1);
            MazeState diagRightDownS = new MazeState(diagRightDown);
            diagRightDownS.setCost(15);
            validStates.add(diagRightDownS);
        }
        if (row - 1 >= 0 && col - 1 >= 0 && map[row - 1][col - 1] == 0 && ((map[row - 1][col] == 0) || (map[row][col - 1] == 0))) {
            Position diagLeftUp = new Position(row - 1, col - 1);
            MazeState diagLeftUpS = new MazeState(diagLeftUp);
            diagLeftUpS.setCost(15);
            validStates.add(diagLeftUpS);
        }
        if (row + 1 < numOfRows && col - 1 >= 0 && map[row + 1][col - 1] == 0 && ((map[row + 1][col] == 0) || (map[row][col - 1] == 0))) {
            Position diagLeftDown = new Position(row + 1, col - 1);
            MazeState diagLeftDownS = new MazeState(diagLeftDown);
            diagLeftDownS.setCost(15);
            validStates.add(diagLeftDownS);
        }
    }

    /**
     * function to get all side(up,down,left,right) successors of a specific state
     * @param row the row of the state
     * @param col the column of the state
     * @param numOfRows maze's num of rows
     * @param numOfCol maze's num of columns
     * @param map the map of the maze
     * @param validStates a list to add all the successors
     */
        private void getSide(int row, int col, int numOfRows, int numOfCol, int[][] map, ArrayList<AState> validStates)
        {
            if (col+1 < numOfCol && map[row][col+1] == 0)
            {
                Position right = new Position(row , col+1);
                MazeState rightS = new MazeState(right);
                rightS.setCost(10);
                validStates.add(rightS);
            }
            if (col -1 >= 0 && map[row][col - 1] == 0)
            {
                Position left = new Position(row , col-1);
                MazeState leftS = new MazeState(left);
                leftS.setCost(10);
                validStates.add(leftS);
            }
            if (row+1 < numOfRows && map[row+1][col] == 0)
            {
                Position down = new Position(row+1 , col);
                MazeState downS = new MazeState(down);
                downS.setCost(10);
                validStates.add(downS);
            }
            if (row-1 >=0 && map[row-1][col]==0)
            {
                Position up = new Position(row-1 , col);
                MazeState upS = new MazeState(up);
                upS.setCost(10);
                validStates.add(upS);
            }
        }
    }

