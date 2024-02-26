package vn.thuanpm.kotlinzen.domains.interfaces

import io.vertx.core.Future
import vn.thuanpm.kotlinzen.domains.dtos.HumanDTO

interface IHumanRepository {
  public  fun  create(name:String):Future<HumanDTO?>;
//  public  fun  getHuman(id:Long):Future<HumanDTO?>;
}
