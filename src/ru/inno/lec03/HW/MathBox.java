package ru.inno.lec03.HW;

import java.util.TreeSet;

/**
 * Класс для хранения коллекции в упоря виде, получения суммы и деления
 *
 * @param <T>
 */

public class MathBox<T extends Number & Comparable> extends ObjectBox{


    public static void main(String[] args) {

        System.out.println("-------пример с Integer----------");
        MathBox<Integer> mb = new MathBox<>(new Integer[]{9,4,6,8,5,3,12,7,18,15,13});
        System.out.println(mb);
        System.out.println("summ : " + mb.summator());

        TreeSet<Double> resDiv = mb.splitter( 5d);
        System.out.println(resDiv);
        mb.deleteObject(12);
        System.out.println(mb);

        System.out.println("------------пример с Float--------------");
        MathBox<Float> mb1 = new MathBox<>(new Float[]{9f,4f,6f,8f,5f,3f,12f,7f,18f,15f,13f});
        System.out.println(mb1);
        System.out.println("summ : " + mb1.summator());

        TreeSet<Double> resDiv1 = mb1.splitter( 5d);
        System.out.println(resDiv1);
    }

    /**
     *
     * @param arr
     */
    public MathBox(T[] arr) {
        super(arr);
    }


    /**
     *
     * @return
     */
    public long summator(){

        long sum = 0l;

        TreeSet<T> in = getIn();
        for (T current : in) {
            sum += current.longValue();
        }
        return sum;
    }


    /**
     *
     * @param div
     * @return
     */
    public TreeSet<Double> splitter(Double div){
        TreeSet<Double> out  = new TreeSet<>();
        TreeSet<T> in = getIn();
        for (T current : in) {
            out.add(current.doubleValue()/div);
        }
        return out;
    }
}
