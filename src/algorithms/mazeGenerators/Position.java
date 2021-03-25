package algorithms.mazeGenerators;

public class Position {


    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    private int rowIndex;
    private int columnIndex;


    public int getRowIndex() {
        return rowIndex;
    }

    public void setRow(int row) {
        this.rowIndex = row;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumn(int column) {
        this.columnIndex = column;
    }

    @Override
    public String toString() {
        return "{" +
                rowIndex +
                "," + columnIndex +
                '}';
    }
}
