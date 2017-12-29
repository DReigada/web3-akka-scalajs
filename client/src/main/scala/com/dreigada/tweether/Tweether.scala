package com.dreigada.tweether

case class Tweeth(user: String, img: String, date: String, content: String)

object Tweether {

  def main(args: Array[String]): Unit = {
    println("BLAAA")
    UI.addTweets(Seq(
      Tweeth("user", "img", "date", "content"),
      Tweeth("user2", "img2", "date2", "content2")
    ))
  }


}