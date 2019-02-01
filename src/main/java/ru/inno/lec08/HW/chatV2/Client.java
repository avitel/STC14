package ru.inno.lec08.HW.chatV2;

import java.io.*;
import java.net.Socket;

/**
 * client process
 *
 */
public class Client {

    private Socket socket;
    private BufferedWriter writer;



    public static void main(String[] args) {

        Client client = new Client();
        client.InputAndSend();
    }


    /**
     * Connects to server
     * @param name
     * @return
     */
    public Socket connect(String name){
        try {
            socket = new Socket("127.0.0.1", Server.PORT);
            OutputStream os = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(name + "\n");
            writer.flush();

            return socket;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * sends message to server
     * @param message
     */
    public void send(String message){
        if (!socket.isClosed()){

            try {
                writer.write(message + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * closes resourses
     */
    public void close(){
        try{
            writer.close();
            socket.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * reads message from keyboard and sends to socket
     */
    public void InputAndSend(){

        String name = "";
        System.out.println("Enter your name please:");
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            name = keyboardReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connect(name);

        ClientSocketReader socketReader = new ClientSocketReader(socket);
        socketReader.setDaemon(true);
        socketReader.start();

        while (true){

            String message = null;

            try {
                message = keyboardReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                close();
                break;
            }

            if (socket.isClosed()) break;

            send(message);
            if ("quit".equals(message)) break;

        }

        socketReader.interrupt();

        close();

        System.out.println("client closed");
    }
}
