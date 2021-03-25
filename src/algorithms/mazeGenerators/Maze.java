package algorithms.mazeGenerators;

public class Maze {

    private int numOfRows;
    private int numOfColumns;
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;


    public Maze(int numOfRows, int numOfColumns) {

        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }


    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }
}
