package models

import java.sql.Timestamp
import slick.jdbc.MySQLProfile.api.*

final case class User(
                       id: Long,
                       username: String,
                       email: String,
                       password: String,
                       isSuperuser: Boolean = false,
                       createdAt: Timestamp = new Timestamp(System.currentTimeMillis())
                     )
object User {
  def tupled: ((Long, String, String, String, Boolean, Timestamp)) => User = (User.apply _).tupled
}

class UsersTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("username")
  def email = column[String]("email")
  def password = column[String]("password")
  def isSuperuser = column[Boolean]("is_superuser", O.Default(false))
  def createdAt = column[Timestamp]("created_at", O.SqlType("TIMESTAMP DEFAULT CURRENT_TIMESTAMP"))

  def * = (id, username, email, password, isSuperuser, createdAt) <> (User.tupled, User.unapply)
}
val Users = TableQuery[UsersTable]