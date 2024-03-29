package com.dreigada.tweether.ui.feed

import com.dreigada.contracts.{Tweeth, Tweether}
import org.scalajs.jquery.{JQuery, jQuery}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

object TweethFeed {

  def initialize(tweether: Tweether)(numberOfTweeths: Int): Unit = Future {
    getAndShowTweets(tweether)(numberOfTweeths)
  }

  private def getAndShowTweets(tweether: Tweether)(count: Int): Future[Unit] = {
    tweether.getNumberOfTweeths.map { ntweets =>
      for {
        i <- ntweets.value - Math.min(count, ntweets.value) to ntweets.value
      } {
        tweether.getTweeth(i).foreach(a => showTweets(Seq(a)))
      }
    }
  }

  private def showTweets(tweeths: Seq[Tweeth]): Unit = {
    for {
      tweeth <- tweeths
      html = genTweeth(tweeth)
    } {
      jQuery("#tweeth-list").prepend(html)
    }
  }

  private def genTweeth(tweeth: Tweeth): JQuery = {

    val template = jQuery("#tweeth-template").clone()

    val date = new js.Date(tweeth.timestamp * 1000)
    val dateText = js.Dynamic.global.jQuery.timeago(date).asInstanceOf[String]
    template.find(".tweeth-time").text(dateText)

    template.find(".tweeth-image").attr("src", tweeth.user.img)
    template.find(".tweeth-user").text(s"@${tweeth.user.userName}")
    template.find(".tweeth-content").text(tweeth.content)

    template.removeClass("hidden")

    template
  }
}
