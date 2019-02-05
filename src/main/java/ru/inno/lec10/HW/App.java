package ru.inno.lec10.HW;

public class App {

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0){
            System.out.println("enter param please");
            return;
        }

        GarbageCollectionDemo gc = new GarbageCollectionDemo();

        String arg = args[0];

        switch (arg){
            case "heapLeaks":
                gc.getHeapException(10000, 30);
                break;
            case "heapSimple":
                Thread.sleep(5000);
                int[] arra = new int[4_000_000];
                Thread.sleep(5000);
                break;
            case "heap":
                gc.getHeapException(10000, 10000);
                break;


                // VM option is need to set
                // -XX:MaxMetaspaceSize=20M
            case "meta":
                gc.getMetaspaceException(10000);
                break;
             default:
                 System.out.println("unknoun param");

        }
    }
}
