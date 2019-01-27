package ru.inno.lec06.HW;

import ru.inno.lec04.HW.MyUtilities;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *  perform covert Object to JSON and back
 */
public class Serialize {

    /**
     * serialize Object to JSON and save to file
     *
     * @param in
     * @param filename
     */
    public void serialize(Object in, String filename){

        StringBuilder out = new StringBuilder();

        out.append("{");
        Class clazz = in.getClass();
        addKeyValue(out, "class", "\"" + clazz.getName() + "\"");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            out.append(", \n");

            field.setAccessible(true);
            try {
                addKeyValue(out, field.getName(), convertToString(field.get(in)));
            }catch (IllegalAccessException e){
                System.out.println(e);
            }
        }
        out.append("}");
        MyUtilities.saveFile(out, filename);
    }


    /**
     * Adds key-value pair in stringBuilder
     *
     * @param out
     * @param key
     * @param value
     */
    private void addKeyValue(StringBuilder out, String key, String value){
        out.append("\"" + key + "\":" + value);

    }


    /**
     * return name of object. If it is string - surrounds it with ""
     *
     * @param o
     * @return
     */
    private String convertToString(Object o) {
        if (o == null) return "null";
        else {
            if ("String".equals(o.getClass().getSimpleName())) return "\"" + o + "\"";
            else return o.toString();
        }
    }


    /**
     * create Object from JSON file
     *
     * @param filename
     * @return
     */
    public Object deSerialize(String filename){

        Map<String, String> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))){
            int letter;
            StringBuilder builder = new StringBuilder();
            boolean objectOpened = false;
            String key = "";
            String value = "";
            while ((letter = br.read()) != -1){
                if (letter == '{') {
                    objectOpened = true;
                    continue;
                }
                else if (letter == '}') {
                    value = builder.toString();
                    processKeyValue(map, key, value);
                    if (objectOpened) break;
                }
                else if (letter == ':') {
                    key = builder.toString();
                    builder = new StringBuilder();
                    continue;
                }
                else if (letter == ',') {
                    value = builder.toString();
                    builder = new StringBuilder();
                    processKeyValue(map, key, value);
                    continue;
                }

                builder.append((char)letter);
            }

        }catch (IOException e){
            System.out.println("file parsing error");
        }

        return getObjectFromMap(map);
    }



    /**
     * cut excess quotes from key-value and puts it to map
     *
     * @param map
     * @param key
     * @param value
     */
    private void processKeyValue(Map<String, String> map, String key, String value){

        map.put(key.replaceAll("\n|\"| ",""), value.replaceAll("\n|\"| ",""));
    }



    /**
     * create object from map
     *
     * @param map
     * @return
     */
    private Object getObjectFromMap(Map<String, String> map){

        String className = map.get("class");

        if (className == null){
            System.out.println("there no field \"class\" in the map. Creating object not possible.");
            return null;
        }

        Object obj;

        try{
            Class c = Class.forName(className);
            obj = c.newInstance();
            map.remove("class");

            for (Map.Entry<String, String> entry : map.entrySet()) {

                Field field = c.getDeclaredField( entry.getKey());
                field.setAccessible(true);
                Class fieldType = field.getType();

                String fieldName = fieldType.getSimpleName();
                String strValue = entry.getValue();

                if ("String".equals(fieldName)){
                    field.set(obj,strValue);
                }
                else if ("int".equals(fieldName)){
                    field.set(obj,Integer.parseInt(strValue));
                }
                else if ("double".equals(fieldName)){
                    field.set(obj,Double.parseDouble(strValue));
                }
                else if ("boolean".equals(fieldName)){
                    field.set(obj,Boolean.parseBoolean(strValue));
                }
            }

        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException e){
            System.out.println(e);
            return null;
        }
        return obj;
    }
}


