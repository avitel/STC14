package ru.inno.lec07.HW;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompilerOnFly {

    private static String className;
    private static String packagePath;

    public static void main(String[] args) {

        className = "SomeClass";
        packagePath = "ru.inno.lec07.HW";

        //reading keyboard
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        StringBuilder code = new StringBuilder();
        while (!" ".equals(str)) {
            try {
                str = reader.readLine();
                code.append(str);
                code.append('\n');
            } catch (IOException e) {
                System.out.println("IO exception");
            }
        }

        //decorate class
        StringBuilder sourse = new StringBuilder();
        sourse.append("package "+packagePath+";\n\n" +
                "public class " + className + " implements Worker{\n" +
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

        //execution
        someClass.doWork();

    }



    public static void saveFile(StringBuilder out, String filename){

        try {
            Files.write(Paths.get(filename), out.toString().getBytes());
        }catch(IOException e){
            System.out.println("saving file error");
        }
    }



    public static void compile(String filename){

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

        String[] javacOpts = {filename};
        try{
            javac.run(null, null, null, javacOpts);
        }catch (Exception e){
            System.out.println("compilation exception " + e);
        }
    }
}
