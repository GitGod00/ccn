import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Enter first number: ");
        int num1 = Integer.parseInt(inFromUser.readLine());
        System.out.print("Enter second number: ");
        int num2 = Integer.parseInt(inFromUser.readLine());
        System.out.print("Enter operation (+, -, *, /): ");
        String operation = inFromUser.readLine();

        outToServer.writeBytes(num1 + " " + num2 + " " + operation + '\n');

        String serverResponse = inFromServer.readLine();
        System.out.println("FROM SERVER: " + serverResponse);

        clientSocket.close();
    }
}
