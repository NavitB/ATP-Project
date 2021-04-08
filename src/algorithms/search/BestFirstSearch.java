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


    @Override
    protected void insertToQueue(AState state, AState start, HashSet<AState> visited, HashMap<AState,Double> min)
    {
        if(!queue.contains(state) && !visited.contains(state))
        {
            state.setCameFrom(start);
            state.setCost(state.getCost() + start.getCost());
            queue.add(state);
            min.put(state,state.getCost());
        }
        else if(min.containsKey(start) && min.containsKey(state) && min.get(start) + state.getCost() < min.get(state) && !visited.contains(state))
        {
            queue.remove(state);
            state.setCameFrom(start);
            state.setCost(state.getCost() + start.getCost());
            min.put(state,state.getCost());
            queue.add(state);
        }

    }
}
