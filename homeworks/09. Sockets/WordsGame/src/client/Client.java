package client;

import network.TCPConnection;
import network.TCPConnectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDR = "localhost";
    private static final int PORT = 8189;

    public static void main(String[] args) {

        new Client();

    }

    PrintWriter writer;
    BufferedReader reader;
    private String nickName = "___";

    Scanner sc = new Scanner(System.in);
    private TCPConnection connection;

    private Client() {
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            printMsg("Connection exception" + e);
        }


        System.out.println("Enter name: ");
        nickName = sc.nextLine();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = sc.nextLine();
    }


    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("Connection close...");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection exception" + e);
    }

    private synchronized void printMsg(String msg) {

        System.out.println(msg + "\n");


    }
}