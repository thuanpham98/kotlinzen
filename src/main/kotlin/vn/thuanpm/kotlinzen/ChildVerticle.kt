package vn.thuanpm.kotlinzen

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.Promise

class ChildVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    vertx.eventBus().consumer<String>("user.io", Handler {message ->  run{
        println("from child" + message.body());
          message.reply("hello main vertx")
    } })
  }

}
