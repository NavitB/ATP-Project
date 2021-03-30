package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {

        int[][] map = resetMazeWithWalls(rows, columns);
        ArrayList<Position> walls = new ArrayList<Position>();
        ArrayList<Position> maze = new ArrayList<Position>();
      //  Map<Position, Boolean> visited = new HashMap<Position, Boolean>();


        return null;
    }

    private int[][] resetMazeWithWalls(int rows, int columns)
    {
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                map[i][j] = 1;
            }
        }
        return map;
    }
}
