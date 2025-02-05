package models

import slick.jdbc.MySQLProfile.api.*

final case class UserGroup(
                            userId: Long,
                            groupId: Long,
                            roleId: Long
                          )

object UserGroup {
  def tupled = (UserGroup.apply _).tupled
}

class UserGroupsTable(tag: Tag) extends Table[UserGroup](tag, "user_groups") {
  def userId = column[Long]("user_id")
  def groupId = column[Long]("group_id")
  def roleId = column[Long]("role_id")

  def pk = primaryKey("pk_user_group", (userId, groupId))
  def userFk = foreignKey("fk_user_group_user", userId, Users)(_.id, onDelete = ForeignKeyAction.Cascade)
  def groupFk = foreignKey("fk_user_group_group", groupId, Groups)(_.id, onDelete = ForeignKeyAction.Cascade)
  def roleFk = foreignKey("fk_user_group_role", roleId, Roles)(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (userId, groupId, roleId) <> (UserGroup.tupled, UserGroup.unapply)
}

val UserGroups = TableQuery[UserGroupsTable]
