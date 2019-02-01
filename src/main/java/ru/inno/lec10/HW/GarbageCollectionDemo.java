package ru.inno.lec10.HW;

import ru.inno.lec04.HW.MyUtilities;
import ru.inno.lec07.HW.CompilerOnFly;

import java.util.ArrayList;
import java.util.Random;

/**
 * class for demonstration garbage collector work
 */
public class GarbageCollectionDemo {



    /**
     * out of memory heap
     * @throws InterruptedException
     */
    public void getHeapException(int mainCycle, int removeOn) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();

        Random random = new Random();
        String str2 ="";
        int remCount=0;
        for (int i = 0; i < mainCycle; i++) {
            String str = "jkdjdkljfsdlfjsdlfjlsdfsdlfldsfldsfldsfsdlfslfldsfjlsdfldskfvnkrvhisernvkjfvsiuehfd" + random.nextInt();
            for (int j = 0; j < 100; j++) {
                str2 = str2 + str;
            }

            list.add(str2);
            Thread.sleep(1);

            if (remCount++ > removeOn){
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
    public void getMetaspaceException(int mainCycle) throws InterruptedException {

        ArrayList<Object> arr = new ArrayList<>();

        String className = "SomeClass";
        String packagePath = "ru.inno.lec10.HW";

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            s.append(MyUtilities.getRandomtext());
        }

        String code = "String s = \""+ s.toString() + "\";";

        //load class
        for (int i = 0; i < mainCycle ; i++) {

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
            MyUtilities.saveFile(sourse, filename);

            //compile
            CompilerOnFly compiler = new CompilerOnFly();
            compiler.compile(filename);

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


