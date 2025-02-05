import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int SERVER_PORT = 12345; // Server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            // Set up input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Read and display welcome message from server
            System.out.println(in.readLine());
            
            // Send client's name to the server
            System.out.print("Enter your name: ");
            String clientName = userInput.readLine();
            out.println(clientName);

            // Start a thread to listen for incoming messages
            Thread listenerThread = new Thread(new IncomingMessageListener(in));
            listenerThread.start();
            
            // Read and send user messages to the server
            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message); // Send message to the server
                if (message.equalsIgnoreCase("bye")) {
                    break; // Disconnect the client if 'bye' is sent
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Listener to print incoming messages from other clients
    private static class IncomingMessageListener implements Runnable {
        private BufferedReader in;

        public IncomingMessageListener(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message); // Display incoming message
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
