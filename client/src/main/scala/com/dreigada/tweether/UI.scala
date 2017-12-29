package com.dreigada.tweether

import org.scalajs.jquery.{JQuery, jQuery}

object UI {

  def addTweets(tweeths: Seq[Tweeth]): Unit = {
    for {
      tweeth <- tweeths
      html = genTweeth(tweeth)
    } {
      jQuery("#tweeth-list").append(html)
    }
  }

  private def genTweeth(tweeth: Tweeth): JQuery = {

    val template = jQuery("#tweeth-template").clone()

    template.find(".tweeth-image").attr("src", tweeth.img)
    template.find(".tweeth-time").text(tweeth.date)
    template.find(".tweeth-user").text(s"@${tweeth.user}")
    template.find(".tweeth-content").text(tweeth.content)

    template.removeClass("hidden")

    template
  }
}
