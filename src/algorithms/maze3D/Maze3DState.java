package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    public Maze3DState(Position3D state) {
        super(state);
    }

    @Override
    public int hashCode() {
        int hash = (53 + ((Position3D)this.getState()).getRowIndex()) * (53 + ((Position3D)this.getState()).getColumnIndex())*(53 +((Position3D) this.getState()).getDepthIndex());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position3D pos = (Position3D)((Maze3DState)obj).getState();
        Position3D thisPos = (Position3D) this.getState();
        return pos.equals(thisPos);
    }

    @Override
    public String toString() {
        return this.getState().toString();
    }
}