package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;

    /**
     * constructor
     */
    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
    }

    /**
     * @param s searchable problem
     * @return a solution to the problem
     * @throws Exception if the searchable problem is null
     */
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

    /**
     * BFS search on the searchable problem to update the "came from" for each state
     * @param problem searchable problem
     * @param start the start state of the problem
     * @param goal the end state of the problem
     */
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

    /**
     * a function to insert to the queue in the BFS search
     * @param state one of the successors state
     * @param start current state
     * @param visited a set of all the visited states
     * @param min map of each state and it's minimum cost (not relevant to BFS)
     */
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
