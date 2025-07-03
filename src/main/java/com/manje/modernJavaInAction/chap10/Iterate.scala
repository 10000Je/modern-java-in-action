package com.manje.modernJavaInAction.chap10

object Iterate {

  def timesStandard(i: Int, f: => Unit): Unit = {
    f
    if (i > 1)
      timesStandard(i - 1, f)
  }

  def timesCurried(i: Int)(f: => Unit): Unit = {
    f
    if (i > 1)
      timesCurried(i - 1)(f)
  }

  implicit def intToTimes(i: Int) = new {
    def times(f: => Unit): Unit = {
      def times(i: Int, f => Unit): Unit = {
        f
        if (i > 1)
          times(i - 1, f)
      }
      times(i, f)
    }
  }

  /**
   * 스칼라 문법 학습을 위한 코드가 아닌, 스칼라가 자바와 다른 점을 보여주기 위한 코드
   * 스칼라의 언어적 특징이 사용자 친화적 DSL을 만드는데 도움을 주는 것을 알 수 있음
   */
  def main(args: Array[String]) = {
    println("The standard way:")
    timesStandard(3, println("Hello World"))

    println("The curried way:") // 커링은 나중에 배웁니다.
    timesCurried(3)(println("Hello World"))

    println("The infixed way:")
    3 times {
      println("Hello World")
    }
  }

}