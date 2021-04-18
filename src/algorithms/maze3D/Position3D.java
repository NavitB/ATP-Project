package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {

    private int depthIndex;
    private int rowIndex;
    private int columnIndex;
    /**
     * @param depthIndex the depth index
     * @param rowIndex the row index
     * @param columnIndex the column index
     */
    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * @return the depth index
     */
    public int getDepthIndex() {
        return depthIndex;
    }

    /**
     * @return the row index
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * @return the column index
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * @return the calculation of the hash code
     */
    @Override
    public int hashCode() {
        int hash = (53 + this.rowIndex) * (53 + this.columnIndex)*(53 + this.depthIndex);
        return hash;
    }

    /**
     * @param obj object
     * @return a boolean value of 2 3D positions are equal
     */
    @Override
    public boolean equals(Object obj) {
        Position3D pos = (Position3D) obj;
        return pos.getRowIndex() == rowIndex && pos.getColumnIndex() == columnIndex && pos.getDepthIndex()==depthIndex;
    }

    /**
     * @return string of 3D position
     */
    @Override
    public String toString() {
        return "{" +depthIndex+","+
                rowIndex +
                "," + columnIndex +
                '}';
    }
}
