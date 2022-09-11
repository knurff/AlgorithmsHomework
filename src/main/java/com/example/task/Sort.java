package com.example.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.Comparator;
import java.util.function.ToIntFunction;

@SuppressWarnings(value = "rawtypes")
public final class Sort {

    private Sort() {
    }

    public static <T> T[] quickSort(@NonNull T[] arr, int begin, int end, Comparator<T> comparator) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, comparator);

            quickSort(arr, begin, partitionIndex - 1, comparator);
            quickSort(arr, partitionIndex + 1, end, comparator);
        }
        return arr;
    }

    private static <T> int partition(T[] arr, int begin, int end, Comparator<T> comparator) {
        T pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (comparator.compare(arr[j], pivot) <= 0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return ++i;
    }

    public static <T> T[] countingSort(@NonNull T[] arr, ToIntFunction<T> function) {
        if (arr.length > 0) {
            Node[] nodes = getNodes(arr, function);
            Object[] sorted = new Object[arr.length];

            for (int i = nodes.length - 1, j = arr.length - 1; i >= 0; i--) {
                Node last = nodes[i];
                while (last != null) {
                    sorted[j] = last.value;
                    j--;
                    last = last.prev;
                }
            }

            return (T[]) sorted;
        }
        return (T[]) new Object[0];
    }

    private static <T> Node[] getNodes(T[] arr, ToIntFunction<T> function) {
        int min = function.applyAsInt(arr[0]);
        int max = min;

        for (T t : arr) {
            int current = function.applyAsInt(t);
            if (current > max) max = current;
            else if (current < min) min = current;
        }

        Node[] result = new Node[max - min + 1];

        for (T t : arr) {
            int position = function.applyAsInt(t) - min;
            if (result[position] == null) {
                result[position] = new Node(null, t, null);
            } else {
                Node nextNode = new Node(result[position], t, null);
                result[position].next = nextNode;
                result[position] = nextNode;
            }
        }

        return result;
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class Node<T> {
        Node<T> prev;

        T value;

        Node<T> next;
    }
}
