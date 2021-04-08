package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {

    private int[][][] map;
    private Position3D startPosition;
    private Position3D goalPosition;

    public Maze3D(int[][][] map, Position3D startPosition, Position3D goalPosition) {
        this.map = map;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    public int[][][] getMap() {
        return map;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }

//    public void print()
//    {
//        int[][][] map = this.getMap();
//        String myMaze3D = "{";
//        for (int i = 0 ; i < map.length ; i++)
//        {
//            if (i!=map.length&& i!=0)
//            {
//                myMaze3D+= "-".repeat(map[0][0].length*2+3);
//            }
//            for (int j = 0 ; j < map[0].length ; j++)
//            {
//                myMaze3D+=" \n";
//                myMaze3D+= "{";
//                for (int k = 0 ; k < map[0][0].length; k++)
//                {
//                    if (i ==this.getStartPosition().getDepthIndex() && j == this.getStartPosition().getRowIndex() && k == this.getStartPosition().getColumnIndex())
//                        myMaze3D+=" S";
//                    else if (i ==this.getGoalPosition().getDepthIndex() && j == this.getGoalPosition().getRowIndex() && k == this.getGoalPosition().getColumnIndex())
//                        myMaze3D+=" E";
//                    else
//                    {
//                        myMaze3D+=" "+map[i][j][k];
//                    }
//                }
//                myMaze3D+= " }";
//            }
//            myMaze3D+=" \n";
//        }
//        myMaze3D+="}";
//        System.out.println(myMaze3D);
//    }
public void print(){
    System.out.println("{");
    for(int depth = 0; depth < map.length; depth++){
        for(int row = 0; row < map[0].length; row++) {
            System.out.print("{ ");
            for (int col = 0; col < map[0][0].length; col++) {
                if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                    System.out.print("S ");
                else {
                    if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                        System.out.print("E ");
                    else
                        System.out.print(map[depth][row][col] + " ");
                }
            }
            System.out.println("}");
        }
        if(depth < map.length - 1) {
            System.out.print("---");
            for (int i = 0; i < map[0][0].length; i++)
                System.out.print("--");
            System.out.println();
        }
    }
    System.out.println("}");
}

}
