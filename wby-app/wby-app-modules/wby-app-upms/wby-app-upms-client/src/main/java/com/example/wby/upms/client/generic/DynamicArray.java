package com.example.wby.upms.client.generic;

import java.util.Arrays;
import java.util.Random;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }


    public static <T> int indexOf(T[] arr, T elm) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    public static <U, V> Pair<U, V> makePair(U first, V second) {
        Pair<U, V> pair = new Pair<>(first, second);
        return pair;
    }


   /* public <T extends E> void addAll(DynamicArray<T> c) {
        for (int i = 0; i < c.size; i++) {
            add(c.get(i));
        }
    }*/

    public void addAll(DynamicArray<? extends E> c) {
        for (int i = 0; i < c.size; i++) {
            add(c.get(i));
        }
    }

    public static int indexOf(DynamicArray<?> arr, Object elm) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(elm)) {
                return i;
            }
        }
        return -1;
    }


    public void copyTo(DynamicArray<? super E> dest) {
        for (int i = 0; i < size; i++) {
            dest.add(get(i));
        }
    }

    public static <T extends Comparable<? super T>> T max(DynamicArray<T> array) {
        T t = array.get(0);
        return t;
    }










    public static void main(String[] args) {
        DynamicArray<Double> arr = new DynamicArray<>();
        Random rnd = new Random();
        int size = 1 + rnd.nextInt(100);
        for (int i = 0; i < size; i++) {
            arr.add(Math.random());
        }
        Double d = arr.get(rnd.nextInt(size));

        DynamicArray<Pair<Integer,String>> array = new DynamicArray<>();

        indexOf(new Integer[]{1, 3, 5}, 10);
        indexOf(new String[]{"123", "小吴"}, "123");

        makePair(1, "小吴");

        DynamicArray<Number> numbers = new DynamicArray<>();
        DynamicArray<Integer> ints = new DynamicArray<>();
        ints.add(100);
        ints.add(34);
        numbers.addAll(ints);

//        DynamicArray<Integer> ints2 = new DynamicArray<>();
//        DynamicArray<? extends Number> numbers2 = ints2;
//        Integer a =200;
//        numbers2.add(a);


        DynamicArray<Child> childs = new DynamicArray<>();
        childs.add(new Child(20));
        childs.add(new Child(80));
        Child maxChild = max(childs);
    }
}
