package vn.thuanpm.kotlinzen

import io.vertx.core.AbstractVerticle
import io.vertx.core.Handler
import io.vertx.core.Promise
import io.vertx.ext.web.Router
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.pgclient.PgBuilder
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import vn.thuanpm.kotlinzen.infrastructures.HumanRepository

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
          val repo = HumanRepository()
          repo.create("ahihi");
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

    val connectOptions = PgConnectOptions()
      .setPort(5432)
      .setHost("localhost")
      .setDatabase("vertx")
      .setUser("thuanpm")
      .setPassword("1")

// Create the pooled client
    val pool = PgBuilder
      .pool()
      .connectingTo(connectOptions)
      .using(vertx)
      .build()

    pool.getConnection { handler -> run{
        if(handler.succeeded()){
          val connection = handler.result();

          connection.query("SELECT * FROM public.human").execute().onSuccess {
              result -> run{
            println(result.toString())
          }
          }.onFailure{
              err -> run{
            println(err.toString())
          }
          }
        }
    } }

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
