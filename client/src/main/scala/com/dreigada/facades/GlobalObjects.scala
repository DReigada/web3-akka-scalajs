package com.dreigada.facades

import scala.scalajs.js

object GlobalObjects {
  lazy val web3: Web3 = js.Dynamic.global.web3.asInstanceOf[Web3]
}
