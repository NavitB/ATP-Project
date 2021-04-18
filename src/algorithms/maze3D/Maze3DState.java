package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState {

    Position3D state;

    /**
     * @param state 3D position that represents a state
     */
    public Maze3DState(Position3D state) {
        this.state = state;
    }

    /**
     * @return 3D position that represents a state
     */
    public Position3D getState() {
        return state;
    }

    /**
     * @return int that represent the hash code
     */
    @Override
    public int hashCode() {
        int hash = (53 + (this.state.getRowIndex()) * (53 + this.state.getColumnIndex())*(53 +this.state.getDepthIndex()));
        return hash;
    }

    /**
     * @param obj object
     * @return a boolean value if 2 positions are equal
     */
    @Override
    public boolean equals(Object obj) {
        Position3D pos = ((Maze3DState)obj).getState();
        Position3D thisPos = this.state;
        return pos.equals(thisPos);
    }

    /**
     * @return a string of 3D position
     */
    @Override
    public String toString() {
        return this.getState().toString();
    }
}