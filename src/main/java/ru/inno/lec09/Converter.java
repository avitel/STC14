package ru.inno.lec09;

@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);


}
