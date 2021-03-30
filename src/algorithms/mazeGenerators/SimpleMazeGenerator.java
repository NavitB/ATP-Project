package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns)
    {
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




//        Maze newMaze = new Maze(rows,columns);
//        double rowS = Math.random() * rows;
//        double colS = Math.random() * columns;
//        Position startP = new Position((int)rowS,(int)colS);
//        newMaze.setStartPosition(startP);
//        double rowG = Math.random() * rows;
//        double colG = Math.random() * columns;
//        while ((int)rowS==(int)rowG && (int)colS == (int)colG)
//        {
//            rowG = Math.random() * rows;
//            colG = Math.random() * columns;
//        }
//        Position goalP = new Position((int)rowG,(int)colG);
//        newMaze.setGoalPosition(goalP);
//
//        int[][] m = new int[rows][columns];
//        return null;









    }
}
