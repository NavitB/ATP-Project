package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution solve(ISearchable s) throws Exception;
    public int getNumberOfVisitedNodes();

}
