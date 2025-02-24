package repositories.tables
import com.sun.jna.platform.win32.Sspi.TimeStamp
import models.SuperUser
import slick.jdbc.MySQLProfile.api.*

import java.sql.Timestamp

class SuperUserTable(tag: Tag) extends Table[SuperUser](tag, "super_users") {

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def username = column[String]("username")
  def email = column[String]("email")
  def password = column[Option[String]]("password")
  def createdAt = column[Option[Timestamp]]("created_at")
  def modifiedAt = column[Option[Timestamp]]("modified_at")
  def modifiedBy = column[Option[String]]("modified_by")
  def status = column[Option[String]]("status")

  def * = (id, firstName, lastName, username, email, password, createdAt, modifiedAt, modifiedBy, status) <> (SuperUser.tupled, SuperUser.unapply)
}
