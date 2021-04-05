package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    public long measureAlgorithmTimeMillis(int depth,int row, int column)
    {
        long startT = System.currentTimeMillis();
        generate(depth,row,column);
        long endT = System.currentTimeMillis();
        return endT - startT;
    }
}
