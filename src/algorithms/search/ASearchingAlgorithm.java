package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int numOfVisited;
    @Override
    public Solution solve(ISearchable s) {
        return null;
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return numOfVisited;
    }

    public void setNumOfVisited(int numOfVisited) {
        this.numOfVisited = numOfVisited;
    }
}
