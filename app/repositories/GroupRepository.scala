//// app/repositories/UserRepository.scala
//package repositories
//
//import javax.inject.{Inject, Singleton}
//import play.api.db.slick.DatabaseConfigProvider
//import play.api.db.slick.HasDatabaseConfigProvider
//import slick.jdbc.JdbcProfile
//import models.*
//import slick.lifted.*
//import slick.jdbc.MySQLProfile.api._
//import slick.lifted.Rep
//import slick.lifted.TableQuery
//
//import scala.concurrent.{ExecutionContext, Future}
//
//class GroupRepository @Inject() (
//                                  protected val dbConfigProvider: DatabaseConfigProvider
//                                )(implicit ec: ExecutionContext)
//  extends HasDatabaseConfigProvider[JdbcProfile] {
//
// private val groups = TableQuery[Groups]
//
// def createZ(group: Group): Future[Int] = db.run(groups += group)
//
// def findByIdZ(id: Long): Future[Option[Group]] =
//  db.run(groups.filter(_.id === id).result.headOption)
//
// def findAllZ(): Future[Seq[Group]] = db.run(groups.result)
//
// def updateZ(group: Group): Future[Int] =
//  db.run(groups.filter(_.id === group.id).update(group))
//
// def deleteZ(id: Long): Future[Int] = db.run(groups.filter(_.id === id).delete)
//}