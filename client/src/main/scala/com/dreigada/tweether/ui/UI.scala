package com.dreigada.tweether.ui

import com.dreigada.contracts.Tweether

import scala.scalajs.js

object UI {

  def initialize(): Unit = {
    val tweether = Tweether("0x7de537ce72eceb976b7c2ffbd1c3ddb3c8807b20")
    js.Dynamic.global.tw = tweether

    TweethInput.initialize(tweether)

    TweethFeed.initialize(tweether)(6)
  }


}
