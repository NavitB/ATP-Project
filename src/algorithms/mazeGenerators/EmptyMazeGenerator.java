package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * @param rows num of rows
     * @param columns num of columns
     * @return an empty maze (only with zeros) in the size of rows*columns
     */
    @Override
    public Maze generate(int rows, int columns) throws Exception {

        if(rows <= 1 || columns <= 1) //if the size of the maze is not valid
        {
            throw new Exception("wrong num of rows/columns");
        }
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                map[i][j] = 0; //all the map is with zeros
            }
        }
        Position start = new Position(0,0);
        Position end = new Position(rows-1,columns-1);
        Maze newMaze = new Maze(start,end, map);
        return newMaze;
    }
}
