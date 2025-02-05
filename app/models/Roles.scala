package models

import slick.jdbc.MySQLProfile.api.*

final case class Role(
                       id: Long,
                       name: String
                     )

object Role {
  def tupled = (Role.apply _).tupled
}

class RolesTable(tag: Tag) extends Table[Role](tag, "roles") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Role.tupled, Role.unapply)
}

val Roles = TableQuery[RolesTable]
