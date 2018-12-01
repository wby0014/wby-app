package com.example.wby.upms.client.generic;

public class Pair<U,V> {
    U first;
    V second;


    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("name", 100);
        Pair<Integer, Integer> integerIntegerPair = new Pair<>(100, 100);
        Integer first = integerIntegerPair.getFirst();
        Integer second = integerIntegerPair.getSecond();
        String first1 = pair.getFirst();
        Integer second1 = pair.getSecond();

    }
}
