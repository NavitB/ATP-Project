package algorithms.maze3D;

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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
