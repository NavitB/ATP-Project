package Server;

import java.lang.reflect.Executable;
import java.net.ServerSocket;
import java.net.Socket;
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
        this.threadPool = Executors.newFixedThreadPool(2);
    }

    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalsMS);
            while (!stop)
            {
                try{
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(()-> handleClient(clientSocket));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            serverSocket.close();
            threadPool.shutdownNow();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try{
            strategy.applyStrategy(clientSocket.getInputStream() , clientSocket.getOutputStream());
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
