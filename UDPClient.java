import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.print("Enter first number: ");
        int num1 = Integer.parseInt(inFromUser.readLine());
        System.out.print("Enter second number: ");
        int num2 = Integer.parseInt(inFromUser.readLine());
        System.out.print("Enter operation (+, -, *, /): ");
        String operation = inFromUser.readLine();

        String clientInput = num1 + " " + num2 + " " + operation;
        sendData = clientInput.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("FROM SERVER: " + serverResponse);

        clientSocket.close();
    }
}
