package ru.inno.lec02;

import java.util.Iterator;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int arrB[] = new int[15];

        for (int i: arr) {
            System.out.println(i);
        }
    }
}

class MyArray implements Iterable {

    @Override
    public Iterator iterator() {
        return null;
    }
}