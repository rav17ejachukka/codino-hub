
package models

import java.time.LocalDateTime
import zio.json._

case class Role(
                 id: Long,
                 name: String,
                 createdBy: Long,
                 createdAt: LocalDateTime,
                 description: String,
                 updatedBy: String
               )

object Role:
  given JsonEncoder[Role] = DeriveJsonEncoder.gen[Role]
  given JsonDecoder[Role] = DeriveJsonDecoder.gen[Role]