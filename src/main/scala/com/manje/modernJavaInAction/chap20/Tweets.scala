package com.manje.modernJavaInAction.chap20

// 스칼라의 함수형 타입은 자바와 다르게 T => R 로 명시해서 지정할 수 있다!
object Tweets {

  def isJavaMentioned(tweet: String) : Boolean = tweet.contains("Java")
  def isShortTweet(tweet: String) : Boolean = tweet.length() < 20

  def main(args: Array[String]) {
    val tweets = List(
      "I love the new features in Java 8",
      "How's it going?",
      "An SQL query walks into a bar, sees two tables and says 'Can I join you?'"
    )

    tweets.filter(isJavaMentioned).foreach(println)
    tweets.filter(isShortTweet).foreach(println)

    val isLongTweet : String => Boolean = (tweet : String) => tweet.length() > 60
    tweets.map(isLongTweet).foreach(println)
  }

}
