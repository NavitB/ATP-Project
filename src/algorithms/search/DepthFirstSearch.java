package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;

public class DepthFirstSearch extends ASearchingAlgorithm
{
    @Override
    public Solution solve(ISearchable s) {
        HashMap<AState, Integer> visited = new HashMap<>();
        recDFS(s ,s.getStartState(),s.getGoalState(),visited);
        ArrayList<AState> path = new ArrayList<>();
        restorePath(s.getStartState(),s.getGoalState(),path);
        Solution sol = new Solution(path);
        return sol;
    }
    private void recDFS(ISearchable problem, AState start, AState goal, HashMap<AState , Integer> visited)
    {
        visited.put(start,1);
        ArrayList<AState> validStates = problem.getAllSuccessors(start);
        for (AState state : validStates)
        {
            if (state == goal)
            {
              goal.setCameFrom(start);
              return;
            }
            if (!visited.containsKey(state))
            {
                state.setCameFrom(start);
                recDFS(problem, state,goal, visited);
            }
        }
    }
    private void restorePath(AState start,AState goal, ArrayList<AState> path)
    {
        if (goal == start)
        {
            path.add(0,start);
            return;
        }
        path.add(0,goal.getCameFrom());
        restorePath(start,goal.getCameFrom(),path);
    }
    @Override
    public int getNumberOfVisitedNodes() {
        return super.getNumberOfVisitedNodes();
    }
}
