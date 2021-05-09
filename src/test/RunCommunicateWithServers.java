package test;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import IO.SimpleDecompressorInputStream;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RunCommunicateWithServers {
//    public static void main(String[] args) {
//        //Initializing servers
//        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
//        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
//        //Starting  servers
//        solveSearchProblemServer.start();
//        //mazeGeneratingServer.start();
//        //Communicating with servers
//        //CommunicateWithServer_MazeGenerating();
//        CommunicateWithServer_SolveSearchProblem();
//        //Stopping all servers
//        //mazeGeneratingServer.stop();
//        solveSearchProblemServer.stop();
//        //stringReverserServer.stop();
//    }
public static void main(String[] args) {
//Initializing servers
    Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
    Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
//Starting servers
    solveSearchProblemServer.start();
    mazeGeneratingServer.start();
//        Thread t = new Thread(() -> mazeGeneratingServer.start());
//        t.start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    for (int j = 0; j < 10; j++) {
        Thread a = new Thread(() -> CommunicateWithServer_MazeGenerating());
        a.start();
    }
    for (int i = 0; i < 10; i++) {
        Thread b = new Thread(() -> CommunicateWithServer_SolveSearchProblem());
        b.start();
    }




//        CommunicateWithServer_SolveSearchProblem();


//

//Stopping all servers
    //mazeGeneratingServer.stop();
    //solveSearchProblemServer.stop();
//        Scanner s = new Scanner(System.in);
//        while (true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if(s.nextLine().equals("exit"))
//            break;
//        }
//        mazeGeneratingServer.stop();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread b = new Thread(() -> solveSearchProblemServer.start());
//        b.start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread c = new Thread(() -> CommunicateWithServer_SolveSearchProblem());
//        c.start();
    Scanner s = new Scanner(System.in);
    while (true){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(s.nextLine().equals("e"))
            break;
    }
    solveSearchProblemServer.stop();
    mazeGeneratingServer.stop();


}

    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
            //Client client = new Client(InetAddress.getByName("127.0.0.1"), 5400, new IClientStrategy() {

            @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{1000, 1000};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed withMyCompressor) from server
                        System.out.println(compressedMaze.length);
                        //InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[1002000 /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(decompressedMaze);
                        //maze.print();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        MyMazeGenerator mg = new MyMazeGenerator();
//                        int[][] map = {{0,0,0,0,0},{0,1,0,1,0},{1,0,0,1,0},{1,1,0,0,0}};
//                        Position start = new Position(0,0);
//                        Position end = new Position(2,4);
//                        Maze maze = new Maze(start,end,map);
                        Maze maze = mg.generate(500, 1000);
                        //maze.print();
                        toServer.writeObject(maze); //send maze to server
                        toServer.flush();
                        Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                        //Print Maze Solution retrieved from the server
                        System.out.println(String.format("Solution steps: %s", mazeSolution));
                        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                        for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                            System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_StringReverser() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5402, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        BufferedReader fromServer = new BufferedReader(new InputStreamReader(inFromServer));
                        PrintWriter toServer = new PrintWriter(outToServer);
                        String message = "Client Message";
                        String serverResponse;
                        toServer.write(message + "\n");
                        toServer.flush();
                        serverResponse = fromServer.readLine();
                        System.out.println(String.format("Server response: %s", serverResponse));
                        toServer.flush();
                        fromServer.close();
                        toServer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            });
            client.communicateWithServer();
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}