package ru.inno.lec02.HW;

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
    public static void bubbleSort(Integer[] arr) {

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


    private static void swap(Object[] arr, int one, int two) {
        Object temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

}
