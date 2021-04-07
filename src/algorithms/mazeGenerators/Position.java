package algorithms.mazeGenerators;

public class Position {

    private int rowIndex;
    private int columnIndex;

    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }


    @Override
    public int hashCode() {
        int hash = (53 + this.rowIndex) * (53 + this.columnIndex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = (Position) obj;
        return pos.getRowIndex() == rowIndex && pos.getColumnIndex() == columnIndex;
    }

    @Override
    public String toString() {
        return "{" +
                rowIndex +
                "," + columnIndex +
                '}';
    }
}
