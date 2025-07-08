package com.manje.modernJavaInAction.chap11;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadPositiveIntParam {

    @Test
    public void testMap() {
        // Properties 는 값을 초 단위의 지속시간으로 해석한다.
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(5, readDurationImperative(props, "a")); // 5 -> 5
        assertEquals(0, readDurationImperative(props, "b")); // true -> 0
        assertEquals(0, readDurationImperative(props, "c")); // -3 -> 0
        assertEquals(0, readDurationImperative(props, "d")); // 0

        assertEquals(5, readDurationWithOptional(props, "a"));
        assertEquals(0, readDurationWithOptional(props, "b"));
        assertEquals(0, readDurationWithOptional(props, "c"));
        assertEquals(0, readDurationWithOptional(props, "d"));
    }

    // 명령형으로 구현한 코드, null 체크, 예외 처리 등으로 코드가 난잡해짐
    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            }
            catch (NumberFormatException ignored) { }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(Quiz::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }

}
