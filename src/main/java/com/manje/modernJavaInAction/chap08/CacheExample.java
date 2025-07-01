package com.manje.modernJavaInAction.chap08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CacheExample {

    private MessageDigest messageDigest;

    public static void main(String[] args) {
        new CacheExample().main();
    }

    public CacheExample() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // 각 String 을 UTF-8 문자열로 인코딩한 byte[] 배열로 변환한 뒤,
    // 이를 SHA-256 해시 알고리즘을 이용하여 해시합니다.
    // 해시한 결과는 256비트가 되는데 이를 8비트(1바이트)씩 끊은 byte 배열로 반환합니다.
    // 때문에 반환되는 배열의 길이는 항상 32입니다.
    public void main() {
        List<String> lines = Arrays.asList(
                " Nel   mezzo del cammin  di nostra  vita ",
                "mi  ritrovai in una  selva oscura",
                " che la  dritta via era   smarrita "
        );
        Map<String, byte[]> dataToHash = new HashMap<>();
        lines.forEach(line -> dataToHash.computeIfAbsent(line, this::calculateDigest));
        dataToHash.forEach((line, hash) ->
                System.out.printf("%s -> %s%n", line,
                        new String(hash).chars().map(i -> i & 0xff).mapToObj(String::valueOf).collect(Collectors.joining(", ", "[", "]"))));
    }

    private byte[] calculateDigest(String key) {
        return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
    }
}
