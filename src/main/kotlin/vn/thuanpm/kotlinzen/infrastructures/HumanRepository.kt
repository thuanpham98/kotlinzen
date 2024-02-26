package vn.thuanpm.kotlinzen.infrastructures

import io.vertx.core.Future
import org.hibernate.reactive.stage.Stage
import vn.thuanpm.kotlinzen.domains.dtos.HumanDTO
import vn.thuanpm.kotlinzen.domains.interfaces.IHumanRepository

import vn.thuanpm.kotlinzen.applications.mapHumanDTO
import vn.thuanpm.kotlinzen.domains.models.HumanEntity

class HumanRepository() : IHumanRepository{

  override   fun  create(name:String): Future<HumanDTO?> {
    val hu = HumanEntity()
    hu.name=name;
    hu.completed=false;

    try {
      val completionStage = HibernateUtils.getSessionFactory().withSession { s: Stage.Session -> s.persist(hu)}
      return Future.fromCompletionStage(completionStage).map(mapHumanDTO(hu))
    }catch (e:Exception){
      throw e
    }

  }

//  override public  fun  getHuman(id:Long): Future<HumanDTO?> {
//    return null;
//  }
}
