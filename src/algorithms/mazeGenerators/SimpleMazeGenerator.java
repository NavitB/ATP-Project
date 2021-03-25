package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns)
    {
        Maze newMaze = new Maze(rows,columns);
        double rowS = Math.random() * rows;
        double colS = Math.random() * columns;
        Position startP = new Position((int)rowS,(int)colS);
        newMaze.setStartPosition(startP);
        double rowG = Math.random() * rows;
        double colG = Math.random() * columns;
        while ((int)rowS==(int)rowG && (int)colS == (int)colG)
        {
            rowG = Math.random() * rows;
            colG = Math.random() * columns;
        }
        Position goalP = new Position((int)rowG,(int)colG);
        newMaze.setGoalPosition(goalP);

        int[][] m = new int[rows][columns];






    }
}
