package services

import zio._
import models.SuperUser
import repositories.SuperUserRepository
import javax.inject.Inject

class SuperUserService @Inject() (superUserRepository: SuperUserRepository) {
  def createSuperUser(superUser: SuperUser): Task[Int] = {
    superUserRepository.create(superUser)
  }

  def getAllSuperUsers: Task[List[SuperUser]] =
    val x = superUserRepository.getAll
    x

}

