package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalsMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalsMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalsMS = listeningIntervalsMS;
        this.strategy = strategy;
        this.threadPool= Executors.newFixedThreadPool(Integer.parseInt(Configurations.getConf().getNumOfThreads()));
    }

    public void start(){
        new Thread(() -> {this.runServer();
        }).start();
    }
    public void runServer(){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalsMS);
            while (!stop)
            {
                try{
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(()-> handleClient(clientSocket));
                }
                catch (SocketTimeoutException e)
                {
                    e.printStackTrace();
                }
            }
            serverSocket.close();
            threadPool.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try{
            strategy.ServerStrategy(clientSocket.getInputStream() , clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        stop=true;
    }
}
