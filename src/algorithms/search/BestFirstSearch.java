package algorithms.search;

import java.util.*;


public class BestFirstSearch extends BreadthFirstSearch{

    private static class costComparator implements Comparator<AState>
    {

        @Override
        public int compare(AState o1, AState o2) {
            return Double.compare(o1.getCost(), o2.getCost());
        }
    }

    public BestFirstSearch() {
        Comparator<AState> comparator = new costComparator();
        this.queue = new PriorityQueue<>(comparator);
    }


    /**
     * a function to insert to the queue in the BestFirstSearch search
     * @param state one of the successors state
     * @param start current state
     * @param visited a set of all the visited states
     * @param min map of each state and it's minimum cost
     */
    @Override
    protected void insertToQueue(AState state, AState start, HashSet<AState> visited, HashMap<AState,Double> min)
    {
        //if the state not in the queue and was never visited before
        if(!queue.contains(state) && !visited.contains(state))
        {
            state.setCameFrom(start);
            state.setCost(state.getCost() + start.getCost());
            queue.add(state);
            min.put(state,state.getCost()); //add to the map for the first time
        }
        //if we founded a cheaper path, switch to this path
        else if(min.containsKey(start) && min.containsKey(state) && min.get(start) + state.getCost() < min.get(state) && !visited.contains(state))
        {
            queue.remove(state); //remove from queue to update the cost
            state.setCameFrom(start);
            state.setCost(state.getCost() + start.getCost());
            min.put(state,state.getCost());
            queue.add(state);
        }

    }
}
