package vn.thuanpm.kotlinzen

import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.Promise
import io.vertx.ext.web.Router


class MainVerticle : AbstractVerticle() {
  private val router : Router
    get() {
      val _router =  Router.router(vertx);

      _router.route().path("/").handler { ctx-> run{
        val response = ctx.response()
        response.putHeader("content-type", "text/plain")
        response.end("Hello thuận from Vert.x-Web!")
      } }

      _router.route().path("/user").handler{ctx ->
        run {
          val response = ctx.response()
          response.putHeader("content-type", "text/plain")
          response.end("Hello user from Vert.x-Web!")
        }
      }
      return _router
    }

  override fun start(startPromise: Promise<Void>) {

    vertx.deployVerticle(ChildVerticle())
   vertx.eventBus().request<String>("user.io","hello child", Handler { rep -> run{
     println(rep.result().body());
   } });

    val sysPropsStore = ConfigStoreOptions().setType("sys")

    val options = ConfigRetrieverOptions().addStore(sysPropsStore)
    val retriever = ConfigRetriever.create(vertx, options)

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8080) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8080")
        } else {
          startPromise.fail(http.cause());
        }
      }
  }
}
