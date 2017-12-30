package com.dreigada.tweether

import com.dreigada.contracts.Tweether
import com.dreigada.tweether.ui._

import scala.scalajs.js


object TweetherJs {

  def main(args: Array[String]): Unit = {
    val tweether = Tweether("0x19a8320781f6dfb6bef5d8e2485f65d569ae9bcb")
    js.Dynamic.global.tw = tweether


    if (js.Dynamic.global.window.location.href.asInstanceOf[String] contains "register") {
      register.UI.initialize(tweether)
    } else if (js.Dynamic.global.window.location.href.asInstanceOf[String] contains "tweether") {
      feed.UI.initialize(tweether)
    } else {
      println("INVALID PAGE")
    }

  }


}