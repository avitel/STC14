package ru.inno.lec06.HW;

import java.util.Objects;

public class testObject{
    int field1;
    String field2;
    double field3;

    public testObject(int field1, String field2, double field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public testObject() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        testObject that = (testObject) o;
        return field1 == that.field1 &&
                Double.compare(that.field3, field3) == 0 &&
                Objects.equals(field2, that.field2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(field1, field2, field3);
    }
}
