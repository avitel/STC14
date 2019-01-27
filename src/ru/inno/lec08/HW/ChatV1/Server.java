package ru.inno.lec08.HW.ChatV1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 *
 */

public class Server {

    private final List<ServerSocketProcessor> clientThreads = new CopyOnWriteArrayList<>();
    public static final int PORT = 10000;
    public ServerSocket serverSocket;


    public void addClientThread(ServerSocketProcessor t) {
        clientThreads.add(t);
    }


    public void removeClientThread(ServerSocketProcessor t) {
        clientThreads.remove(t);
    }

    public List<ServerSocketProcessor> getClientThreads() {
        return clientThreads;
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = new Server();
        server.serverSocket= new ServerSocket(PORT);

        Thread listener = new Listener(server);
        listener.start();

        // keyboard reading
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

        String word = null;
        while (!"quit".equals(word)){
            word = keyboardReader.readLine();
        }


        for (ServerSocketProcessor clientThread : server.clientThreads) {
            clientThread.interrupt();
        }


        listener.interrupt();
        try {
            Socket socket = new Socket("127.0.0.1", PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener.join();


        System.out.println("server closed");
    }



    /**
     *
     * @param message
     */
    public void sendToAll(String message, Thread except){

        for (ServerSocketProcessor t : clientThreads) {
            if (t != except) t.sendToClent(message);
        }
    }

}








