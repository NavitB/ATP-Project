package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {

    private int depthIndex;
    private int rowIndex;
    private int columnIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public int hashCode() {
        int hash = (53 + this.rowIndex) * (53 + this.columnIndex)*(53 + this.depthIndex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position3D pos = (Position3D) obj;
        return pos.getRowIndex() == rowIndex && pos.getColumnIndex() == columnIndex && pos.getDepthIndex()==depthIndex;
    }
    @Override
    public String toString() {
        return "{" +depthIndex+","+
                rowIndex +
                "," + columnIndex +
                '}';
    }
}
