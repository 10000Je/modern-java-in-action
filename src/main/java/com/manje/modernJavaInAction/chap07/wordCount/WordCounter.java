package com.manje.modernJavaInAction.chap07.wordCount;

// stream.reduce(init, acc, combine) 호출을 위한 클래스
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        }
        else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, lastSpace);
    }

    public int getCounter() {
        return counter;
    }

}
