package com.manje.modernJavaInAction.chap06.partitionPrimeNumbers;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionPrimeNumbers {

    public static void main(String... args) {
        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimes(100));
        System.out.println("Numbers partitioned in prime and non-prime: " + partitionPrimesWithCustomCollector(100));
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(PartitionPrimeNumbers::isPrime));
    }

    public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, candidate-1)
                .takeWhile(i -> i*i <= candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    // Java 8
    public static <T> List<T> takeWhile(List<T> list, Predicate<T> p) {
        int i = 0;
        for(T item : list) {
            if(!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

}
