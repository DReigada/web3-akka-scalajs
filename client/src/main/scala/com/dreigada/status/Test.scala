package com.dreigada.status

import com.dreigada.contracts.Tweether
import org.scalajs.jquery.{JQueryEventObject, jQuery}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js

object Test {
  def TODOmain(args: Array[String]): Unit = {
    val tweether = Tweether("0x7de537ce72eceb976b7c2ffbd1c3ddb3c8807b20")

    js.Dynamic.global.contract = tweether


    def bla(): Unit = tweether.getNumberOfTweeths.map { res =>
      js.Dynamic.global.res = res
      println(res)
    }

    onClick("#register")(tweether.registerUser("user1", "url").onComplete(println))
    onClick("#makeT")(tweether.makeTweeth("tweet").onComplete(println))
    onClick("#getT")(tweether.getTweeth(0).onComplete(println))


    onClick("#getNumber")(bla())


  }

  def onClick(query: String)(body: => Unit): Unit = {
    jQuery(query).on("click", (_: JQueryEventObject) => body)
  }
}
