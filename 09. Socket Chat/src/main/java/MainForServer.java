import java.util.LinkedList;

public class MainForServer {
    public static final int PORT = 7777;


    public static void main(String[] args) {

        EchoServerSocket serverSocket = new EchoServerSocket();
        serverSocket.start(PORT);
    }
}
