package com.dreigada.tweether.ui.feed

import com.dreigada.contracts.Tweether
import com.dreigada.tweether.ui.Helpers
import org.scalajs.jquery.jQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TweethInput {

  def initialize(tweether: Tweether): Unit = Future {
    Helpers.onClick("#tweeth-input-button") { _ =>
      val text = jQuery("#tweeth-input").`val`().asInstanceOf[String]
      println(s"Tweething $text")
      tweether.makeTweeth(text)
    }
  }
}
