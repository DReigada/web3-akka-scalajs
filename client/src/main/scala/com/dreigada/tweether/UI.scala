package com.dreigada.tweether

import com.dreigada.contracts.Tweeth
import org.scalajs.jquery.{JQuery, jQuery}

object UI {

  def showTweets(tweeths: Seq[Tweeth]): Unit = {
    for {
      tweeth <- tweeths
      html = genTweeth(tweeth)
    } {
      jQuery("#tweeth-list").prepend(html)
    }
  }

  private def genTweeth(tweeth: Tweeth): JQuery = {

    val template = jQuery("#tweeth-template").clone()

    template.find(".tweeth-image").attr("src", tweeth.user.img)
    template.find(".tweeth-time").text(tweeth.timestamp.toString)
    template.find(".tweeth-user").text(s"@${tweeth.user.userName}")
    template.find(".tweeth-content").text(tweeth.content)

    template.removeClass("hidden")

    template
  }

}
