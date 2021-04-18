package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    private Position state;

    /**
     * constructor
     * @param state each maze state has a position in the maze
     */
    public MazeState(Position state) {
        this.state = state;
    }

    public Position getState() {
        return state;
    }

    @Override
    public int hashCode() {
        int hash = (53 + (this.state).getRowIndex()) * (53 + (this.state).getColumnIndex());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = ((MazeState)obj).getState();
        Position thisPos = this.state;
        return pos.equals(thisPos);
    }

    @Override
    public String toString() {
        return this.getState().toString();
    }
}