package com.manje.modernJavaInAction.chap19.persistentTrainJourney;

// 간단한 연결리스트 형태로, 기차 경로를 나타내는 클래스
// 각 TrainJourney 객체는 노드라고 생각하면 된다.
public class TrainJourney {

    public int price;
    public TrainJourney onward;

    public TrainJourney(int p, TrainJourney t) {
        price = p;
        onward = t;
    }

    @Override
    public String toString() {
        return String.format("TrainJourney[%d] -> %s", price, onward);
    }

}