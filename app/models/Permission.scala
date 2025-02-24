// app/models/Permission.scala
package models

import zio.json._

case class Permission(
                       id: Long,
                       name: String
                     )

object Permission:
  given JsonEncoder[Permission] = DeriveJsonEncoder.gen[Permission]
  given JsonDecoder[Permission] = DeriveJsonDecoder.gen[Permission]