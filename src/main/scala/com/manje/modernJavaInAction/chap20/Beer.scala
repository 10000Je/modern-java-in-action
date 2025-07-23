package com.manje.modernJavaInAction.chap20

object Beer {

  def main(args: Array[String]) {
    imperative()
    functional()
  }

  // 명령형으로 구현한 메서드
  def imperative() {
    var n: Int = 2
    while (n <= 6) {
      println(s"Hello ${n} bottles of beer")
      n += 1
    }
  }

  // 함수형으로 구현한 메서드
  def functional() {
    2 to 6 foreach { n => println(s"Hello ${n} bottles of beer") }
  }

}