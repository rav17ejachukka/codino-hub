package repositories

import javax.inject.Inject
import models.{Group, Groups}
import slick.jdbc.MySQLProfile.api.*
import scala.concurrent.Future


class GroupRepository @Inject()(db: Database) {

  def create(group: Group): Future[Int] = db.run(Groups += group)

  def getAll: Future[Seq[Group]] = db.run(Groups.result)
}
