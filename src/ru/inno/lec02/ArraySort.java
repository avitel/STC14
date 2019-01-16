package ru.inno.lec02;

/**
 * Класс выполняющий сортировку целочисленного массива
 * @author Ilya Kruglov
 * @version 1.0
 */
public class ArraySort {

    /**
     * Выполняет сортировку целочисленного массива методом пузырька
     * @param arr - целочисленный массив, который требуется отсортировать
     */
    public static void Bubblesort(int[] arr) {

        int out, in;

        for (out = arr.length - 1; out > 1; out--)
            for (in = 0; in < out; in++)
                if (arr[in] > arr[in + 1])
                    swap(arr, in, in + 1);
    }

    private static void swap(int[] arr, int one, int two) {
        int temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

    /**
     * Выводит в консоль целочисленный массив
     * @param arr - целочисленный массив, который требуется вывести
     */
    public static void print(int[] arr) {
        for (int i : arr)
            System.out.println(i);

    }
}
