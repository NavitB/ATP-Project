package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState {

    Position3D state;
    public Maze3DState(Position3D state) {
        this.state = state;
    }

    public Position3D getState() {
        return state;
    }

    @Override
    public int hashCode() {
        int hash = (53 + (this.state.getRowIndex()) * (53 + this.state.getColumnIndex())*(53 +this.state.getDepthIndex()));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Position3D pos = ((Maze3DState)obj).getState();
        Position3D thisPos = this.state;
        return pos.equals(thisPos);
    }

    @Override
    public String toString() {
        return this.getState().toString();
    }
}