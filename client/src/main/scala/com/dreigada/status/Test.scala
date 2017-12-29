package com.dreigada.status

import com.dreigada.contracts.TestContract
import org.scalajs.jquery.{JQueryEventObject, jQuery}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js

object Test {
  def TODOmain(args: Array[String]): Unit = {
    val testContract = TestContract("0x91c7E53A88A80743150ca554D9C5D5E2287062F9")

    js.Dynamic.global.contract = testContract

    jQuery("#testPure").on("click", (_: JQueryEventObject) => {
      testContract.test()
        .onComplete(println)
    })

    jQuery("#testNotPure").on("click", (_: JQueryEventObject) => {
      testContract.testNotPure()
        .onComplete(println)
    })

  }
}
