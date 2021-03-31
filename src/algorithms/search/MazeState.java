package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position state) {
        super(state);
    }

    @Override
    public int hashCode() {
        int hash = (53 + ((Position)this.getState()).getRowIndex()) * (53 + ((Position)this.getState()).getColumnIndex());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = (Position)((MazeState)obj).getState();
        Position thisPos = (Position) this.getState();
        return pos.equals(thisPos);
       // return pos.getRowIndex() == thisPos.getRowIndex() && pos.getColumnIndex() == thisPos.getColumnIndex();
    }

    @Override
    public String toString() {
        return this.getState().toString();
    }
}
