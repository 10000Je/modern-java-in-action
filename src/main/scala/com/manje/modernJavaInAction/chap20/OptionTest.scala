package com.manje.modernJavaInAction.chap20

class Insurance(val name: String)
class Car(val insurance: Option[Insurance])
class Person(val car: Option[Car], val age: Int)

// 스칼라의 Option 객체를 이용한 예제로, 자바의 Optional과 유사한 역할을 한다.
object OptionTest {

  def main(args: Array[String]) {
    val insurance = Option(new Insurance("Rock Bottom"))
    val car = Option(new Car(insurance))
    val person = new Person(car, 40)

    val search1 = getCarInsuranceName(Option(person), 25)
    println(search1)
    val search2 = getCarInsuranceName(Option(person), 50)
    println(search2)
  }

  def getCarInsuranceName(person: Option[Person], minAge: Int) =
    person.filter(_.age >= minAge)
      .flatMap(_.car)
      .flatMap(_.insurance)
      .map(_.name)
      .getOrElse("Unknown")

}
