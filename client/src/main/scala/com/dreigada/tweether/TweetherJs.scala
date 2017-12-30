package com.dreigada.tweether

import com.dreigada.contracts.Tweether

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js


object TweetherJs {

  def main(args: Array[String]): Unit = {
    val tweether = Tweether("0x7de537ce72eceb976b7c2ffbd1c3ddb3c8807b20")
    js.Dynamic.global.tw = tweether

    showTweets(tweether)(10)

  }

  def showTweets(tweether: Tweether)(count: Int): Future[Unit] = {
    tweether.getNumberOfTweeths.map { ntweets =>
      for {
        i <- ntweets.value - Math.min(count, ntweets.value) to ntweets.value
      } {
        tweether.getTweeth(i).foreach(a => UI.showTweets(Seq(a)))
      }
    }
  }

}