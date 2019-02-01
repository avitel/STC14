package ru.inno.lec14;

public class Calculator {


    public int sum(int a, int b){
        return a+b;
    }

    public int divide(int a, int b) throws DivideByZeroException {
        if (b==0) {
            throw new DivideByZeroException();
        }
        return a/b;
    }
}
