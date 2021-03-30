package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {

        int[][] map = resetMazeWithWalls(rows, columns);
        ArrayList<Position> maze = new ArrayList<>();
        //ArrayList<Position> visited = new ArrayList<>();
        HashMap<Position,Integer> visited = new HashMap<>();
        Position start = getStartPos(map,rows,columns);
        Position end = start;
        maze.add(start);
        visited.put(start,1);
        //visited.add(start);
        ArrayList<Position> walls = new ArrayList<>(ifWalls(map, getNeighbors(start.getRowIndex(), start.getColumnIndex(), rows, columns)));
        while(!walls.isEmpty())
        {
            int randomIndex = (int)(Math.random() * walls.size());
            Position pos = walls.get(randomIndex);
            walls.remove(pos);
            visited.put(pos,1);
            //visited.add(pos);
            if(checkVisitedNeighbors(map,pos,visited))
            {
                addToMaze(map,pos,maze);
                walls.addAll(ifWalls(map,getNeighbors(pos.getRowIndex(),pos.getColumnIndex(),rows,columns))); //add neighbors walls
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
        while ((int)rowS != 0 && (int)rowS != rows - 1 && (int)colS != 0 && (int)colS != columns-1 ) //if the start not on the frame
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

    private ArrayList<Position> ifWalls (int[][] map, ArrayList<Position> neighbors)
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
        if(rowIndex == 0 || rowIndex == rows - 1 || colIndex == 0 || colIndex == columns-1 )
        {
            return rowIndex != start.getRowIndex() && colIndex != start.getColumnIndex();
        }
        return false;
    }

    private void addToMaze(int[][] map, Position pos,ArrayList<Position> maze)
    {
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        map[rowIndex][colIndex] = 0;
        maze.add(pos);
    }
}