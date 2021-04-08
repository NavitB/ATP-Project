package test;

import algorithms.maze3D.*;
import algorithms.maze3D.Maze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunMaze3DGenerator {

        public static void main(String[] args) throws Exception {

                IMaze3DGenerator mg = new MyMaze3DGenerator();
                System.out.println(String.format("Maze generation time(ms): %s", mg.measureAlgorithmTimeMillis(100,100,100)));
                Maze3D maze = mg.generate(100,100,100);
        }


}
