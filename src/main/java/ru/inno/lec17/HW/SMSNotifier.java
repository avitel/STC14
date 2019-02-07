package ru.inno.lec17.HW;

public class SMSNotifier implements Notifier{

    private Notifier mainNotifier;

    public SMSNotifier(Notifier mainNotifier) {
        this.mainNotifier = mainNotifier;
    }


    @Override
    public void Notify() {
        mainNotifier.Notify();
        System.out.println("notify by sms");
    }
}
