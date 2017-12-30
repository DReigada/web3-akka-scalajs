package com.dreigada.contracts

import com.dreigada.facades.Contract.Callback
import com.dreigada.facades.{Contract, SolidityUInt}

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js

case class User(userName: String, img: String, address: String)

case class Tweeth(user: User, timestamp: Long, content: String)

@js.native
trait Tweether extends Contract {
  protected def registerUser(userName: String, avatarUrl: String, callback: Callback[Unit]): Unit = js.native

  protected def makeTweeth(content: String, callback: Callback[Unit]): Unit = js.native

  protected def getNumberOfTweeths(callback: Callback[SolidityUInt]): Unit = js.native

  protected def getTweeth(index: Double, callback: Callback[Tweether.TweethTuple]): Unit = js.native

}

object Tweether extends ContractCompanion[Tweether] {

  private type TweethTuple = js.Tuple5[String, String, String, String, SolidityUInt]
  val abiStr = """[{"constant":false,"inputs":[{"name":"content","type":"string"}],"name":"makeTweeth","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"index","type":"uint256"}],"name":"getTweeth","outputs":[{"name":"","type":"string"},{"name":"","type":"string"},{"name":"","type":"address"},{"name":"","type":"string"},{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"userName","type":"string"},{"name":"avatarUrl","type":"string"}],"name":"registerUser","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"","type":"address"}],"name":"users","outputs":[{"name":"userName","type":"string"},{"name":"avatarUrl","type":"string"},{"name":"userAddress","type":"address"},{"name":"isValid","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"name":"signer","type":"address"}],"name":"isRegistered","outputs":[{"name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfTweeths","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"anonymous":false,"inputs":[{"components":[{"components":[{"name":"userName","type":"string"},{"name":"avatarUrl","type":"string"},{"name":"userAddress","type":"address"},{"name":"isValid","type":"bool"}],"name":"user","type":"tuple"},{"name":"content","type":"string"},{"name":"timestamp","type":"uint256"}],"indexed":true,"name":"tweeth","type":"tuple"}],"name":"NewTweeth","type":"event"},{"anonymous":false,"inputs":[{"components":[{"name":"userName","type":"string"},{"name":"avatarUrl","type":"string"},{"name":"userAddress","type":"address"},{"name":"isValid","type":"bool"}],"indexed":true,"name":"newUser","type":"tuple"}],"name":"UserRegistered","type":"event"}]"""

  implicit class TweetherExtension(private val contract: Tweether) extends AnyVal {
    def registerUser(userName: String, avatarUrl: String): Future[Unit] =
      makeAsync(contract.registerUser(userName, avatarUrl, _: Callback[Unit]))

    def makeTweeth(content: String): Future[Unit] =
      makeAsync(contract.makeTweeth(content, _: Callback[Unit]))

    def getNumberOfTweeths: Future[SolidityUInt] = makeAsync(contract.getNumberOfTweeths)

    def getTweeth(index: Long)(implicit ctx: ExecutionContext): Future[Tweeth] = {
      makeAsync(contract.getTweeth(index, _: Callback[TweethTuple]))
        .map(a => a.copy())
        .map { case (user, avatar, addr, content, timestamp) =>
          Tweeth(User(user, avatar, addr), timestamp.value, content)
        }
    }

  }

}

