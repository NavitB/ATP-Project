//package algorithms.mazeGenerators;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MyMazeGenerator extends AMazeGenerator{
//
//    @Override
//    public Maze generate(int rows, int columns) {
//
//        int[][] map = resetMazeWithWalls(rows, columns);
//        ArrayList<Position> walls = new ArrayList<Position>();
//        ArrayList<Position> maze = new ArrayList<Position>();
//        ArrayList<Position> visited = new ArrayList<Position>();
//        //Map<Position, Boolean> visited = new HashMap<Position, Boolean>();
//        Position start = getStartPos(map,rows,columns);
//        maze.add(start);
//        visited.add(start);
//        walls.addAll(getNeighbours(map,start.getRowIndex(),start.getColumnIndex(),rows,columns));
//        while(!walls.isEmpty())
//        {
//            int randomIndex = (int)(Math.random() * walls.size());
//            Position temp = walls.get(randomIndex);
//            walls.remove(temp);
//            visited.add(temp);
//
//
//
//        }
//    return null;
//    }
//
//    private Position getStartPos (int[][] map,int rows,int columns)
//    {
//        double rowS = Math.random() * rows;
//        double colS = Math.random() * columns;
//        while ((int)rowS != 0 || (int)rowS != rows-1 || (int)colS != 0 || (int)colS != columns-1 ) //if the start not on the frame
//        {
//            rowS = Math.random() * rows;
//            colS = Math.random() * columns;
//        }
//        map[(int)rowS][(int)colS] = 0;
//        Position start = new Position((int)rowS, (int)colS); //making new start position
//        return null;
//    }
//
//    private int[][] resetMazeWithWalls(int rows, int columns)
//    {
//        int[][] map = new int[rows][columns];
//        for(int i = 0; i < rows; i++)
//        {
//            for(int j = 0; j < columns; j++)
//            {
//                map[i][j] = 1;
//            }
//        }
//        return map;
//    }
//
//    private ArrayList<Position> getNeighbours(int[][] map, int rowIndex, int colIndex , int rows, int columns)
//    {
//        ArrayList<Position> neighbours = new ArrayList<Position>();
//        if(rowIndex + 1 <= rows && map[rowIndex+1][colIndex] == 1)
//        {
//            Position downN = new Position(rowIndex+1, colIndex);
//            neighbours.add(downN);
//        }
//        if (colIndex + 1 <= columns && map[rowIndex][colIndex+1] == 1)
//        {
//            Position rightN = new Position(rowIndex, colIndex+1);
//            neighbours.add(rightN);
//        }
//        if (rowIndex - 1 >= 0 && map[rowIndex-1][colIndex] == 1)
//        {
//            Position upN = new Position(rowIndex-1, colIndex);
//            neighbours.add(upN);
//        }
//        if(colIndex - 1 >= 0 && map[rowIndex][colIndex-1] == 1)
//        {
//            Position leftN = new Position(rowIndex, colIndex-1);
//            neighbours.add(leftN);
//        }
//    }
//
//    private boolean checkVisitedNeighbours (int[][]map, Position temp)
//    {
//        ArrayList<Position> neighbours = getNeighbours(map,temp.getRowIndex(), temp.getColumnIndex(),map.length,map[0].length);
//        return null;
//    }
//
//}
