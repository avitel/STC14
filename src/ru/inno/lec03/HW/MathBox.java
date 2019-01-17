package ru.inno.lec03.HW;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class MathBox<T extends Number> {

    private TreeSet<T> in;
    final private int id;

    public int getId() {
        return id;
    }


    public MathBox(T[] arr) {
        in = new TreeSet<T>(Arrays.asList(arr));

        id = (int)Math.random()*2147483647;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        return getId() == ((MathBox)obj).getId();
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "in=" + in +
                '}';
    }

    public void print(){
        for (T current : in) {
            System.out.println(current);
        }
    }

    public long summator(){

        long sum = 0l;
        for (T current : in) {

            sum += current.longValue();
        }
        return sum;
    }

    public TreeSet<Double> splitter(Double div){
        TreeSet<Double> out  = new TreeSet<Double>();
        for (T current : in) {
            out.add(current.doubleValue()/div);
        }
        return out;
    }

    public void delete(T val){
        in.remove(val);
    }




    public static void main(String[] args) {

        MathBox<Integer> mb = new MathBox<Integer>(new Integer[]{9,4,6,8,5,3,12,7,18,15,13});
        mb.print();
        System.out.println("summ : " + mb.summator());
        TreeSet<Double> resDiv = mb.splitter( 5d);
        for (Double aDouble : resDiv) {
            System.out.println(" res div : " + aDouble);
        }
        mb.delete(99);
        mb.print();

        MathBox<Long> mb1 = new MathBox<Long>(new Long[]{9l,4l,6l,8l,5l,3l,12l,7l,18l,15l,13l});
        mb1.print();
        System.out.println("summ : " + mb1.summator());
        TreeSet<Double> resDiv1 = mb1.splitter( 5d);
        for (Double aDouble : resDiv1) {
            System.out.println(" res div : " + aDouble);
        }

    }
}
