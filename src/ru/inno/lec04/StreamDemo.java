package ru.inno.lec04;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamDemo {
    public static void main(String[] args)  throws IOException{
        byte[] arr = "Hello мир".getBytes();
        InputStream byteArrayInputStream = new ByteArrayInputStream(arr);
        int b;
        while ((b = byteArrayInputStream.read()) != -1) {
            System.out.println((char)b);
        }
    }
}
