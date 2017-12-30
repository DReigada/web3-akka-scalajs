package com.dreigada.tweether.ui

import com.dreigada.contracts.Tweether
import org.scalajs.jquery.jQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TweethCount {
  def initialize(tweether: Tweether): Unit = Future {
    tweether.getNumberOfTweeths.map(i => jQuery("#tweeths-number").text(i.toString))
  }
}
