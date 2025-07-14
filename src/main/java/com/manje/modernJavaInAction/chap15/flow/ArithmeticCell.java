package com.manje.modernJavaInAction.chap15.flow;

public class ArithmeticCell extends SimpleCell {

    private int left;
    private int right;

    public static void main(String[] args) {
        test1();
        System.out.println("------------");
        test2();
    }

    private static void test1() {
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c1.onNext(10); // C1의 값을 10으로 갱신
        c2.onNext(20); // C2의 값을 20으로 갱
        c1.onNext(15); // C1의 값을 15로 갱신
    }

    private static void test2() {
        ArithmeticCell c5 = new ArithmeticCell("C5");
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c4 = new SimpleCell("C4");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft); // subscribe 함수는 Consumer<Integer> 객체를 받는다.
        c2.subscribe(c3::setRight); // c1 -> c3.left, c2 -> c3.right

        c3.subscribe(c5::setLeft); // c3 -> c5.left, c4 -> c5.right
        c4.subscribe(c5::setRight);

        c1.onNext(10); // C1의 값을 10으로 갱신, C1(10) -> C3(10) -> C5(10)
        c2.onNext(20); // C2의 값을 20으로 갱신, C2(20) -> C3(30) -> C5(30)
        c1.onNext(15); // C1의 값을 15로 갱신, C1(15) -> C3(35) -> C5(35)
        c4.onNext(1); // C4의 값을 1로 갱신, C4(1) -> C5(36)
        c4.onNext(3); // C4의 값을 3으로 갱신, C4(3) -> C5(38)
    }

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(left + right);
    }

    public void setRight(int right) {
        this.right = right;
        onNext(right + left);
    }

}
