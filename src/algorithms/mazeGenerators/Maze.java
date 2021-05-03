package algorithms.mazeGenerators;


import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {


    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;
    private int index=0;


    /**
     * constructor of a maze
     * @param start position where the maze begins
     * @param end position where the maze ends
     * @param map two-dimension array that represents a maze
     */
    public Maze(Position start, Position end, int[][] map) {

        this.startPosition = start;
        this.goalPosition = end;
        this.maze = map;
    }

    public Maze(byte[] b)
    {
        int numOfRows = convertFromByteToInt(b);
        int numOfCol = convertFromByteToInt(b);
        int startR = convertFromByteToInt(b);
        int startC = convertFromByteToInt(b);
        int endR = convertFromByteToInt(b);
        int endC = convertFromByteToInt(b);
        Position start = new Position(startR,startC);
        this.startPosition = start;
        Position end = new Position(endR,endC);
        this.goalPosition = end;
        int[][] map = new int[numOfRows][numOfCol];
        for (int j = 0 ; j < numOfRows ; j++)
        {
            for (int k = 0 ; k < numOfCol ; k++)
            {
                map[j][k]= (int)b[index];
                index++;
            }
        }
        maze = map;
    }

    private int convertFromByteToInt(byte[] b )
    {
        int sum = 0;
        if (b[index] == 0)
        {
            index+=2;
            return 0;
        }
        else
        {
            while(b[index]!=0)
            {
                sum+=Byte.toUnsignedInt(b[index]);
                index++;
            }
            index++;
            return sum;
        }

    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }


    /**
     * prints the maze
     */
    public void print()

    {
        int[][] map = this.getMaze();
        String myMaze = "";
        for (int i = 0 ; i < map.length ; i++)
        {
            myMaze+= "{";
            for (int j = 0 ; j < map[0].length ; j++)
            {
                if (i == this.getStartPosition().getRowIndex() && j == this.getStartPosition().getColumnIndex())
                    myMaze+=" S";
                else if (i == this.getGoalPosition().getRowIndex() && j == this.getGoalPosition().getColumnIndex())
                    myMaze+=" E";
                else
                {
                    myMaze+=" "+map[i][j];
                }
            }
            myMaze+=" }\n";
        }
        System.out.println(myMaze);


    }
    public byte[] toByteArray(){
        ArrayList<Byte> b = new ArrayList<Byte>();
        int numOfRows = maze.length;
        int numOfCol = maze[0].length;
        int startR = startPosition.getRowIndex();
        int startC = startPosition.getColumnIndex();
        int endR = goalPosition.getRowIndex();
        int endC = goalPosition.getColumnIndex();
        b.addAll(convertToByte(numOfRows));
        b.addAll(convertToByte(numOfCol));
        b.addAll(convertToByte(startR));
        b.addAll(convertToByte(startC));
        b.addAll(convertToByte(endR));
        b.addAll(convertToByte(endC));
        b.addAll(convertMap());
        byte[] array = new byte[b.size()];
        for (int i = 0 ; i < b.size();i++)
        {
            array[i] = b.get(i);
        }
        return array;




    }

    private ArrayList<Byte> convertToByte(int num){
        ArrayList<Byte> b = new ArrayList<Byte>();
        while ( num > 255)
        {
            num-=255;
            b.add((byte)255);
        }
        if (num >=0)
        {
            b.add((byte)num);
        }
        b.add((byte)0);
        return b;
    }

    private ArrayList<Byte> convertMap()
    {
        ArrayList<Byte> b = new ArrayList<Byte>();
        for (int i = 0 ; i < maze.length ; i++)
        {
            for (int j = 0 ; j < maze[0].length ; j++)
            {
                b.add((byte)maze[i][j]);
            }
        }
        return b;
    }
}
