
package models

import java.time.{Instant, LocalDateTime, ZoneId}
import play.api.libs.json.*
import java.sql.Timestamp

case class SuperUser(
                      id: Option[Long],
                      firstName: String,
                      lastName: String,
                      username: String,
                      email: String,
                      password: Option[String],
                      createdAt: Option[Timestamp] = Some(Timestamp.from(Instant.now())),
                      modifiedAt: Option[Timestamp],
                      modifiedBy: Option[String],
                      status: Option[String] = Some("ACTIVE")
                    )

object SuperUser {
  implicit val timestampReads: Reads[Timestamp] = Reads[Timestamp] {
    case JsString(s) =>
      try {
        JsSuccess(Timestamp.valueOf(s))
      } catch {
        case _: IllegalArgumentException => JsError("Invalid timestamp format")
      }
    case _ => JsError("Expected timestamp as string")
  }

  implicit val timestampWrites: Writes[Timestamp] = Writes[Timestamp] { ts =>
    JsString(ts.toString)
  }
  implicit val optionTimestampFormat: Format[Option[Timestamp]] =
    Format(Reads.optionWithNull(timestampReads), Writes.optionWithNull(timestampWrites))

  implicit val format: OFormat[SuperUser] = Json.format[SuperUser]

  def tupled: ((Option[Long], String, String, String, String, Option[String], Option[Timestamp],Option[Timestamp],
    Option[String],Option[String]))
    => SuperUser = (SuperUser.apply _).tupled
}
