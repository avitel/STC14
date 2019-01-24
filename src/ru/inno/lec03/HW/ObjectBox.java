package ru.inno.lec03.HW;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.Random;

/**
 * Класс для хранения массива в упорядоченом виде и различных манипуляций с ним
 * @param <T>
 */


public class ObjectBox<T extends Comparable> {

    private TreeSet<T> in;
    private final int id;


    public static void main(String[] args) {

        System.out.println("-------пример с String----------");
        ObjectBox<String> mb = new ObjectBox<>(new String[]{"f","e","asdfewg","qomciwi"});
        System.out.println(mb);

        mb.deleteObject("e");
        System.out.println(mb);

        System.out.println("----------------пример с Long-------------");
        ObjectBox<Long> mb1 = new ObjectBox<>(new Long[]{9l,4l,6l,8l,5l,3l,12l,7l,18l,15l,13l});
        System.out.println(mb1);
        }

    public TreeSet<T> getIn() {
        return in;
    }

    /**
     *
     * @param arr
     */
    public ObjectBox(T[] arr) {
        in = new TreeSet<T>(Arrays.asList(arr));

        Random random = new Random();
        id = random.nextInt();
    }


    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Box content{"+ in + '}';
    }

    /**
     *
     * @return
     */
    public String dump() {
        return "dump{"+ in +'}';
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        return getId() == ((MathBox)obj).getId();
    }


    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return id;
    }


    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     *
     * @param val
     */
    public void addObject(T val){
        in.add(val);
    }

    /**
     *
     * @param val
     */
    public void deleteObject(T val){
        in.remove(val);
    }
}
