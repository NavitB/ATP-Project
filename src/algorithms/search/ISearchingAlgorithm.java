package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution solve(ISearchable s);
    public int getNumberOfVisitedNodes();
    public void setAlgoTime(ISearchable s);
    public long getAlgoTime();

    long measureAlgorithmTimeMillis(ISearchable domain);
}
