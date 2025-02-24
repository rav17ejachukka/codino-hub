package controllers

import models.SuperUser
import zio.*
import play.api.mvc.*
import services.{MailService, SuperUserService}
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.json.Json
import javax.inject.*
import java.security.SecureRandom
import org.apache.commons.text.RandomStringGenerator
import utils.PasswordGenerator

@Singleton
class SuperUserController @Inject()(
                                     cc: ControllerComponents,
                                     superUserService: SuperUserService,
                                     mailService: MailService
                                   )(implicit runtime: zio.Runtime[Any]) extends AbstractController(cc) {

  private val secureRandom = new SecureRandom()

  def createSuperUser: Action[SuperUser] = Action.async(parse.json[SuperUser]) { request =>
    val zioEffect = for {
      generatedPassword <- ZIO.succeed(PasswordGenerator.generatePassword())
      hashedPassword <- PasswordHasher.hashPassword(generatedPassword)
      superUser = request.body.copy(password = Some(hashedPassword))
      _ <- superUserService.createSuperUser(superUser)
      emailResult <- mailService.sendEmail(superUser.email, "SuperUser Account Created",
        s"Your account has been created. Your password is: $generatedPassword").either
    } yield emailResult match {
      case Left(emailError) =>
        println(s"Email sending failed: ${emailError.getMessage}")
        Created
      case Right(_) => Created
    }
    Unsafe.unsafe { implicit unsafe =>
      runtime.unsafe.runToFuture(
        zioEffect.catchAll(error => ZIO.succeed(InternalServerError(error.getMessage)))
      )
    }
  }
  

  def getAll: Action[AnyContent] = Action.async {
    Unsafe.unsafe { implicit u =>
      Runtime.default.unsafe.runToFuture {
        superUserService.getAllSuperUsers
          .map(users => Ok(Json.toJson(users)))
          .catchAll(error => ZIO.succeed(InternalServerError(s"Error fetching super users: ${error.getMessage}")))
      }
    }
  }
  
}
 


object PasswordHasher {

  def hashPassword(password: String): Task[String] = ZIO.attempt {
    BCrypt.hashpw(password, BCrypt.gensalt())
  }

  // Verify if a given password matches the hashed password
  def checkPassword(password: String, hashedPassword: String): Task[Boolean] = ZIO.attempt {
    BCrypt.checkpw(password, hashedPassword)
  }
}



