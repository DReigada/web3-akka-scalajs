package com.dreigada.facades

import scala.scalajs.js

@js.native
trait Web3 extends js.Object {
  val eth: Eth = js.native
}

@js.native
trait Eth extends js.Object {
  def contract(abi: js.Object): ContractDef = js.native

  val accounts: js.Array[String] = js.native
}

@js.native
trait ContractDef extends js.Object {
  def at[A <: Contract](abi: String): A = js.native
}

@js.native
trait Contract extends js.Object


object Contract {

  type Callback[A] = js.Function2[js.Object, A, Unit]

}