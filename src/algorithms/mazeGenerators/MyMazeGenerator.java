package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MyMazeGenerator extends AMazeGenerator{


    /**
     * @param rows num of rows in the maze
     * @param columns num of columns in the maze
     * @return a new maze
     * @throws Exception if the maze is not valid
     */
    @Override
    public Maze generate(int rows, int columns) throws Exception {
        if(rows <= 1 || columns <= 1)
        {
            throw new Exception("wrong num of rows/columns");
        }
        int[][] map = resetMazeWithWalls(rows, columns);
        HashSet<Position> visited = new HashSet<>();
        Position start = getStartPos(map,rows,columns);
        Position end = start;
        visited.add(start);
        ArrayList<Position> walls = new ArrayList<>(neighborsWalls(map, getNeighbors(start.getRowIndex(), start.getColumnIndex(), rows, columns)));
        while(!walls.isEmpty())
        {
            int randomIndex = (int)(Math.random() * walls.size());
            Position pos = walls.get(randomIndex);
            walls.remove(pos);
            visited.add(pos);
            if(checkVisitedNeighbors(map,pos,visited))
            {
                addToMaze(map,pos);
                walls.addAll(neighborsWalls(map,getNeighbors(pos.getRowIndex(),pos.getColumnIndex(),rows,columns))); //add neighbors walls
                if(findGoalPos(pos,start,columns))
                {
                    end = pos;
                }
            }
        }
        Maze newMaze = new Maze(start,end,map);
        return newMaze;
    }

    /**
     * @param map 2D array of integers
     * @param rows num of rows
     * @param columns num of columns
     * @return start Position
     */
    private Position getStartPos (int[][] map,int rows,int columns)
    {
        double rowS = Math.random() * rows;
        double colS = Math.random() * columns;
        while ((int)colS != 0 ) //if the start not on the left
        {
            rowS = Math.random() * rows;
            colS = Math.random() * columns;
        }
        map[(int)rowS][(int)colS] = 0;
        return new Position((int)rowS, (int)colS);
    }

    /**
     * @param rows num of rows
     * @param columns num of columns
     * @return 2D array of integers
     */
    private int[][] resetMazeWithWalls(int rows, int columns)
    {
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                map[i][j] = 1;
            }
        }
        return map;
    }

    /**
     * @param rowIndex index of a specific row
     * @param colIndex index of a specific column
     * @param rows num of rows
     * @param columns num of columns
     * @return array list of positions
     */
    private ArrayList<Position> getNeighbors(int rowIndex, int colIndex , int rows, int columns)
    {
        ArrayList<Position> neighbors = new ArrayList<>();
        if(rowIndex + 1 < rows)
        {
            Position downN = new Position(rowIndex+1, colIndex);
            neighbors.add(downN);
        }
        if (colIndex + 1 < columns)
        {
            Position rightN = new Position(rowIndex, colIndex+1);
            neighbors.add(rightN);
        }
        if (rowIndex - 1 >= 0)
        {
            Position upN = new Position(rowIndex-1, colIndex);
            neighbors.add(upN);
        }
        if(colIndex - 1 >= 0 )
        {
            Position leftN = new Position(rowIndex, colIndex-1);
            neighbors.add(leftN);
        }
        return neighbors;
    }

    /**
     * @param map 2D array of integers
     * @param neighbors array list of positions that symbol the neighbors
     * @return array list of positions
     */
    private ArrayList<Position> neighborsWalls (int[][] map, ArrayList<Position> neighbors)
    {
        ArrayList<Position> neighborWalls = new ArrayList<>();
        for(Position pos : neighbors)
        {
            int rowIndex = pos.getRowIndex();
            int colIndex = pos.getColumnIndex();
            if(map[rowIndex][colIndex] == 1)
                neighborWalls.add(pos);
        }
        return neighborWalls;
    }

    /**
     * @param map 2D array of integers
     * @param pos Position
     * @param visited hash map of positions we visited
     * @return boolean value if we visited only in one neighbors
     */
    private boolean checkVisitedNeighbors (int[][]map, Position pos, HashSet<Position> visited)
    {
        ArrayList<Position> neighbors = getNeighbors(pos.getRowIndex(), pos.getColumnIndex(),map.length,map[0].length);
        int totalVisited = 0;
        for(Position p : neighbors)
        {
            if(visited.contains(p))
                totalVisited++;
        }
        return totalVisited == 1;
    }


    /**
     * @param pos the position we will check if it is a valid goal position
     * @param start the start position
     * @param columns num of columns
     * @return boolean value if is a valid goal position
     */
    private boolean findGoalPos (Position pos,Position start, int columns)
    {
        int colIndex = pos.getColumnIndex();
        if(colIndex == columns-1 )
        {
            return pos != start;
        }
        return false;
    }

    /**
     * @param map 2D array of integers, the maze
     * @param pos the position we want to add to the maze
     */
    private void addToMaze(int[][] map, Position pos)
    {
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        map[rowIndex][colIndex] = 0;
    }
}