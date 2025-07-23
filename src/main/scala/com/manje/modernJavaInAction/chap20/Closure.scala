package com.manje.modernJavaInAction.chap20

object Closure {

  // scala 의 익명 함수는 지역 변수를 캡쳐링하여 변경할 수 있음. (클로저)
  def main(args: Array[String]) {
    var count = 0
    val inc = () => count += 1
    inc()
    println(count)
    inc()
    println(count)
  }

}
