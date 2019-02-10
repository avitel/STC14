package ru.inno.lec08.HW.chatV2;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Pipe {

    private final CopyOnWriteArrayList<ClientData> clients = new CopyOnWriteArrayList<>();


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



}
