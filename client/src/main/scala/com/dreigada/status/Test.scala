package com.dreigada.status

import com.dreigada.contracts.TestContract

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js

object Test {
  def main(args: Array[String]): Unit = {
    val testContract = TestContract("0x91c7E53A88A80743150ca554D9C5D5E2287062F9")

    js.Dynamic.global.document.getElementById("testH").onclick = (a: js.Any) => {
      testContract.test()
        .onComplete(println)
    }
  }
}
