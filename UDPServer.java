import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String clientInput = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] parts = clientInput.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            String operation = parts[2];

            int result = 0;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        result = Integer.MIN_VALUE; // Error code for division by zero
                    }
                    break;
                default:
                    result = Integer.MIN_VALUE; // Error code for invalid operation
                    break;
            }

            System.out.println("Received from client: " + num1 + " " + operation + " " + num2);
            System.out.println("Result: " + result);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String response = (result == Integer.MIN_VALUE) ? "Error: Invalid operation or division by zero" : "Result: " + result;
            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
