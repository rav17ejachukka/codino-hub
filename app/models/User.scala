// app/models/User.scala
package models

import java.time.LocalDateTime
import zio.json._

case class User(
                 id: Long,
                 groupId: Long,
                 firstName: String,
                 lastName: String,
                 username: String,
                 email: String,
                 password: String,
                 isSuperuser: Boolean,
                 createdAt: LocalDateTime,
                 createdBy: String,
                 updatedBy: String
               )

object User:
  given JsonEncoder[User] = DeriveJsonEncoder.gen[User]
  given JsonDecoder[User] = DeriveJsonDecoder.gen[User]

  def tupled = (User.apply _).tupled