package com.dreigada.tweether.ui

import com.dreigada.contracts.Tweether

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

object UI {

  def initialize(): Unit = Future {
    val tweether = Tweether("0x19a8320781f6dfb6bef5d8e2485f65d569ae9bcb")
    js.Dynamic.global.tw = tweether

    TweethInput.initialize(tweether)

    TweethCount.initialize(tweether)

    TweethFeed.initialize(tweether)(6)
  }


}
