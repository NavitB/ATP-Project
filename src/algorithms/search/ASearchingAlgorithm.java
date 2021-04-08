package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int numOfVisited;
    private long algoTime;

    public void setAlgoTime(ISearchable s) {
        this.algoTime = measureAlgorithmTimeMillis(s);
    }

    @Override
    public Solution solve(ISearchable s) {
        return null ;
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return numOfVisited;
    }

    public void setNumOfVisited(int numOfVisited) {
        this.numOfVisited = numOfVisited;
    }
    protected void restorePath(AState start,AState goal, ArrayList<AState> path)
    {
        if (goal == start)
        {
            path.add(0,start);
            return;
        }
        path.add(0,goal);
        restorePath(start,goal.getCameFrom(),path);
    }

    public long getAlgoTime() {
        return algoTime;
    }

    public long measureAlgorithmTimeMillis(ISearchable s)
    {
        long startT = System.currentTimeMillis();
        solve(s);
        long endT = System.currentTimeMillis();
        algoTime = endT - startT;
        return algoTime;
    }
}
