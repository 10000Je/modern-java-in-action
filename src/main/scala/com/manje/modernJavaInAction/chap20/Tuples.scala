package com.manje.modernJavaInAction.chap20

// 스칼라의 튜플 기능을 사용한 예제, 이거 ㄹㅇ 좋음.
object Tuples {

  def main(args: Array[String]) {
    val book = (2017, "Java in Action", "Manning")
    val numbers = (42, 1337, 0, 3, 14)
    println(book._1)
    println(numbers._4)
  }

}
