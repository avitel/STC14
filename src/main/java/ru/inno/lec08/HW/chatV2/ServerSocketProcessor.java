package ru.inno.lec08.HW.chatV2;

import java.io.*;
import java.util.List;

/**
 * class perform receive messages from clients and forwards to others
 */
public class ServerSocketProcessor extends Thread{
    private final Server server;


    public ServerSocketProcessor(Server server) {
        this.server = server;
    }


    @Override
    public void run() {
        System.out.println("server socket processor started");

        while (true) {

            if (isInterrupted()) break;

            List<ClientData> clients = server.getClients();

            for (int ind = 0; ind < clients.size(); ind++) {

                if (isInterrupted()) break;

                ClientData client = clients.get(ind);
                BufferedReader reader = client.getReader();
                try {

                    if (reader.ready()){
                        String message = reader.readLine();

                        if ("quit".equals(message)) {
                            server.leaveChat(ind);
                            break;
                        }
                        server.sendToAll(client.getName() + " : " + message, ind);
                    }

                }catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }catch (IOException e) {
                    server.removeClient(ind);
                    server.sendToAll(client.getName() + " disconnected" , ind);
                    System.out.println(client.getName() + " disconnected");
                    break;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("server socket processor closed");
    }
}
