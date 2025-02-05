package models

import slick.jdbc.MySQLProfile.api.*

final case class Permissions(
                             id: Long,
                             name: String
                           )

object Permissions {
  def tupled = (Permissions.apply _).tupled
}

class PermissionsTable(tag: Tag) extends Table[Permissions](tag, "permissions") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Permissions.tupled, Permissions.unapply)
}

val Permission = TableQuery[PermissionsTable]
