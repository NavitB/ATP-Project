package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int numOfVisited;


    @Override
    public Solution solve(ISearchable s) throws Exception {
        return null ;
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return numOfVisited;
    }

    public void setNumOfVisited(int numOfVisited) {
        this.numOfVisited = numOfVisited;
    }


    /**
     * @param start the start state
     * @param goal the goal state
     * @param path a list to update with all the states that in the path from start to goal
     */
    protected void restorePath(AState start,AState goal, ArrayList<AState> path)
    {
        AState temp = goal;
        while (!temp.equals(start))
        {
            path.add(0,temp);
            temp = temp.getCameFrom();
        }
        path.add(0,start);
    }



}
