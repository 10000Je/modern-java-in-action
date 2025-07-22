package com.manje.modernJavaInAction.chap19.persistentTree;

// 키, 값 쌍과 2개의 자식을 갖는 트리 노드 자료구조
public class Tree {

    String key;
    int val;
    Tree left, right;

    public Tree(String k, int v, Tree l, Tree r) {
        key = k;
        val = v;
        left = l;
        right = r;
    }

}

