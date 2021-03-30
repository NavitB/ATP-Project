package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                map[i][j] = 0;
            }
        }
        Maze newMaze = new Maze(rows,columns, map);
        return newMaze;
    }
}
