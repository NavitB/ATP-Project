package algorithms.search;

public class AState
{
    private Object state;
    private AState cameFrom;
    private double cost;

    public AState(Object state)
    {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public Object getState() {
        return state;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
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
