package com.manje.modernJavaInAction.chap03.executeAround;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    private static final String FILE = ExecuteAround.class.getResource("./data.txt").getFile();

    public static void main(String... args) throws IOException{
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);
    }

    // 제한된 동작만 할 수 있음.
    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return br.readLine();
        }
    }

    // 람다식을 인자로 넘겨서 다양한 동작을 수행할 수 있음
    // (BufferedReader) -> String (throws IOException)
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.process(br);
        }
    }

}
