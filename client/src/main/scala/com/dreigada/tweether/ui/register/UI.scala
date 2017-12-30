package com.dreigada.tweether.ui.register

import com.dreigada.contracts.Tweether
import com.dreigada.tweether.ui.Helpers
import org.scalajs.jquery.jQuery

object UI {
  def initialize(tweether: Tweether): Unit = {

    Helpers.onClick("#submit-register") { _ =>
      val username = jQuery("#username").`val`().asInstanceOf[String]
      val avatar = jQuery("#avatar").`val`().asInstanceOf[String]

      tweether.registerUser(username, avatar)
    }

  }
}
