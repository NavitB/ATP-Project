package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator{

    /**
     * @param rows num of rows
     * @param columns num of columns
     * @return a new maze
     * @throws Exception if the size of the maze is not valid
     */
    @Override
    public Maze generate(int rows, int columns) throws Exception {
        if(rows <= 1 || columns <= 1)
        {
            throw new Exception("wrong num of rows/columns");
        }
        int[][] map = new int[rows][columns];
        Position startP = new Position(0,0);
        Position goalP = new Position(rows-1,columns-1);
        for (int i = 0 ; i < columns ; i++)
        {
          map[0][i] = 0;
        }
        for (int j = 0 ; j < rows ; j++)
        {
            map[columns-1][j] = 0;
        }
        for (int i = 1 ; i < columns-1 ; i ++)
        {
            for (int j = 0 ; j < rows -1 ; j ++ )
            {
                double randomNum = Math.random();
                if (randomNum > 0.5)
                {
                    map[i][j] = 0;
                }
                else
                {
                    map[i][j] = 1;
                }
            }
        }
        Maze newMaze = new Maze(startP,goalP,map);
        return newMaze;



    }
}
