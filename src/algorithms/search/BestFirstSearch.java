package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;



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
}
