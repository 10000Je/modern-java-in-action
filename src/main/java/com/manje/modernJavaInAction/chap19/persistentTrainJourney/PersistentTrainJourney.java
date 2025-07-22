package com.manje.modernJavaInAction.chap19.persistentTrainJourney;

import java.util.function.Consumer;

public class PersistentTrainJourney {

    public static void main(String[] args) {
        TrainJourney tj1 = new TrainJourney(40, new TrainJourney(30, null));
        TrainJourney tj2 = new TrainJourney(20, new TrainJourney(50, null));

        TrainJourney appended = append(tj1, tj2);
        visit(appended, tj -> {
            System.out.print(tj.price + " - ");
        });
        System.out.println();

        // tj1, tj2를 바꾸지 않고 새 TrainJourney를 생성, 똑같은 결과를 출력(참조 투명성)
        TrainJourney appended2 = append(tj1, tj2);
        visit(appended2, tj -> {
            System.out.print(tj.price + " - ");
        });
        System.out.println();

        // tj1은 바뀌었지만 여전히 결과에서 확인할 수 없음
        TrainJourney linked = link(tj1, tj2);
        visit(linked, tj -> {
            System.out.print(tj.price + " - ");
        });
        System.out.println();

        /*
        ... 여기서 이 코드의 주석을 해제하면 tj2가 이미 바뀐 tj1의 끝에 추가된다.
        ... 간단하게 설명하자면, tj1의 끝이 tj2의 끝인데, 다시 tj2 의 시작지점으로 돌아가는 것이다.
        ... 떄문에 재귀 호출이 더 이상 끝나지 못하고 영원히 tj2 를 빙글빙글 돌게된다...
        ... 이는 앞에서 파괴적으로 tj1을 변경한 사이드 이펙트(부작용) 때문이다.
        TrainJourney linked2 = link(tj1, tj2);
        visit(linked2, tj -> { System.out.print(tj.price + " - "); });
        System.out.println();
        */

        System.out.println("--------------------");
        compareLinkAndAppend();
    }

    // 함수형과 비함수형으로 변경했을 때 결과 차이를 보여주는 메서드
    private static void compareLinkAndAppend() {
        System.out.println("Destructive update:");
        TrainJourney firstJourney = new TrainJourney(1, null);
        TrainJourney secondJourney = new TrainJourney(2, null);
        TrainJourney xToZ = link(firstJourney, secondJourney);
        System.out.printf("firstJourney (X to Y) = %s%n", firstJourney); // 경로가 변경됨...
        System.out.printf("secondJourney (Y to Z) = %s%n", secondJourney);
        System.out.printf("X to Z = %s%n", xToZ);

        System.out.println();
        System.out.println("The functional way:");
        firstJourney = new TrainJourney(1, null);
        secondJourney = new TrainJourney(2, null);
        xToZ = append(firstJourney, secondJourney);
        System.out.printf("firstJourney (X to Y) = %s%n", firstJourney); // 경로가 유지됨
        System.out.printf("secondJourney (Y to Z) = %s%n", secondJourney);
        System.out.printf("X to Z = %s%n", xToZ);
    }

    // a를 파괴적으로 갱신하면서 경로를 이어붙임.
    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    // a를 파괴적으로 갱신하지 않고(비함수형), 새로운 TrainJourney 객체를 생성하여 b를 이어붙임(함수형)
    // TrainJourney 객체를 불변 객체로 선언하여(모든 필드에 final 선언) 안전하게 객체를 사용할 수 있다.
    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    // TrainJourney 객체를 재귀적으로 순회하면서 Consumer 함수로 객체를 소비함
    static void visit(TrainJourney journey, Consumer<TrainJourney> c) {
        if (journey != null) {
            c.accept(journey);
            visit(journey.onward, c);
        }
    }

}
