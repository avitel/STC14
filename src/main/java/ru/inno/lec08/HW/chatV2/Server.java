package ru.inno.lec08.HW.chatV2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class
 *
 */

public class Server {

    private final CopyOnWriteArrayList<ClientData> clients = new CopyOnWriteArrayList<>();
    public static final int PORT = 10000;
    private ServerSocket serverSocket;


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Adds client to array and send messages
     * @param client
     */
    public void addClient(ClientData client) {

        clients.add(client);
        int ind = clients.size()-1;

        String name = client.getName();

        System.out.println("client  " + name + " connected");
        client.sendToClent(name + " welcome on board!");
        sendToAll(name + " joined the chat", ind);
    }


    /**
     * Gets array of clients data
     *
     * @return
     */
    public List<ClientData> getClients() {
        return clients;
    }


    /**
     * Send message to all clients exept one
     *
     * @param message
     */
    public void sendToAll(String message, int except) {

        for (int i = 0; i < clients.size(); i++) {
            if (i != except) {
                sendToClent(i, message);
            }
        }
    }


    /**
     * Send message to client with index
     *
     * @param ind
     * @param message
     */
    public void sendToClent(int ind, String message) {

        ClientData client = clients.get(ind);
        if (!client.sendToClent(message)) {
            removeClient(ind);
        }
    }


    /**
     * Remove client data with index
     *
     * @param ind
     */
    public void removeClient(int ind) {
        ClientData client = clients.get(ind);
        client.closeResourses();
        clients.remove(ind);
    }


    /**
     * remove client data and send messages
     *
     * @param ind
     */
    public void leaveChat(int ind){
        ClientData client = clients.get(ind);
        String name = client.getName();

        sendToAll(name + " leaved the chat", ind);
        System.out.println(name + " leaved the chat");
        removeClient(ind);
    }



    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = new Server();
        server.serverSocket = new ServerSocket(PORT);

        ServerSocketProcessor sp = new ServerSocketProcessor(server);
        sp.start();

        Thread listener = new Listener(server);
        listener.start();

        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

        String word = null;
        while (!"quit".equals(word)) {
            word = keyboardReader.readLine();
        }

        sp.interrupt();
        listener.interrupt();

        //инициируем соединение чтобы вывести listener из ожидания
        try {
            Socket socket = new Socket("127.0.0.1", PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sp.join();
        listener.join();

        System.out.println("server closed");
    }
}










