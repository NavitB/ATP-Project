package algorithms.search;


public abstract class AState{
    //private Object state;
    private AState cameFrom;
    private double cost;

    public AState()
    {
        //this.state = state;
        this.cost = 0;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

//    public Object getState() {
//        return state;
//    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
