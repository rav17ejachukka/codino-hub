package services
import zio._
import jakarta.mail._
import jakarta.mail.internet._
import java.util.Properties


trait MailService {
  def sendEmail(to: String, subject: String, body: String): Task[Unit]
}

case class MailServiceImpl(smtpHost: String, smtpPort: Int, username: String, password: String) extends MailService {

  override def sendEmail(to: String, subject: String, body: String): Task[Unit] = ZIO.attempt {
    val props = new Properties()
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", smtpHost)
    props.put("mail.smtp.port", smtpPort.toString)

    val session = Session.getInstance(props, new Authenticator {
      override protected def getPasswordAuthentication: PasswordAuthentication =
        new PasswordAuthentication(username, password)
    })

    val message = new MimeMessage(session)
    message.setFrom(new InternetAddress(username))
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to).asInstanceOf[Array[Address]])
    message.setSubject(subject)
    message.setText(body)

    Transport.send(message)
  }
}

object MailServiceLive {
  val layer: ULayer[MailService] = ZLayer.succeed(
    MailServiceImpl(
      smtpHost = "smtp.your-email-provider.com",  // Replace with actual SMTP server
      smtpPort = 587, // Usually 587 for TLS, 465 for SSL
      username = "your-email@example.com",
      password = "your-email-password" // Consider using environment variables instead of hardcoding
    )
  )
}
