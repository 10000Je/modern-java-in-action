package com.manje.modernJavaInAction.chap20

// 스칼라에선 클래스를 정의하면 자동으로 게터, 세터, 생성자가 생성됨
class Student(var name: String, var id: Int)

object Student {

  def main(args: Array[String]) {
    val s = new Student("Raoul", 1)
    println(s.name)
    s.id = 1337
    println(s.id)

  }

}
