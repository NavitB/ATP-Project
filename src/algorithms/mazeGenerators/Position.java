package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {

    private int rowIndex;
    private int columnIndex;

    /**
     * @param rowIndex the row index
     * @param columnIndex the column index
     */
    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * @return the index of the row
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * @return the index of the column
     */
    public int getColumnIndex() {
        return columnIndex;
    }


    /**
     * @return the calculation of the hash code
     */
    @Override
    public int hashCode() {
        int hash = (53 + this.rowIndex) * (53 + this.columnIndex);
        return hash;
    }

    /**
     * @param obj object
     * @return a boolean value of 2 positions are equal
     */
    @Override
    public boolean equals(Object obj) {
        Position pos = (Position) obj;
        return pos.getRowIndex() == rowIndex && pos.getColumnIndex() == columnIndex;
    }

    /**
     * @return string of position
     */
    @Override
    public String toString() {
        return "{" +
                rowIndex +
                "," + columnIndex +
                '}';
    }
}
