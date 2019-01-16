package ru.inno.lec02;

/**
 * Класс выполняющий сортировку ссылочного массива
 * @author Ilya Kruglov
 * @version 1.0
 */
public class ArraySort {

    /**
     * Выполняет сортировку массива Integer методом пузырька
     * @param arr -  массив объектов, который требуется отсортировать
     */
    public static void Bubblesort(Integer[] arr) {

        int out, in;

        if (arr == null){
            System.out.println("В процедуру сортировки передан null вместо массива ");
            return;
        }

        try {
            for (out = arr.length - 1; out > 1; out--)
                for (in = 0; in < out; in++)
                    if (arr[in] > arr[in + 1])
                        swap(arr, in, in + 1);
        }
        catch (NullPointerException e){
            System.out.println("В массиве имеется null. Сортировка не выполнена.");
        }
    }

    /**
     * Выполняет сортировку массива String методом пузырька
     * @param arr -  массив объектов, который требуется отсортировать
     */
    public static void Bubblesort(String[] arr) {

        int out, in;

        if (null == arr){
            System.out.println("В процедуру сортировки передан null вместо массива ");
            return;
        }

        try {
            for (out = arr.length - 1; out > 1; out--)
                for (in = 0; in < out; in++)
                    if (arr[in].compareTo(arr[in + 1]) > 0)
                        swap(arr, in, in + 1);
        }
        catch (NullPointerException e) {
            System.out.println("В массиве имеется null. Сортировка не выполнена.");
        }
    }

    private static void swap(Object[] arr, int one, int two) {
        Object temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
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
