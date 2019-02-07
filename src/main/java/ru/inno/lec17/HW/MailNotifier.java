package ru.inno.lec17.HW;

public class MailNotifier implements Notifier {

    @Override
    public void Notify() {
        System.out.println("-------------------");
        System.out.println("notify by email");
    }
}
