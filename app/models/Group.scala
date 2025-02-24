// app/models/Group.scala
package models

import java.time.LocalDateTime
import zio.json._

case class Group(
                  id: Long,
                  name: String,
                  createdBy: Long,
                  createdAt: LocalDateTime,
                  description: String,
                  updatedBy: String
                )

object Group:
  given JsonEncoder[Group] = DeriveJsonEncoder.gen[Group]
  given JsonDecoder[Group] = DeriveJsonDecoder.gen[Group]