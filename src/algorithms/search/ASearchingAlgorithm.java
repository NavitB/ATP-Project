package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    @Override
    public Solution solve(ISearchable s) {
        return null;
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return 0;
    }
    protected ArrayList<AState> findNeighbors (AState state){
        return null;
    }
}
