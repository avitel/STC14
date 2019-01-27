package ru.inno.lec08.HW.chatV2;

import java.io.*;
import java.util.List;

public class ServerSocketProcessor extends Thread{
    private final Server server;


    public ServerSocketProcessor(Server server) {
        this.server = server;
    }


    /**
     *
     */
    @Override
    public void run() {
        System.out.println("server socket processor started");

        while (true) {

            if (isInterrupted()) break;

            List<ClientData> clients = server.getClients();

            for (int ind = 0; ind < clients.size(); ind++) {

                if (isInterrupted()) break;

                try {
                    ClientData client = clients.get(ind);

                    if (client.reader.ready()){
                        String message = client.reader.readLine();

                        if ("quit".equals(message)) {
                            server.leaveChat(ind);
                            break;
                        }
                        server.sendToAll(client.name + " : " + message, ind);
                    }

                }catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }catch (IOException e) {
                    System.out.println(e);
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
