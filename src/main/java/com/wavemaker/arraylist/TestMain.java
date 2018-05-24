package com.wavemaker.arraylist;

import java.util.Collections;
import java.util.List;

public class TestMain {
    public static void main(String args[])
    {
        List<Integer> sampleList = new MyArrayList<>();
        sampleList.add(1);
        sampleList.add(2);
        sampleList.add(3);
        sampleList.remove("a");
        sampleList.remove(1);
        sampleList.indexOf(1);
        sampleList.lastIndexOf(3);
        Object[] a = new Object[10];
        sampleList.toArray(a);

        Integer x = sampleList.get(0);
        Integer y = sampleList.set(0,x);

        Employee employee = new Employee();
        employee.equals("");
        employee.hashCode();

    }

}
