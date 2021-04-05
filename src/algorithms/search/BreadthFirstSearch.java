package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        BFS(s ,s.getStartState(),s.getGoalState());
        ArrayList<AState> path = new ArrayList<>();
        restorePath(s.getStartState(),s.getGoalState(),path);
        return new Solution(path);
    }
    private void BFS(ISearchable problem, AState start, AState goal )
    {
        HashMap<AState, Integer> visited = new HashMap<>();
        visited.put(start,1);
        queue.add(start);
        while (!queue.isEmpty())
        {
            start = queue.poll();
            ArrayList<AState> validStates = problem.getAllSuccessors(start);
            for (AState state: validStates)
            {
                if (state.equals(goal))
                {
                    goal.setCameFrom(start);
                    this.setNumOfVisited(visited.size());
                    return;
                }
                if (!visited.containsKey(state))
                {
                    state.setCameFrom(start);
                    queue.add(state);
                    visited.put(state,1);
                }
            }
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
