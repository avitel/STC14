package ru.inno.lec10.HW;

public class App {

    public static void main(String[] args) throws InterruptedException {
        GarbageCollectionDemo gc = new GarbageCollectionDemo();
        gc.getHeapException(10000, 30);

        //gc.getMetaspaceException(10000);
    }
}
