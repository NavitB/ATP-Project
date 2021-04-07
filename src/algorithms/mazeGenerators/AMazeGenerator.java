package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * @param rows num of rows of the maze
     * @param columns num of columns of the maze
     * @return the time to generate a maze 
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns)
    {
        long startT = System.currentTimeMillis();
        generate(rows,columns);
        long endT = System.currentTimeMillis();
        return endT - startT;
    }
}
