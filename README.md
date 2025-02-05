# MULTITHREADED-CHAT-APPLICATION

**COMPANY** : CODTECH IT SOLUTION

**NAME** : PURVA KISHOR BAKARE

**INTERN ID** : CT08GOM

**DOMAIN** : JAVA PROGRAMMING

**BATCH DURATION** : JANUARY 5th, 2025 to FEBRUVARY 5th, 2025

**MENTOR NAME** : NEELA SANTOSH

**DESCRIPTION OF TASK** : This Java-based chat application enables multiple clients to communicate with each other through a centralized server. The system consists of two main components:

ChatServer: Manages client connections, assigns names, and broadcasts messages.

ChatClient: Connects to the server, sends and receives messages.

The chat application allows real-time communication among multiple users over a network using Java's networking and I/O libraries.

Tools and Resources Used :
1. Java Development Kit (JDK)
The program is written in Java and requires JDK for compilation and execution.
Java provides built-in networking capabilities, which are leveraged in this project.

2. Integrated Development Environment (IDE)
An IDE such as IntelliJ IDEA, Eclipse, or NetBeans can be used for writing, debugging, and executing the program.

3. Java Networking Libraries
Java provides built-in classes for network communication via sockets.
The java.net package is used to establish client-server communication.

4. Input/Output (I/O) Handling
The java.io package is used for reading and writing data between clients and the server.
BufferedReader and PrintWriter are used for efficient handling of input and output streams.

5. Multithreading
Each client connection is handled using separate threads, allowing multiple users to communicate concurrently.
The Thread class is used to manage individual client sessions.

Libraries Used :
1. java.net (Networking Library)
ServerSocket: Listens for incoming client connections.
Socket: Establishes a connection between the client and server.

2. java.io (Input/Output Library)
BufferedReader: Reads text input from clients.
PrintWriter: Sends text output to clients.
InputStreamReader: Converts byte streams into character streams for easier handling.

3. java.util (Utility Library)
Set and HashSet: Manage connected clients efficiently.
Scanner: Reads user input from the console.

How the Chat Application Works
1. Chat Server (ChatServer.java)
Step 1: Start the Server
The server runs on a specified port (e.g., 12345) and waits for client connections using ServerSocket.
When a client connects, a new thread is spawned to handle that connection.

Step 2: Client Registration
The server requests a username from the client.
If the name is unique, the client is registered; otherwise, the connection is refused.
The username is added to a Set to ensure uniqueness.

Step 3: Broadcasting Messages
When a client sends a message, the server relays it to all connected clients using a Set of PrintWriter objects.
This enables real-time communication.

Step 4: Handling Disconnections
If a client sends "bye", the connection is closed.
The client's name is removed from the active list, ensuring no orphaned connections.

2. Chat Client (ChatClient.java)
Step 1: Connect to the Server
The client initiates a connection to the server using Socket.
It establishes input and output streams for communication.

Step 2: User Interaction
The user enters their name, which is sent to the server.
A separate thread (IncomingMessageListener) listens for messages from the server and displays them.
The user can send messages via the console.

Step 3: Handling Incoming Messages
The IncomingMessageListener thread continuously reads and prints messages from the server.
This allows real-time message updates from other clients.

Step 4: Exiting the Chat
If the user types "bye", the connection is terminated.
The server is notified to remove the client from active connections.

Error Handling
1. Connection Issues
If the server is down, the client displays an error message and exits.
If a client disconnects unexpectedly, the server removes them gracefully.

2. Username Conflicts
If a client tries to use an already taken name, they receive a message and are disconnected.

3. Handling Input/Output Errors
Try-catch blocks ensure that network failures do not crash the application.
The server cleans up inactive connections to prevent resource leakage.

**OUTPUT OF THE TASK** :
