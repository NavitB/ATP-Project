package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;


    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }


    public void communicateWithServer() {
        try(Socket serverSocket = new Socket(serverIP,serverPort)){
            strategy.clientStrategy(serverSocket.getInputStream() , serverSocket.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
