package com.manje.modernJavaInAction.chap07.parallelStreams;

public class Accumulator {

    public long total = 0;

    public void add(long value) {
        total += value;
    }

}
