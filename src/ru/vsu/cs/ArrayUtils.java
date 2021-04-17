package ru.vsu.cs;

public class ArrayUtils {
    public static Integer[] toInteger(String[] inputArr) {
        Integer[] newArr = new Integer[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            newArr[i] = Integer.parseInt(inputArr[i]);
        }
        return newArr;
    }
}
