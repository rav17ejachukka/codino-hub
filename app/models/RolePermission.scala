package models

import slick.jdbc.MySQLProfile.api.*
import java.sql.Timestamp



final case class RolePermission(
                                 roleId: Long,
                                 permissionId: Long
                               )


object RolePermission {
  def tupled: ((Long, Long)) => RolePermission = (RolePermission.apply _).tupled
}

class RolePermissionsTable(tag: Tag) extends Table[RolePermission](tag, "role_permissions") {
  def roleId = column[Long]("role_id")
  def permissionId = column[Long]("permission_id")

  def pk = primaryKey("pk_role_permission", (roleId, permissionId))
  def roleFk = foreignKey("fk_role_permission_role", roleId, Roles)(_.id, onDelete = ForeignKeyAction.Cascade)
  def permissionFk = foreignKey("fk_role_permission_perm", permissionId, Permission)(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (roleId, permissionId) <> (RolePermission.tupled, RolePermission.unapply)
}

val RolePermissions = TableQuery[RolePermissionsTable]

