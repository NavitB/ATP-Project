package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solutionPath;

    /**
     * constructor
     * @param solutionPath the array list of the states that are the path from start to end in the searchable problem
     */
    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }
    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }
}
