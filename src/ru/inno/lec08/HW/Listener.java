package ru.inno.lec08.HW;

import java.io.IOException;
import java.net.Socket;


public class Listener extends Thread {
    private final Server server;

    public Listener(Server server) {
        this.server = server;

    }


    /**
     *
     */
    @Override
    public void run() {

        System.out.println("server listener has started");

        while (true) {
            try {
                Socket clientSocket = server.serverSocket.accept();

                if (isInterrupted()) break;

                //server socket processor
                ServerSocketProcessor sp = new ServerSocketProcessor(clientSocket, server);
                sp.setDaemon(true);
                sp.start();

                server.addClientThread(sp);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("server listener closed");
    }
}
