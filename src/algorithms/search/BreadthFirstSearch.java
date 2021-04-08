package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable s) throws Exception {
        if(s == null)
        {
            throw new Exception("searchable is null");
        }
        BFS(s ,s.getStartState(),s.getGoalState());
        ArrayList<AState> path = new ArrayList<>();
        restorePath(s.getStartState(),s.getGoalState(),path);
        return new Solution(path);
    }
    private void BFS(ISearchable problem, AState start, AState goal )
    {
        HashSet<AState> visited = new HashSet<>();
        HashMap<AState, Double> min = new HashMap<AState, Double>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty())
        {
            start = queue.poll();
            if (start.equals(goal))
            {
                goal.setCameFrom(start.getCameFrom());
                goal.setCost(start.getCost());
                this.setNumOfVisited(visited.size());
                return;
            }
            visited.add(start);
            ArrayList<AState> validStates = problem.getAllSuccessors(start);
            for (AState state: validStates)
            {
                insertToQueue(state,start,visited,min);
            }
        }
    }

    protected void insertToQueue(AState state,AState start, HashSet<AState> visited, HashMap<AState,Double> min)
    {
        if(!visited.contains(state))
        {
            state.setCameFrom(start);
            queue.add(state);
            visited.add(state);
        }
    }
    @Override
    public int getNumberOfVisitedNodes() {
        return super.getNumberOfVisitedNodes();
    }

    @Override
    public void setNumOfVisited(int numOfVisited) {
        super.setNumOfVisited(numOfVisited);
    }
}
