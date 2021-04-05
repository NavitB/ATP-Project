package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

class costComparator implements Comparator<AState>
{

    @Override
    public int compare(AState o1, AState o2) {
        if(o1.getCost()< o2.getCost())
            return -1;
        if (o1.getCost() > o2.getCost())
            return 1;
        return 0;
    }
}

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch() {
        Comparator<AState> comparator = new costComparator();
        this.queue = new PriorityQueue<>(comparator);
    }
}
