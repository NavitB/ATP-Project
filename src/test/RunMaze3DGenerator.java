package test;

import algorithms.maze3D.*;
import algorithms.maze3D.Maze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunMaze3DGenerator {

        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(3,3,3);

}
