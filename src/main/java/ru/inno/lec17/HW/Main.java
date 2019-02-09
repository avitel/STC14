package ru.inno.lec17.HW;


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
