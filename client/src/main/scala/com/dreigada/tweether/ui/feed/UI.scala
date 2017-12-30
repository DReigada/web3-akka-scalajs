package com.dreigada.tweether.ui.feed

import com.dreigada.contracts.Tweether

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object UI {

  def initialize(tweether: Tweether): Unit = Future {
    TweethInput.initialize(tweether)

    TweethCount.initialize(tweether)

    TweethFeed.initialize(tweether)(6)
  }


}
