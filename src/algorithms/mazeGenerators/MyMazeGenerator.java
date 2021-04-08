package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyMazeGenerator extends AMazeGenerator{


    @Override
    public Maze generate(int rows, int columns) throws Exception {
        if(rows <= 1 || columns <= 1)
        {
            throw new Exception("wrong num of rows/columns");
        }
        int[][] map = resetMazeWithWalls(rows, columns);
        HashMap<Position,Integer> visited = new HashMap<>();
        Position start = getStartPos(map,rows,columns);
        Position end = start;
        visited.put(start,1);
        ArrayList<Position> walls = new ArrayList<>(neighborsWalls(map, getNeighbors(start.getRowIndex(), start.getColumnIndex(), rows, columns)));
        while(!walls.isEmpty())
        {
            int randomIndex = (int)(Math.random() * walls.size());
            Position pos = walls.get(randomIndex);
            walls.remove(pos);
            visited.put(pos,1);
            if(checkVisitedNeighbors(map,pos,visited))
            {
                addToMaze(map,pos);
                walls.addAll(neighborsWalls(map,getNeighbors(pos.getRowIndex(),pos.getColumnIndex(),rows,columns))); //add neighbors walls
                if(findGoalPos(pos,start,rows,columns))
                {
                    end = pos;
                }
            }
        }
        Maze newMaze = new Maze(start,end,map);
        return newMaze;
    }

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

    private boolean checkVisitedNeighbors (int[][]map, Position pos, HashMap<Position,Integer> visited)
    {
        ArrayList<Position> neighbors = getNeighbors(pos.getRowIndex(), pos.getColumnIndex(),map.length,map[0].length);
        int totalVisited = 0;
        for(Position p : neighbors)
        {
            if(visited.containsKey(p))
                totalVisited++;
        }
        return totalVisited == 1;
    }

    private boolean findGoalPos (Position pos,Position start,int rows, int columns)
    {
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        if(colIndex == columns-1 )
        {
            return pos != start;
        }
        return false;
    }

    private void addToMaze(int[][] map, Position pos)
    {
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        map[rowIndex][colIndex] = 0;
    }
}