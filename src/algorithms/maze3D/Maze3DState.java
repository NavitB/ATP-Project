package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

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
        // return pos.getRowIndex() == thisPos.getRowIndex() && pos.getColumnIndex() == thisPos.getColumnIndex();
    }

    @Override
    public String toString() {
        return this.getState().toString();
    }
}
