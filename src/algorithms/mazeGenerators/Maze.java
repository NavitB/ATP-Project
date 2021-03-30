package algorithms.mazeGenerators;

public class Maze {

    private int numOfRows;
    private int numOfColumns;
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;


    public Maze(int numOfRows, int numOfColumns, int[][] map) {

        this.maze = map;
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public int[][] getMaze() {
        return maze;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }
}
