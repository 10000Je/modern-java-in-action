package com.manje.modernJavaInAction.chap18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetsMain {

    public static void main(String[] args) {
        List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
        subs.forEach(System.out::println);
    }
    
    // List<T> 의 모든 부분 집합을 List<List<T>> 로 반환
    public static <T> List<List<T>> subsets(List<T> l) {
        if (l.isEmpty()) {
            List<List<T>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        T first = l.get(0);
        List<T> rest = l.subList(1, l.size());
        List<List<T>> subans = subsets(rest);
        List<List<T>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    // 인자로 주어진 배열을 변경시키지 않고, 새로운 값을 반환하는 함수
    public static <T> List<List<T>> insertAll(T first, List<List<T>> lists) {
        List<List<T>> result = new ArrayList<>();
        for (List<T> l : lists) {
            List<T> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(l);
            result.add(copyList);
        }
        return result;
    }

    // 인자로 주어진 배열 a 를 변경시키는, 함수형이 아닌 메서드
    static <T> List<List<T>> concat(List<List<T>> a, List<List<T>> b) {
        List<List<T>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

}
