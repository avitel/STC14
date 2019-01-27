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
 *
 *
 */

public class Server {

    private final CopyOnWriteArrayList<ClientData> clients = new CopyOnWriteArrayList<>();
    public static final int PORT = 10000;
    public ServerSocket serverSocket;


    /**
     *
     * @param c
     */
    public void addClient(ClientData c) {

        clients.add(c);
        int ind = clients.size()-1;

        System.out.println("client  " + c.name + " connected");
        c.sendToClent(c.name + " welcome on board!");
        sendToAll(c.name + " joined the chat", ind);

    }


    /**
     *
     * @param ind
     * @return
     */
    public ClientData getClient(int ind) {
        return clients.get(ind);
    }


    /**
     *
     * @param ind
     */
    public void removeClient(int ind) {
        ClientData client = getClient(ind);
        try {
            client.reader.close();
            client.writer.close();
            client.socket.close();
        } catch (IOException e) {
            System.out.println("close resourses error");
        }
        clients.remove(ind);
    }


    /**
     *
     * @return
     */
    public List<ClientData> getClients() {
        return clients;
    }


    /**
     * @param message
     */
    public void sendToAll(String message, int except) {

        for (int i = 0; i < clients.size(); i++) {
            if (i != except) sendToClent(i, message);
        }
    }


    /**
     *
     * @param ind
     * @param message
     */
    public void sendToClent(int ind, String message) {

        ClientData client = clients.get(ind);
        client.sendToClent(message);
    }


    /**
     *
     * @param ind
     */
    public void leaveChat(int ind){
        ClientData client = clients.get(ind);

        sendToAll(client.name + " leaved the chat", ind);
        System.out.println(client.name + " leaved the chat");
        removeClient(ind);
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = new Server();
        server.serverSocket = new ServerSocket(PORT);

        ServerSocketProcessor sp = new ServerSocketProcessor(server);
        sp.start();

        Thread listener = new Listener(server);
        listener.start();

        // keyboard reading
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


/**
 *
 */
class ClientData{

    public Socket socket;
    public String name;
    public BufferedWriter writer;
    public BufferedReader reader;


    public ClientData(Socket socket, String name, BufferedWriter bw, BufferedReader br) {
        this.socket = socket;
        this.name = name;
        this.writer = bw;
        this.reader = br;
    }


    public void sendToClent(String message) {

        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("send message error to " + name);
            System.out.println(e);
        }
    }

}










