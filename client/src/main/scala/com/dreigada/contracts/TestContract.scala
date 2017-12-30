package com.dreigada.contracts

import com.dreigada.facades.Contract
import com.dreigada.facades.Contract.Callback

import scala.concurrent.Future
import scala.scalajs.js

@js.native
trait TestContract extends Contract {

  protected def test(callback: Callback[String]): Unit = js.native

  protected def test(): String = js.native

  protected def testNotPure(callback: Callback[String]): Unit = js.native

  protected def testNotPure(): String = js.native

}

object TestContract extends ContractCompanion[TestContract] {
  val abiStr = """[ { "constant": true, "inputs": [], "name": "test", "outputs": [ { "name": "", "type": "string", "value": "Hello World" } ], "payable": false, "stateMutability": "pure", "type": "function" }, { "constant": false, "inputs": [], "name": "testNotPure", "outputs": [ { "name": "", "type": "string" } ], "payable": false, "stateMutability": "nonpayable", "type": "function" } ]"""

  implicit class TestContractExtension(private val contract: TestContract) extends AnyVal {
    def test(): Future[String] = makeAsync(contract.test)

    def testNotPure(): Future[String] = makeAsync(contract.testNotPure)
  }

}



