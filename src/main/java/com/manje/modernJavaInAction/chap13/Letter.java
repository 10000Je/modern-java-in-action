package com.manje.modernJavaInAction.chap13;

import java.util.function.Function;

// 디폴트 메서드인 andThen 의 활용 예시...를 보여주는 거 같음
// 근데 왜 C++ 욕이 적혀있는진 잘 몰루 ㅋㅋ C++이 좀 빡치긴해~
public class Letter {

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan:" + text;
    }

    public static String addFooter(String text) {
        return text + "Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("C\\+\\+", "**Censored**");
    }

    public static void main(String... args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("C++ stay away from me!"));
    }

}
