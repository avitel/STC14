package ru.inno.lec02;

/**
 * Приложение для демонстрации работы алгоритмов сортировки
 * @author Kruglov Ilya
 * @version 1.0
 */
public class ArraySortApp {

        public static void main(String[] args) {

            System.out.println("Пример сортировки Integer");

            Integer[] arr = getIntArr();
            ArraySort.print(arr);
            ArraySort.bubbleSort(arr);
            System.out.println("----------------");
            ArraySort.print(arr);

            System.out.println("----------------");
            System.out.println("Пример сортировки String");
            System.out.println("----------------");

            String[] arr1 = getStringArr();
            ArraySort.print(arr1);
            ArraySort.bubbleSort(arr1);
            System.out.println("----------------");
            ArraySort.print(arr1);

        }


        private static Integer[] getIntArr(){
            int maxSize = 10;

            Integer[] arr =  new Integer[maxSize];

            for(int j=0; j<maxSize; j++)
                arr[j] = (int)(java.lang.Math.random()*99);

            arr[5] = null;

            return arr;
        }


        private static String[] getStringArr(){
            int maxSize = 10;
            int strSize = 10;

            char[] currentStr = new char[strSize];

            String[] arr =  new String[maxSize];

            for(int j=0; j<maxSize; j++) {
                for (int k = 0; k < strSize; k++)
                    currentStr[k] = (char)(int) (java.lang.Math.random() * 57 + 65);//коды символов от A(65) до z(122)
                arr[j] = new String(currentStr);
            }

            return arr;
        }

}


