package ru.inno.lec17.HW;

public class telegramNotifier implements Notifier {

    private Notifier mailNotifier;

    public telegramNotifier(Notifier mailNotifier) {
        this.mailNotifier = mailNotifier;
    }

    @Override
    public void Notify() {
        mailNotifier.Notify();

        System.out.println("notify by telegram");

    }
}
