package ru.inno.lec08.HW.chatV2;

import ru.inno.lec04.HW.MyUtilities;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * bot for immitation of chatting
 */
public class ClientBot {

    public static List<Client> clients = new ArrayList<>();
    public static List<ClientSocketReader> clientsReaders = new ArrayList<>();


    public static void main(String[] args) {
        ClientBot cb = new ClientBot();
        cb.start();

    }

    public ClientBot() {}

    public  void start() {

        //clients connection
        for (int i = 0; i < 10; i++) {
            Client client = new Client();

            Random random = new Random();
            String name = "user " + i;

            Socket socket = client.connect(name);

            ClientSocketReader clientReader = new ClientSocketReader(socket);

            clients.add(client);
            clientsReaders.add(clientReader);
        }

        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        //chatting
        for (int i = 0; i < 50; i++) {

            for (Client client : clients) {
                String message = MyUtilities.getRandomtext();
                client.send(message);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            for (ClientSocketReader client : clientsReaders) {
                client.getMessage();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //closing
        for (Client client : clients) {
            client.send("quit");
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        for (ClientSocketReader client : clientsReaders) {
            client.close();
        }

        for (Client client : clients) {
            client.close();
        }
    }
}
