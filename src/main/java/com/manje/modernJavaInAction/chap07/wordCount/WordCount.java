package com.manje.modernJavaInAction.chap07.wordCount;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita "
            + "mi  ritrovai in una  selva oscura"
            + " che la  dritta via era   smarrita ";

    public static void main(String[] args) {
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Found " + countWords(SENTENCE) + " words");
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true; // 현재 칸이 공백이라면, 이를 체크해준다.
            }
            else {
                if(lastSpace) {
                    counter++; // 마지막 칸이 공백이라면, 새로운 단어가 등장한 것
                }
                lastSpace = false; // 현재 칸은 공백이 아니므로 갱신해준다.
            }
        }
        return counter;
    }

    public static int countWords(String s) {
        /*
        Spliterator를 따로 지정하지 않아 한 단어도 마구잡이로 잘라서 처리해서 결과가 이상함
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(s::charAt).parallel();
        */
        Spliterator<Character> spliterator = new WordCounterSpliterator(s); // 문자열로 Spliterator 생성
        Stream<Character> stream = StreamSupport.stream(spliterator, true); // Spliterator 로 Stream 생성
        return countWords(stream);
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }


}
