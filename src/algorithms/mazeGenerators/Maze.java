package algorithms.mazeGenerators;

public class Maze {


    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;


    public Maze(Position start, Position end, int[][] map) {

        this.maze = map;

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
