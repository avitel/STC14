package ru.inno.lec08.HW.ChatV1;

import java.io.*;
import java.net.Socket;

/**
 *
 *
 */
public class Client {

    private Socket socket;

    public static void main(String[] args) {

        Client client = new Client();
        client.InputAndSend();
    }



    public void InputAndSend(){

        try {
            socket = new Socket("127.0.0.1", Server.PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        //
        String name = "";
        System.out.println("Enter your name please:");
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            name = keyboardReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // write text to server
        try{

            OutputStream os = socket.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(name + "\n");
            bw.flush();

            //start reader
            ClientSocketReader socketReader = new ClientSocketReader(socket);
            socketReader.setDaemon(true);
            socketReader.start();


            //waiting for enter a string
            String word = null;
            while (!"quit".equals(word)){

                word = keyboardReader.readLine();

                if (socket.isClosed()) {
                    break;
                }

                bw.write(word + "\n");
                bw.flush();
            }
            socketReader.interrupt();

            os.close();

        }catch (IOException e){
            System.out.println(e);
        }


        System.out.println("client closed");
    }
}
