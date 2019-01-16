package ru.inno.lec02;

/**
 * Приложение для демонстрации работы алгоритмов сортировки
 * @author Kruglov Ilya
 * @version 1.0
 */
public class ArraySortApp {
        public static void main(String[] args) {
            int maxSize = 16;

            int[] arr =  new int[maxSize];

            for(int j=0; j<maxSize; j++)
                arr[j] = (int)(java.lang.Math.random()*99);;

            ArraySort.print(arr);
            ArraySort.Bubblesort(arr);
            System.out.println("--------------------------------");
            ArraySort.print(arr);
        }
    }


