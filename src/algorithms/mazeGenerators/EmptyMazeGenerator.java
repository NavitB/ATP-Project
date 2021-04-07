package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * @param rows num of rows
     * @param columns num of columns
     * @return an empty maze (only with zeros) in the size of rows*columns
     */
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
        Position start = new Position(0,0);
        Position end = new Position(rows-1,columns-1);
        Maze newMaze = new Maze(start,end, map);
        return newMaze;
    }
}
