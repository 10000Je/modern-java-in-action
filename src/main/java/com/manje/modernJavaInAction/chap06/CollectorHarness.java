package com.manje.modernJavaInAction.chap06;

import com.manje.modernJavaInAction.chap06.partitionPrimeNumbers.PartitionPrimeNumbers;

import java.util.function.Consumer;

public class CollectorHarness {

    public static void main(String[] args) {
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimes) + " msecs");
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrimesWithCustomCollector) + " msecs");
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i<10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            fastest = Math.min(fastest, duration);
            System.out.println("done in " + duration);
        }
        return fastest;
    }

}
