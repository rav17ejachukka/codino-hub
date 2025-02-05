package controllers

import javax.inject.Inject
import play.api.mvc._
import repositories.GroupRepository
import scala.concurrent.ExecutionContext

class HomeController @Inject()(
                                groupRepo: GroupRepository,
                                cc: ControllerComponents
                              )(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action.async {
    groupRepo.getAll.map { groups =>
      Ok(s"Groups: $groups")
    }
  }
}