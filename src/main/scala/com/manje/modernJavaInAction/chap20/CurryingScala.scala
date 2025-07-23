package com.manje.modernJavaInAction.chap20

// 스칼라의 커링 기능을 사용한 예제
object CurryingScala {

  def main(args: Array[String]) {
    def multiply(x : Int, y: Int) = x * y
    val r1 = multiply(2, 10)
    println(r1)

    def multiplyCurry(x :Int)(y : Int) = x * y
    val r2 = multiplyCurry(2)(10)
    println(r2)

    val multiplyByTwo : Int => Int = multiplyCurry(2)
    val r3 = multiplyByTwo(10)
    println(r3)
  }

}
