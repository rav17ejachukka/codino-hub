package models

import slick.jdbc.MySQLProfile.api.*
import java.sql.Timestamp

final case class Group(
                        id: Long,
                        name: String,
                        createdBy: Long,
                        createdAt: Timestamp = new Timestamp(System.currentTimeMillis())
                      )

object Group {
  def tupled = (Group.apply _).tupled
}

class GroupsTable(tag: Tag) extends Table[Group](tag, "groups") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def createdBy = column[Long]("created_by")
  def createdAt = column[Timestamp]("created_at", O.SqlType("TIMESTAMP DEFAULT CURRENT_TIMESTAMP"))

  def createdByFk = foreignKey("fk_group_creator", createdBy, Users)(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (id, name, createdBy, createdAt) <> (Group.tupled, Group.unapply)
}

val Groups = TableQuery[GroupsTable]
