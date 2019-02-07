package ru.inno.lec17.HW;

import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Notifier myNotifier = new MailNotifier();
        myNotifier.Notify();

        myNotifier = new SMSNotifier(myNotifier);
        myNotifier.Notify();

        myNotifier = new telegramNotifier(myNotifier);
        myNotifier.Notify();

        //классичекий пример
        //BufferedReader br = new BufferedReader(new FileReader("dfdf")));


    }
}
