package com.manje.modernJavaInAction.chap10

import scala.annotation.tailrec
import scala.language.{implicitConversions, reflectiveCalls}

object Iterate {

  def timesStandard(i: Int, f: => Unit): Unit = {
    f
    if (i > 1) timesStandard(i - 1, f)
  }

  def timesCurried(i: Int)(f: => Unit) : Unit = {
    f
    if (i > 1) timesCurried(i - 1)(f)
  }

  trait TimesOps {
    def times(f: => Unit): Unit
  }

  implicit def intToTimes(i: Int): TimesOps = new TimesOps {
    def times(f: => Unit): Unit = {
      @tailrec
      def times(n: Int, f: => Unit): Unit = {
        if (n > 0) {
          f
          times(n - 1, f)
        }
      }

      times(i, f)
    }
  }

  /**
   * 메서드 정의 순서는 크게 중요하지 않지만 아래 main 메서드에서 사용한 implicit 
   * 정의는 반드시 사용전에 나타나야 한다.
   */
  def main(args: Array[String]) = {
    println("The standard way:")
    timesStandard(3, println("Hello World"))

    println("The curried way:")
    timesCurried(3)(println("Hello World"))

    println("The infixed way:")
    3 times {
      println("Hello World")
    }
  }

}