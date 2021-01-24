import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class EchoServerSocket {
    public static LinkedList<Socket> clientList = new LinkedList<>();
    ServerSocket server;
    Thread serverThread;
    public void start(int port) {


        try {
            server = new ServerSocket(port);

            while (true) {
                Socket client = server.accept();
                try {
                    clientList.add(client);

                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

                    String messageFromClient = fromClient.readLine();
                    while (messageFromClient != null) {
                        System.out.println("Message from client: " + messageFromClient);
                        toClient.println("Message from server: " + messageFromClient);
                        messageFromClient = fromClient.readLine();
                    }

                } catch (IOException e) {
                    client.close();
                    throw new IllegalStateException(e);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
