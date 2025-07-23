package com.manje.modernJavaInAction.chap20

// 스칼라의 동반 객체를 이용한 예제로
// object Hello 는 class Hello 의 private 멤버에 접근할 수 있다.
class Hello {

  def sayThankYou(){
    println("Thanks for reading our book")
  }

}

object Hello {

  def main(args: Array[String]) {
    val h = new Hello()
    h.sayThankYou()
  }

}

