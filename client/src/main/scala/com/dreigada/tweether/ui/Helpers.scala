package com.dreigada.tweether.ui

import org.scalajs.jquery.{JQueryEventObject, jQuery}

object Helpers {
  def onClick(query: String)(body: JQueryEventObject => Unit): Unit = {
    jQuery(query).on("click", body)
  }
}
