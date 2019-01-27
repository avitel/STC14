package ru.inno.lec08.HW.chatV2;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientBot {

    public static List<Client> clients = new ArrayList<>();
    public static List<ClientSocketReader> clientsReaders = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {

        //clients connection
        for (int i = 0; i < 50; i++) {
            Client client = new Client();

            Random random = new Random();
            String name = "user " + i;

            Socket socket = client.connect(name);

            ClientSocketReader clientReader = new ClientSocketReader(socket);

            clients.add(client);
            clientsReaders.add(clientReader);
        }

        Thread.sleep(100);


        //chatting
        for (int i = 0; i < 50; i++) {

            for (Client client : clients) {
                String message = getRandomtext();
                client.send(message);
                Thread.sleep(1);
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
            Thread.sleep(10);
        }

        for (ClientSocketReader client : clientsReaders) {
            client.close();
        }

        for (Client client : clients) {
            client.close();
        }


    }


    public static String getRandomtext(){
        Random rnd = new Random();
        int length = rnd.nextInt(100);
        StringBuilder builder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        return builder.toString();
    }
}
