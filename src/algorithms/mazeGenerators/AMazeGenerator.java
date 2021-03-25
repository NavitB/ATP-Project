package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns)
    {
        long startT = System.currentTimeMillis();
        generate(rows,columns);
        long endT = System.currentTimeMillis();
        return endT - startT;
    }
}
