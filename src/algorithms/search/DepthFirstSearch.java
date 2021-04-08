package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm
{
    @Override
    public Solution solve(ISearchable s) throws Exception {
        if(s == null)
        {
            throw new Exception("searchable is null");
        }
        iterativeDFS(s,s.getStartState(),s.getGoalState());
        ArrayList<AState> path = new ArrayList<>();
        restorePath(s.getStartState(),s.getGoalState(),path);
        return new Solution(path);
    }

    private void iterativeDFS(ISearchable problem, AState start, AState goal)
    {
        AState v;
        Stack<AState> stack = new Stack<>();
        HashSet<AState> visited = new HashSet<>();
        stack.push(start);
        visited.add(start);
        while (!stack.isEmpty())
        {
            v = stack.peek();
            stack.pop();
            ArrayList<AState> validStates = problem.getAllSuccessors(v);
            for (AState state: validStates)
            {
                if (state.equals(goal))
                {
                    goal.setCameFrom(v);
                    visited.add(goal);
                    this.setNumOfVisited(visited.size());
                    return;
                }
                if (!visited.contains(state))
                {
                    state.setCameFrom(v);
                    stack.push(state);
                    visited.add(state);
                }
            }
        }
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return super.getNumberOfVisitedNodes();
    }
}
