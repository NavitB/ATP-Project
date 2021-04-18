package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {

    private int[][][] map;
    private Position3D startPosition;
    private Position3D goalPosition;

    /**
     * @param map 3D array of integers the represent the maze
     * @param startPosition start position of the maze
     * @param goalPosition goal position of the maze
     */
    public Maze3D(int[][][] map, Position3D startPosition, Position3D goalPosition) {
        this.map = map;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    /**
     * @return 3D array of integers that represent the maze
     */
    public int[][][] getMap() {
        return map;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }


    /**
     * print function
     */
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
