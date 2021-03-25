package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {

        int[][] m = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                m[i][j] = 0;
            }
        }
        Maze newMaze = new Maze(rows,columns);
        newMaze.setMaze(m);
        return newMaze;
    }
}
