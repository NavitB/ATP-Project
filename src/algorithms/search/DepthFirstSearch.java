package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm
{
    @Override
    public Solution solve(ISearchable s) {
        //HashMap<AState, Integer> visited = new HashMap<>();
        //recDFS(s ,s.getStartState(),s.getGoalState(),visited);
        iterativeDFS(s,s.getStartState(),s.getGoalState());
        ArrayList<AState> path = new ArrayList<>();
        restorePath(s.getStartState(),s.getGoalState(),path);
        return new Solution(path);
    }
//    private void recDFS(ISearchable problem, AState start, AState goal, HashMap<AState , Integer> visited)
//    {
//        visited.put(start,1);
//        ArrayList<AState> validStates = problem.getAllSuccessors(start);
//        for (AState state : validStates)
//        {
//            if (state.equals(goal))
//            {
//              goal.setCameFrom(start);
//              return;
//            }
//            if (!visited.containsKey(state))
//            {
//                state.setCameFrom(start);
//                recDFS(problem, state,goal, visited);
//            }
//        }
//    }

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
