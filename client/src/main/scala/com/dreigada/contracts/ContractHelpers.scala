package com.dreigada.contracts

import com.dreigada.facades.Contract
import com.dreigada.facades.Contract.Callback
import com.dreigada.facades.GlobalObjects.web3

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.JSON


trait ContractHelpers {

  def makeAsync[A](f: Callback[A] => Unit): Future[A] = {
    val promise = Promise[A]
    f((err, result) => {
      if (err != null) {
        promise.failure(new Throwable(s"Call failed: $err"))
      } else {
        promise.success(result)
      }
    })
    promise.future
  }


  def getContract[A <: Contract](abi: String, address: String): A =
    web3.eth.contract(JSON.parse(abi).asInstanceOf[js.Object]).at[A](address)

}