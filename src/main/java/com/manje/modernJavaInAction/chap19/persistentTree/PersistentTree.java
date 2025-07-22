package com.manje.modernJavaInAction.chap19.persistentTree;

public class PersistentTree {

    public static void main(String[] args) {
        Tree t = new Tree(
                "Mary", 22,
                new Tree(
                        "Emily", 20,
                        new Tree("Alan", 50, null, null),
                        new Tree("Georgie", 23, null, null)
                ),
                new Tree(
                        "Tian", 29,
                        new Tree("Raoul", 23, null, null),
                        null
                )
        );

        // 발견 = 23
        System.out.printf("Raoul: %d%n", lookup("Raoul", -1, t));
        // 발견되지 않음 = -1
        System.out.printf("Jeff: %d%n", lookup("Jeff", -1, t));

        Tree f = fupdate("Jeff", 80, t); // 함수형으로 변경, t에 변경X
        // 발견 = 80
        System.out.printf("Jeff: %d%n", lookup("Jeff", -1, f));

        Tree u = update("Jim", 40, t); // 비함수형으로 변경, t에 변경O
        // fupdate로 t가 바뀌지 않았으므로 Jeff는 발견되지 않음 = -1
        System.out.printf("Jeff: %d%n", lookup("Jeff", -1, u));
        // 발견 = 40
        System.out.printf("Jim: %d%n", lookup("Jim", -1, u));

        Tree f2 = fupdate("Jeff", 80, t);
        // 발견 = 80
        System.out.printf("Jeff: %d%n", lookup("Jeff", -1, f2));
        // t로 만든 f2는 위 update()에서 갱신되므로 Jim은 여전히 존재함 = 40
        System.out.printf("Jim: %d%n", lookup("Jim", -1, f2));
    }

    // 간단하게 트리에서 키로 값을 찾아 반환하는 메서드
    public static int lookup(String k, int defaultval, Tree t) {
        if (t == null) {
            return defaultval;
        }
        if (k.equals(t.key)) {
            return t.val;
        }
        return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
    }

    // 기존 트리의 필드를 갱신하면서 비함수형으로 트리를 업데이트하는 메서드
    public static Tree update(String k, int newval, Tree t) {
        if (t == null) {
            t = new Tree(k, newval, null, null);
        }
        else if (k.equals(t.key)) {
            t.val = newval;
        }
        else if (k.compareTo(t.key) < 0) {
            t.left = update(k, newval, t.left);
        }
        else {
            t.right = update(k, newval, t.right);
        }
        return t;
    }

    // 트리의 필드를 변경하지 않고, 변경된 트리를 새로 반환하는 방식(함수형)으로 트리를 업데이트하는 메서드
    public static Tree fupdate(String k, int newval, Tree t) {
        return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.key) ?
                        new Tree(k, newval, t.left, t.right) :
                        k.compareTo(t.key) < 0 ?
                                new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :
                                new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
    }

}
