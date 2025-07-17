package com.manje.modernJavaInAction.chap17;

import java.util.Random;

// 도시와 온도 정보를 담고있는 데이터 클래스
public class TempInfo {

    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }
    
    // 10% 확률로 에러(RuntimeException)를 발생시키는 온도를 받아오는 메서드
    public static TempInfo fetch(String town) {
        if (random.nextInt(10) == 0) {
            throw new RuntimeException("Error!");
        }
        return new TempInfo(town, random.nextInt(100));
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public int getTemp() {
        return temp;
    }

    public String getTown() {
        return town;
    }

}
