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
    public void bubbleSort(Integer[] arr) throws IllegalArgumentException{

        int out, in;

        if (arr == null){
            throw new IllegalArgumentException("Передан null вместо массива. Сортировка не выполнена.");
        }

        for (out = arr.length - 1; out > 1; out--)
            for (in = 0; in < out; in++)
                if (compare(arr[in], arr[in + 1])>0)
                    swap(arr, in, in + 1);
    }


    /**
     *
     * @param arg1
     * @param arg2
     * @return
     */
    private int compare(Integer arg1, Integer arg2){
        if(arg1 == null) return -1;
        if(arg2 == null) return 1;

        return arg1.compareTo(arg2);
    }


    /**
     *
     * @param arr
     * @param one
     * @param two
     */
    private void swap(Object[] arr, int one, int two) {
        Object temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

}
