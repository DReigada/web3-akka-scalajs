package com.dreigada.akkascalajsweb3

import akka.http.scaladsl.server.Directives
import com.dreigada.akkascalajsweb3.shared.SharedMessages
import com.dreigada.akkascalajsweb3.twirl.Implicits._

class WebService() extends Directives {

  val route = {
    pathSingleSlash {
      get {
        complete {
          com.dreigada.akkascalajsweb3.html.index.render(SharedMessages.itWorks)
        }
      }
    } ~
      pathPrefix("assets" / Remaining) { file =>
        // optionally compresses the response with Gzip or Deflate
        // if the client accepts compressed responses
        encodeResponse {
          getFromResource("public/" + file)
        }
      }
  }
}
