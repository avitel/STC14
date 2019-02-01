package ru.inno.lec06.HW;

public class App {

    public static void main(String[] args) {
        testObject t = new testObject(1, "строка", 454.3543534d);

        Serialize serialize = new Serialize();
        serialize.serialize(t, "./testSerialize");

        Object t1 = serialize.deSerialize("./testSerialize");
        if (t.equals(t1)) System.out.println("objects are identical");
        else System.out.println("objects are different! something went wrong!");
    }

}
