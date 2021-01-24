import java.util.Scanner;


public class MainForClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("localhost", 7777);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }
}
