package com.dreigada.tweether.ui

import com.dreigada.contracts.Tweether
import org.scalajs.jquery.jQuery

object TweethInput {

  def initialize(tweether: Tweether): Unit = {
    Helpers.onClick("#tweeth-input-button") { _ =>
      val text = jQuery("#tweeth-input").`val`().asInstanceOf[String]
      println(s"Tweething $text")
      tweether.makeTweeth(text)
    }
  }
}
