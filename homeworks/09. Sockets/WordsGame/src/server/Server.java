package server;

import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements TCPConnectionListener {

    public static void main(String[] args) {
        new Server();
    }

    public String lastMsg = "";

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private Server() {
        System.out.println("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(8189);) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept());

                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Client connected: " + tcpConnection);

    }

    @Override
    public synchronized void onReceiveString(TCPConnection connection, String msg) {

        if (msg.equals("")) {
            return;
        } else {
            if (lastMsg.equals("")) {

                sendToAllConnections(msg);
                lastMsg = msg;


            } else {
                if (msg.split(": ")[1].charAt(0) == lastMsg.charAt(lastMsg.length() - 1) ) {
                    sendToAllConnections(msg);
                    lastMsg = msg;
                } else {
                    connection.sendString("Incorrect word");
                }
            }

        }

    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        lastMsg = "";
        sendToAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        final int cnt = connections.size();
        for (int i = 0; i < cnt; i++) {
            connections.get(i).sendString(value);

        }
    }


}


