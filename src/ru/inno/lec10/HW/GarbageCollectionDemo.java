package ru.inno.lec10.HW;

import java.util.ArrayList;
import java.util.Random;

public class GarbageCollectionDemo {

    private static final int LOOP_COUNT = 10_000;


    public static void main(String[] args) throws InterruptedException {
        var1();
    }


    /**
     * out of memory heap
     * @throws InterruptedException
     */
    public static void var1() throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();

        Random random = new Random();
        String str2 ="";
        int remCount=0;
        for (int i = 0; i < LOOP_COUNT; i++) {
            String str = "Qqqqqqqqqqqqjkdjdkljfsdlfjsdlfjlsdfsdlfldsfldsfldsfsdlfslfjlsfjslfjlsdjfldsfjlsdfldskfvnkrvhisernvkjfvsiuehfd" + random.nextInt();
            for (int j = 0; j < 100; j++) {
                str2 = str2 + str;
            }

            list.add(str2);
            Thread.sleep(1);

            if (remCount++ > 30){
                remCount =0;
                System.out.println("removing");
                for (int j = list.size()-1; j >= 0; j--) {
                   list.remove(j);
                }
                list.trimToSize();
            }
        }

    }



    /**
     * out of memory metaspace
     * @throws InterruptedException
     */
    public static void var2() throws InterruptedException {

        ArrayList<Object> arr = new ArrayList<>();

        String className = "SomeClass";
        String packagePath = "ru.inno.lec10.HW";

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            s.append(ru.inno.lec08.HW.chatV2.ClientBot.getRandomtext());
        }

        String code = "String s = \""+ s.toString() + "\";";

        //load class
        for (int i = 0; i < LOOP_COUNT ; i++) {

            className = "SomeClass" +i;

            //decorate class
            StringBuilder sourse = new StringBuilder();
            sourse.append("package "+packagePath+";\n\n" +
                    "public class " + className +" implements Worker{\n" +
                    //"@Override\n" +
                    "public void doWork(){\n" +
                    code +
                    "}\n" +
                    "}\n");

            //write class.java
            String filename = "./Files/Class/"+ className + ".java";
            ru.inno.lec07.HW.CompilerOnFly.saveFile(sourse, filename);

            //compile
            ru.inno.lec07.HW.CompilerOnFly.compile(filename);

            //load class
            ru.inno.lec10.HW.MyClassLoader loader = new ru.inno.lec10.HW.MyClassLoader();
            try {
                Class<?> clazz = loader.loadClass(packagePath +"."+ className);
                Object someClass = (Worker) clazz.newInstance();
                arr.add(someClass);
            }catch (ClassNotFoundException |InstantiationException|IllegalAccessException e){
                System.out.println(e);
            }

            Thread.sleep(1);
        }
    }
}


