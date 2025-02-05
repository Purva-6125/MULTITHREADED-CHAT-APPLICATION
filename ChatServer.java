import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345; // Port to listen on
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static Set<String> clientNames = new HashSet<>();
    
    public static void main(String[] args) {
        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start(); // Accept client connections
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Set up input/output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Request and assign client name
                out.println("Enter your name:");
                clientName = in.readLine();
                
                synchronized (clientNames) {
                    if (clientNames.contains(clientName)) {
                        out.println("Name already taken. Disconnecting.");
                        socket.close();
                        return;
                    }
                    clientNames.add(clientName);
                }
                
                synchronized (clientWriters) {
                    clientWriters.add(out); // Add client's writer to the list
                }
                
                // Send welcome message to client
                out.println("Welcome " + clientName + "!");
                
                // Start receiving messages from client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("bye")) {
                        break; // Disconnect the client if 'bye' is sent
                    }
                    sendMessageToClients(clientName + ": " + message); // Broadcast message
                }
                
                // Clean up and close connection
                synchronized (clientNames) {
                    clientNames.remove(clientName);
                }
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                
                socket.close(); // Close client socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Broadcast message to all connected clients
        private void sendMessageToClients(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
