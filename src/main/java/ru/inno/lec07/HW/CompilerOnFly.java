package ru.inno.lec07.HW;


import ru.inno.lec04.HW.MyUtilities;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

/**
 * class do only one function : compilation on fly
 */
public class CompilerOnFly {

    private String className;
    private String packagePath;


    public CompilerOnFly(String className, String packagePath) {
        this.className = className;
        this.packagePath = packagePath;
    }

    public CompilerOnFly() {}


    /**
     * perform input code, compile, load and run
     */
    public void doCompileOnFly() {

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
                "public class " + className + " implements WorkerOld{\n" +
                //"@Override\n" +
                "public void doWork(){\n" +
                code +
                "}\n" +
                "}\n");

        //write class.java
        String filename = "./"+className + ".java";
        MyUtilities.saveFile(sourse, filename);

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
