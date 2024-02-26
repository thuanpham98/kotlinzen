package vn.thuanpm.kotlinzen.infrastructures

import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.cfg.Environment
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder
import org.hibernate.reactive.stage.Stage
import org.hibernate.service.ServiceRegistry
import vn.thuanpm.kotlinzen.domains.models.HumanEntity
import java.util.*


object HibernateUtils {
  private var sessionFactory : Stage.SessionFactory?=null

  public fun getSessionFactory() : Stage.SessionFactory {
    if(sessionFactory===null){
      try {
        val configuration: Configuration = Configuration()

        val settings = Properties()
        settings[Environment.JAKARTA_JDBC_DRIVER] = "com.mysql.cj.jdbc.Driver"
        settings[Environment.JAKARTA_JDBC_URL] = "jdbc:postgresql://localhost:5432/vertx"
        settings[Environment.JAKARTA_JDBC_USER] = "thuanpm"
        settings[Environment.JAKARTA_JDBC_PASSWORD] = "1"

        settings[Environment.SHOW_SQL] = "true"
        settings[Environment.JAKARTA_HBM2DDL_DATABASE_ACTION] = "create-drop"

        configuration.setProperties(settings)
        configuration.addAnnotatedClass(HumanEntity::class.java)
        val serviceRegistry: ServiceRegistry = ReactiveServiceRegistryBuilder()
          .applySettings(configuration.properties).build()

        sessionFactory = configuration.buildSessionFactory(serviceRegistry).unwrap(Stage.SessionFactory::class.java)
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }

    return sessionFactory!!;
  }
}

