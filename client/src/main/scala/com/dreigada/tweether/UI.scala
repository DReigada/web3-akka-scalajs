package com.dreigada.tweether

import com.dreigada.contracts.{Tweeth, Tweether}
import org.scalajs.jquery.{JQuery, JQueryEventObject, jQuery}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

object UI {

  def initialize(): Unit = {
    val tweether = Tweether("0x7de537ce72eceb976b7c2ffbd1c3ddb3c8807b20")
    js.Dynamic.global.tw = tweether

    onClick("#tweeth-input-button") { _ =>
      val text = jQuery("#tweeth-input").`val`().asInstanceOf[String]
      println(s"Tweething $text")
      tweether.makeTweeth(text)
    }

    getAndShowTweets(tweether)(10)
  }

  private def onClick(query: String)(body: JQueryEventObject => Unit): Unit = {
    jQuery(query).on("click", body)
  }

  private def getAndShowTweets(tweether: Tweether)(count: Int): Future[Unit] = {
    tweether.getNumberOfTweeths.map { ntweets =>
      for {
        i <- ntweets.value - Math.min(count, ntweets.value) to ntweets.value
      } {
        tweether.getTweeth(i).foreach(a => UI.showTweets(Seq(a)))
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

    template.find(".tweeth-image").attr("src", tweeth.user.img)
    template.find(".tweeth-time").text(tweeth.timestamp.toString)
    template.find(".tweeth-user").text(s"@${tweeth.user.userName}")
    template.find(".tweeth-content").text(tweeth.content)

    template.removeClass("hidden")

    template
  }

}
