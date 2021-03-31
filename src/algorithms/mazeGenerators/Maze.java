package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {


    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;


    public Maze(Position start, Position end, int[][] map) {

        this.startPosition = start;
        this.goalPosition = end;
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


    public void print()
    {
        int[][] map = this.getMaze();
        String myMaze = "";
        for (int i = 0 ; i < map.length ; i++)
        {
            myMaze+= "{";
            for (int j = 0 ; j < map[0].length ; j++)
            {
                if (i == this.getStartPosition().getRowIndex() && j == this.getStartPosition().getColumnIndex())
                    myMaze+=" S";
                else if (i == this.getGoalPosition().getRowIndex() && j == this.getGoalPosition().getColumnIndex())
                    myMaze+=" E";
                else
                {
                    myMaze+=" "+map[i][j];
                }
            }
            myMaze+=" }\n";
        }
        System.out.println(myMaze);


    }
}
