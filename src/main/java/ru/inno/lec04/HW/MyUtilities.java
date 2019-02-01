package ru.inno.lec04.HW;

import ru.inno.lec07.HW.MyClassLoader;
import ru.inno.lec07.HW.Worker;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class MyUtilities {


    /**
     * save stringBuilder to file
     *
     * @param out
     * @param filename
     */
    public static void saveFile(StringBuilder out, String filename) {

        try {
            Files.write(Paths.get(filename), out.toString().getBytes());
        } catch (IOException e) {
            System.out.println("saving file error");
        }
    }


    /**
     * save collection to file
     *
     * @param arr
     * @param filename
     */
    public static void saveCollectionToFile(Collection arr, String filename){
        try(FileWriter writer = new FileWriter(filename, false)) {
                for (Object elem : arr) {
                    writer.write(elem.toString());
                    writer.write('\n');
                }

        }catch (IOException e){
            System.out.println("save result to collection error");
        }
    }

    /**
     * get random text
     *
     * @return
     */
    public static String getRandomtext(){
        Random rnd = new Random();
        int length = rnd.nextInt(100);
        StringBuilder builder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        return builder.toString();
    }


    /**
     * make sourse file from string, compile and load class
     * Interface Worker must have
     *
     * @param code
     * @param packagePath
     * @param className
     */
    public void loadClass(String code, String packagePath, String className){

        //decorate class
        StringBuilder sourse = new StringBuilder();
        sourse.append("package "+packagePath+";\n\n" +
                "public class " + className + " implements Worker {\n" +
                //"@Override\n" +
                "public void doWork(){\n" +
                code +
                "}\n" +
                "}\n");

        //write class.java
        String filename = "./"+className + ".java";
        saveFile(sourse, filename);

        //compile
        compile(filename);

        //load class
        Worker someClass = null;
        MyClassLoader loader = new MyClassLoader();
        try {
            Class<?> clazz = loader.loadClass(packagePath +"."+ className);
            someClass = (Worker) clazz.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println(e);
        }

    }


    /**
     * Compiles file. Compiled file is put in the same directory.
     *
     * @param filename
     */
    public void compile(String filename){

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

        String[] javacOpts = {filename};
        try{
            javac.run(null, null, null, javacOpts);
        }catch (Exception e){
            System.out.println("compilation exception " + e);
        }
    }

}
