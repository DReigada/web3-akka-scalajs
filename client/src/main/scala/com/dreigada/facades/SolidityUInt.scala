package com.dreigada.facades

import scala.scalajs.js

@js.native
trait SolidityUInt extends js.Object {
  protected val c: js.Array[Double] // y u do dis scala.js?
}

object SolidityUInt {

  implicit class SolidityUIntExtension(private val uint: SolidityUInt) extends AnyVal {
    def value: Long = uint.c(0).toLong
  }

}