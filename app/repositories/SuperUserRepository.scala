package repositories

import javax.inject.Inject
import zio.*
import models.SuperUser
import play.api.db.slick._
import repositories.tables.SuperUserTable
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext

class SuperUserRepository @Inject() (
                                      protected val dbConfigProvider: DatabaseConfigProvider
                                    )(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

   val superUsersTbl = TableQuery[SuperUserTable]

  def create(superUser: SuperUser): Task[Int] = {
    val insert = superUsersTbl += superUser
    ZIO.fromFuture(_ => dbConfig.db.run(insert))
  }
  

  def getAll: Task[List[SuperUser]] = {
   ZIO.fromFuture(_ => dbConfig.db.run(superUsersTbl.result)).map(_.toList)
  }
  
}
