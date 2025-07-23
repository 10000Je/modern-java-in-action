package com.manje.modernJavaInAction.chap20

// 스칼라의 trait 는 자바의 인터페이스와 유사하며, 가변 필드를 사용할 수 있음!
trait Sized {
  var size: Int = 0
  def isEmpty() = size == 0
}

object SizedRunner {

  def main(args: Array[String]) {
    class Empty extends Sized
    println(new Empty().isEmpty())

    class Box
    val b1 = new Box() with Sized // 객체 생성시 with 을 통해 인터페이스의 구현체로 선언할 수 있다.
    println(b1.isEmpty())
    val b2 = new Box()
    // Won't compile
    // b2.isEmpty()
  }

}
