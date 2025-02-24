//package controllers
//
//import javax.inject.*
//import play.api.mvc.*
//import play.api.libs.json.*
//import repositories.GroupRepository
//import models.*
//import zio.*
//import zio.interop.catz.*
//
//
//import java.sql.Timestamp
//import scala.concurrent.{ExecutionContext, Future}
//
//@Singleton
//class GroupController @Inject()(cc: ControllerComponents, groupRepository: GroupRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {
//
//  private def timestampToLong(t: Timestamp): Long = t.getTime
//  private def longToTimestamp(dt: Long): Timestamp = new Timestamp(dt)
//
//  implicit val timestampFormat: Format[Timestamp] = new Format[Timestamp] {
//    def writes(t: Timestamp): JsValue = JsNumber(timestampToLong(t))
//    def reads(json: JsValue): JsResult[Timestamp] = json match {
//      case JsNumber(n) => JsSuccess(longToTimestamp(n.toLong))
//      case _ => JsError("Expected Number (Long) for Timestamp")
//    }
//  }
//
//  implicit val groupFormat: OFormat[Group] = Json.format[Group]
//
//  def getGroup(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    groupRepository.findByIdZ(id).map {
//      case Some(group) => Ok(Json.toJson(group))
//      case None        => NotFound(s"Group with ID $id not found")
//    }
//  }
//
//  def getAllGroups: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    groupRepository.findAllZ().map { groups =>
//      Ok(Json.toJson(groups))
//    }
//  }
//
//  implicit val runtime: Runtime[Any] = Runtime.default
//
//  def createGroup(): Action[AnyContent] = Action.async { implicit request =>
//    val effect: ZIO[GroupRepository, Result, Result] =
//      for {
//        json  <- ZIO.fromOption(request.body.asJson).orElseFail(BadRequest("Invalid Group data"))
//        group <- ZIO.fromEither(json.validate[Group].asEither).mapError(_ => BadRequest("Invalid Group data"))
//        _     <- ZIO.fromFuture(_ => groupRepository.createZ(group)).mapError(_ => InternalServerError("Failed to create group"))
//      } yield Created(s"Group ${group.name} created")
//
//    Unsafe.unsafe { implicit unsafe =>
//      Runtime.default.unsafe.runToFuture(effect.catchAll(error => ZIO.succeed(error))).future
//
//    }
//  }
//
//  def deleteGroup(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    groupRepository.deleteZ(id).map { rowsAffected =>
//      if (rowsAffected > 0) {
//        Ok(s"Group with ID $id deleted")
//      } else {
//        NotFound(s"Group with ID $id not found")
//      }
//    }
//  }
//}
//
