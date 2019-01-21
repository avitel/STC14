package ru.inno.lec02.HW;

import java.util.Random;

/**
 * Приложение для демонстрации работы алгоритмов сортировки
 * @author Kruglov Ilya
 * @version 1.0
 */
public class ArraySortApp {

        public static void main(String[] args) {

            System.out.println("Пример сортировки Integer");

            Integer[] arr = getIntArr();
            print(arr);

            ArraySort.bubbleSort(arr);

            System.out.println("----------------");
            print(arr);
        }


        private static Integer[] getIntArr(){
            int maxSize = 10;

            Integer[] arr =  new Integer[maxSize];

            Random random = new Random();

            for(int j=0; j<maxSize; j++)
                arr[j] = (int)(random.nextInt(99));

            //arr[5] = null;

            return arr;
        }

        /**
         * Выводит в консоль ссылочный массив
         * @param arr - ссылочный массив, который требуется вывести
         */
        public static void print(Object[] arr) {
            for (Object i : arr)
                System.out.println(i);

        }


}


