package ru.inno.lec02;

class ArrraySort {

    static void sort(int[] theArray){

    }

    static void print(int[] theArray){
        for (int i : theArray) {
            System.out.println(i);
        }
    }

}

public class ArrayOrder {

    public static void main(String[] args) {

        int theArray[] = {1,5,8,97,54,12,34,56,6,78,76,56,23,46,96,5,32,24,56,72,25,75,67};

        ArrraySort.sort(theArray);

        ArrraySort.print(theArray);

    }

}