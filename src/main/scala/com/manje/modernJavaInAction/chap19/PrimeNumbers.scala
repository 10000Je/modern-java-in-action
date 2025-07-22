package com.manje.modernJavaInAction.chap19

object PrimeNumbers {

  def numbers(n: Int): Stream[Int] = n #:: numbers(n + 1) // #:: 로 게으르게 호출할 수 있다.
  def primes(numbers: Stream[Int]): Stream[Int] = {
    numbers.head #:: primes(numbers.tail filter (n => n % numbers.head != 0))
  }

  def main(args: Array[String]) = {
    val nums : Stream[Int] = numbers(2)
    val prms = primes(nums)
    prms.take(25).print()
  }

}