package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    @Override
    public Maze3D generate(int depth, int row, int column) throws Exception {
        if(depth <= 1|| row <= 1 || column <= 1)
        {
            throw new Exception("wrong num of depth/rows/columns");
        }
        int[][][] map = resetMazeWithWalls(depth ,row, column);
        HashMap<Position3D,Integer> visited = new HashMap<>();
        Position3D start = getStartPos(map,depth,row,column);
        Position3D end = start;
        visited.put(start,1);
        ArrayList<Position3D> walls = new ArrayList<>(ifWalls(map, getNeighbors(start.getDepthIndex(), start.getRowIndex(), start.getColumnIndex(),depth, row, column)));
        while(!walls.isEmpty())
        {
            int randomIndex = (int)(Math.random() * walls.size());
            Position3D pos = walls.get(randomIndex);
            walls.remove(pos);
            visited.put(pos,1);
            if(checkVisitedNeighbors(map,pos,visited))
            {
                addToMaze(map,pos);
                walls.addAll(ifWalls(map,getNeighbors(pos.getDepthIndex(),pos.getRowIndex(),pos.getColumnIndex(),depth,row,column))); //add neighbors walls
                if(findGoalPos(pos,start,row,column))
                {
                    end = pos;
                }
            }
        }
        Maze3D newMaze = new Maze3D(map,start,end);
        return newMaze;
    }
    private Position3D getStartPos (int[][][] map,int depth,int rows,int columns)
    {
        double depthS = Math.random() * depth;
        double rowS = Math.random() * rows;
        double colS = Math.random() * columns;
        while ((int)rowS != 0 && (int)rowS != rows - 1 && (int)colS != 0 && (int)colS != columns-1 ) //if the start not on the frame
        {
            depthS = Math.random() * depth;
            rowS = Math.random() * rows;
            colS = Math.random() * columns;
        }
        map[(int)depthS][(int)rowS][(int)colS] = 0;
        return new Position3D((int)depthS,(int)rowS, (int)colS);
    }

    private int[][][] resetMazeWithWalls(int depth,int rows, int columns)
    {
        int[][][] map = new int[depth][rows][columns];
        for(int i = 0; i < depth; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                for(int k = 0; k < columns; k++ )
                map[i][j][k] = 1;
            }
        }
        return map;
    }

    private ArrayList<Position3D> getNeighbors(int depthIndex,int rowIndex, int colIndex ,int depth, int rows, int columns)
    {
        ArrayList<Position3D> neighbors = new ArrayList<>();
        if(depthIndex + 1 < depth)
        {
            Position3D down3D = new Position3D(depthIndex+1, rowIndex,colIndex);
            neighbors.add(down3D);
        }
        if(depthIndex - 1 >= 0)
        {
            Position3D up3D = new Position3D(depthIndex-1 , rowIndex,colIndex);
            neighbors.add(up3D);
        }
        if(rowIndex + 1 < rows)
        {
            Position3D downN = new Position3D(depthIndex,rowIndex+1, colIndex);
            neighbors.add(downN);
        }
        if (colIndex + 1 < columns)
        {
            Position3D rightN = new Position3D(depthIndex,rowIndex, colIndex+1);
            neighbors.add(rightN);
        }
        if (rowIndex - 1 >= 0)
        {
            Position3D upN = new Position3D(depthIndex,rowIndex-1, colIndex);
            neighbors.add(upN);
        }
        if(colIndex - 1 >= 0 )
        {
            Position3D leftN = new Position3D(depthIndex,rowIndex, colIndex-1);
            neighbors.add(leftN);
        }
        return neighbors;
    }

    private ArrayList<Position3D> ifWalls (int[][][] map, ArrayList<Position3D> neighbors)
    {
        ArrayList<Position3D> neighborWalls = new ArrayList<>();
        for(Position3D pos : neighbors)
        {
            int depthIndex = pos.getDepthIndex();
            int rowIndex = pos.getRowIndex();
            int colIndex = pos.getColumnIndex();
            if(map[depthIndex][rowIndex][colIndex] == 1)
                neighborWalls.add(pos);
        }
        return neighborWalls;
    }

    private boolean checkVisitedNeighbors (int[][][]map, Position3D pos, HashMap<Position3D,Integer> visited)
    {
        ArrayList<Position3D> neighbors = getNeighbors(pos.getDepthIndex(),pos.getRowIndex(), pos.getColumnIndex(),map.length,map[0].length, map[0][0].length);
        int totalVisited = 0;
        for(Position3D p : neighbors)
        {
            if(visited.containsKey(p))
                totalVisited++;
        }
        return totalVisited == 1;
    }

    private boolean findGoalPos (Position3D pos,Position3D start,int rows, int columns)
    {
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        if(rowIndex == 0 || rowIndex == rows - 1 || colIndex == 0 || colIndex == columns-1 )
        {
            return pos != start;
        }
        return false;
    }

    private void addToMaze(int[][][] map, Position3D pos)
    {
        int depthIndex = pos.getDepthIndex();
        int rowIndex = pos.getRowIndex();
        int colIndex = pos.getColumnIndex();
        map[depthIndex][rowIndex][colIndex] = 0;
    }


}
