
package models

import java.time.LocalDateTime
import zio.json._

case class EventHistory(
                         id: Long,
                         eventName: String,
                         eventType: String,
                         eventOccurredAt: LocalDateTime,
                         executedBy: String
                       )

object EventHistory:
  given JsonEncoder[EventHistory] = DeriveJsonEncoder.gen[EventHistory]
  given JsonDecoder[EventHistory] = DeriveJsonDecoder.gen[EventHistory]