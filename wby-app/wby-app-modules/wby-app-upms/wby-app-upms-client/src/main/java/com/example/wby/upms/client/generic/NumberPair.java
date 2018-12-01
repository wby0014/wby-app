package com.example.wby.upms.client.generic;

public class NumberPair<U extends Number, V extends Number> extends Pair<U,V>{


    public NumberPair(U first, V second) {
        super(first, second);
    }

    public double sum() {
        return getFirst().doubleValue() + getSecond().doubleValue();
    }

    public static <T extends Comparable<T>> T max(T[] arr) {
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }



    public static void main(String[] args) {
        NumberPair<Integer, Double> pair = new NumberPair<>(10, 12.34);
        double sum = pair.sum();



    }

}
