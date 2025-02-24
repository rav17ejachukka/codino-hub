// app/repositories/tables/UserTable.scala
package repositories.tables

import models.User
import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime

class UserTable(tag: Tag) extends Table[User](tag, "users"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def groupId = column[Long]("group_id")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def username = column[String]("username")
  def email = column[String]("email")
  def password = column[String]("password")
  def isSuperuser = column[Boolean]("is_superuser")
  def createdAt = column[LocalDateTime]("created_at")
  def createdBy = column[String]("createdBy")
  def updatedBy = column[String]("updated_by")

  def * = (id, groupId, firstName, lastName, username, email, password, isSuperuser, createdAt, createdBy, updatedBy) <> (User.tupled, User.unapply)
