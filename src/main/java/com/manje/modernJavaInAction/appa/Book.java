package com.manje.modernJavaInAction.appa;

import java.util.Arrays;

@Author(name = "Raoul")
@Author(name = "Mario")
@Author(name = "Alan")
public class Book {

    public static void main(String[] args) {
        // 런타임에 어노테이션 정보를 불러오기 위해선, 
        // 해당 어노테이션이 @Retention(RetentionPolicy.RUNTIME)으로 설정되어 런타임에 정보률 유지하고 있어야함
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).stream().forEach(a -> {
            System.out.println(a.name());
        });
    }

}

