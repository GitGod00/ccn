import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientInput = inFromClient.readLine();
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

            outToClient.writeBytes((result == Integer.MIN_VALUE) ? "Error: Invalid operation or division by zero" : "Result: " + result + "\n");
        }
    }
}
