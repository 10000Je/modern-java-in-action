/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manje.modernJavaInAction.chap10.grouping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

// T: 들어오는 데이터의 타입
// K: 그룹화의 기준이 되는 키의 타입
// D: 그룹화된 결과의 타입
public class GroupingBuilder<T, D, K> {

    // Collector 는 내부에서 T 타입의 데이터를 적절히 처리하여 키 K를 기준으로 D로 그룹화한다.
    // 그럼 내부 변수에서 T 타입의 데이터를 가리키려면 T 또는 T의 부모 타입 변수가 선언이 되야한다.
    // 부모는 자식 타입을 가리킬 수 있으므로 (반대는 안됨), ? super T 와 같이 정의한다.
    private final Collector<? super T, ?, Map<K, D>> collector;

    public GroupingBuilder(Collector<? super T, ? , Map<K, D>> collector) {
        this.collector = collector;
    }

    public Collector<? super T, ?, Map<K, D>> get() {
        return collector;
    }

    // T -> J 로 매핑한 키를 기준으로 컬렉터<? super T, ?, Map<K, D>>를 감싸는 메서드
    // 이 메서드는 최종적으로 Collector<? super T, ?, Map<J, Map<K, D>>>를 반환해야 한다.
    // 그러기 위해선 함수 classifier 가 J가 가리킬 수 있는 타입을 반환해야한다. (? extends J)
    public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier) {
        return new GroupingBuilder<>(groupingBy(classifier, collector));
    }

    // T -> K 로 매핑한 키를 기준으로 리스트로 그룹화하는 메서드
    // 이 메서드는 최종적으로 Collector<? super T, ?, Map<K, List<T>> 를 반환한다.
    public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
        return new GroupingBuilder<>(groupingBy(classifier));
    }

}
