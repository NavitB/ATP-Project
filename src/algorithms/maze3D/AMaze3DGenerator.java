package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     * @param depth the depth of the maze
     * @param row num of rows
     * @param column num of columns
     * @return a long that measure the time
     * @throws Exception if the size of the maze is not valid
     */
    public long measureAlgorithmTimeMillis(int depth,int row, int column) throws Exception {
        long startT = System.currentTimeMillis();
        generate(depth,row,column);
        long endT = System.currentTimeMillis();
        return endT - startT;
    }
}
