package ru.vsu.cs;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2,4,56,87,9,3,2,7,778,5,3,3423,5,10};
        SmoothSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
